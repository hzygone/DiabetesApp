package home.diabetesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import helper.database.BGLDBHelper;
import helper.domain.BGL;

public class AddBGLactivity extends AppCompatActivity {

    public Button cancel;
    public Button add;
    BGLDBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bglactivity);
        dbHelper = new BGLDBHelper(this);
        cancel = (Button) findViewById(R.id.CancelAddDataButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go back to parent activity
                finish();
            }
        });

        add = (Button) findViewById(R.id.AddDataButton);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                System.out.println("Adding BGL reading...");

                try {
                    System.out.println("Adding Activity...");
                    EditText date = (EditText) findViewById(R.id.BGLDate);
                    EditText time = (EditText) findViewById(R.id.BGLTime);
                    EditText duration = (EditText) findViewById(R.id.BGLInt);
                    EditText comment = (EditText) findViewById(R.id.BGLComment);
                    if (date != null && time != null & duration != null) {

                        //Date parsing
                        String sDate = date.getText().toString().trim();
                        DateFormat df = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
                        Date inputDate = df.parse(sDate);

                        //Time Parsing
                        String sTime = time.getText().toString().trim();
                        df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
                        Date dTime = df.parse(sTime);
                        Time inputTime = new Time(dTime.getTime());

                        //Duration parsing
                        int iBGLReading = Integer.parseInt(duration.getText().toString().trim());

                        BloodGlucoseReading bgl = new BloodGlucoseReading(inputDate, inputTime, iBGLReading);
                        String dateAndTime = inputDate +" : " + inputTime;
                        //TODO ADD ACTIVITY TO DATABASE

                        BGL bglInput = new BGL(dateAndTime, Integer.parseInt(duration.getText().toString()),comment.getText().toString());
                        dbHelper.addBGL(bglInput);
                    }
                }catch(Exception ex){}
                finish();
            }
        });

    }
}
