package ppcc.jhordyabonia.com.petropresidentecapturaaloscorruptos;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class WithoutCoins extends DialogFragment implements DialogInterface.OnClickListener
{
    private  final Activity MAIN;
    public WithoutCoins(Activity main){MAIN=main;}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view=getActivity().getLayoutInflater()
                .inflate(R.layout.tuto_dialo, null);
        ((TextView)view.findViewById(R.id.text_tuto)).setText(Html.fromHtml(getString(R.string.tuto)));
        final AlertDialog.Builder builder =
                new AlertDialog.Builder(MAIN);
        builder.setTitle(getString(R.string.nav_header_subtitle))
                .setView(view)
                .setIcon(R.mipmap.ch_logo)
                .setPositiveButton(" Empezar...", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which)
    {
        if(which==DialogInterface.BUTTON_POSITIVE)
        {
        }else dialog.dismiss();
    }
}
