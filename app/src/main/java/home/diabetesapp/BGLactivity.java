package home.diabetesapp;

import android.app.FragmentManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class BGLActivity extends AppCompatActivity {
    ListView listView;
    private List<BGL> bglList;
    BGLDBHelper dbHelper;
    public String msg;
    private String m_Text = "";

    Button btnWeeklyActivity, btnMonthlyActivity, btnGraphView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bglactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Blood Glucose");
            toolbar.setLogo(R.mipmap.ic_launcher);   //uses the ic_launcher icon as title log
            toolbar.setBackground(new ColorDrawable(Color.argb(255, 237, 84, 84)));
        }
        setSupportActionBar(toolbar);

        btnWeeklyActivity = (Button) findViewById(R.id.btnweeklyActivity);
        btnMonthlyActivity = (Button) findViewById(R.id.btnMonthlyActivity);
        btnGraphView = (Button) findViewById(R.id.btnGraphView);
        dbHelper = new BGLDBHelper(this);
        bglList = new ArrayList<BGL>();
        // Todo - need to implement custom alarm(notification) handler
        //  setAlarm();
        //  showNotification();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 0, 0, "Quit").setIcon(R.drawable.plus_white);

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bglactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_bgldata) {
            Intent i = new Intent(BGLActivity.this, AddBGLactivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    //    public void showWeeklyActivity(View view) {
//        bglList = dbHelper.getAllBGL();
//        listView.setAdapter(new ArrayAdapter<BGL>(this, android.R.layout.simple_list_item_1, bglList));
//    }
//
//    public void showMonthlyActivity(View view) {
//
//        bglList = dbHelper.getAllBGL();
//        listView.setAdapter(new ArrayAdapter<BGL>(this, android.R.layout.simple_list_item_1, bglList));
//
//    }
    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();


        if (view == btnWeeklyActivity) {
            android.app.FragmentTransaction ft = fm.beginTransaction();
            BGListViewFragment bglFragment = new BGListViewFragment();
            ft.replace(R.id.fragmentContainer, bglFragment);
            ft.commit();

        } else if (view == btnGraphView) {
            android.app.FragmentTransaction ft = fm.beginTransaction();
            GraphViewFragment bglFragment = new GraphViewFragment();
            ft.replace(R.id.fragmentContainer, bglFragment);
            ft.commit();

        } else if (view == btnMonthlyActivity) {

            //Todo need to populate monthly bgl readings
            android.app.FragmentTransaction ft = fm.beginTransaction();
            BGListViewFragment bglFragment = new BGListViewFragment();
            ft.replace(R.id.fragmentContainer, bglFragment);
            ft.commit();

        } else {
            //
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

    private void showNotification() {
        Intent intent = new Intent(this, ExerciseActivty.class);

        // Holds the intent in waiting until it’s ready to be used
        PendingIntent pi = PendingIntent.getActivity(this, 1, intent, 0);

        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("BGL reading Remainder").setContentText("BGL Taking time sample notification")
                .setTicker("Ticker Text").setSmallIcon(R.drawable.plus_white)
                .setContentIntent(pi).setStyle(new Notification.BigTextStyle()
                        .bigText("Time to take BGL measurement ")).build();

        // Get an instance of the notification manager
        NotificationManager noteManager = (NotificationManager)
                getSystemService(Context.NOTIFICATION_SERVICE);
        // Post to the system bar
        noteManager.notify(1, notification);
    }
}
