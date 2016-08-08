package helper.util;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import home.diabetesapp.BGLActivity;
import home.diabetesapp.R;
/**
 * Created by Asmamaw on 8/7/16.
 * Sample code from https://developer.android.com/reference/android/app/
 * */

/**
 * This {@code IntentService} does the app's actual work.
 * {@code RegimenAlarmReceiver} (a {@code WakefulBroadcastReceiver}) holds a
 * partial wake lock for this service while the service does its work. When the
 * service is finished, it calls {@code completeWakefulIntent()} to release the
 * wake lock.
 */
public class RegimenReminderService extends IntentService {
    public RegimenReminderService() {
        super("RegimenReminderService");
    }
    public static final int NOTIFICATION_ID = 1;


    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    @Override
    protected void onHandleIntent(Intent intent) {


        if (true) {
            sendNotification("Time to take Blood Glucose Level");
            Log.i("INFO", "Time  to take BGL");
        } else {
            sendNotification("Other Activity");
            Log.i("INFO", "Time for other activity");
        }
        // Release the wake lock provided by the BroadcastReceiver.
        RegimenAlarmReceiver.completeWakefulIntent(intent);
        // END_INCLUDE(service_onhandle)
    }

    //Send notification to take BG level , when clicked it opens the BGL Activity
    private void sendNotification(String msg) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent openBGLActivity = new Intent(getBaseContext(), BGLActivity.class);
        openBGLActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);

       // startActivity(openBGLActivity);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, BGLActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("BGL Activity Alert!")
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

}
