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
import android.widget.Button;

public class ActivityActivty extends AppCompatActivity {
    Button monthly, graphView;

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

        monthly = (Button) findViewById(R.id.Monthly);
        graphView =(Button)findViewById(R.id.graphView);
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

        if (id == R.id.action_add_activity) {
            Intent i = new Intent(ActivityActivty.this, AddActivityActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view){
        FragmentManager fm = getFragmentManager();

        if(view == monthly ){
            android.app.FragmentTransaction ft = fm.beginTransaction();
            BGListViewFragment bglFragmentView = new BGListViewFragment();
            ft.replace(R.id.fragmentContainer, bglFragmentView);
            ft.commit();


        }
        else if(view == graphView){
            android.app.FragmentTransaction ft = fm.beginTransaction();
            GraphViewFragment  bglFragmentView = new GraphViewFragment();
            ft.replace(R.id.fragmentContainer, bglFragmentView);
            ft.commit();
        }
        else
        {
          // place holder for the remaining views
        }

    }
}

