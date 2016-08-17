package home.diabetesapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import helper.database.DBHelper;
import helper.domain.Nutrition;

public class AddNutritionActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cancel, add, btnDatePicker, btnTimePicker;
    private EditText textName, textDate, textTime, textQuantity, textComment;

    private DBHelper dbHelper;

    private String msg;

    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrition);
        setTitle("Add Nutrition Info");

        dbHelper = new DBHelper(this);

        cancel = (Button) findViewById(R.id.CancelNutritionButton);
        add = (Button) findViewById(R.id.AddNutritionButton);
        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

        btnTimePicker.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        cancel.setOnClickListener(this);
        add.setOnClickListener(this);

        textName = (EditText) findViewById(R.id.textDietName);
        textDate = (EditText) findViewById(R.id.textDate);
        textTime = (EditText) findViewById(R.id.textTime);
        textQuantity = (EditText) findViewById(R.id.textQuantity);
        textComment = (EditText) findViewById(R.id.textComment);
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

    @Override
    public void onClick(View v) {
        if (v == btnDatePicker) {
            //Get Current Date
            final Calendar calender = Calendar.getInstance();
            mYear = calender.get(Calendar.YEAR);
            mMonth = calender.get(Calendar.MONTH);
            mDay = calender.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    // Dateformal YYYY-MM-DD
                    monthOfYear = monthOfYear + 1;
                    textDate.setText(year + "-" + monthOfYear + "-" + dayOfMonth);

                }
            }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } else if (v == btnTimePicker) {
            //Get current time
            final Calendar calender = Calendar.getInstance();
            mHour = calender.get(Calendar.HOUR_OF_DAY);
            mMinute = calender.get(Calendar.MINUTE);

            //Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    textTime.setText(hourOfDay + ":" + minute);
                }
            }, mHour, mMinute, false);
            timePickerDialog.show();
        } else if (v == add) {
            try {
                Log.i("INFO", "Adding Nutrition Info...");

                if (!(textName.getText().toString().isEmpty())
                        && !(textDate.getText().toString().isEmpty())
                        && !(textTime.getText().toString().isEmpty())
                        && !(textQuantity.getText().toString().isEmpty())) {
                    Nutrition inputNutrition = new Nutrition(0, textName.getText().toString(), textDate.getText().toString(),
                            textTime.getText().toString(), Integer.parseInt(textQuantity.getText().toString()),
                            textComment.getText().toString());

                    dbHelper.insertNutrition(inputNutrition);
                    dbHelper.closeDB();
                    finish();

                } // end if block

                else {
                    Toast.makeText(getApplicationContext(), "Name, Date, Time or quantity fields can not be empty!", Toast.LENGTH_LONG).show();

                }
            } // end try block
            catch (Exception ex) {
                Log.i("INFO", ex.getMessage());
            } // end catch block
        } // end if block

        else if (v == cancel) {
            //Go back to parent activity
            finish();
        }  // end else if

        else {
            //
        }
    }
}
