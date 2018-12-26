package com.example.dcl.qrcodesellerapp.Utils;

import com.example.dcl.qrcodesellerapp.QRserver.QRapi;
import com.example.dcl.qrcodesellerapp.QRserver.RetrofitClient;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Common {
    public static final  String BASE_URL="http://192.168.0.105/QR/";

    public static QRapi getApi(){
        return RetrofitClient.getClient(BASE_URL).create(QRapi.class);
    }



}
