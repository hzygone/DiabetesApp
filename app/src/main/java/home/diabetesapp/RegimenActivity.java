package home.diabetesapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import helper.util.RegimenAlarmReceiver;

public class RegimenActivity extends AppCompatActivity {

    RegimenAlarmReceiver alarm = new RegimenAlarmReceiver();

    String msg;

    Button btnStartReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regimen);

        btnStartReminder = (Button) findViewById(R.id.btnStartReminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackground(new ColorDrawable(Color.argb(255, 120, 144, 156)));

        if (toolbar != null) {
            toolbar.setLogo(R.mipmap.ic_launcher);   //uses the ic_launcher icon as title log
            setSupportActionBar(toolbar);
        }
    }

    /**
     * Called when the activity is about to become visible.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

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
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

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
