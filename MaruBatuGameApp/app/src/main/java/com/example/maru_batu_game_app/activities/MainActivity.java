package com.example.maru_batu_game_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.maru_batu_game_app.BuildConfig;
import com.example.maru_batu_game_app.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("TAG", getString(R.string.version_name, BuildConfig.VERSION_NAME));
        Button startBtn = findViewById(R.id.start_btn);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, JyankenActivity.class));
                finish();
            }
        });
    }
}
