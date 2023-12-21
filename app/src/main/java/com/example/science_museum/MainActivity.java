package com.example.science_museum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        Intent intentMain = new Intent(MainActivity.this,ScienceMuseumActivity.class);
        startActivity(intentMain);
        Log.d("MainActivity","ScienceMuseumActivity");

    }
}
