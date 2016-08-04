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
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class BGLactivity extends AppCompatActivity {
    ListView listView ;
    private List<BGL> bglList;
    BGLDBHelper dbHelper;
    public String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bglactivity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(toolbar != null) {
            toolbar.setTitle("Blood Glucose");
            toolbar.setBackground(new ColorDrawable(Color.argb(255, 237, 84, 84)));
        }
        setSupportActionBar(toolbar);
        dbHelper = new BGLDBHelper(this);
        bglList = new ArrayList<BGL>();
        listView = (ListView)findViewById(R.id.bglListView);


        //Setup Linechart
        //DOCUMENTATION: https://github.com/PhilJay/MPAndroidChart/wiki/Getting-Started

        //DummyData
//        ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
//        ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
//
//        for(int i = 0; i < 10; i++){
//            if(i % 2 == 0){
//                valsComp1.add(new Entry((float)i, 0));
//            } else{
//                valsComp2.add(new Entry((float)i, 1));
//            }
//        }
//
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Level 1");
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Level 2");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(setComp1);
//        dataSets.add(setComp2);
//
//        ArrayList<String> xVals = new ArrayList<String>();
//        xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q");
//
//        LineData data = new LineData(dataSets);
//        //END DUMMY DATA
//
//        LineChart chart = (LineChart) findViewById(R.id.BGLChart);
//        chart.setBackgroundColor(12);
//        chart.setDescription("BG Level");
//        chart.setData(data);
//        chart.invalidate();

        //Set up BGLList Data
        //ListView BGLListView = (ListView) findViewById(R.id.BGLList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bglactivity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_bgldata) {
            Intent i = new Intent(BGLactivity.this, AddBGLactivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void showWeeklyActivity(View view) {
        bglList = dbHelper.getAllBGL();
        listView.setAdapter(new ArrayAdapter<BGL>(this, android.R.layout.simple_list_item_1, bglList));
    }

    public void showMonthlyActivity(View view) {

        bglList = dbHelper.getAllBGL();
        listView.setAdapter(new ArrayAdapter<BGL>(this, android.R.layout.simple_list_item_1, bglList));

    }

    public void showGraphView(View View){
        GraphView bglLineGraph = (GraphView) findViewById(R.id.graph);
        bglList = dbHelper.getAllBGL();
        int len = bglList.size();
        DataPoint[] bglDataPoint = new DataPoint[len];
        int counter = 0;
        for(BGL a : bglList){

            bglDataPoint[counter] = new DataPoint(counter,a.getBgReading());
            counter++;
        }

        LineGraphSeries<DataPoint> line_series = new LineGraphSeries<DataPoint>();
        line_series.resetData(bglDataPoint);
        line_series.setDrawDataPoints(true);
        line_series.setDataPointsRadius(6);

        bglLineGraph.addSeries(line_series);

        // set the bound
        // set manual X bounds

        bglLineGraph.getViewport().setXAxisBoundsManual(true);
        bglLineGraph.getViewport().setMinX(1);
        bglLineGraph.getViewport().setMaxX(31);

        // set manual Y bounds
        bglLineGraph.getViewport().setYAxisBoundsManual(true);
        bglLineGraph.getViewport().setMinY(90);
        bglLineGraph.getViewport().setMaxY(300);

        bglLineGraph.getViewport().setScrollable(true);
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
