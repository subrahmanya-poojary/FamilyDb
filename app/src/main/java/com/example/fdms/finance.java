package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class finance extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
    String[] bank = {"Savings Account","Fixed Account","Loan"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText e1, e2, e3, e4, e5;
    Spinner spin;
    public String bk;
    myDbAdapter helper;
    public String r1;
    public String r2;
    public DatePickerDialog datePickerDialog;
    //public Date d1;
    //public String dss;
    public String days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);
        global g=global.getInstance();
        String s=g.getData();
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        Spinner spin = (Spinner) findViewById(R.id.spinner2);
        spin.setOnItemSelectedListener(this);
        e1.setText(""+s);
        helper=new myDbAdapter(this);
        e1.setKeyListener(null);
        e5.setKeyListener(null);
        getSupportActionBar().setTitle("Financial Details");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bank);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        bk = bank[position].toString();
        if(bk.equals("Loan"))
        {
            e5.setHint("Enter Loan  Tenure");
        }
        else
        {
            e5.setHint("Enter deposite date");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Message.message(getApplicationContext(), "select");
    }
    public void acDate(View view)
    {
        if (view == e5) {


            /*SimpleDateFormat dd = new SimpleDateFormat("mm-dd-yyyy");
            dss = dd.format(c);
            d1=null;
           // d1=dd.parse(dss);*/
            Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
            datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            String mDay2,mMonth2;
                            mDay2=String.valueOf(dayOfMonth);
                            mMonth2=String.valueOf(monthOfYear+1);
                            if((mDay2.length()==1)&&(mMonth2.length()==1))
                            {
                                e5.setText("0"+dayOfMonth + "-" +"0"+(monthOfYear + 1) + "-" + year);
                            }
                            else if(mDay2.length()==1)
                            {
                                e5.setText("0"+dayOfMonth + "-" +(monthOfYear + 1) + "-" + year);
                            }
                            else if(mMonth2.length()==1)
                            {
                                e5.setText(dayOfMonth + "-" +"0"+(monthOfYear + 1) + "-" + year);
                            }
                            //Message.message(getApplicationContext(),""+mMonth2.length());
                        }
                    }, mYear, mMonth, mDay);
            if(bk.equals("Loan"))
            {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }
            else if(bk.equals("Fixed Account"))
            {
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            }
            else
            {
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            }
            datePickerDialog.show();
        }
    }
    public void Finance(View view)
    {
        try {
            valid.addValidation(this, R.id.editText3, Patterns.PHONE, R.string.invalidname);
            valid.addValidation(this, R.id.editText4, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.invalidname);
            valid.addValidation(this, R.id.editText5, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText6, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            /*r1=e5.getText().toString();
            SimpleDateFormat dd = new SimpleDateFormat("mm-dd-yyyy");
            r2 = dd.format(r1);*/
            String mid, acc_no, acc_type, amt, interest, date;
            mid = e1.getText().toString();
            acc_no = e2.getText().toString();
            acc_type = bk;
            amt = e3.getText().toString();
            interest = e4.getText().toString();
            date=e5.getText().toString();
            if (mid.isEmpty() || acc_no.isEmpty() || acc_type.isEmpty() || amt.isEmpty() || interest.isEmpty() || date.isEmpty()) {
                //Message.message(getApplicationContext(), "Enter Mandatory Fields");
                new SweetAlertDialog(finance.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error...")
                        .setContentText("Enter Mandatory Fields")
                        .setConfirmText("OK")
                        .show();
            } else {
                /*String mDay2;
                mDay2=String.valueOf(mDay);
                if(mDay2.length()==1)
                {
                    mDay2="0"+mDay2;
                }
                String mMonth2;
                mMonth2=String.valueOf(mMonth);
                if(mMonth2.length()==1)
                {
                    mMonth2="0"+mMonth;
                }*/
                long id6 = helper.fsave(mid, acc_no, acc_type, amt, interest, date);
                if (id6 <= 0) {
                    //Message.message(getApplicationContext(), "Member Account is Not Exist");
                    SweetAlertDialog pDialog = new SweetAlertDialog(finance.this,SweetAlertDialog.WARNING_TYPE);
                    pDialog.setTitleText("Warning..!");
                    pDialog.setContentText("Information Not Stored");
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent i1=new Intent(finance.this,MainActivity.class);
                            startActivity(i1);
                            finish();
                        }
                    });
                    pDialog.setCancelable(false);
                    pDialog.show();

                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(finance.this, SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText("Success");
                    pDialog.setContentText("Member Information Stored");
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");
                            e5.setText("");
                            Intent i1 = new Intent(finance.this, MainActivity.class);
                            startActivity(i1);
                            finish();
                        }
                    });
                    pDialog.setCancelable(false);
                    pDialog.show();
                    /*Message.message(getApplicationContext(), "Member Information is Saved");
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    e5.setText("");
                    Intent i1=new Intent(this,MainActivity.class);
                    startActivity(i1);*/
                }
            }
        }
        catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }
    }
    public void move(View view)
    {
        Intent i=new Intent(this,finance_delete.class);
        startActivity(i);
        finish();
    }
    public void upmove(View view)
    {
        Intent i1=new Intent(this,finance_update.class);
        startActivity(i1);
        finish();
    }
}
