package com.example.moa;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qr extends AppCompatActivity {
    private ImageView qrCode;
    private Button qrBtn;
    private WindowManager.LayoutParams params;
    private float brightness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrBtn = findViewById(R.id.qrBtn);
        qrCode = findViewById(R.id.qr);

        params = getWindow().getAttributes();

        qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MyApp app = ((MyApp)getApplicationContext());
        String customer = app.getUser_id();

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        try{
            BitMatrix bitMatrix = multiFormatWriter.encode(customer, BarcodeFormat.QR_CODE, 500, 500);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            qrCode.setImageBitmap(bitmap);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

    @Override
    protected void onResume() {
        super.onResume();

        brightness = params.screenBrightness;
        params.screenBrightness = 1f;
        getWindow().setAttributes(params);
    }

    @Override
    protected void onPause() {
        super.onPause();

        params.screenBrightness = brightness;
        getWindow().setAttributes(params);
    }
}

