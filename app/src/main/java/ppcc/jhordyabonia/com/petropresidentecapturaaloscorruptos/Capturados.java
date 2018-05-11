package ppcc.jhordyabonia.com.petropresidentecapturaaloscorruptos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;

public class Capturados extends AppCompatActivity {

    public static final int MOSTRAR_TODOS=0,MOSTRAR_PRFUGOS=1,MOSTRAR_CAPTURADOS=2;
    public static final String MOSTRAR="mostrar";
    private static int MOSTRAR_QUE=MOSTRAR_TODOS;
    int LEVEL=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capturados);
        Store store= new Store(this);
        Intent intent =getIntent();
        if(intent!=null)
            MOSTRAR_QUE = intent.getIntExtra(MOSTRAR, MOSTRAR_TODOS);

        SharedPreferences sharedPref = getSharedPreferences(Store.STORE, Context.MODE_PRIVATE);
        LEVEL=sharedPref.getInt(Store._LEVEL,-1);
        if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
        try
        {
            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<0)return;
            ((TextView) findViewById(R.id.name0))
                    .setText(store.get(0).getString(Store._NAME));
            if(LEVEL>0)
                ((TextView) findViewById(R.id.state0)).setText("Capturado");
            ((ImageView) findViewById(R.id.image0))
                    .setImageResource(store.get(0).getInt(Store._IMG));

            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<1)return;
            ((TextView) findViewById(R.id.name1))
                    .setText(store.get(1).getString(Store._NAME));
            if(LEVEL>1)
                ((TextView) findViewById(R.id.state1)).setText("Capturado");
            ((ImageView) findViewById(R.id.image1))
                    .setImageResource(store.get(1).getInt(Store._IMG));

            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<2)return;
            ((TextView) findViewById(R.id.name2))
                    .setText(store.get(2).getString(Store._NAME));
            if(LEVEL>2)
                ((TextView) findViewById(R.id.state2)).setText("Capturado");
            ((ImageView) findViewById(R.id.image2))
                    .setImageResource(store.get(2).getInt(Store._IMG));


            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<3)return;
            ((TextView) findViewById(R.id.name3))
                    .setText(store.get(3).getString(Store._NAME));
            if(LEVEL>3)
                ((TextView) findViewById(R.id.state3)).setText("Capturado");
            ((ImageView) findViewById(R.id.image3))
                    .setImageResource(store.get(3).getInt(Store._IMG));


            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<4)return;
            ((TextView) findViewById(R.id.name4))
                    .setText(store.get(4).getString(Store._NAME));
            if(LEVEL>4)
                ((TextView) findViewById(R.id.state4)).setText("Capturado");
            ((ImageView) findViewById(R.id.image4))
                    .setImageResource(store.get(4).getInt(Store._IMG));


            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<5)return;
            ((TextView) findViewById(R.id.name5))
                    .setText(store.get(5).getString(Store._NAME));
            if(LEVEL>5)
                ((TextView) findViewById(R.id.state5)).setText("Capturado");
            ((ImageView) findViewById(R.id.image5))
                    .setImageResource(store.get(5).getInt(Store._IMG));


            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<6)return;
            ((TextView) findViewById(R.id.name6))
                    .setText(store.get(6).getString(Store._NAME));
            if(LEVEL>6)
                ((TextView) findViewById(R.id.state6)).setText("Capturado");
            ((ImageView) findViewById(R.id.image6))
                    .setImageResource(store.get(6).getInt(Store._IMG));

            if(MOSTRAR_CAPTURADOS==MOSTRAR_QUE)
                if(LEVEL<7)return;
            ((TextView) findViewById(R.id.name7))
                    .setText(store.get(7).getString(Store._NAME));
            if(LEVEL>7)
                ((TextView) findViewById(R.id.state7)).setText("Capturado");
            ((ImageView) findViewById(R.id.image7))
                    .setImageResource(store.get(7).getInt(Store._IMG));
        }catch (JSONException e){}
    }
}
