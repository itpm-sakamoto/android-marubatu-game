package com.example.maru_batu_game_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.maru_batu_game_app.R;

import java.util.Random;


public class JyankenActivity extends AppCompatActivity {


    private FrameLayout jyanen_btn_pa;
    private FrameLayout jyanken_btn_gu;
    private FrameLayout jyanken_btn_tyoki;

    private static final int JANKEN_WIN = 1;
    private static final int JANKEN_LOSE = 2;
    private static final int JANKEN_DRAW = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jyanken);

        jyanken_btn_gu = findViewById(R.id.gu_btn);
        jyanken_btn_tyoki = findViewById(R.id.tyoki_btn);
        jyanen_btn_pa = findViewById(R.id.pa_btn);

        jyanen_btn_pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = jankenJuge(3);
                int juge = resultDialog(result);
//                if(juge != 3){
//                    Intent intent = new Intent(JyankenActivity.this, MaruBatuActivity.class);
//                    intent.putExtra( "MaruBatuActivity",juge);
//                    startActivity(intent);
//                }
            }
        });
        jyanken_btn_tyoki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = jankenJuge(2);
                int juge = resultDialog(result);
//                if(juge != 3){
//                    Intent intent = new Intent(JyankenActivity.this, MaruBatuActivity.class);
//                    intent.putExtra( "MaruBatuActivity",juge);
//                    startActivity(intent);
//                }

            }
        });
        jyanken_btn_gu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result = jankenJuge(1);
                int juge = resultDialog(result);
//                if(juge != 3){
//                    Intent intent = new Intent(JyankenActivity.this, MaruBatuActivity.class);
//                    intent.putExtra( "MaruBatuActivity",juge);
//                    startActivity(intent);
//                }
            }
        });
    }

    private static int jankenJuge(int jugeHand) {
        Random random = new Random();
        int randomNo = random.nextInt(3) + 1;

        if ((jugeHand == 1 && randomNo == 2)
                || (jugeHand == 2 && randomNo == 3)
                || (jugeHand == 3 && randomNo == 1)) {
            return JANKEN_WIN;
        } else if ((jugeHand == 1 && randomNo == 3)
                || (jugeHand == 2 && randomNo == 1)
                || (jugeHand == 3 && randomNo == 2)) {
            return JANKEN_LOSE;
        }
        return JANKEN_DRAW;
    }

    private int resultDialog(int reresultDialogNo) {

        int nom = 0;
        switch (reresultDialogNo) {

            case JANKEN_WIN:
                new AlertDialog.Builder(this)
                        .setTitle("勝ち")
                        .setMessage("あなたは先行です")
                        .setPositiveButton("OK", null)
                        .show();
                        nom = 1;
                        break;

            case JANKEN_LOSE:
                new AlertDialog.Builder(JyankenActivity.this)
                        .setTitle("負け")
                        .setMessage("あなたは後攻です")
                        .setPositiveButton("OK", null)
                        .show();
                        nom = 2;
                        break;

            case JANKEN_DRAW:
                new AlertDialog.Builder(JyankenActivity.this)
                        .setTitle("あいこ")
                        .setMessage("もう一度です")
                        .setPositiveButton("OK", null)
                        .show();
                        nom = 3;
                        break;

        }
        return nom;
    }

}
