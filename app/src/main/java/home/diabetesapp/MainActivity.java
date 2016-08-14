package home.diabetesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import helper.util.RegimenAlarmReceiver;

public class MainActivity extends AppCompatActivity {
    RegimenAlarmReceiver alarm = new RegimenAlarmReceiver();

    public String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Diabetes Management");
            toolbar.setLogo(R.mipmap.ic_launcher);   //uses the ic_launcher icon as title log

        }
        setSupportActionBar(toolbar);

        Button MedicationButton = (Button) findViewById(R.id.MedicationButton);
        if(MedicationButton != null) {
            MedicationButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, MedicationActivity.class);
                    startActivity(i);
                }

            });
        }

        Button RegimenButton = (Button) findViewById(R.id.RegimenButton);
        if(RegimenButton != null) {
            RegimenButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, RegimenActivity.class);
                    startActivity(i);
                }

            });
        }

        Button BGLbutton = (Button) findViewById(R.id.BGLbutton);
        if(BGLbutton != null) {
            BGLbutton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, BGLActivity.class);
                    startActivity(i);
                }

            });
        }

        Button NutritionButton = (Button) findViewById(R.id.NutritionButton);
        if(NutritionButton != null) {
            NutritionButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, NutritionActivity.class);
                    startActivity(i);
                }

            });
        }

        Button ActivityButton = (Button) findViewById(R.id.ActivityButton);
        if(ActivityButton != null) {
            ActivityButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(MainActivity.this, ExerciseActivty.class);
                    startActivity(i);
                }

            });
        }
    }


    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }

    //Called to display the menu action buttons or overflow action buttons

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_about:
                Intent intentAbout = new Intent(this, AboutActivity.class);
                startActivity(intentAbout);
                return true;

            case R.id.action_help:
                Intent intentHelp = new Intent(this, AboutActivity.class);
                startActivity(intentHelp);
                return true;
            // When the user clicks START ALARM, set the alarm.
            case R.id.start_action:
                Toast.makeText(this,"Set Alram: ", Toast.LENGTH_SHORT).show();
                alarm.setAlarm(this);
                return true;
            // When the user clicks CANCEL ALARM, cancel the alarm.
            case R.id.cancel_action:
                alarm.cancelAlarm(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
