package com.example.dcl.qrcodesellerapp.QRserver;

import com.example.dcl.qrcodesellerapp.Model.QR;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface QRapi {

    @FormUrlEncoded
    @POST("retrivedata.php")
    Call<QR> getAlldata(@Field("code") String qrcode);

    @FormUrlEncoded
    @POST("updatedate.php")
    Call<String> updateDate(@Field("code") String code,@Field("date") String date);



}
