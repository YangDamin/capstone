package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class stamp extends AppCompatActivity {
    private TextView stName, stampCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);

        stName = findViewById(R.id.stName);
        stampCount = findViewById(R.id.stamp_count);
        GridView gridView =findViewById(R.id.gridView);
        SingleAdapter adapter = new SingleAdapter();

        Intent intent = getIntent();

        stName.setText(intent.getStringExtra("storeName"));
        int n = Integer.parseInt(intent.getStringExtra("numStamp"));

        stampCount.setText(intent.getStringExtra("numStamp"));

        for(int i = 0; i <n; i++){
            adapter.addItem(new Coupon(R.drawable.stamp_moa));
        }

        gridView.setAdapter(adapter);
    }
    private class SingleAdapter extends BaseAdapter {
        ArrayList<Coupon> items = new ArrayList<Coupon>();

        @Override
        public int getCount(){
            return items.size();
        }
        public void addItem(Coupon item){
            items.add(item);
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            CouponView couponView = null;

            if(convertView == null){
                couponView = new CouponView((getApplicationContext()));
            }else{
                couponView = (CouponView) convertView;
            }
            Coupon item = items.get(position);
            couponView.setImage(item.getResld());
            return couponView;
        }
    }
}