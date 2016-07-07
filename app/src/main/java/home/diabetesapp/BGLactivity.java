package home.diabetesapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.view.View;
import android.content.Context;
import android.content.Intent;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.util.TimeUtils;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BGLactivity extends AppCompatActivity {


    TextView completiontext;
    //TextView submittedtext;
    Button submit;
    Button home;
    String range;
    String now;
    int convert;
    NumberPicker BGLnums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bglactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        completiontext = (TextView) findViewById(R.id.completiontext);
        BGLnums = (NumberPicker) findViewById((R.id.numberPicker));
        BGLnums.setMaxValue(200);
        BGLnums.setMinValue(0);
        //submittedtext = (TextView) findViewById(R.id.BGLeditone);
        submit = (Button) findViewById(R.id.BGLsubmitbutton);
        home = (Button) findViewById(R.id.BGLhomebutton);
        completiontext.setText("");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submission();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(BGLactivity.this ,MainActivity.class);
                startActivity(i);
            }
        });

    }

    public void submission(){
        //now.setToNow();
        if(BGLnums.getValue() < 70)
        {range = "too low";}
        else if (BGLnums.getValue() <140)
        {range = "Good";}
        else
        {range = "too high";}
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

// textView is the TextView view that should display it
        now = (currentDateTimeString);

        completiontext.setText("Thank you your BGL is "+ range + " and the submission time is " + now + "");
    }

}
