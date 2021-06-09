package com.example.moa;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class list implements Comparable<list>{
    private String store, dist, address , phone;
    private double latitude, longitude;

    public list(String store, String dist, String address,double latitude, double longitude, String phone){
        this.store = store;
        this.dist = dist;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
    }
    public String getStore(){
        return store;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setStore(String store){
        this.store = store;
    }
    public String getDist(){
        return dist;
    }
    public void setDist(String dist){
        this.dist = dist;
    }
    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String toString(){
        return "SingleItem{" + "Store = " + store + "dist = " + dist+"}";
    }

    @Override
    public int compareTo(list o) {
        if(Integer.parseInt(this.dist) < Integer.parseInt(o.getDist())){
            return -1;
        }else if(Integer.parseInt(this.dist) > Integer.parseInt(o.getDist())){
            return 1;
        }
        return 0;
    }
}