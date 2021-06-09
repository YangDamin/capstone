package com.example.moa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class storeInfo extends AppCompatActivity implements OnMapReadyCallback {
    private TextView storename, distance, address, tel;
    private GoogleMap mMap;
    private double la, lo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_info);
        storename = findViewById(R.id.storename);
        distance = findViewById(R.id.distance);
        address = findViewById(R.id.address);
        tel = findViewById(R.id.tel);
        Intent intent = getIntent();

        storename.setText(intent.getStringExtra("storeName"));
        distance.setText(intent.getStringExtra("distance"));
        address.setText(intent.getStringExtra("address"));
        tel.setText(intent.getStringExtra("phone"));
        la=intent.getDoubleExtra("la",0);
        lo=intent.getDoubleExtra("lo",0);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        LatLng storepin = new LatLng(la, lo);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(storepin);
        mMap.addMarker(markerOptions);

        // 기존에 사용하던 다음 2줄은 문제가 있습니다.

        // CameraUpdateFactory.zoomTo가 오동작하네요.
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(storepin, 16));
    }
}