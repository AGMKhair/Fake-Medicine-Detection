package com.example.dcl.qrcodescanner.QRserver;

import com.example.dcl.qrcodescanner.Model.Check;
import com.example.dcl.qrcodescanner.Model.LastSellDate;
import com.example.dcl.qrcodescanner.Model.QR;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface QRapi {
    @FormUrlEncoded
    @POST("insertdata.php")

    /* isset($_POST['code'])&&
       isset($_POST['name'])&&
	   isset($_POST['description'])&&
	   isset($_POST['manufacturedate'])&&
	   isset($_POST['expdate'])&&
	   isset($_POST['productstatus'])&&
	   isset($_POST['companyName'])&&
	   isset($_POST['countryName'])&&
	   isset($_POST['sellDate']*/
    Call<String>insertdata(@Field("code") String qrcode,
                             @Field("name") String productname,
                             @Field("description") String productelement,
                             @Field("manufacturedate") String empdate,
                             @Field("expdate") String expdate,
                             @Field("productstatus") int productstats,
                             @Field("companyName") String companyName,
                             @Field("countryName") String countryName,
                             @Field("sellDate") String sellDate

                             );

    @FormUrlEncoded
    @POST("checkcode.php")
    Call<Check>checkcode(@Field("Barcode") String qrcode);

    @FormUrlEncoded
    @POST("retrivedata.php")
    Call<QR>getAlldata(@Field("barcode") String qrcode,@Field("date") String date);

    @FormUrlEncoded
    @POST("updateDate.php")
    Call<String> updateDate(@Field("code") String code,@Field("date") String date);

    @FormUrlEncoded
    @POST("retriveLastSellDate.php")
    Call<LastSellDate> getLastSellDate(@Field("code") String code);



  /*  isset($_POST['code'])&&
    isset($_POST['name'])&&
    isset($_POST['elemet'])&&
    isset($_POST['empdate'])&&
    isset($_POST['expdate'])&&
    isset($_POST['productstatus']
    */

}
