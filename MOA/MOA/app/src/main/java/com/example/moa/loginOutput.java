package com.example.moa;

public class loginOutput {
    private String user_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String toString(){
        return "아이디 : " + this.user_id;
    }
}
