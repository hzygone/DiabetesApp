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

import helper.database.DBHelper;
import helper.domain.Exercise;

public class ModifyExerciseActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText exerciseName, exerciseDate, exerciseTime, exerciseDuration, exerciseComment;
    private Button btnUpdate, btnDelete, btnCancel, btnDatePicker, btnTimePicker;
    private String id, name, date, time, duration, comment;

    private int _id;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_exercise);
        setTitle("Modify Exercise Activity Record");
        dbHelper = new DBHelper(this);

        exerciseName = (EditText) findViewById(R.id.textExerciseName) ;
        exerciseDate = (EditText) findViewById(R.id.exerciseDate);
        exerciseTime = (EditText) findViewById(R.id.exerciseTime);
        exerciseDuration = (EditText) findViewById(R.id.textDuration);
        exerciseComment = (EditText) findViewById(R.id.exerciseComment);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        btnDatePicker = (Button) findViewById(R.id.btnDatePicker);
        btnTimePicker = (Button) findViewById(R.id.btnTimePicker);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        name = intent.getStringExtra("name");
        date = intent.getStringExtra("date");
        time = intent.getStringExtra("time");
        duration = intent.getStringExtra("duration");
        comment = intent.getStringExtra("comment");

        Log.i("INFO", "id= "+ id+ " name= "+ name+ " date= "+ date+ "time= "+ time + "duration= "+ duration+ "comment= "+ comment );

        exerciseName.setText(name);
        exerciseDate.setText(date);
        exerciseTime.setText(time);
        exerciseDuration.setText(duration);
        exerciseComment.setText(comment);

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
                Exercise updatedExercise = new Exercise();
                updatedExercise.setActivityName(exerciseName.getText().toString());
                updatedExercise.setTimeStamp(exerciseTime.getText().toString());
                updatedExercise.setDateStamp(exerciseDate.getText().toString());
                updatedExercise.setDuration(Integer.parseInt(exerciseDuration.getText().toString().trim()));
                updatedExercise.setComment(exerciseComment.getText().toString());
                int i = dbHelper.updateExerciseByID(Integer.parseInt(id.trim()), updatedExercise);
                Log.i("INFO", "" + i);
                dbHelper.closeDB();
                finish();
                break;

            case R.id.btnDelete:
                Log.i("INFO", "id is: " + id);
                dbHelper.deleteExerciseByID(Integer.parseInt(id.trim()));
                dbHelper.closeDB();
                finish();
                break;

            case R.id.btnCancel:
                dbHelper.closeDB();
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

                        exerciseDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
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
                        exerciseTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

        }

    }
}
