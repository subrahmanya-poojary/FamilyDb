package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class assets_update extends AppCompatActivity {
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
    EditText e1, e2, e3, e4, e5;
    Spinner s1;
    myDbAdapter helper;
    String mid;
    int Id;
    public String[] ast;
    public String[] ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets_update);
        global g=global.getInstance();
        final String s=g.getData();

        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        s1=(Spinner)findViewById(R.id.spinner1);
        e1.setKeyListener(null);
        e3.setKeyListener(null);
        getSupportActionBar().setTitle("Assets Details Update");
        helper = new myDbAdapter(this);
        ast=helper.astupdate();
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ast);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=ast[position];
                Id=s1.getSelectedItemPosition();
                //Message.message(getApplicationContext(),""+Id);
                if(Id==0)
                {
                    Message.message(getApplicationContext(),"Select MemID");
                    e1.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                }
                else
                {
                    e1.setVisibility(View.VISIBLE);
                    e2.setVisibility(View.VISIBLE);
                    e3.setVisibility(View.VISIBLE);
                    e4.setVisibility(View.VISIBLE);
                    e5.setVisibility(View.VISIBLE);
                    ad=helper.astupdate2(mid,Id);
                    if(ad!=null)
                    {
                        String cs=helper.ar2[1];
                        e1.setText(""+s);
                        e2.setText(""+helper.ar2[1]);
                        e3.setText(""+helper.ar2[2]);
                        e4.setText(""+helper.ar2[3]);
                        e5.setText(""+helper.ar2[4]);

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
        valid.addValidation(this, R.id.editText2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        // valid.addValidation(this, R.id.editText3, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText4, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText5, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        String mid,ades,rtc,area,type;
        mid=e1.getText().toString();
        ades=e2.getText().toString();
        rtc=e3.getText().toString();
        area=e4.getText().toString();
        type=e5.getText().toString();

        if(mid.isEmpty()||ades.isEmpty()||rtc.isEmpty()||area.isEmpty()||type.isEmpty())
        {
            //Message.message(getApplicationContext(),"Enter all Mandatory Fields");
            new SweetAlertDialog(assets_update.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error...")
                    .setContentText("Enter Mandatory Fields")
                    .setConfirmText("OK")
                    .show();
        }
        else if(valid.validate())
        {
            long id5=helper.astupdate3(mid,ades,rtc,area,type);
            if(id5<=0)
            {
                //Message.message(getApplicationContext(),"Member Account is Not Exist");
                SweetAlertDialog pDialog = new SweetAlertDialog(assets_update.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Member Account Not Exist");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(assets_update.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }
            else
            {
                SweetAlertDialog pDialog = new SweetAlertDialog(assets_update.this,SweetAlertDialog.SUCCESS_TYPE);
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

                        Intent i1=new Intent(assets_update.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
                /*Message.message(getApplicationContext(),"Member Information is Saved");
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
}
