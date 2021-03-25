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

public class academic_update extends AppCompatActivity {
    EditText e1,e3, e4, e5, e6, e7,e8;
    String[] std={"Select Member Class","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","SSLC","1PUC","2PUC","UG-Degree"};
    myDbAdapter helper;
    Spinner s1,s2,s3;
    String []b;
    String cl;
    String []ed;
    int Id;
    String mid;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_update);
        e1 = (EditText) findViewById(R.id.editText1);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        e7 = (EditText) findViewById(R.id.editText7);
        e8=(EditText)findViewById(R.id.editText8);
        s1=(Spinner)findViewById(R.id.spinner1);
        e1.setKeyListener(null);
        e3.setKeyListener(null);
        getSupportActionBar().setTitle("Academic Details Update");

        //s2=(Spinner)findViewById(R.id.spinner2);
        s3=(Spinner)findViewById(R.id.spinner3);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,std);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s3.setAdapter(aa);

        s3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cl=std[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        helper=new myDbAdapter(this);
        global g=global.getInstance();
        final String s=g.getData();
        b=helper.eupdate();

       ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,b);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=b[position];
                Id=s1.getSelectedItemPosition();
                //Message.message(getApplicationContext(),""+Id);
                if(Id==0)
                {
                    Message.message(getApplicationContext(),"Select MemID");
                    e1.setVisibility(View.INVISIBLE);
                    s3.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                    e6.setVisibility(View.INVISIBLE);
                    e7.setVisibility(View.INVISIBLE);
                    e8.setVisibility(View.INVISIBLE);
                }
                else
                {
                    e1.setVisibility(View.VISIBLE);
                    s3.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    e5.setVisibility(View.VISIBLE);
                    e6.setVisibility(View.VISIBLE);
                    e7.setVisibility(View.VISIBLE);
                    e8.setVisibility(View.VISIBLE);
                    ed=helper.eupdate2(mid,Id);
                    if(ed!=null)
                    {
                        String cs=helper.ar2[1];
                        e1.setText(""+s);
                        s3.setPrompt(""+cs);
                        e3.setText(""+helper.ar2[2]);
                        e4.setText(""+helper.ar2[3]);
                        e5.setText(""+helper.ar2[4]);
                        e6.setText(""+helper.ar2[5]);
                        e7.setText(""+helper.ar2[6]);
                        e8.setText(""+helper.ar2[7]);
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
    public void update(View view)
    {
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
        int a=Integer.parseInt(tscr);
        int b=Integer.parseInt(mscr);
        cls=cl;
        if(mid.isEmpty()||cl==std[0]||clg.isEmpty()||board.isEmpty()||regno.isEmpty()||mscr.isEmpty()||tscr.isEmpty()||crs.isEmpty())
        {
            //Message.message(getApplicationContext(),"Enter Mandatory Fields");
            new SweetAlertDialog(academic_update.this, SweetAlertDialog.ERROR_TYPE)
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
            long id3=helper.eupdate3(mid,cls,regno,crs,clg,board,tscr,mscr,Id);
            if(id3<=0)
            {
                //Message.message(getApplicationContext(),"Member Account is not updated");
                SweetAlertDialog pDialog = new SweetAlertDialog(academic_update.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Member Account Not Updated");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(academic_update.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }
            else
            {
               // Message.message(getApplicationContext(),""+Id);
                SweetAlertDialog pDialog = new SweetAlertDialog(academic_update.this,SweetAlertDialog.SUCCESS_TYPE);
                pDialog.setTitleText("Success");
                pDialog.setContentText("Member Information Updated");
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

                        Intent i1=new Intent(academic_update.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();

            }

        }

    }
}
