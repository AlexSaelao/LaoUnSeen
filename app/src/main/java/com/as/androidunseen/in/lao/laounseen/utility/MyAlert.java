package com.as.androidunseen.in.lao.laounseen.utility;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.as.androidunseen.in.lao.laounseen.R;

public class MyAlert  {

    private Context context;

    public MyAlert(Context context) {
        this.context = context;
    }

    public void  normalDialog(String titleString, String mesageString) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.ic_action_alert);
        builder.setTitle(titleString);
        builder.setMessage(mesageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
