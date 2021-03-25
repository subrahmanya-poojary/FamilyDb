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

public class achieve extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);
    EditText e1, e2, e3, e4, e5, e6;
    myDbAdapter helper;
    Spinner s1;
    String plcs;
    String []place={"FIRST","SECOND","THIRD"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achieve);
        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        e6 = (EditText) findViewById(R.id.editText6);
        helper=new myDbAdapter(this);
        s1= (Spinner) findViewById(R.id.spinner1);
        s1.setOnItemSelectedListener(this);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        getSupportActionBar().setTitle("Achievement Details");
        // basic_update bu=new basic_update();
        //bu.sp.setOnItemSelectedListener(this);



        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,place);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(aa);


    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        plcs=place[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        Message.message(getApplicationContext(),"select");

    }
    public void achieve(View view)
    {
        valid.addValidation(this, R.id.editText2, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText3, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText4, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        valid.addValidation(this, R.id.editText6, "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", R.string.invalidname);
        String mid,des,cat,lvl,plc,hnr;
        mid=e1.getText().toString();
        des=e2.getText().toString();
        cat=e3.getText().toString();
        lvl=e4.getText().toString();
        //plc=e5.getText().toString();
        hnr=e6.getText().toString();
        plc=plcs;
        if(mid.isEmpty()||des.isEmpty()||cat.isEmpty()||lvl.isEmpty()||hnr.isEmpty())
        {
            //Message.message(getApplicationContext(),"Enter Mandatory Fields");
            new SweetAlertDialog(achieve.this, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("Error...")
                .setContentText("Enter Mandatory Fields")
                .setConfirmText("OK")
                .show();
        }
        else if(valid.validate())
        {
            long id4=helper.asave(mid,des,cat,lvl,plc,hnr);
            if(id4<=0)
            {
                //Message.message(getApplicationContext(),"Member Account is Not Exist");
                SweetAlertDialog pDialog = new SweetAlertDialog(achieve.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Information Not Stored");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(achieve.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();

            }
            else
            {
                SweetAlertDialog pDialog = new SweetAlertDialog(achieve.this, SweetAlertDialog.SUCCESS_TYPE);
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
                        e6.setText("");
                        Intent i1 = new Intent(achieve.this, MainActivity.class);
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
                e6.setText("");
                Intent i1=new Intent(this,MainActivity.class);
                startActivity(i1);*/

            }

        }
    }
    public void move(View view)
    {
        Intent i=new Intent(this,achieve_delete.class);
        startActivity(i);
        finish();

    }
    public void achupdate(View view)
    {
        Intent i1=new Intent(this,achieve_update.class);
        startActivity(i1);
        finish();

    }
}

