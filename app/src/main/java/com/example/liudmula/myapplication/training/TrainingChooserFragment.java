package com.example.liudmula.myapplication.training;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.liudmula.myapplication.R;

public class TrainingChooserFragment extends Fragment implements View.OnClickListener{

    Button btnWT, btnTW, btnCards, btnType;
    Toast toast;
    Context context;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_training_chooser, container, false);
        context = inflater.getContext();
        btnWT = (Button) v.findViewById(R.id.btn_w_t);
        btnWT.setOnClickListener(this);
        btnTW = (Button) v.findViewById(R.id.btn_t_w);
        btnTW.setOnClickListener(this);
        btnCards = (Button) v.findViewById(R.id.btn_card);
        btnCards.setOnClickListener(this);
        btnType = (Button) v.findViewById(R.id.btn_type);
        btnType.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()){
            case R.id.btn_w_t:
                intent.setClass(context, TrainingActivity.class);
                intent.putExtra("training", 0);
                intent.putExtra("type", true);
                startActivity(intent);
                break;
            case R.id.btn_t_w:
                intent.setClass(context, TrainingActivity.class);
                intent.putExtra("training", 1);
                intent.putExtra("type", false);
                startActivity(intent);
                break;
            case R.id.btn_type:
                intent.setClass(context, TrainingActivity.class);
                intent.putExtra("training", 2);
                startActivity(intent);
                break;
            case R.id.btn_card:
                intent.setClass(context, TrainingActivity.class);
                intent.putExtra("training", 3);
                startActivity(intent);
                break;
        }
    }
}
