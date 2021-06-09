package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Change_pw extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();

    private EditText exist_pw, newpw, newpw_check;
    private Button pw_change;
    private boolean flagpassword;
    private TextView newpw_ok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pw);

        exist_pw = findViewById(R.id.exist_pw);
        newpw = findViewById(R.id.newpw);
        newpw_check = findViewById(R.id.newpw_check);
        pw_change = findViewById(R.id.pw_change);
        newpw_ok = findViewById(R.id.newpw_ok);

        flagpassword = false;

        // 비밀번호 8자 이상
        newpw.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(minLength(newpw.getText().toString()) == true){
                    flagpassword = true;
                }else{
                    flagpassword = false;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //비밀번호 확인
        newpw_check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(newpw.getText().toString().equals(newpw_check.getText().toString())){
                    newpw_ok.setText("비밀번호가 일치합니다.");
                    newpw_ok.setTextColor(Color.parseColor("#1B5E20"));
                }else{
                    newpw_ok.setText("비밀번호가 일치하지 않습니다.");
                    newpw_ok.setTextColor(Color.parseColor("#BD1414"));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);

        changePwdInput cpi = new changePwdInput();

        MyApp app = ((MyApp)getApplicationContext());
        String customer = app.getUser_id();

        //비밀번호 변경 클릭 이벤트
        pw_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cpi.setExisting_pw(exist_pw.getText().toString());
                cpi.setNew_pw(newpw.getText().toString());

                Call<Void> call = api.Change_PW(customer, cpi);
                if(flagpassword == false){
                    Toast.makeText(getApplicationContext(),"비밀번호를 8자이상 입력하세요",Toast.LENGTH_SHORT).show();
                }else{
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Log.d(TAG,"ChangePW onResponse: 성공, 결과 " + response.code());
                                Toast.makeText(getApplicationContext(),"비밀번호 변경이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                            }else{
                                Log.d(TAG,"ChangePW onResponse: 실패, 결과 " + response.code());
                                if(response.code() == 400){
                                    Toast.makeText(getApplicationContext(),"유저 아이디가 잘못되었습니다.",Toast.LENGTH_SHORT).show();
                                }else if(response.code() == 401){
                                    Toast.makeText(getApplicationContext(),"현재 비밀번호와 새 비밀번호가 같습니다.",Toast.LENGTH_SHORT).show();
                                }else if(response.code() == 402){
                                    Toast.makeText(getApplicationContext(),"현재 비밀번호가 틀립니다.",Toast.LENGTH_SHORT).show();
                                    Log.d(TAG,"로그으 " + cpi.getExisting_pw() + " " + cpi.getNew_pw());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.d(TAG,"ChangePW onFailure " + t.getMessage());
                            Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    // 비밀번호 8자 이상
    public boolean minLength(String pwd){
        if(pwd.length() < 8){
            return false;
        }else if(pwd.length() >= 8){
            return true;
        }
        return false;
    }

}