package com.example.maru_batu_game_app.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.maru_batu_game_app.R;

import java.util.Random;

import static android.icu.text.DateTimePatternGenerator.PatternInfo.OK;

public class JyankenActivity extends AppCompatActivity {


    private Button jyanen_btn_pa;
    private Button jyanken_btn_gu;
    private Button jyanken_btn_tyoki;

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

                switch (result){

                    case JANKEN_WIN:
                        new AlertDialog.Builder( JyankenActivity.this )
                                .setTitle( "結果" )
                                .setMessage( "勝ち" )
                                .setPositiveButton("OK" , null )
                                .show();

                    case JANKEN_LOSE:

                    case JANKEN_DRAW:

                }

            }
        });

    }
    private static int jankenJuge(int jugeHand){
        Random random = new Random();
        int randomNo = random.nextInt(3)+1;

        if((jugeHand == 1 && randomNo == 2)
                || (jugeHand == 2 && randomNo == 3)
                || (jugeHand == 3 && randomNo == 1)){
            return JANKEN_WIN;
        } else if((jugeHand == 1 && randomNo == 3)
                || (jugeHand == 2 && randomNo == 1)
                || (jugeHand == 3 && randomNo == 2)){
            return JANKEN_LOSE;
        }
        return JANKEN_DRAW;
    }
}
