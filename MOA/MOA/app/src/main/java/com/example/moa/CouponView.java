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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CouponView extends LinearLayout {
    private ImageView imageView;

    public CouponView(Context context){
        super(context);
        init(context);
    }
    public CouponView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.single_coupon_list, this, true);

        imageView = findViewById(R.id.imageView);
    }
    public void setImage(int resld){
        imageView.setImageResource(resld);
    }
}