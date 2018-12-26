package com.example.dcl.qrcodescanner;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MyDatePickerFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), dateSetListener, year, month, day);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int month, int day) {
                   /* Toast.makeText(getActivity(), "selected date is " + view.getYear() +
                            " / " + (view.getMonth()+1) +
                            " / " + view.getDayOfMonth(), Toast.LENGTH_SHORT).show();*/

                   if (year!=0||month!=0||day != 0){

                       StringBuilder stringBuilder=new StringBuilder();
                       stringBuilder.append(year+"-").append(month+"-").append(day+"").toString();


                       AddProductInfo.add_emp_date.setVisibility(View.GONE);
                       AddProductInfo.show_empdate.setVisibility(View.VISIBLE);
                       AddProductInfo.show_empdate.setText(stringBuilder.toString());


                   }

                }
            };


}