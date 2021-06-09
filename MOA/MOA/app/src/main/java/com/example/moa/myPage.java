package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class myPage extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();

    private TextView mypage_id,mypage_name,mypage_email,mypage_phone,mypage_birth;
    private Button changePW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        mypage_id = findViewById(R.id.mypage_id);
        mypage_name = findViewById(R.id.mypage_name);
        mypage_email = findViewById(R.id.mypage_email);
        mypage_phone = findViewById(R.id.mypage_phone);
        mypage_birth = findViewById(R.id.mypage_birth);

        changePW = findViewById(R.id.changePW);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyAPI api = retrofit.create(MyAPI.class);

        MyApp app = ((MyApp)getApplicationContext());
        String customer = app.getUser_id();

        Call<myPageOutput> call = api.MyPage_OUTPUT(customer);
        call.enqueue(new Callback<myPageOutput>() {
            @Override
            public void onResponse(Call<myPageOutput> call, Response<myPageOutput> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"MyPage onResponse: 성공, 결과 " + response.code());
                    mypage_id.setText(response.body().getUser_id());
                    mypage_name.setText(response.body().getName());
                    mypage_email.setText(response.body().getEmail());
                    mypage_phone.setText(response.body().getPhone());
                    mypage_birth.setText(response.body().getBirth());
                }else{
                    Log.d(TAG,"MyPage onResponse: 실패, 결과 " + response.code());
                }
            }

            @Override
            public void onFailure(Call<myPageOutput> call, Throwable t) {
                Log.d(TAG,"MyPage onFailure " + t.getMessage());
            }
        });


        // 비밀번호 변경 누를시 페이지 이동
        changePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myPage.this,Change_pw.class);
                startActivityForResult(intent,1);
            }
        });
    }
}