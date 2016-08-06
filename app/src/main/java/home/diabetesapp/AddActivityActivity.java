package home.diabetesapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddActivityActivity extends AppCompatActivity {

    Button cancel;
    Button add;
    String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        cancel = (Button) findViewById(R.id.CancelActivityButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go back to parent activity
                finish();
            }
        });

        add = (Button) findViewById(R.id.AddActivityButton);

        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {
                    System.out.println("Adding Activity...");
                    EditText date = (EditText) findViewById(R.id.ActivityDate);
                    EditText time = (EditText) findViewById(R.id.ActivityTime);
                    EditText duration = (EditText) findViewById(R.id.ActivityDuration);
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
                        int iDuration = Integer.parseInt(duration.getText().toString().trim());

                        Activity act = new Activity(inputDate, inputTime, iDuration);
                        //TODO ADD ACTIVITY TO DATABASE
                    }
                }catch(Exception ex){
                    System.out.println("Oops...");
                }

                finish();
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
