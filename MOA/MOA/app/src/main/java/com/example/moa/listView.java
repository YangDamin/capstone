package com.example.moa;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class listView extends LinearLayout {
    private TextView store, dist;

    public listView(Context context){
        super(context);
        init(context);
    }
    public listView(Context context, @Nullable AttributeSet attrs) {super(context, attrs);}

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.activity_list, this, true);

        store = findViewById(R.id.Store);
        dist = findViewById(R.id.Dist);
    }
    public void setStore(String Store){store.setText(Store);}
    public void setDist(String Dist){dist.setText(Dist);}
}
