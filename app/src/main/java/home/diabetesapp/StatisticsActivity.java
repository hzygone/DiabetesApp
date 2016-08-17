package home.diabetesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class StatisticsActivity extends AppCompatActivity {

    TextView textAverageBGL, textMaxBGL, textMinBGL, textVariance;
    EditText textDateFrom, getTextDateTo;
    Button btnDateFrom, btnDateTo;

    private List<BGL> bglList;
    BGLDBHelper dbHelper;
    public String msg;
    private String m_Text = "";

    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        textAverageBGL = (TextView) findViewById(R.id.textAverageBGLValue);
        textMaxBGL = (TextView) findViewById(R.id.textMaxBGLValue);
        textMinBGL = (TextView) findViewById(R.id.textMinBGLValue);
        textVariance = (TextView)findViewById(R.id.textVarianceValue);

        btnDateFrom = (Button) findViewById(R.id.btnDatePickerFrom);
        btnDateTo =(Button) findViewById(R.id.btnDatePickerTo);

        dbHelper = new BGLDBHelper(this);
        bglList = new ArrayList<BGL>();

    }

    public void onClick(View view) {
        if(view == btnDateFrom){
           bglList = dbHelper.getBGLBetweenDates(textDateFrom.getText().toString(), getTextDateTo.getText().toString());
            int count = bglList.size();
            int sum = 0;
            double average;
            int min = 0;
            int max = 0;
            if(count > 0){
                for(BGL a : bglList){
                    sum += a.getBgReading();

                }
                average = sum/count;
            }
        }
        else if(view == btnDateTo){

        }
        else{

        }
    }
}
