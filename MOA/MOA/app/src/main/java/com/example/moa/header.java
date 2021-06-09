package com.example.moa;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class header extends AppCompatActivity {
    private TextView header_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);

        header_id = findViewById(R.id.header_id);

        MyApp app = ((MyApp)getApplicationContext());

        header_id.setText(app.getUser_id());


    }
}
