package com.example.moa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.google.zxing.MultiFormatWriter;


public class moaMain extends AppCompatActivity {
    private ActionBar actionBar;
    private DrawerLayout drawerLayout;
    private ViewFlipper ad;
    private ImageButton qrCreate;
    private NavigationView navigation;
    private Toolbar toolbar;
    private Button btnStamp, btnGift, btnFindStore, btnMypage;
    private TextView main_id;
    private long pressedTime;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moa_main);

        qrCreate = findViewById(R.id.qrCreate);
        drawerLayout = findViewById(R.id.drawer);
        ad = findViewById(R.id.ad);
        toolbar = findViewById(R.id.toolbar);
        btnStamp = findViewById(R.id.btnStamp);
        btnFindStore = findViewById(R.id.btnFindStore);
        btnGift = findViewById(R.id.btnGift);
        btnMypage = findViewById(R.id.btnMypage);
        main_id = findViewById(R.id.main_id);

        MyApp app = ((MyApp)getApplicationContext());
        main_id.setText(app.getUser_id());

        SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = auto.edit();

        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.whitemenu);

        navigation = findViewById(R.id.navigation);
        View nav_header_view = navigation.getHeaderView(0);
        TextView header_id = nav_header_view.findViewById(R.id.header_id);
        header_id.setText(app.getUser_id());
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_stamp:
                        Intent intent = new Intent(moaMain.this, listStore.class);
                        startActivity(intent);
                        return true;
                    case R.id.nav_findStore:
                        Intent intent1 = new Intent(moaMain.this, findStore.class);
                        startActivity(intent1);
                        return true;
                    case R.id.nav_gift:
                        Intent intent2 = new Intent(moaMain.this, gift.class);
                        startActivity(intent2);
                        return true;
                    case R.id.nav_mypage:
                        Intent intent3 = new Intent(moaMain.this, myPage.class);
                        startActivity(intent3);
                        return true;
                    case R.id.nav_logout:
                        editor.clear();
                        editor.commit();
                        finish();
                }
                return false;
            }
        });

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnStamp:
                        Intent intent = new Intent(moaMain.this, listStore.class);
                        startActivity(intent);
                        break;
                    case R.id.btnFindStore:
                        Intent intent1 = new Intent(moaMain.this, findStore.class);
                        startActivity(intent1);
                        break;
                    case R.id.btnGift:
                        Intent intent2 = new Intent(moaMain.this, gift.class);
                        startActivity(intent2);
                        break;
                    case R.id.btnMypage:
                        Intent intent3 = new Intent(moaMain.this, myPage.class);
                        startActivity(intent3);
                        break;
                    case R.id.qrCreate:
                        Intent intent4 = new Intent(moaMain.this, qr.class);
                        startActivity(intent4);
                        break;
                }
            }
        };

        btnStamp.setOnClickListener(listener);
        btnMypage.setOnClickListener(listener);
        btnFindStore.setOnClickListener(listener);
        btnGift.setOnClickListener(listener);
        qrCreate.setOnClickListener(listener);

        ad.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        ad.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
        ad.setFlipInterval(3000);
        ad.startFlipping();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        if(pressedTime == 0){
            Toast.makeText(getApplicationContext(),"한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            pressedTime = System.currentTimeMillis();
        }else{
            int seconds = (int)(System.currentTimeMillis() - pressedTime);

            if(seconds > 2000){
                pressedTime = 0;
            }else{
                finishAffinity();
                System.runFinalization();
                System.exit(0);
            }
        }
    }

}
