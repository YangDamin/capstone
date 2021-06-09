package com.example.moa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MyAPI {
    @POST("/account/login/")
    Call<loginOutput> Login(
            @Body loginInput login
    );

    @GET("/account/customer_list/")
    Call<Void> check(
            @Query("info") String info,
            @Query("data") String data
    );

    @POST("/account/customer_list/")
    Call<Void> signUp(
            @Body signUpInput signUpInput
    );

    @GET("/coupon/coupon_list/")
    Call<List<storeListOutput>> couponList(
            @Query("customer") String customer
    );

    @GET("/management/cafe_list/")
    Call<List<findCafeOutput>> findCafe(
    );

    @GET("/account/find_id/")
    Call<findIdOutput> Find_ID_OUTPUT(
            @Query("name") String name,
            @Query("phone") String phone
    );

    @GET("/account/find_pw/")
    Call<findPwOutput> Find_PW_OUTPUT(
            @Query("user_id") String name,
            @Query("name") String phone,
            @Query("birth") String birth
    );

    @GET("/account/customer/{user_id}/")
    Call<myPageOutput> MyPage_OUTPUT(
            @Path("user_id") String user_id
    );

    @PUT("/account/customer/{user_id}/")
    Call<Void> Change_PW(
            @Path("user_id") String user_id,
            @Body changePwdInput change
    );

    @PUT("/coupon/present/{send_id}/{get_id}/")
    Call<Void> Gift(
            @Path("send_id") String send_id,
            @Path("get_id") String get_id,
            @Body GiftInput gift
    );
}
