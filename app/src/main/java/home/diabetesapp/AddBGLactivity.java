package home.diabetesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    public BGLDBHelper dbHelper;
    public String msg;

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
                Log.i("INFO", "Adding BGL reading...");

                try {
                    Log.i("INFO", "Inside Try block , Adding BGL reading...");
                    EditText date = (EditText) findViewById(R.id.BGLDate);
                    EditText time = (EditText) findViewById(R.id.BGLTime);
                    EditText duration = (EditText) findViewById(R.id.BGLInt);
                    EditText comment = (EditText) findViewById(R.id.BGLComment);
                    Log.i("INFO", "Date is: "+ date.getText().toString() + ", Time is :  "+
                            time.getText().toString()+ ", duration is: "+ duration.getText().toString());

                    if ((!(date.getText().toString().isEmpty())
                            && !(time.getText().toString().isEmpty())
                            && !(duration.getText().toString().isEmpty()))) {

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

                        BGL bglInput = new BGL(dateAndTime, Integer.parseInt(duration.getText().toString()), comment.getText().toString());
                        dbHelper.addBGL(bglInput);
                        finish();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Date or Time or mg/dl field can not be empty!",Toast.LENGTH_LONG).show();
                    }

                }
                catch(Exception ex){

                }
            }
        });
    }
    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "The onStart() event");
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "The onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "The onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "The onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "The onDestroy() event");
    }
}
