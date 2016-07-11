package home.diabetesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddNutritionActivity extends AppCompatActivity {

    Button cancel;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nutrition);

        cancel = (Button) findViewById(R.id.CancelNutritionButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Go back to parent activity
                finish();
            }
        });

        add = (Button) findViewById(R.id.AddNutritionButton);

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
