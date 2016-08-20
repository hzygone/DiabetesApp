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
import helper.domain.Nutrition;

public class ModifyNutritionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nutritionName, nutritionDate, nutritionTime, nutritionQuantity, nutritionComment;
    private Button btnUpdate, btnDelete, btnCancel, btnDatePicker, btnTimePicker;
    private String id, name, date, time, quantity, comment;

    private int _id;

    private int mYear, mMonth, mDay, mHour, mMinute;

    private DBHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_nutrition);
        setTitle("Modify Nutrition Activity Record");
        dbHelper = new DBHelper(this);

        nutritionName = (EditText) findViewById(R.id.textDietName) ;
        nutritionDate = (EditText) findViewById(R.id.textDate);
        nutritionTime = (EditText) findViewById(R.id.textTime);
        nutritionQuantity = (EditText) findViewById(R.id.textQuantity);
        nutritionComment = (EditText) findViewById(R.id.textComment);

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
        quantity = intent.getStringExtra("quantity");
        comment = intent.getStringExtra("comment");

        Log.i("INFO", nutritionName+ "id= "+ id+ " name= "+ name+ " date= "+ date+ "time= "+ time + "duration= "+ quantity+ "comment= "+ comment );

        nutritionName.setText(name);
        nutritionDate.setText(date);
        nutritionTime.setText(time);
        nutritionQuantity.setText(quantity);
        nutritionComment.setText(comment);

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
                Nutrition updatedNutrition = new Nutrition();
                updatedNutrition.setNameOfDiet(nutritionName.getText().toString());
                updatedNutrition.setDateStamp(nutritionDate.getText().toString());
                updatedNutrition.setTimestamp(nutritionTime.getText().toString());
                updatedNutrition.setQuantity(Integer.parseInt(nutritionQuantity.getText().toString().trim()));
                updatedNutrition.setComment(nutritionComment.getText().toString());
                int i = dbHelper.updateNutritionByID(Integer.parseInt(id.trim()), updatedNutrition);
                Log.i("INFO", "" + i);
                dbHelper.closeDB();
                finish();
                break;

            case R.id.btnDelete:
                Log.i("INFO", "id is: " + id);
                dbHelper.deleteNutritionByID(Integer.parseInt(id.trim()));
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

                        nutritionDate.setText(dayOfMonth + "/" + monthOfYear + "/" + year);
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
                        nutritionTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
                timePickerDialog.show();
                break;

        }

    }
}
