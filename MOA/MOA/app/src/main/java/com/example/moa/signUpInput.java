package com.example.moa;

public class signUpInput {
    private String user_id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String birth;

    public signUpInput(String user_id, String password, String name, String phone, String email, String birth){
        this.user_id = user_id;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birth = birth;
    }
}

