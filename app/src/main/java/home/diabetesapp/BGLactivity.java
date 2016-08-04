package home.diabetesapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class BGLactivity extends AppCompatActivity {
    ListView listView ;
    private List<BGL> bglList;
    BGLDBHelper dbHelper;

    Button btnWeeklyActivity, btnMonthlyActivty, btnGraphView;


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

        btnWeeklyActivity = (Button) findViewById(R.id.btnweeklyActivity);
        btnMonthlyActivty = (Button) findViewById(R.id.btnMonthlyActivity);
        btnGraphView =(Button) findViewById(R.id.btnGraphView);
        dbHelper = new BGLDBHelper(this);
        bglList = new ArrayList<BGL>();

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

        } else {

        }
    }
}
