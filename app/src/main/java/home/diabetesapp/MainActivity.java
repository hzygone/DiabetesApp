package home.diabetesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public String msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Diabetes Management");
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
                    Intent i = new Intent(MainActivity.this, BGLactivity.class);
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
                    Intent i = new Intent(MainActivity.this, ActivityActivty.class);
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
}
