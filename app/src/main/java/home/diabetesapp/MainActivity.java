package home.diabetesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Diabetes Management");
        setSupportActionBar(toolbar);

        Button MedicationButton = (Button) findViewById(R.id.MedicationButton);
        MedicationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MedicationActivity.class);
                startActivity(i);
            }

        });

        Button RegimenButton = (Button) findViewById(R.id.RegimenButton);
        RegimenButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegimenActivity.class);
                startActivity(i);
            }

        });
        Button BGLbutton = (Button) findViewById(R.id.BGLbutton);
        BGLbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BGLactivity.class);
                startActivity(i);
            }

        });

        Button NutritionButton = (Button) findViewById(R.id.NutritionButton);
        NutritionButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NutritionActivity.class);
                startActivity(i);
            }

        });

        Button ActivityButton = (Button) findViewById(R.id.ActivityButton);
        ActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityActivty.class);
                startActivity(i);
            }

        });



    }
}
