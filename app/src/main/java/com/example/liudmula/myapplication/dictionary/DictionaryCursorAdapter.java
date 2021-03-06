package com.example.liudmula.myapplication.dictionary;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.liudmula.myapplication.database.DatabaseHelper;
import com.example.liudmula.myapplication.R;

import com.filippudak.ProgressPieView.ProgressPieView;

/**
 * Created by liudmula on 12.10.16.
 */

public class DictionaryCursorAdapter extends CursorAdapter {


    public DictionaryCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.dict_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView textView = (TextView) view.findViewById(R.id.tv_id);
        Integer _id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper._ID));
        textView.setText(_id.toString());

        textView = (TextView) view.findViewById(R.id.tvWord);
        String string = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.WORD));
        textView.setText(string);

        textView = (TextView) view.findViewById(R.id.tvDescription);
        string = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.DESC));
        textView.setText(string);


        Integer integer = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.PROGRESS));
        ProgressPieView ppv = (ProgressPieView) view.findViewById(R.id.pvProgress);
        ppv.setProgress(integer%100);


    }
}
