package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class finance_update extends AppCompatActivity {

    EditText e1,e2,e3, e4, e5;
    String[] bank = {"Savings Account","Fixed Account","Loan"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    Spinner s1,s2;
    myDbAdapter helper;
    String mid;
    int Id;
    String[] f;
    String[] fd;
    String bk;
    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_update);
        e1 = (EditText) findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        s1=(Spinner)findViewById(R.id.spinner1);
        s2=(Spinner)findViewById(R.id.spinner2);
        e1.setKeyListener(null);
        e2.setKeyListener(null);
        e5.setKeyListener(null);
        getSupportActionBar().setTitle("Financial Details Update");

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bank);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s2.setAdapter(aa);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bk = bank[position].toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        helper=new myDbAdapter(this);
        global g=global.getInstance();
        final String s=g.getData();
        f=helper.fupdate();
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,f);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=f[position];
                Id=s1.getSelectedItemPosition();
                if(Id==0)
                {
                    Message.message(getApplicationContext(),"Select MemID");
                    e1.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                    s2.setVisibility(View.INVISIBLE);
                }
                else
                {
                    e1.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    e5.setVisibility(View.VISIBLE);
                    s2.setVisibility(View.VISIBLE);
                    fd=helper.fupdate2(mid,Id);
                    if(fd!=null)
                    {
                        String cs=helper.ar2[1];
                        e1.setText(""+s);
                        e2.setText(""+helper.ar2[1]);
                        e3.setText(""+helper.ar2[3]);
                        e4.setText(""+helper.ar2[4]);
                        e5.setText(""+helper.ar2[5]);
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"Error");
                    }

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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
    public void update(View view)
    {

        try {
            String mid, acc_no, acc_type, amt, interest, date;

            mid = e1.getText().toString();
            acc_no = e2.getText().toString();
            acc_type = bk;
            amt = e3.getText().toString();
            interest = e4.getText().toString();
            date = e5.getText().toString();

            if (mid.isEmpty() || acc_no.isEmpty() || acc_type.isEmpty() || amt.isEmpty() || interest.isEmpty() || date.isEmpty()) {
                //Message.message(getApplicationContext(), "Enter Mandatory Fields");
                new SweetAlertDialog(finance_update.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error...")
                        .setContentText("Enter Mandatory Fields")
                        .setConfirmText("OK")
                        .show();
            } else {
                long id6 = helper.fupdate3(mid, acc_no, acc_type, amt, interest, date);
                if (id6 <= 0) {
                   // Message.message(getApplicationContext(), "Member Account is Not Exist");
                    SweetAlertDialog pDialog = new SweetAlertDialog(finance_update.this,SweetAlertDialog.WARNING_TYPE);
                    pDialog.setTitleText("Warning..!");
                    pDialog.setContentText("Member Account Not Exist");
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent i1=new Intent(finance_update.this,MainActivity.class);
                            startActivity(i1);
                            finish();
                        }
                    });
                    pDialog.setCancelable(false);
                    pDialog.show();

                } else {
                    SweetAlertDialog pDialog = new SweetAlertDialog(finance_update.this,SweetAlertDialog.SUCCESS_TYPE);
                    pDialog.setTitleText("Success");
                    pDialog.setContentText("Member Information Updated");
                    pDialog.setConfirmText("OK");
                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            e1.setText("");
                            e2.setText("");
                            e3.setText("");
                            e4.setText("");
                            e5.setText("");

                            Intent i1=new Intent(finance_update.this,MainActivity.class);
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
}
