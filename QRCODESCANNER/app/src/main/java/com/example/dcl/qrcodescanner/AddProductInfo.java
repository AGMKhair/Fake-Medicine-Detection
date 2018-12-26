package com.example.dcl.qrcodescanner;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dcl.qrcodescanner.Model.Check;
import com.example.dcl.qrcodescanner.QRAdeper.ListViewItemCheckboxBaseAdapter;
import com.example.dcl.qrcodescanner.QRserver.QRapi;
import com.example.dcl.qrcodescanner.Utils.Common;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProductInfo extends AppCompatActivity {
    public  static  EditText edt_name,add_metarial;
    public static   Button save;
    public static   Button add_emp_date;
    public static  Button add_exp_date;
    public static   String qrcode;
    public static String empdate,expdate;

    public static TextView textView;

    public QRapi qRapi;

    public  List<String> selected_item=new ArrayList<>();

   public static TextView show_empdate,show_expdate,show_material,contry_show,company_show;


   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product_info);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        qRapi= Common.getApi();

        textView = findViewById(R.id.bar_Code);
        show_empdate=findViewById(R.id.show_emp_date);
        show_expdate=findViewById(R.id.show_exp_date);

        qrcode=getIntent().getStringExtra("qrcode");

        empdate=getIntent().getStringExtra("empdata");

        edt_name=findViewById(R.id.product_name);

        add_metarial=findViewById(R.id.add_material);

        add_emp_date=findViewById(R.id.add_empdate);

        add_exp_date=findViewById(R.id.add_exp_date);
       // show_material=findViewById(R.id.show_material);

       contry_show = findViewById(R.id.country_show);
       company_show = findViewById(R.id.company_show);


        save = findViewById(R.id.save);

        int count = 3;

        String country = qrcode.substring(0,count);
        final String company = qrcode.substring(3,6);

        System.out.println(company);
        System.out.println(country);

        textView.setText(qrcode);
        String[] countryName = {"Indea", "Buttan","Bangladesh", "chaina","Indea", "Buttan", "chaina"};
        final String[] companyName = {"","A. H. Janakalyan Pharmaceuticals (waqf) Bangladesh.",
                "Ablation Pharmaceuticals Ltd.",
                "Acme Laboratories Ltd.",
                "Ad-din pharmaceuticals Ltd.",
                "Aexim Pharmaceutical Ltd.",
                "Al-Amin Laboratories Ltd.",
                "Albert David (BD) Ltd.",
                "Albion Laboratories Ltd.",
                "Alco Pharma Ltd",
                "Ambee Pharmaceuticals Ltd.",
                "Apcco Ltd."};

        int i,j;

        for( i= 0;i <9;i++)
        {

            int k = Integer.valueOf(String.valueOf(57) + String.valueOf(i));

            if(Integer.parseInt(country) == k )
            {
                contry_show.setText(countryName[i]);
                break;
            }
        }

        if(i==9)
        {
            Toast.makeText(this,"Country Code not Match",Toast.LENGTH_SHORT).show();
        }else
        {
            for( j= 1;j <9;j++)
            {

                int k = Integer.valueOf(String.valueOf(24) + String.valueOf(j));

                if( Integer.parseInt(company) == k)
                {
                    company_show.setText(companyName[j]);
                    break;
                }
            }

            if(j==9)
            {
                Toast.makeText(this,"Company Code not Match",Toast.LENGTH_SHORT).show();

            }
        }









        add_metarial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert=new AlertDialog.Builder(AddProductInfo.this);

                View v= LayoutInflater.from(getApplicationContext()).inflate(R.layout.add_element_dialog,null);
                alert.setView(v);


                final ListView listViewWithCheckbox = (ListView)v.findViewById(R.id.list_view_with_checkbox);
                final List<ListViewItemDTO> initItemList = getInitViewItemDtoList();

                final ListViewItemCheckboxBaseAdapter listViewDataAdapter = new ListViewItemCheckboxBaseAdapter(getApplicationContext(), initItemList);
                listViewWithCheckbox.setAdapter(listViewDataAdapter);


                listViewWithCheckbox.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int itemIndex, long l) {
                        // Get user selected item.
                        Object itemObject = adapterView.getAdapter().getItem(itemIndex);

                        // Translate the selected item to DTO object.
                        ListViewItemDTO itemDto = (ListViewItemDTO)itemObject;

                        // Get the checkbox.
                        CheckBox itemCheckbox = (CheckBox) view.findViewById(R.id.list_view_item_checkbox);

                        // Reverse the checkbox and clicked item check state.
                        if(itemDto.isChecked())
                        {
                            itemCheckbox.setChecked(false);
                            itemDto.setChecked(false);
                        }else
                        {
                            itemCheckbox.setChecked(true);
                            itemDto.setChecked(true);

                            selected_item.add(((ListViewItemDTO) itemObject).getItemText().toString());

                        }

                    }
                });


                Button selectAllButton = (Button)v.findViewById(R.id.check_all);
                selectAllButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int size = initItemList.size();
                        selected_item.clear();
                        for(int i=0;i<size;i++)
                        {
                            ListViewItemDTO dto = initItemList.get(i);
                            dto.setChecked(true);
                            selected_item.add(dto.getItemText().toString());

                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                Button selectNoneButton = (Button)v.findViewById(R.id.remove_check);
                selectNoneButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int size = initItemList.size();
                        for(int i=0;i<size;i++)
                        {
                            ListViewItemDTO dto = initItemList.get(i);
                            dto.setChecked(false);
                        }

                        listViewDataAdapter.notifyDataSetChanged();
                    }
                });

                alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       // Toast.makeText(getApplicationContext(),"Item "+selected_item,Toast.LENGTH_LONG).show();
                        if (selected_item.size()>0) {
                            add_metarial.setVisibility(View.GONE);
                            //show_material.setVisibility(View.VISIBLE);
                            //show_material.setText("Item Silected");
                        }else {

                            AlertDialog.Builder  alertdialog = new AlertDialog.Builder(AddProductInfo.this);
                            alertdialog.setTitle("Error Message");
                            alertdialog.setCancelable(false);
                            alertdialog.setMessage("Please select item");
                            alertdialog.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                            alertdialog.show();

                            //Toast.makeText(getApplicationContext(),"Please select item",Toast.LENGTH_LONG).show();
                        }

                    }
                });

                alert.show();
            }


        });



        add_emp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new MyDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "date picker");
            }
        });
        add_exp_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogFragment newFragment = new MyDatePickerFragment2();
                newFragment.show(getSupportFragmentManager(), "date picker");

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final StringBuilder stringBuilder=new StringBuilder();
                for (int i=0;i<selected_item.size()-1;i++){
                    stringBuilder.append(selected_item.get(i)).append(",").toString();
                }



                if (!TextUtils.isEmpty(edt_name.getText().toString())
                        ||!TextUtils.isEmpty(show_empdate.getText().toString())
                        ||!TextUtils.isEmpty(show_expdate.getText().toString())||qrcode!=null
                        ){


                    qRapi.checkcode(qrcode).enqueue(new Callback<Check>() {
                        @Override
                        public void onResponse(Call<Check> call, Response<Check> response) {

                            Check check=response.body();

                            if (check.getDatacheck().equals("ok")){

                                AlertDialog.Builder  alertdialog = new AlertDialog.Builder(AddProductInfo.this);
                                alertdialog.setTitle(" Add Message ");
                                alertdialog.setMessage("Already have the code in database");
                                alertdialog.setCancelable(false);
                                alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                alertdialog.show();
                                //Toast.makeText(getApplicationContext(),"Already have the code in database",Toast.LENGTH_LONG).show();

                            }else {

                                /*@Field("code") String qrcode,
                             @Field("name") String productname,
                             @Field("description") String productelement,add_material
                             @Field("manufacturedate") String empdate,
                             @Field("expdate") String expdate,


                             @Field("productstatus") int productstats,
                             @Field("companyName") String companyName,
                             @Field("countryName") String countryName,
                             @Field("sellDate") String sellDate*/

                                qRapi.insertdata(qrcode,edt_name.getText().toString(),add_metarial.getText().toString(),show_empdate.getText().toString(),show_expdate.getText().toString(),0,company_show.getText().toString(),contry_show.getText().toString(),"00:00:00").enqueue(new Callback<String>() {
                                    @Override
                                    public void onResponse(Call<String> call, Response<String> response) {

                                        AlertDialog.Builder  alertdialog = new AlertDialog.Builder(AddProductInfo.this);
                                        alertdialog.setTitle(" Add Message ");
                                        alertdialog.setMessage(" Indert Data Successfully ");
                                        alertdialog.setCancelable(false);
                                        alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });
                                        alertdialog.show();
                                        //  Toast.makeText(getApplicationContext(),"Result :="+response.body(),Toast.LENGTH_LONG).show();
                                    }

                                    @Override
                                    public void onFailure(Call<String> call, Throwable t) {

                                       /* AlertDialog.Builder  alertdialog = new AlertDialog.Builder(AddProductInfo.this);
                                        alertdialog.setTitle(" Error Message.. ");

                                        alertdialog.setMessage(t.getMessage());
                                        alertdialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });
                                        alertdialog.show();*/
                                       // Toast.makeText(getApplicationContext(),""+t.getMessage(),Toast.LENGTH_LONG).show();

                                        AlertDialog.Builder  alertdialog = new AlertDialog.Builder(AddProductInfo.this);
                                        alertdialog.setTitle(" Add Message ");
                                        alertdialog.setMessage(" Indert Data Successfully ");
                                        alertdialog.setCancelable(false);
                                        alertdialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                finish();
                                            }
                                        });
                                        alertdialog.show();

                                    }
                                });
                            }

                        }

                        @Override
                        public void onFailure(Call<Check> call, Throwable t) {

                        }
                    });






                }else {


                    //Toast.makeText(getApplicationContext(),"Empty ",Toast.LENGTH_LONG).show();
                }
                //startActivity(new Intent(AddProductInfo.this,MainActivity.class));

            }
        });





    }






    private List<ListViewItemDTO> getInitViewItemDtoList()
    {
        String itemTextArr[] = {"Sulfuric acid", "Magnesium", "Phosphorous", "Barium", "Fluorine",
                "Sodium chloride", "Match heads", "Metal alloys", "Sulfur", "Medicine"};

        List<ListViewItemDTO> ret = new ArrayList<ListViewItemDTO>();

        int length = itemTextArr.length;

        for(int i=0;i<length;i++)
        {
            String itemText = itemTextArr[i];

            ListViewItemDTO dto = new ListViewItemDTO();
            dto.setChecked(false);
            dto.setItemText(itemText);

            ret.add(dto);
        }

        return ret;
    }
}
