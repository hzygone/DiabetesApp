package home.diabetesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddActivityActivity extends AppCompatActivity {

    Button cancel;
    Button add;

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
                System.out.println("Add data button action has fired...");
                //Go back to parent activity
                finish();
            }
        });

    }
}
