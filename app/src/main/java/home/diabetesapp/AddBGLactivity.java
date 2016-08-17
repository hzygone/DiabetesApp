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
import helper.domain.BGL;

public class AddBGLActivity extends AppCompatActivity implements View.OnClickListener {

    private Button cancel, add, btnTimePicker, btnDatePicker;
    private EditText textDate, textTime, textDuration, textComment;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private DBHelper dbHelper;

    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bglactivity);
        setTitle("Add BGL Reading");
        dbHelper = new DBHelper(this);

        textDate = (EditText) findViewById(R.id.BGLDate);
        textTime = (EditText) findViewById(R.id.BGLTime);
        textDuration = (EditText) findViewById(R.id.BGLInt);
        textComment = (EditText) findViewById(R.id.BGLComment);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

        cancel = (Button) findViewById(R.id.CancelAddDataButton);
        add = (Button) findViewById(R.id.AddDataButton);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go back to parent activity
                finish();
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("INFO", "Adding BGL reading...");

                try {
                    Log.i("INFO", "Inside Try block , Adding BGL reading...");

                    Log.i("INFO", "Date is: " + textDate.getText().toString() + ", Time is :  " +
                            textTime.getText().toString() + ", duration is: " + textDuration.getText().toString());

                    if ((!(textDate.getText().toString().isEmpty())
                            && !(textTime.getText().toString().isEmpty())
                            && !(textDuration.getText().toString().isEmpty()))) {

                        //Duration parsing
                        int iBGLReading = Integer.parseInt(textDuration.getText().toString().trim());

                        BGL bglInput = new BGL(0, textTime.getText().toString(), textDate.getText().toString(), Integer.parseInt(textDuration.getText().toString()), textComment.getText().toString());

                        dbHelper.addBGL(bglInput);
                        dbHelper.closeDB();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Date or Time or mg/dl field can not be empty!", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception ex) {

                }
            }
        });
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
        }
        if (v == btnTimePicker) {
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

        }
    }
}

