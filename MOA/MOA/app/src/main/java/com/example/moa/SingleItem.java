package com.example.moa;

public class SingleItem {
    private String store;
    private String stamp;
    private String totalStamp;
    private int resld;

    public SingleItem(String store,String stamp, String totalStamp ,int resld){
        this.store = store;
        this.stamp = stamp;
        this.totalStamp = totalStamp;
        this.resld = resld;
    }
    public String getTotalStamp() {
        return totalStamp;
    }
    public void setTotalStamp(String totalStamp) {
        this.totalStamp = totalStamp;
    }
    public String getStore(){
        return store;
    }
    public void setStore(String store){
        this.store = store;
    }
    public String getStamp(){
        return stamp;
    }
    public void setStamp(String stamp){
        this.stamp = stamp;
    }
    public int getResld(){
        return resld;
    }
    public String toString(){
        return "SingleItem{" + "Store = " + store + "Stamp = " + stamp+"}";
    }
}