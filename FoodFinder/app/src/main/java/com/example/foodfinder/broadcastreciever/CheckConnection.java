package com.example.foodfinder.broadcastreciever;

import static com.example.foodfinder.Constants.NETWORK_CONNECTED;
import static com.example.foodfinder.Constants.NETWORK_NOT_CONNECTED;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

public class CheckConnection {

    public static String getNetworkState(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null) {
            return NETWORK_CONNECTED;
        } else {
            return NETWORK_NOT_CONNECTED;
        }
    }
}
