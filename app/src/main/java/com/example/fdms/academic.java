package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class academic extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    EditText e1, e2, e3, e4, e5, e6, e7, e8;
    myDbAdapter helper;
    Spinner s1;
    String cl;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
    String[] std={"Select Member Class","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","SSLC","1PUC","2PUC","UG-Degree"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        e7 = (EditText) findViewById(R.id.editText7);
        e8 = (EditText) findViewById(R.id.editText8);
        helper=new myDbAdapter(this);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        getSupportActionBar().setTitle("Academic Details");
        s1= (Spinner) findViewById(R.id.spinner1);
        s1.setOnItemSelectedListener(this);

        // basic_update bu=new basic_update();
        //bu.sp.setOnItemSelectedListener(this);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,std);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(aa);


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        cl=std[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Message.message(getApplicationContext(),"select");

    }
    public  void Eadd(View view) {
        valid.addValidation(this, R.id.editText3, Patterns.PHONE, R.string.invalidname);
        valid.addValidation(this, R.id.editText4, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.invalidname);
        valid.addValidation(this, R.id.editText5, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText6, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        String mid,cls,clg,board,regno,mscr,tscr,crs;
        cls=null;
        mid=e1.getText().toString();
        regno=e3.getText().toString();
        crs=e4.getText().toString();
        clg=e5.getText().toString();
        board=e6.getText().toString();
        tscr=e7.getText().toString();
        mscr=e8.getText().toString();
        int a= Integer.parseInt(tscr);
        int b=Integer.parseInt(mscr);
        cls=cl;
        if(mid.isEmpty()||cl==std[0]||clg.isEmpty()||board.isEmpty()||regno.isEmpty()||mscr.isEmpty()||tscr.isEmpty()||crs.isEmpty())
        {
            //Message.message(getApplicationContext(),"Enter Mandatory Fields");
            new SweetAlertDialog(academic.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error...")
                    .setContentText("Enter Mandatory Fields")
                    .setConfirmText("OK")
                    .show();
        }
        else if(a>100||b>100)
        {
            Message.message(getApplicationContext(),"Percentage should between 0-100");
        }
        else if(b>a)
        {
            Message.message(getApplicationContext(),"Scored percentage should less than total percentage");
        }
        else if(valid.validate())
        {
            long id3=helper.esave(mid,cls,regno,crs,clg,board,tscr,mscr);
            if(id3<=0)
            {
               //
                //Message.message(getApplicationContext(),"Member Account is Not Exist");
                SweetAlertDialog pDialog = new SweetAlertDialog(academic.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Information Not Stored");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(academic.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }
            else {
                SweetAlertDialog pDialog = new SweetAlertDialog(academic.this, SweetAlertDialog.SUCCESS_TYPE);
                pDialog.setTitleText("Success");
                pDialog.setContentText("Member Information Stored");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        e1.setText("");
                        e3.setText("");
                        e4.setText("");
                        e5.setText("");
                        e6.setText("");
                        e7.setText("");
                        e8.setText("");
                        Intent i1 = new Intent(academic.this, MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }

        }
    }
    public void move(View view)
    {
        Intent i=new Intent(this,academic_delete.class);
        startActivity(i);
        finish();

    }
}
