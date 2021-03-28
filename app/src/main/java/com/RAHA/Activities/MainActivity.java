package com.RAHA.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.RAHA.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Button btnGyroscope = (Button) findViewById(R.id.button_gyroscope);
        Button btnGravity = (Button) findViewById(R.id.button_gravity);

        View.OnClickListener handler = new View.OnClickListener() {

            public void onClick(View v) {
                if (v == btnGyroscope) {
                    System.out.println("gyro button pressed");
                    Intent intentGyroscope = new Intent(MainActivity.this,
                            GyroscopeActivity.class);
                    startActivity(intentGyroscope);
                }
                if (v == btnGravity) {
                    Intent intentGravity = new Intent(MainActivity.this,
                            GravityActivity.class);
                    startActivity(intentGravity);
                }
            }
        };

        btnGyroscope.setOnClickListener(handler);
        btnGravity.setOnClickListener(handler);
    }
}