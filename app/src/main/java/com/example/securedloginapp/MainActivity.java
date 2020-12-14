package com.example.securedloginapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    public static int BATTERY_LEVEL = 70;
    MaterialButton clickBtn;
    EditText edtTxt;
    TextView txt;
    ImageButton imgBalloon;
    int counterClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);
        //hide action bar
        this.getSupportActionBar().hide();

        //find views
        clickBtn = findViewById(R.id.clickBtn);
        edtTxt = findViewById(R.id.edtTxt);
        imgBalloon = findViewById(R.id.imgBalloon);
        txt = findViewById(R.id.txt);

        //battery level
        BatteryManager bm = (BatteryManager) getApplicationContext().getSystemService(BATTERY_SERVICE);
        int batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY);
        //get orientation of the screen
        int orientation = this.getResources().getConfiguration().orientation;

        clickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (batLevel >= BATTERY_LEVEL && (edtTxt.getText().toString().equals("it's a secret")) && counterClicks > 5 && orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Toast.makeText(getApplicationContext(), "you are in the app", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "you can't login to the app", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgBalloon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counterClicks++;
                txt.setText("balloon clicked : " + counterClicks + " times");
            }
        });

    }


}