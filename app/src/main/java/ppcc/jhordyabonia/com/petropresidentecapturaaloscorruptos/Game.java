package ppcc.jhordyabonia.com.petropresidentecapturaaloscorruptos;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BaseInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class Game extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener  {
    private static final String URL_SERVER = "http://123seller.azurewebsites.net/ppcc/";
    private static boolean MOVE=false ;
    private int WIDTH =0,HEIGHT =0;
    ObjectAnimator translateX,translateY;
    FloatingActionButton fab;
    TextView corrupto;
    View tapiz;
    WithoutCoins withoutCoins;
    Store store;
    String NAME="Ningun";
    int LEVEL=-1,LIVES=0,SPEED=1000;

    InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        getWindow().setFlags(
                WindowManager.LayoutParams.TYPE_APPLICATION_MEDIA,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_game);

        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, "ca-app-pub-7036101536380541~1515752766");

        // Create the InterstitialAd and set the adUnitId.
        interstitialAd = new InterstitialAd(this);
        // Defined in res/values/strings.xml
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                setCorrupto(++LEVEL);
                if(LEVEL==0)
                    withoutCoins.show(getSupportFragmentManager(), "missiles");
                if(!MOVE)
                    move();
            }
            @Override
            public void onAdLoaded ()
            { interstitialAd.show();}

            @Override
            public void onAdFailedToLoad(int errorCode)
            {
                if(AdRequest.ERROR_CODE_NETWORK_ERROR==errorCode)
                {  startGame();}
            }
        });
        startGame();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, NAME+" tiene aun "+LIVES+ " Porcesos de investigacion. Capturalo pronto, para hacer realidad el sueño de colombia humana", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        WIDTH = metrics.widthPixels;
        HEIGHT = metrics.heightPixels;
        corrupto=(TextView) findViewById(R.id.hellow);
        corrupto.setVisibility(View.INVISIBLE);
        float coo[][]=getCoordenadas();
        translateX = ObjectAnimator
                .ofFloat(corrupto,"translationX",coo[0]);
        translateY = ObjectAnimator
                .ofFloat(corrupto,"translationY",coo[1]);
        corrupto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(LIVES>0) {
                    LIVES--;
                    corrupto.setText(""+LIVES);
                }else {
                    corrupto.setVisibility(View.INVISIBLE);
                    startGame();
                }
            }
        });
        tapiz=findViewById(R.id.main);
        tapiz.setOnClickListener(this);
        store=new Store(this);
        withoutCoins= new WithoutCoins(this);

        SharedPreferences sharedPref = getSharedPreferences(Store.STORE, Context.MODE_PRIVATE);
        LEVEL=sharedPref.getInt(Store._LEVEL,0);
        win();
    }

    private void startGame() {
        // Request a new ad if one isn't already loaded, hide the button, and kick off the timer.
        if (!interstitialAd.isLoading()/* && !interstitialAd.isLoaded()*/){
            AdRequest.Builder builder = new AdRequest.Builder();
            //builder.addTestDevice(AdRequest.DEVICE_ID_EMULATOR);
            interstitialAd.loadAd(builder.build());
        }
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            translateX.resume();
            translateY.resume();
        }
    }
    @Override
    public void onPause()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            translateX.pause();
            translateY.pause();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        translateX.removeAllListeners();
        translateX.cancel();
        translateX.end();
        translateX=null;

        translateY.removeAllListeners();
        translateY.cancel();
        translateY.end();
        translateY=null;
        super.onDestroy();
    }

    public void setCorrupto(int t)
    {
        if(t>store.get().length())
            win();
        else try{
            Toast.makeText(Game.this,NAME+" Capturado", Toast.LENGTH_LONG).show();
            JSONObject tmp=store.get(t);
            corrupto.setBackgroundResource(tmp.getInt(Store._IMG));
            LIVES=tmp.getInt(Store._LIVES);
            NAME=tmp.getString(Store._NAME);
            SPEED=tmp.getInt(Store._VELOCIDAD);
            corrupto.setText(""+LIVES);
            corrupto.setVisibility(View.VISIBLE);
            tapiz.setBackgroundColor(Color.WHITE);
            save(t);
        }catch(JSONException e){}
    }
    public void save(int l)
    {
        SharedPreferences sharedPref =
                getSharedPreferences(Store.STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(Store._LEVEL,l);
        editor.commit();
    }
    @Override
    public void onClick(View view){

        LIVES++;
        corrupto.setText(""+LIVES);
    }
    public void move()
    {
        Random ran= new Random();
        boolean bounce=ran.nextBoolean();
        BounceInterpolator bounceI= new BounceInterpolator();
        AccelerateDecelerateInterpolator acc_des = new AccelerateDecelerateInterpolator();
       if(bounce) {
           translateY.setInterpolator(bounceI);
           translateX.setInterpolator(bounceI);
       }else {
           translateY.setInterpolator(acc_des);
           translateX.setInterpolator(acc_des);
       }
        translateX.setRepeatMode(ValueAnimator.RESTART);
        translateY.setRepeatMode(ValueAnimator.RESTART);
        translateX.setRepeatCount(10000);
        translateX.addListener( new AnimatorListenerAdapter()
        {
            @Override
            public void onAnimationRepeat(Animator anim)
            {
                translateX.setupEndValues();
                translateY.setupEndValues();
                float coo[][]=getCoordenadas();
    //this.onAnimationRepeat();
                translateX.setFloatValues(coo[0]);
                //translateX.setupStartValues();
                translateX.setDuration(SPEED);
                translateX.start();

                translateY.setFloatValues(coo[1]);
                translateY.setDuration(SPEED);
                //translateY.setupStartValues();
                translateY.start();
            }
        });
        translateX.start();
        translateY.start();
        MOVE=true;
    }
    private float[][] getCoordenadas()
    {
        float x=corrupto.getX(),y=corrupto.getY();
        int width=corrupto.getWidth(),heigth=corrupto.getHeight();
        Random ran= new Random();
        boolean reverse=ran.nextBoolean();
        int DIVISOR=2,LONG=ran.nextInt(5),count=0;
        float[][] out=new float[2][LONG];
        for(int m=0;m<LONG;m++) {
            if(count>2){reverse=false;count=0;}
            if(count<-2){reverse=true;count=0;}
            if(reverse) {
                if (x -((width / DIVISOR))> 0)
                    out[0][m] = -m*(width / DIVISOR);//out[0][m] = x - (width / 10);
                count++;
            }else {
                if (x +((width / DIVISOR))< WIDTH)
                    out[0][m] = m*(width / DIVISOR);//out[0][m] = x + (width / 10);
                count--;
            }
        }
        for(int m=0;m<LONG;m++) {
            if(count>2){reverse=false;count=0;}
            if(count<-2){reverse=true;count=0;}
            if(reverse) {
                if (y -((heigth / DIVISOR))> 50)
                    out[1][m] = -m*(heigth / DIVISOR);// out[1][m] = y - (heigth / 10);
                count++;
            }else {
                if (y +((heigth / DIVISOR))< HEIGHT)
                    out[1][m] = m* (heigth / DIVISOR);//out[1][m] = y + (heigth / 10);
                count--;
            }
        }
        return out;
    }
    private void win()
    {
        //getActionBar().hide();
        corrupto.setVisibility(View.INVISIBLE);
        tapiz.setBackgroundResource(R.mipmap.bg_ch);
        Snackbar.make(fab, "La utopía... Colombia humana", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
        String url = "http://123seller.azurewebsites.net/";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            String url = "http://123seller.azurewebsites.net/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }
    }
   @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       Intent intent =new Intent(Game.this,Capturados.class);
        if (id == R.id.nav_camera) {
            intent.putExtra(Capturados.MOSTRAR,Capturados.MOSTRAR_CAPTURADOS);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            intent.putExtra(Capturados.MOSTRAR,Capturados.MOSTRAR_CAPTURADOS);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            intent.putExtra(Capturados.MOSTRAR,Capturados.MOSTRAR_TODOS);
            startActivity(intent);
        } else if (id == R.id.nav_share) {

            Intent intent2 = new Intent(Intent.ACTION_SEND);
            intent2.putExtra(Intent.EXTRA_TEXT, getString(R.string.msj_share)+" "+URL_SERVER);
            intent2.setType("text/plain");
            Intent chooser = Intent.createChooser(intent2, getString(R.string.share));

            if (intent2.resolveActivity(getPackageManager()) != null)
                startActivity(chooser);
        } else if (id == R.id.nav_help) {
            withoutCoins.show(getSupportFragmentManager(), "missiles");
        }
        else if (id == R.id.nav_send) {
            String url = "http://123seller.azurewebsites.net/";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
