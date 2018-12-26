package com.example.dcl.qrcodescanner.Utils;

import com.example.dcl.qrcodescanner.QRserver.QRapi;
import com.example.dcl.qrcodescanner.QRserver.RetrofitClient;

public class Common {

    public static final  String BASE_URL="http://fakemedicinedetection.gem4tech.com/api/";
    public static QRapi getApi(){
        return RetrofitClient.getClient(BASE_URL).create(QRapi.class);
    }

}
