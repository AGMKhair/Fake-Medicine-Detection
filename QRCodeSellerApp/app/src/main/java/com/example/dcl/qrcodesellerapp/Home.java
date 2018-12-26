package com.example.dcl.qrcodesellerapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.qrcodesellerapp.Model.QR;
import com.example.dcl.qrcodesellerapp.QRserver.QRapi;
import com.example.dcl.qrcodesellerapp.Utils.Common;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.dcl.qrcodesellerapp.R.id.button;

public class Home extends AppCompatActivity {

    Button scaner_button,chack_button,About;
    TextView scanner_output;
    QRapi qRapi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        qRapi= Common.getApi();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        chack_button = findViewById(R.id.button2);
        About = findViewById(R.id.button);
        scaner_button = findViewById(R.id.Button_Scaner);
        //scanner_output = findViewById(R.id.TextView_Scanner);
        final Activity activity = this;
        scaner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator =new IntentIntegrator(activity);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setPrompt("Scan");
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.initiateScan();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result != null) {
            if (result.getContents() == null)
            {
                //Toast.makeText(this, "You cancelled the scanning", Toast.LENGTH_LONG).show();
              //  scanner_output.setText(" Scan Output ");
            }
            else
            {
              final String code=result.getContents().toString();

                qRapi.getAlldata(code).enqueue(new Callback<QR>() {
                    @Override
                    public void onResponse(Call<QR> call, Response<QR> response) {
                        final QR qr=response.body();


                        if (qr.getError_msg().equals("have")){
                          //  scanner_output.setText(qr.getProductName());

                            String timeStamp = new SimpleDateFormat("yyyy_MM_dd").format(Calendar.getInstance().getTime());
                            qRapi.updateDate(code,timeStamp).enqueue(new Callback<String>() {
                                @Override
                                public void onResponse(Call<String> call, Response<String> response) {

                                }

                                @Override
                                public void onFailure(Call<String> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(),"data found"+t.getMessage(),Toast.LENGTH_LONG).show();

                                }
                            });

                            Toast.makeText(getApplicationContext(),"data found"+qr.getProductName(),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"data found"+qr.getEmpDate(),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"data found"+qr.getExpDate(),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"data found"+qr.getMaterial(),Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),"data found"+qr.getSellDate(),Toast.LENGTH_LONG).show();
                         //   Toast.makeText(getApplicationContext(),"data found"+timeStamp,Toast.LENGTH_LONG).show();


                        }else if (qr.getError_msg().equals("Already sell")){
                            Toast.makeText(getApplicationContext(),"Already checked Ok",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"This code is not avilable in databse",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<QR> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Filed"+t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });


            }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

}