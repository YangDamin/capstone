package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class findIdPwd extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();

    private EditText findid_name,findid_phone,findpw_ID,findpw_name,findpw_birth;
    private Button btnFindID,btnFindPW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_pwd);

        findid_name = findViewById(R.id.findid_name);
        findid_phone = findViewById(R.id.findid_phone);
        findpw_ID = findViewById(R.id.findpw_ID);
        findpw_name = findViewById(R.id.findpw_name);
        findpw_birth = findViewById(R.id.findpw_birth);
        btnFindID = findViewById(R.id.btnFindID);
        btnFindPW = findViewById(R.id.btnFindPW);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);

        // 아이디 찾기 버튼을 누르면
        btnFindID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<findIdOutput> call = api.Find_ID_OUTPUT(findid_name.getText().toString(),findid_phone.getText().toString());
                call.enqueue(new Callback<findIdOutput>() {
                    @Override
                    public void onResponse(Call<findIdOutput> call, Response<findIdOutput> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG,"FindID onResponse: 성공, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"아이디는 " + response.body().getUser_id() ,Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"FindID onResponse: 실패, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"아이디를 찾지 못했습니다.",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<findIdOutput> call, Throwable t) {
                        Log.d(TAG,"FindID onFailure " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        // 비번 찾기 버튼을 누르면
        btnFindPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<findPwOutput> call = api.Find_PW_OUTPUT(findpw_ID.getText().toString(),findpw_name.getText().toString(),findpw_birth.getText().toString());
                call.enqueue(new Callback<findPwOutput>() {
                    @Override
                    public void onResponse(Call<findPwOutput> call, Response<findPwOutput> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG,"FindPW onResponse: 성공, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"비밀번호는 " + response.body().getPassword() ,Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"FindPW onResponse: 실패, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"비밀번호를 찾지 못했습니다.",Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<findPwOutput> call, Throwable t) {
                        Log.d(TAG,"FindPW onFailure " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}