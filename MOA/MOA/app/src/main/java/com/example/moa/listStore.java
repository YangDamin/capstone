package com.example.moa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class listStore extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();
    private ActionBar actionBar;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_store);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);
        MyApp app = ((MyApp)getApplicationContext());
        String customer = app.getUser_id();

        Call<List<storeListOutput>> call = api.couponList(customer);

        ListView listView = findViewById(R.id.listView);
        SingleAdapter adapter = new SingleAdapter();

        call.enqueue(new Callback<List<storeListOutput>>() {
            @Override
            public void onResponse(Call<List<storeListOutput>> call, Response<List<storeListOutput>> response) {
                List<storeListOutput> sl = response.body();
                if(response.isSuccessful()){
                    Log.d(TAG,"Response 성공");
                    for(int i = 0; i < sl.size(); i++){
                        adapter.addItem(new SingleItem(sl.get(i).getStore(), sl.get(i).getCurrent_cnt(), sl.get(i).getCafe_stamp() ,R.drawable.store_img));
                    }
                    listView.setAdapter(adapter);
                }else{
                    Log.d(TAG,"Response 실패");
                }

            }

            @Override
            public void onFailure(Call<List<storeListOutput>> call, Throwable t) {
                Log.d(TAG,"Failure" + t.getMessage());
            }
        });

        //매장리스트 클릭 이벤트
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(listStore.this, stamp.class);
                SingleItem s = adapter.getItem(position);
                String sn = s.getStamp();
                intent.putExtra("storeName", s.getStore());
                intent.putExtra("numStamp", sn);
                startActivity(intent);
            }
        });
    }

    private class SingleAdapter extends BaseAdapter {
        ArrayList<SingleItem> items = new ArrayList<SingleItem>();

        @Override
        public int getCount(){
            return items.size();
        }
        public void addItem(SingleItem item){
            items.add(item);
        }

        @Override
        public SingleItem getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            SingleItemView singleItemView = null;

            if (convertView == null) {
                singleItemView = new SingleItemView((getApplicationContext()));
            } else {
                singleItemView = (SingleItemView) convertView;
            }

            SingleItem item = items.get(position);
            singleItemView.setStore(item.getStore());
            singleItemView.setStamp(item.getStamp());
            singleItemView.setImage(item.getResld());
            singleItemView.setTotalStampView(item.getTotalStamp());
            return singleItemView;
        }
    }
}