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

public class signUp extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();

    private Button phoneCheckBtn, idCheckBtn, completionBtn, emailCheckBtn;
    private EditText ID, pwd, pwdCheck, email, reNum, phoneNum , name;
    private TextView pwdText;
    private boolean flagId, flagEmail, flagPhone, flagPwd, flagpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneCheckBtn = findViewById(R.id.phoneCheckBtn);
        idCheckBtn = findViewById(R.id.idCheckBtn);
        emailCheckBtn = findViewById(R.id.emailCheckBtn);
        completionBtn = findViewById(R.id.completionBtn);

        ID = findViewById(R.id.ID);
        pwd = findViewById(R.id.pwd);
        pwdCheck = findViewById(R.id.pwdCheck);
        email = findViewById(R.id.email);
        reNum = findViewById(R.id.reNum);
        phoneNum = findViewById(R.id.phoneNum);
        name = findViewById(R.id.name);

        pwdText = findViewById(R.id.pwdText);

        flagPhone = false;
        flagEmail = false;
        flagPwd = false;
        flagId = false;
        flagpassword = false;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        MyAPI api = retrofit.create(MyAPI.class);

        pwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(minLength(pwd.getText().toString()) == true){
                    flagpassword = true;
                }else{
                    flagpassword = false;
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        pwdCheck.addTextChangedListener(new TextWatcher() {     //비밀번호 확인
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(pwd.getText().toString().equals(pwdCheck.getText().toString())){
                    pwdText.setText("비밀번호가 일치합니다.");
                    pwdText.setTextColor(Color.parseColor("#1B5E20"));
                    flagPwd = true;
                }else{
                    pwdText.setText("비밀번호가 일치하지 않습니다.");
                    pwdText.setTextColor(Color.parseColor("#BD1414"));
                    flagPwd = false;
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        idCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = api.check("0",ID.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            flagId = true;
                            Log.d(TAG,"IDcheck onResponse: 성공, 결과 " + response.code() +  flagId + " " + flagPwd);
                            Toast.makeText(getApplicationContext(),"사용 가능한 아이디입니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"IDcheck onResponse: 실패, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"사용 불가능한 아이디입니다.",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG,"IDcheck onFailure " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        emailCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = api.check("1",email.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            flagEmail = true;
                            Log.d(TAG,"emailcheck onResponse: 성공, 결과 " + response.code() + flagEmail);
                            Toast.makeText(getApplicationContext(),"사용 가능한 이메일입니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"emailcheck onResponse: 실패, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"사용 불가능한 이메일입니다.",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG,"emailcheck onFailure " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        phoneCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Void> call = api.check("2",phoneNum.getText().toString());
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            flagPhone = true;
                            Log.d(TAG,"phonecheck onResponse: 성공, 결과 " + response.code() + " " + flagPhone);
                            Toast.makeText(getApplicationContext(),"사용 가능한 핸드폰 번호입니다.",Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG,"phonecheck onResponse: 실패, 결과 " + response.code());
                            Toast.makeText(getApplicationContext(),"사용 불가능한 핸드폰 번호입니다.",Toast.LENGTH_SHORT).show();

                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d(TAG,"phonecheck onFailure " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생!",Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        completionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ID.getText().toString().trim().length() == 0 || pwd.getText().toString().trim().length() == 0 || pwdCheck.getText().toString().trim().length() == 0 || name.getText().toString().trim().length() == 0 ||email.getText().toString().trim().length() == 0 || reNum.getText().toString().trim().length() == 0 || phoneNum.getText().toString().trim().length() == 0){
                    Toast.makeText(getApplicationContext(),"공백이 있습니다. 빈칸을 채워주세요.",Toast.LENGTH_SHORT).show();
                }else{
                    if(flagpassword == false){
                        Toast.makeText(getApplicationContext(),"비밀번호를 8자이상 입력하세요",Toast.LENGTH_SHORT).show();
                    }else{
                        if(flagId == false || flagPwd == false || flagEmail == false || flagPhone == false){
                            Toast.makeText(getApplicationContext(),"중복체크 및 비밀번호가 맞는지 확인해주세요", Toast.LENGTH_SHORT).show();
                        }else{
                            signUpInput signup_input = new signUpInput(ID.getText().toString(),pwd.getText().toString(),name.getText().toString(),phoneNum.getText().toString(),email.getText().toString(),reNum.getText().toString());
                            Call<Void> call = api.signUp(signup_input);
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    if(response.isSuccessful()){
                                        Log.d(TAG,"회원가입 onResponse: 성공, 결과 " + response.code());
                                        Toast.makeText(getApplicationContext(),"가입을 환영합니다^^",Toast.LENGTH_SHORT).show();
                                        finish();
                                    }else{
                                        Log.d(TAG,"회원가입 onResponse: 실패");
                                        Toast.makeText(getApplicationContext(),"회원가입 실패",Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {
                                    Log.d(TAG,"회원가입 onFailure: 오류발생" + t.getMessage());
                                }
                            });
                        }
                    }
                }
            }
        });
    }

    public boolean minLength(String pwd){
        if(pwd.length() < 8){
            return false;
        }else if(pwd.length() >= 8){
            return true;
        }
        return false;
    }
}