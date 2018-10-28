package com.location.locationpicker;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Util {

    public static boolean checkConnectivity(Context context){

        ConnectivityManager cm =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activityNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activityNetwork != null && activityNetwork.isConnected();
        return isConnected;
    }
}
