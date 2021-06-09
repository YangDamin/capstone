package com.example.moa;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class gift extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();

    private Spinner spinner;
    private EditText getID, stamp_num;
    private Button btnSend;

    private ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);

        spinner = findViewById(R.id.spinner);
        getID = findViewById(R.id.getID);
        stamp_num = findViewById(R.id.stamp_num);
        btnSend = findViewById(R.id.btnSend);

        MyApp app = ((MyApp)getApplicationContext());
        String customer = app.getUser_id();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);

        Call<List<storeListOutput>> call = api.couponList(customer);

        call.enqueue(new Callback<List<storeListOutput>>() {
            @Override
            public void onResponse(Call<List<storeListOutput>> call, Response<List<storeListOutput>> response) {
                List<storeListOutput> sl = response.body();
                if(response.isSuccessful()){
                    Log.d(TAG,"Response 성공 ");
                    for(int i = 0; i<sl.size(); i++) {
                        items.add(sl.get(i).getStore());
                        Log.d(TAG,"items : " + items.get(i));
                    }



                }else{
                    Log.d(TAG,"Response 실패 " + response.code() );
                }

            }

            @Override
            public void onFailure(Call<List<storeListOutput>> call, Throwable t) {
                Log.d(TAG,"Failure 실패 " + t.getMessage() );
            }

        });
        items.add("선물 보낼 매장 선택하세요");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,items
        );
        adapter.notifyDataSetChanged();
        adapter.setDropDownViewResource(
                R.layout.support_simple_spinner_dropdown_item
        );
        spinner.setAdapter(adapter);

        //보내기 버튼 클릭 이벤트
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GiftInput giftInput = new GiftInput(spinner.getSelectedItem().toString(),stamp_num.getText().toString());
                Call<Void> call = api.Gift(customer,getID.getText().toString(), giftInput);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG,"Response 성공 " + spinner.getSelectedItem().toString());
                            Toast.makeText(getApplicationContext(), "선물 완료!", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"Response 실패 " + response.code() + spinner.getSelectedItem().toString());
                            if(response.code() == 401){
                                Toast.makeText(getApplicationContext(),"받는 유저의 아이디가 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                            }else if(response.code() == 402){
                                Toast.makeText(getApplicationContext(),"카페이름이 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                            }else if(response.code() == 403){
                                Toast.makeText(getApplicationContext(),"보내는 사람한테 등록되지 않은 카페입니다.",Toast.LENGTH_SHORT).show();
                            }else if(response.code() == 404){
                                Toast.makeText(getApplicationContext(),"스탬프 갯수를 다시한번 확인하세요.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG,"Response 실패 " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}