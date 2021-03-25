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

public class achieve_update extends AppCompatActivity {

    EditText e1, e2, e3, e4, e5;
    Spinner s1,s2;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
    myDbAdapter helper;
    String mid;
    int Id;
    String[] ach;
    String[] achd;
    String []place={"FIRST","SECOND","THIRD"};
    String plcs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve_update);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e1.setKeyListener(null);
        e2.setKeyListener(null);
        global g=global.getInstance();
        final String s=g.getData();
        s1=(Spinner)findViewById(R.id.spinner1);
        s2=(Spinner)findViewById(R.id.spinner2);
        helper = new myDbAdapter(this);
        getSupportActionBar().setTitle("Achievement Details update");

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,place);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s2.setAdapter(aa);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                plcs=place[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ach=helper.achupdate();
        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,ach);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=ach[position];
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
                    achd=helper.achupdate2(mid,Id);
                    if(achd!=null)
                    {
                        String cs=helper.ar2[1];
                        e1.setText(""+s);
                        e2.setText(""+helper.ar2[1]);
                        e3.setText(""+helper.ar2[2]);
                        e4.setText(""+helper.ar2[3]);
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
    public void aupdate(View view)
    {
        valid.addValidation(this, R.id.editText2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText3, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText4, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText5, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        String mid,des,cat,lvl,plc,hnr;
        mid=e1.getText().toString();
        des=e2.getText().toString();
        cat=e3.getText().toString();
        lvl=e4.getText().toString();
        //plc=e5.getText().toString();
        hnr=e5.getText().toString();
        plc=plcs;
        if(mid.isEmpty()||des.isEmpty()||cat.isEmpty()||lvl.isEmpty()||hnr.isEmpty())
        {
           // Message.message(getApplicationContext(),"Enter Mandatory Fields");
            new SweetAlertDialog(achieve_update.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error...")
                    .setContentText("Enter Mandatory Fields")
                    .setConfirmText("OK")
                    .show();
        }
        else if(valid.validate())
        {
            long id4=helper.achupdate3(mid,des,cat,lvl,plc,hnr);
            if(id4<=0)
            {
               // Message.message(getApplicationContext(),"Member Account is Not Exist");
                SweetAlertDialog pDialog = new SweetAlertDialog(achieve_update.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Member Account Not Exist");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(achieve_update.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }
            else
            {
                SweetAlertDialog pDialog = new SweetAlertDialog(achieve_update.this,SweetAlertDialog.SUCCESS_TYPE);
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

                        Intent i1=new Intent(achieve_update.this,MainActivity.class);
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
