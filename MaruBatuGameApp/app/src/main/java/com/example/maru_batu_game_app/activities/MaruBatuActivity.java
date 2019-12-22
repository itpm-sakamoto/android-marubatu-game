package com.example.maru_batu_game_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.maru_batu_game_app.R;

public class MaruBatuActivity extends AppCompatActivity {

    public MaruBatuActivity() {

    }

    public MaruBatuActivity(JyankenActivity jyankenActivity, Class<MaruBatuActivity> maruBatuActivityClass) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maru_batu);
    }
}
