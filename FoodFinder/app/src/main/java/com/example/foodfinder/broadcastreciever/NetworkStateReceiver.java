package com.example.foodfinder.broadcastreciever;

import static com.example.foodfinder.Constants.NETWORK_CONNECTED;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.foodfinder.MainActivity;
import com.example.foodfinder.interfaces.NetworkAccessListener;

public class NetworkStateReceiver extends BroadcastReceiver {

    private static final String TAG = "NetworkStateReceiver";
    private NetworkAccessListener listener;

    public NetworkStateReceiver(NetworkAccessListener listener) {
        this.listener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String state = CheckConnection.getNetworkState(context);

        if (state.equals(NETWORK_CONNECTED)) {
            Log.d(TAG, "NETWORK_CONNECTED");
            listener.onConnected();
        } else {
            Toast.makeText(context.getApplicationContext(), "No Internet", Toast.LENGTH_LONG).show();
            Log.d(TAG, "NETWORK_NOT_CONNECTED");
            listener.onDisconnected();
        }
    }
}