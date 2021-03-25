package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class assets extends AppCompatActivity {
    EditText e1, e2, e3, e4, e5;
    myDbAdapter helper;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assets);

        e1 = (EditText) findViewById(R.id.editText1);
        e2 = (EditText) findViewById(R.id.editText2);
        e3 = (EditText) findViewById(R.id.editText3);
        e4 = (EditText) findViewById(R.id.editText4);
        e5 = (EditText) findViewById(R.id.editText5);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        helper=new myDbAdapter(this);
        e1.setKeyListener(null);
        getSupportActionBar().setTitle("Assets Details");

    }
    public void aAdd(View view)
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
           // Message.message(getApplicationContext(),"Enter all Mandatory Fields");
            new SweetAlertDialog(assets.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Error...")
                    .setContentText("Enter Mandatory Fields")
                    .setConfirmText("OK")
                    .show();
        }
        else if(valid.validate())
        {
            long id5=helper.assets(mid,ades,rtc,area,type);
            if(id5<=0)
            {
                //Message.message(getApplicationContext(),"Member Account is Not Exist");
                SweetAlertDialog pDialog = new SweetAlertDialog(assets.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Warning..!");
                pDialog.setContentText("Information Not Stored");
                pDialog.setConfirmText("OK");
                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        Intent i1=new Intent(assets.this,MainActivity.class);
                        startActivity(i1);
                        finish();
                    }
                });
                pDialog.setCancelable(false);
                pDialog.show();
            }
            else
            {
                SweetAlertDialog pDialog = new SweetAlertDialog(assets.this, SweetAlertDialog.SUCCESS_TYPE);
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
                        e2.setText("");
                        Intent i1 = new Intent(assets.this, MainActivity.class);
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
    public void move(View view)
    {
        Intent i=new Intent(this,assets_delete.class);
        startActivity(i);
        finish();

    }
    public void amove(View view)
    {
        Intent i1=new Intent(this,assets_update.class);
        startActivity(i1);
        finish();
    }
}
