package home.diabetesapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class ModifyBGLActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText bglDate, bglTime, bglReading, bglComment;
    private Button btnUpdate, btnDelete, btnCancel, btnDatePicker, btnTimePicker;
    private String id, date, time, bglValue, comment;

    private int _id;

    private int mYear, mMonth, mDay, mHour, mMinute;

    public BGLDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_bgl);
        setTitle("Modify BGL Record");
        dbHelper = new BGLDBHelper(this);

        bglDate = (EditText) findViewById(R.id.BGLDate);
        bglTime = (EditText) findViewById(R.id.BGLTime);
        bglReading = (EditText) findViewById(R.id.BGLInt);
        bglComment = (EditText) findViewById(R.id.BGLComment);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        bglValue = intent.getStringExtra("bglValue");
        comment = intent.getStringExtra("comment");

        bglDate.setText(date);
        bglTime.setText(time);
        bglReading.setText(bglValue);
        bglComment.setText(comment);

        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnDatePicker.setOnClickListener(this);
        btnTimePicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnUpdate:
                BGL updatedBGL = new BGL();
//                updatedBGL.setId(_id);
                updatedBGL.setTimeStamp(bglTime.getText().toString());
                updatedBGL.setDateStamp(bglDate.getText().toString());
                updatedBGL.setBgReading(Integer.parseInt(bglReading.getText().toString()));
                updatedBGL.setComment(bglComment.getText().toString());
                int i = dbHelper.updateBGLByID(Integer.parseInt(id), updatedBGL);
                Log.i("INFO", "" + i);
                dbHelper.close();
                finish();
                break;

            case R.id.btnDelete:
                Log.i("INFO", "id is: " + id);
                dbHelper.deleteByID(Integer.parseInt(id));
                dbHelper.close();
                finish();
                break;

            case R.id.btnCancel:
                dbHelper.close();
                finish();  // go to the main activity;
                break;

            case R.id.btnDatePicker:
                //select Current Date
                final Calendar calender = Calendar.getInstance();

                mYear = calender.get(Calendar.YEAR);
                mMonth = calender.get(Calendar.MONTH);
                mDay = calender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        bglDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.btnTimePicker:
                //select current time
                final Calendar calender1 = Calendar.getInstance();
                mHour = calender1.get(Calendar.HOUR_OF_DAY);
                mMinute = calender1.get(Calendar.MINUTE);

                //Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        bglTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

        }

    }
}
