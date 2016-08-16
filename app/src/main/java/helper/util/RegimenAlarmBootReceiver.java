package helper.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * /**
 * Created by Asmamaw on 8/7/16.
 */

public class RegimenAlarmBootReceiver extends BroadcastReceiver {
    RegimenAlarmReceiver alarm = new RegimenAlarmReceiver();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            alarm.setAlarm(context);
        }
    }
}
