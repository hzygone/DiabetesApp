package home.diabetesapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.Calendar;

import helper.util.RegimenAlarmReceiver;

public class RegimenActivity extends AppCompatActivity {

    RegimenAlarmReceiver alarm = new RegimenAlarmReceiver();

    String msg;
    String alarmtype;

    private PendingIntent pendingIntent;

    Button btnStartReminder;
    CheckBox BGLbox;
    CheckBox Exercisebox;
    CheckBox dietbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regimen);

          /* Retrieve a PendingIntent that will perform a broadcast */
        Intent alarmIntent = new Intent(RegimenActivity.this, RegimenAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(RegimenActivity.this, 0, alarmIntent, 0);

        btnStartReminder = (Button) findViewById(R.id.btnStartReminder);
        BGLbox = (CheckBox) findViewById(R.id.BGLbox);
        Exercisebox = (CheckBox) findViewById(R.id.exercisebox);
        dietbox = (CheckBox) findViewById(R.id.dietbox);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setLogo(R.mipmap.ic_launcher);   //uses the ic_launcher icon as title log
            setSupportActionBar(toolbar);
        }



        findViewById(R.id.btnStartReminder).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startReminder(v);
            }
        });

    }

    public void start() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;


        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        if(BGLbox.isChecked())
        {alarmtype = "BGL Alarm Set";}
        else if(dietbox.isChecked())
        {alarmtype = "Diet Alarm Set;";} else
        {alarmtype = "Exercise Alarm Set";}
        Toast.makeText(this, alarmtype, Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(this, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 10:30 AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);

        /* Repeating on every 20 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 20, pendingIntent);
    }

    /**
     * Called when the activity is about to become visible.
     */



    /**
     * Called when the activity has become visible.
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /**
     * Called when another activity is taking focus.
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /**
     * Called when the activity is no longer visible.
     */


    /**
     * Called just before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    public void startReminder(View view) {
        alarm.setAlarm(this);

    }

    public void cancelReminder(View view) {
        alarm.cancelAlarm(this);

    }


}