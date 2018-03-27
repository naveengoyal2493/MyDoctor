package com.example.android.mydoctor;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

/**
 * Created by DELL on 9/16/2017.
 */

public class CheckInternetConnection {

    Context context;

    CheckInternetConnection(Context context){
        this.context = context;
    }

    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo != null)
        {
            return true;
        }
        else{
            return false;
        }
    }

    public void generateAlert(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Internet Unavailable")
                .setMessage("Please check your internet connection")
                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean isconnected = isConnected();
                        if(!isconnected){
                            generateAlert();
                        }
                    }
                });
        alert.show();
    }

}
