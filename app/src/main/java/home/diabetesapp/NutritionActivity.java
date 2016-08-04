package home.diabetesapp;

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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class NutritionActivity extends AppCompatActivity {

    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Nutrition");
            toolbar.setBackground(new ColorDrawable(Color.argb(255, 124, 194, 131)));
        }
        setSupportActionBar(toolbar);


        //Setup Line chart
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

        LineChart chart = (LineChart) findViewById(R.id.NutritionChart);
        if(chart != null) {
            chart.setBackgroundColor(12);
            chart.setDescription("Nutrition Level");
            chart.setData(data);
            chart.invalidate();
        }

        //Set up BGLList Data
        //ListView BGLListView = (ListView) findViewById(R.id.BGLList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nutritionactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_nutrition) {
            Intent i = new Intent(NutritionActivity.this, AddNutritionActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
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