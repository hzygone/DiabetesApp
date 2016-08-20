package home.diabetesapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        // For our recurring task, we'll just display a message
        Toast.makeText(context, "You have a regimen activity to complete.", Toast.LENGTH_SHORT).show();
    }
}