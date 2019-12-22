package com.example.maru_batu_game_app.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.maru_batu_game_app.R;

import java.util.ArrayList;
import java.util.Random;

public class MaruBatuActivity extends AppCompatActivity implements View.OnClickListener {

    private final int USER_TURN = 1;
    private final int CPU_TURN = 2;

    private int[][] mMaruBatuMap = {{0, 0, 0},{0, 0, 0},{0, 0, 0}};

    private int[] maruBatsuIcns = {0, R.mipmap.maru, R.mipmap.batu};

    private ImageView mImageViews[][] = new ImageView[3][3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maru_batu);

        mImageViews[0][0] = findViewById(R.id.zero_zero);
        mImageViews[0][1] = findViewById(R.id.zero_one);
        mImageViews[0][2] = findViewById(R.id.zero_two);

        mImageViews[1][0] = findViewById(R.id.one_zero);
        mImageViews[1][1] = findViewById(R.id.one_one);
        mImageViews[1][2] = findViewById(R.id.one_two);

        mImageViews[2][0] = findViewById(R.id.two_zero);
        mImageViews[2][1] = findViewById(R.id.two_one);
        mImageViews[2][2] = findViewById(R.id.two_two);

        mImageViews[0][0].setOnClickListener(this);
        mImageViews[0][1].setOnClickListener(this);
        mImageViews[0][2].setOnClickListener(this);

        mImageViews[1][0].setOnClickListener(this);
        mImageViews[1][1].setOnClickListener(this);
        mImageViews[1][2].setOnClickListener(this);

        mImageViews[2][0].setOnClickListener(this);
        mImageViews[2][1].setOnClickListener(this);
        mImageViews[2][2].setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        //画像差し替え
        //押されたボタンがどれか判定する
        int resId = view.getId();

        switch (resId) {
            case R.id.zero_zero:
                if(mMaruBatuMap[0][0] != 0) {
                    return;
                }
                mImageViews[0][0].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[0][0] = 1;
                break;

            case R.id.zero_one:
                if(mMaruBatuMap[0][1] != 0) {
                    return;
                }
                mImageViews[0][1].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[0][1] = 1;
                break;

            case R.id.zero_two:
                if(mMaruBatuMap[0][2] != 0) {
                    return;
                }
                mImageViews[0][2].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[0][2] = 1;
                break;


            case R.id.one_zero:
                if(mMaruBatuMap[1][0] != 0) {
                    return;
                }
                mImageViews[1][0].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[1][0] = 1;
                break;

            case R.id.one_one:
                if(mMaruBatuMap[1][1] != 0) {
                    return;
                }
                mImageViews[1][1].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[1][1] = 1;
                break;

            case R.id.one_two:
                if(mMaruBatuMap[1][2] != 0) {
                    return;
                }
                mImageViews[1][2].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[1][2] = 1;
                break;


            case  R.id.two_zero:
                if(mMaruBatuMap[2][0] != 0) {
                    return;
                }
                mImageViews[2][0].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[2][0] = 1;
                break;

            case  R.id.two_one:
                if(mMaruBatuMap[2][1] != 0) {
                    return;
                }
                mImageViews[2][1].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[2][1] = 1;
                break;

            case  R.id.two_two:
                if(mMaruBatuMap[2][2] != 0) {
                    return;
                }
                mImageViews[2][2].setImageResource(maruBatsuIcns[1]);
                mMaruBatuMap[2][2] = 1;
                break;
        }




        //判定する
        if (checkHorizen() || checkVerchical() || checkCross()) {
            //勝判定判定
            AlertDialog.Builder altBuilder = new AlertDialog.Builder(this);
            altBuilder.setMessage("勝ちました");
            altBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(MaruBatuActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            altBuilder.show();

        } else {
            if(!existsBlanckCell()) {
                //引き分け判定
                AlertDialog.Builder altBuilder = new AlertDialog.Builder(this);
                altBuilder.setMessage("引き分け");
                altBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MaruBatuActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
                altBuilder.show();
            }
            //CPUがランダムなところに入力する処理
            inputByCpu();

        }

        //結果を判定する（勝ったor負けた）

        //入力欄が残っているか
        //もし残ってたらｃぷターン
        //残ってなかったら引き分けダイアログを出す
    }

    //今かったかどうかを判定するロジック

    //横一列がそろっているか確認する
    private boolean checkHorizen() {

        for(int i = 0; i < 3; i++) {
            int inputNum = -1;
            for(int j = 0; j < 3; j ++) {
                if(mMaruBatuMap[i][j] == 0) {
                    break;
                }
                if (inputNum == -1) {
                    inputNum = mMaruBatuMap[i][j];
                }
                else if (inputNum != mMaruBatuMap[i][j]) {
                    break;
                }

                if (j == 2) {
                    // TODO:
                    return true;
                }
            }
        }
        return false;
    }


    //縦一列がそろっているか確認する
    private  boolean checkVerchical() {

        for(int i = 0; i < 3; i++) {
            int inputNum = -1;
            for(int j = 0; j < 3; j++) {
                if(mMaruBatuMap[j][i] == 0) {
                    break;
                }
                if (inputNum == -1) {
                    inputNum = mMaruBatuMap[j][i];
                }
                else if (inputNum != mMaruBatuMap[j][i]) {
                    break;
                }

                if(j == 2) {
                    return true;
                }
            }
        }
        return false;
    }


    //斜めを確認する
    private  boolean checkCross() {

        int inputNum = mMaruBatuMap[0][0];

        if(inputNum != 0) {
            if(inputNum == mMaruBatuMap[1][1]
                    && inputNum == mMaruBatuMap[2][2]) {
                return true;
            }
        }

        inputNum = mMaruBatuMap[0][2];

        if(inputNum != 0) {
            if(inputNum == mMaruBatuMap[1][1]
                    && inputNum == mMaruBatuMap[2][0]) {
                return true;
            }
        }

        return false;
    }

    //0が二次元配列の中にあるかどうかをチェックする
    private boolean existsBlanckCell() {
        int blanckNum = 0;

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(blanckNum == mMaruBatuMap[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    //CPUの入力ロジック
    private void inputByCpu() {
        ArrayList<BlankCell> blankCellsArray = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                if(0 == mMaruBatuMap[i][j]) {
                    blankCellsArray.add(new BlankCell(i, j));
                }
            }
        }

        Random random = new Random();
        int randomNum = random.nextInt(blankCellsArray.size());

        BlankCell blankCell = blankCellsArray.get(randomNum);

        //CPUのイメージ画像を差し替える
        mImageViews[blankCell.getFirstDmnl()][blankCell.getSecondDmnl()].setImageResource(maruBatsuIcns[2]);
        //CPUが入力した場所を「2」に変更する
        mMaruBatuMap[blankCell.getFirstDmnl()][blankCell.getSecondDmnl()] = CPU_TURN;
    }

    private class BlankCell {
        int firstDmnl;
        int secondDmnl;

        public BlankCell(int firstDmnl, int secondDmnl) {
            this.firstDmnl = firstDmnl;
            this.secondDmnl = secondDmnl;
        }

        public int getFirstDmnl() {
            return firstDmnl;
        }

        public void setFirstDmnl(int firstDmnl) {
            this.firstDmnl = firstDmnl;
        }

        public int getSecondDmnl() {
            return secondDmnl;
        }

        public void setSecondDmnl(int secondDmnl) {
            this.secondDmnl = secondDmnl;
        }
    }
}
