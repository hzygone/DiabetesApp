package home.diabetesapp;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
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
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ExerciseActivty extends AppCompatActivity {
    private Button btnListView, btnDateFrom, btnDateTo;
    private EditText textDateFrom, textDateTo;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle("Exercise Activity");
            toolbar.setLogo(R.mipmap.ic_launcher);   //uses the ic_launcher icon as title log
            toolbar.setBackground(new ColorDrawable(Color.argb(255, 255, 180, 65)));
        }
        setSupportActionBar(toolbar);

        btnListView = (Button) findViewById(R.id.btnListView);
        btnDateFrom = (Button) findViewById(R.id.btnDatePickerFrom);
        btnDateTo = (Button) findViewById(R.id.btnDatePickerTo);

        textDateFrom = (EditText) findViewById(R.id.textDateFrom);
        textDateTo = (EditText) findViewById(R.id.textDateTo);

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
            Intent i = new Intent(ExerciseActivty.this, AddExerciseActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        FragmentManager fm = getFragmentManager();

        if (view == btnListView) {
            android.app.FragmentTransaction ft = fm.beginTransaction();
            ExerciseListViewFragment exerciseFragmentView = new ExerciseListViewFragment();
            ft.replace(R.id.fragmentContainer, exerciseFragmentView);
            ft.commit();


        } else if (view == btnDateFrom) {
            final Calendar calender = Calendar.getInstance();
            mYear = calender.get(Calendar.YEAR);
            mMonth = calender.get(Calendar.MONTH);
            mDay = calender.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Dateformal YYYY-MM-DD
                    monthOfYear = monthOfYear + 1;
                    textDateFrom.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();

        } else if (view == btnDateTo) {
            final Calendar calender = Calendar.getInstance();
            mYear = calender.get(Calendar.YEAR);
            mMonth = calender.get(Calendar.MONTH);
            mDay = calender.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Dateformal YYYY-MM-DD
                    monthOfYear = monthOfYear + 1;
                    textDateTo.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
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
}

