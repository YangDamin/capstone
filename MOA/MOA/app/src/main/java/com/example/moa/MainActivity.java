package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String url =  "http://bcd38c990cd9.ngrok.io/";
    private final  String TAG = getClass().getSimpleName();
    private EditText ID, pwd;
    private Button loginBtn;
    private TextView idPwdFind, signUp;
    private String loginId, loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ID = findViewById(R.id.ID);
        pwd = findViewById(R.id.pwd);
        loginBtn = findViewById(R.id.loginBtn);
        idPwdFind = findViewById(R.id.idPwdFind);
        signUp = findViewById(R.id.signUp);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyAPI api = retrofit.create(MyAPI.class);

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        loginId = auto.getString("inputId",null);
        loginPwd = auto.getString("inputPwd",null);

        if(loginId !=null && loginPwd != null) {
            Toast.makeText(MainActivity.this, loginId +"님 자동로그인 입니다.", Toast.LENGTH_SHORT).show();
            MyApp app = ((MyApp)getApplicationContext());
            app.setUser_id(loginId);
            Intent intent = new Intent(MainActivity.this, moaMain.class);
            startActivity(intent);
        }else{

        }

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = ID.getText().toString();
                String password = pwd.getText().toString();

                SharedPreferences.Editor autoLogin = auto.edit();

                autoLogin.putString("inputId", ID.getText().toString());
                autoLogin.putString("inputPwd", pwd.getText().toString());
                autoLogin.commit();

                loginInput login = new loginInput(user_id,password);
                Call<loginOutput> call = api.Login(login);
                call.enqueue(new Callback<loginOutput>() {
                    @Override
                    public void onResponse(Call<loginOutput> call, Response<loginOutput> response) {
                        if(response.isSuccessful()){
                            Log.d(TAG,"onRespose: 성공, 결과 " + response.body().getUser_id());
                            String i = response.body().getUser_id();
                            Intent intent = new Intent(MainActivity.this, moaMain.class);
                            MyApp app = ((MyApp)getApplicationContext());
                            app.setUser_id(i);
                            startActivity(intent);
                        }else{
                            Log.d(TAG,"onResponse: 실패");
                            Toast.makeText(getApplicationContext(),"로그인 실패!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<loginOutput> call, Throwable t) {
                        Log.d(TAG,"onFailure: " + t.getMessage());
                        Toast.makeText(getApplicationContext(),"오류 발생", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        idPwdFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, findIdPwd.class);
                startActivityForResult(intent, 1);
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivityForResult(intent, 1);
            }
        });
    }
}