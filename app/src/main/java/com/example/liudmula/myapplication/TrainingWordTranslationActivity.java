package com.example.liudmula.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import android.os.Handler;
import android.widget.TextView;


public class TrainingWordTranslationActivity extends Activity implements View.OnClickListener{
    Button btnTrans1, btnTrans2, btnTrans3, btnTrans4, btnTrans5, btnTrans6;
    TextView tvWord;
    Cursor learningWordsCursor, randomWordsCursor;
    DBManager dbManager;
    int indexWord, indexDesc;
    long id;
    final Handler handler = new Handler();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_word_translation);

        tvWord = (TextView)findViewById(R.id.tv_trn1_word);

        btnTrans1 = (Button)findViewById(R.id.btn_trn1_w1);
        btnTrans1.setOnClickListener(this);
        btnTrans2 = (Button)findViewById(R.id.btn_trn1_w2);
        btnTrans2.setOnClickListener(this);
        btnTrans3 = (Button)findViewById(R.id.btn_trn1_w3);
        btnTrans3.setOnClickListener(this);
        btnTrans4 = (Button)findViewById(R.id.btn_trn1_w4);
        btnTrans4.setOnClickListener(this);
        btnTrans5 = (Button)findViewById(R.id.btn_trn1_w5);
        btnTrans5.setOnClickListener(this);
        btnTrans6 = (Button)findViewById(R.id.btn_trn1_w6);

        dbManager = new DBManager(getApplicationContext());
        dbManager.open();
        learningWordsCursor = dbManager.fetch();
        learningWordsCursor = dbManager.getWordsWithLowProgress("3");
        indexWord = learningWordsCursor.getColumnIndex(DatabaseHelper.WORD);
        indexDesc = learningWordsCursor.getColumnIndex(DatabaseHelper.DESC);
        setButtonsText();

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_trn1_w1:
                if(btnTrans1.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans1);

                }else{
                    changeButtonColor(false, btnTrans1);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;


            case R.id.btn_trn1_w2:
                if(btnTrans2.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans2);

                }else{
                    changeButtonColor(false, btnTrans2);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;
            case R.id.btn_trn1_w3:
                if(btnTrans3.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans3);

                }else{
                    changeButtonColor(false, btnTrans3);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;
            case R.id.btn_trn1_w4:
                if(btnTrans4.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans4);

                }else{
                    changeButtonColor(false, btnTrans4);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;
            case R.id.btn_trn1_w5:
                if(btnTrans5.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans5);

                }else{
                    changeButtonColor(false, btnTrans5);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;
            case R.id.btn_trn1_w6:
                if(btnTrans6.getText().toString().equalsIgnoreCase(learningWordsCursor.getString(indexDesc))){
                    updateProgress();
                    changeButtonColor(true, btnTrans6);

                }else{
                    changeButtonColor(false, btnTrans6);
                    changeButtonColor(true, findAnswerButton(learningWordsCursor.getString(indexDesc)));
                }
                break;

        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonPressed();
            }
        }, 4000);
       //buttonPressed();


    }


    public void buttonPressed(){
        if(learningWordsCursor.moveToNext()){

            setButtonsText();
        }else{
            learningWordsCursor.close();
        }
    }

    public void updateProgress(){
        id = learningWordsCursor.getLong(0);
        Integer progress = learningWordsCursor.getInt(learningWordsCursor.getColumnIndex(DatabaseHelper.PROGRESS));
        progress += 20;
        dbManager.update(id, progress);
    }


    public void setButtonsText(){
        ArrayList<String> randoms = new ArrayList<>();
        tvWord.setText(learningWordsCursor.getString(indexWord));
        randoms.add(learningWordsCursor.getString(indexDesc));
        randomWordsCursor = dbManager.getRandomRows("5");
        try {
            do {
               randoms.add(randomWordsCursor.getString(indexDesc)) ;
            }while(randomWordsCursor.moveToNext());
        } finally {
            randomWordsCursor.close();
        }
        long seed = System.nanoTime();
        Collections.shuffle(randoms, new Random(seed));
        btnTrans1.setText(randoms.get(0));
        btnTrans2.setText(randoms.get(1));
        btnTrans3.setText(randoms.get(2));
        btnTrans4.setText(randoms.get(3));
        btnTrans5.setText(randoms.get(4));
        btnTrans6.setText(randoms.get(5));


    }

    public void changeButtonColor(final boolean answer, final Button button){
        if(answer) {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    button.setBackgroundColor(getResources().getColor(R.color.colorGreen));

                }
            }, 2000);

        }else{
            button.setBackgroundColor(getResources().getColor(R.color.colorRed));
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    button.setBackgroundColor(getResources().getColor(R.color.colorRed));
//
//                }
//            }, 2000);

        }

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//                    Thread.sleep(2000);
//                    button.setBackgroundResource(android.R.drawable.btn_default);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        thread.run();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                button.setBackgroundResource(android.R.drawable.btn_default);

            }
        }, 4000);




    }

    public Button findAnswerButton(String desc){
        if(desc.equalsIgnoreCase(btnTrans1.getText().toString()))
            return btnTrans1;
        if(desc.equalsIgnoreCase(btnTrans2.getText().toString()))
            return btnTrans2;
        if(desc.equalsIgnoreCase(btnTrans3.getText().toString()))
            return btnTrans3;
        if(desc.equalsIgnoreCase(btnTrans4.getText().toString()))
            return btnTrans4;
        if(desc.equalsIgnoreCase(btnTrans5.getText().toString()))
            return btnTrans5;
        if(desc.equalsIgnoreCase(btnTrans6.getText().toString()))
            return btnTrans6;
        return null;
    }
}
