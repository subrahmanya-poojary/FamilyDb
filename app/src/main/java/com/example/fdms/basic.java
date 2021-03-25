package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class basic extends AppCompatActivity {

    String[] zodiac={"Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagittarius","Capricorn","Aquarius","Pisces"};
    private int mYear, mMonth, mDay, mHour, mMinute;
    EditText e1, e2, e3, e4, e5, e6, e7,e8,e9;
    Spinner s1;
    //public String zs;
    myDbAdapter helper;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
   // public String[] bArr;
    login l;
    public String s;
    String zod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);

        helper=new myDbAdapter(this);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        e7 = (EditText) findViewById(R.id.editText7);
        e8=(EditText)findViewById(R.id.editText8);
        e9=(EditText)findViewById(R.id.editText9);
        s1=(Spinner)findViewById(R.id.spinner1);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        e3.setKeyListener(null);
        e4.setKeyListener(null);
        getSupportActionBar().setTitle("Basic Details");
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,zodiac);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(aa);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                zod=zodiac[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void date(View view) {
        if (view == e3) {

            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            e3.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }
    public void time(View view)
    {
        if(view==e4){

            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            e4.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();

        }
    }

    String bid,bname,bdate,btime,bplace,f_name,m_name,sign;
    public void add(View view)
    {
        try {
            //valid.addValidation(this, R.id.editText1, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText5, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText6, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText7, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
            valid.addValidation(this, R.id.editText8, Patterns.PHONE, R.string.invalidname);
            valid.addValidation(this, R.id.editText9, Patterns.EMAIL_ADDRESS, R.string.invalidemail);
                String mid, bname, bdate, btime, bplace, f_name, m_name, phno, bemail,sign;
                mid = e1.getText().toString();
                bname = e2.getText().toString();
                bdate = e3.getText().toString();
                btime = e4.getText().toString();
                bplace = e5.getText().toString();
                m_name = e6.getText().toString();
                f_name = e7.getText().toString();
                phno = e8.getText().toString();
                bemail = e9.getText().toString();
                sign=zod;
                if (mid.isEmpty() || bname.isEmpty() || bdate.isEmpty() || btime.isEmpty() || bplace.isEmpty() || m_name.isEmpty() || f_name.isEmpty() || phno.isEmpty() || bemail.isEmpty()) {
                    //Message.message(getApplicationContext(),"Enter Mandatory Fields");
                    new SweetAlertDialog(basic.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error...")
                            .setContentText("Enter Mandatory Fields")
                            .setConfirmText("OK")
                            .show();
                } else if(valid.validate()) {
                    long id2 = helper.bsave(mid, bname, bdate, btime, bplace, m_name, f_name, phno, bemail,sign);
                    if (id2 <= 0) {
                        //Message.message(getApplicationContext(),"Member Account is Already Exist");
                   /* new SweetAlertDialog(basic.this).setTitleText("Member Account Exist")
                            .setConfirmText("OK")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent i1=new Intent(basic.this,MainActivity.class);
                                    startActivity(i1);
                                    finish();
                                }
                            }).show();*/
                        SweetAlertDialog pDialog = new SweetAlertDialog(basic.this, SweetAlertDialog.WARNING_TYPE);
                        pDialog.setTitleText("Warning..!");
                        pDialog.setContentText("Member Account Already Exist");
                        pDialog.setConfirmText("OK");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i1 = new Intent(basic.this, MainActivity.class);
                                startActivity(i1);
                                finish();
                            }
                        });
                        pDialog.setCancelable(false);
                        pDialog.show();


                    } else {
                        //Message.message(getApplicationContext(),"Member Information Saved");
                        SweetAlertDialog pDialog = new SweetAlertDialog(basic.this, SweetAlertDialog.SUCCESS_TYPE);
                        pDialog.setTitleText("Success");
                        pDialog.setContentText("Member Information Stored");
                        pDialog.setConfirmText("OK");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i1 = new Intent(basic.this, MainActivity.class);
                                startActivity(i1);
                                finish();
                            }
                        });
                        pDialog.setCancelable(false);
                        pDialog.show();
                        e1.setText("");
                        e2.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");
                        e7.setText("");
                        e8.setText("");
                        e9.setText("");
                        // Intent i1=new Intent(this,MainActivity.class);
                        //startActivity(i1);

                    }
                }
            }catch(Exception e)
            {
                Message.message(getApplicationContext(), "" + e);
            }
    }
    public void move(View view)
    {
        Intent i1=new Intent(this,basic_delete.class);
        startActivity(i1);
        finish();
    }
   /* public void update(View view)
    {
        //Intent i2=new Intent(this,basic_update.class);
        //startActivity(i2);
        try
        {

           Intent i2=new Intent(this,basic_update.class);
           startActivity(i2);
           finish();
        }catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }
    }
*/
}