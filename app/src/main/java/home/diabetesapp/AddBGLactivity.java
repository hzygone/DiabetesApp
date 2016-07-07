package home.diabetesapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class AddBGLactivity extends AppCompatActivity {

    public Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bglactivity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.AddBGLToolbar);
//        if(toolbar != null) {
//            toolbar.setTitle("Add Blood Glucose Level");
//            toolbar.setBackground(new ColorDrawable(Color.argb(255, 237, 84, 84)));
//        }
//        setSupportActionBar(toolbar);

        cancel = (Button) findViewById(R.id.CancelAddDataButton);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
