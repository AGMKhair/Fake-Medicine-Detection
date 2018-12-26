package com.example.dcl.qrcodescanner.QRserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    public  static Retrofit  retrofit=null;

    public  static  Retrofit getClient(String Baseurl){


      /*  Gson gson = new GsonBuilder()
                .setLenient()
                .create();
*/
      //  OkHttpClient client = new OkHttpClient();


        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(Baseurl)
                  //  .client(client)
                     .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .build();

        }
        return retrofit;
    }

}
