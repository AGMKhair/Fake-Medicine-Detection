package com.example.dcl.qrcodescanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.qrcodescanner.Model.Check;
import com.example.dcl.qrcodescanner.Model.LastSellDate;
import com.example.dcl.qrcodescanner.Model.QR;
import com.example.dcl.qrcodescanner.QRserver.QRapi;
import com.example.dcl.qrcodescanner.Utils.Common;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Button scaner_button,result_scanner;
    TextView scanner_output;
    Activity activity;
    int COMPAYER_BARCODE_TYPE;

    int dialog_status;

    QRapi qRapi;
     String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scaner_button = findViewById(R.id.Button_Scaner);
        result_scanner = findViewById(R.id.button_result_show);
        //scanner_output = findViewById(R.id.TextView_Scanner);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        qRapi= Common.getApi();

         activity = this;

        result_scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COMPAYER_BARCODE_TYPE = 0;
                scaning();
            }
        });

        scaner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COMPAYER_BARCODE_TYPE = 1;
                scaning();
            }
        });

    }

    private void scaning()
    {
        IntentIntegrator intentIntegrator =new IntentIntegrator(activity);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt(" Scaning.... ");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(false);
        intentIntegrator.initiateScan();
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);

        if(result != null)
        {


            if (result.getContents() == null)
            {

            }
            else
                {
                    if(COMPAYER_BARCODE_TYPE == 0) {

                      //  Toast.makeText(getApplicationContext(), " Rssult " , Toast.LENGTH_LONG).show();

                        qRapi.getAlldata(result.getContents(),new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime())).enqueue(new Callback<QR>() {
                            @Override
                            public void onResponse(Call<QR> call, Response<QR> response) {
                                QR qr=response.body();

                                if (qr.getError_msg().equals("nodata")){

                                    AlertDialog.Builder  alertdialog = new AlertDialog.Builder(MainActivity.this);
                                    alertdialog.setTitle(" Add Message ");
                                    alertdialog.setCancelable(false);
                                    alertdialog.setMessage("This product is not avilable");
                                    alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alertdialog.show();
                                    //Toast.makeText(getApplicationContext(),"This product is not avilable",Toast.LENGTH_LONG).show();
                                }else {
                                //    Toast.makeText(getApplicationContext(), " test  " , Toast.LENGTH_LONG).show();
                                    if (qr.getProductStatus().equals("0")){
                                  //      Toast.makeText(getApplicationContext(), " test 2 " , Toast.LENGTH_LONG).show();
                                        showRetrivedataintothedialog(qr.getProductName(),qr.getCompanyName(),qr.getCountryName(),qr.getExpdate(),qr.getManufacturedate(),qr.getSellDate(),1);
                                    }
                                    else if(qr.getProductStatus().equals("1")){
                                        dialog_status=0;
                                        showRetrivedataintothedialog(qr.getProductName(),qr.getCompanyName(),qr.getCountryName(),qr.getExpdate(),qr.getManufacturedate(),qr.getSellDate(),0);
                                        //Toast.makeText(getApplicationContext(),""+qr.getCountryName()+" +"+"All ready sell",Toast.LENGTH_LONG).show();
                                    }


                                }


                            }

                            @Override
                            public void onFailure(Call<QR> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Failed " +t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });



                    }else if(COMPAYER_BARCODE_TYPE == 1) {

                        qRapi.checkcode(result.getContents()).enqueue(new Callback<Check>() {
                            @Override
                            public void onResponse(Call<Check> call, Response<Check> response) {


/*
                            if(COMPAYER_BARCODE_TYPE == 1)
                            {*/
                                Check check = response.body();

                                if (check.getDatacheck().equals("ok")) {

                                    AlertDialog.Builder  alertdialog = new AlertDialog.Builder(MainActivity.this);
                                    alertdialog.setTitle(" Add Message ");
                                    alertdialog.setCancelable(false);
                                    alertdialog.setMessage("Already have the code in database");
                                    alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alertdialog.show();

                                    //Toast.makeText(getApplicationContext(), "Already have the code in database", Toast.LENGTH_LONG).show();


                                } else {
                                    Intent in = new Intent(MainActivity.this, AddProductInfo.class);
                                    in.putExtra("qrcode", result.getContents().toString());
                                    startActivity(in);
                                }

                           /* }
                            else if(COMPAYER_BARCODE_TYPE == 0)
                            {*/

//                            }
                                //Toast.makeText(getApplicationContext(),""+check.getDatacheck(),Toast.LENGTH_LONG).show();
                            }
                            @Override
                            public void onFailure(Call<Check> call, Throwable t) {
                                Toast.makeText(getApplicationContext(), "Problem :===" + t.getMessage(), Toast.LENGTH_LONG).show();

                            }
                        });

                  /*  Intent in=new Intent(MainActivity.this,AddProductInfo.class);
                    in.putExtra("qrcode",result.getContents().toString());
                    startActivity(in);*/


                    }
                }
        }else{
            super.onActivityResult(requestCode,resultCode,data);
        }

    }

    private void showRetrivedataintothedialog(String productName, String companyName, String countryName, String expdate, String manufacturedate, String sellDate,int check) {

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(this);

        View v;
        String msg = null;

        final TextView message_show, product_name,product_name2,product_mfg_date,product_exp_date,product_sell_date,product_sell_date2,product_material,product_country_Name,product_company_name;


        if (check == 1)
        {
            msg = "Now You Sell This Product";

        }else if (check == 0)
        {
            msg = "Already Sell This Product";
        }
            v = LayoutInflater.from(this).inflate(R.layout.product_new_selles,null);
            alertdialog.setView(v);
            alertdialog.setCancelable(false);
            message_show = v.findViewById(R.id.message_show_textview);
            product_name = v.findViewById(R.id.product_name_show);
            product_company_name=v.findViewById(R.id.product_company_show);
            product_country_Name=v.findViewById(R.id.product_country_show);
            product_mfg_date = v.findViewById(R.id.product_Mfg_show);
            product_exp_date= v.findViewById(R.id.product_Exp_show);
            product_sell_date = v.findViewById(R.id.product_selles_show);
           // product_material = v.findViewById(R.id.product_material_show);

            message_show.setText(msg);
            product_name.setText(productName);
            product_company_name.setText(companyName);
            product_country_Name.setText(countryName);
            product_sell_date.setText(new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()));
            product_mfg_date.setText(manufacturedate);
            product_exp_date.setText(expdate);


           // Toast.makeText(getApplicationContext(),"0  "+product_name.getText().toString(),Toast.LENGTH_LONG).show();
            //product_material.setText(material);




        /*    v = LayoutInflater.from(this).inflate(R.layout.product_old_selles,null);
            alertdialog.setView(v);
            alertdialog.setCancelable(false);
            product_name2 = v.findViewById(R.id.product_name_show_2);
            product_sell_date2 = v.findViewById(R.id.product_selles_show_2);

            product_name2.setText(productName);
            product_sell_date2.setText(sellDate);

            Toast.makeText(getApplicationContext(),"1  "+product_name2.getText().toString(),Toast.LENGTH_LONG).show();

*/

        alertdialog.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertdialog.show();

    }

}
