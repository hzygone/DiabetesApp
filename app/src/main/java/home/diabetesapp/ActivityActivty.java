package home.diabetesapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ActivityActivty extends AppCompatActivity {

    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Activity");
            toolbar.setBackground(new ColorDrawable(Color.argb(255, 255, 180, 65)));
        }
        setSupportActionBar(toolbar);


        home = (Button) findViewById(R.id.ActivityHomeButton);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityActivty.this, MainActivity.class);
                startActivity(i);
            }
        });

        //Setup Linechart
        //DOCUMENTATION: https://github.com/PhilJay/MPAndroidChart/wiki/Getting-Started

        //DummyData
        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();

        for(int i = 0; i < 10; i++){
            if(i % 2 == 0){
                valsComp1.add(new Entry((float)i, 0));
            } else{
                valsComp2.add(new Entry((float)i, 1));
            }
        }

        LineDataSet setComp1 = new LineDataSet(valsComp1, "Level 1");
        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet setComp2 = new LineDataSet(valsComp2, "Level 2");
        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(setComp1);
        dataSets.add(setComp2);

        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q");

        LineData data = new LineData(dataSets);
        //END DUMMY DATA

        LineChart chart = (LineChart) findViewById(R.id.ActivityChart);
        chart.setBackgroundColor(12);
        chart.setDescription("Nutrition Level");
        chart.setData(data);
        chart.invalidate();

        //Set up BGLList Data
        //ListView BGLListView = (ListView) findViewById(R.id.BGLList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_nutrition) {
            Intent i = new Intent(ActivityActivty.this, AddActivityActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }
}

