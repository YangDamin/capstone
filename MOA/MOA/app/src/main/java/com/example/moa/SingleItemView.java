package com.example.moa;

import android.content.Context;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

public class SingleItemView extends LinearLayout{
    private TextView StoreView, StampView, totalStampView;
    private ImageView imageView;

    public SingleItemView(Context context){
        super(context);
        init(context);
    }
    public SingleItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.single_item_list, this, true);

        StoreView = findViewById(R.id.Store);
        StampView = findViewById(R.id.Stamp);
        totalStampView = findViewById(R.id.totalStamp);
        imageView = findViewById(R.id.imageView);
    }
    public void setStore(String store){
        StoreView.setText(store);
    }
    public void setStamp(String stamp){
        StampView.setText(stamp);
    }
    public void setTotalStampView(String totalStamp) {totalStampView.setText(totalStamp);}
    public void setImage(int resld){
        imageView.setImageResource(resld);
    }
}
