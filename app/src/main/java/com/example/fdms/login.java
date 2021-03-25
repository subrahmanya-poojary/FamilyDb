package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;
import android.widget.TextView;


import com.example.fdms.ui.home.HomeFragment;

import java.util.Timer;

public class login extends AppCompatActivity {
    myDbAdapter helper;
    EditText ed1, ed2,e3;
    public String em;
    public String e;
    public String check;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiy_login);
        ed2 = (EditText) findViewById(R.id.editpassword);
        ed1 = (EditText) findViewById(R.id.editemail);
        e3=(EditText)findViewById(R.id.editText3);
        helper = new myDbAdapter(this);
        String Userid;
        getSupportActionBar().hide();
        //check=ed1.getText().toString();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(login.this);
        Userid = prefs.getString("userid", e);
        if (Userid != null) {
            global g = global.getInstance();
            g.setData(Userid);
            if (Userid.length() > 0) {
                // Means  u are already logged in  do here code what u want if u are login
                Intent i1 = new Intent(login.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        }
    }
    public  void loginuser(View view)
    {
        try
        {
            e= ed1.getText().toString();
            String e2 = ed2.getText().toString();
            if (e.isEmpty()) {
                ed1.setError("Must Enter a MemId(email)");
            }
            else if(e2.isEmpty())
            {
                ed2.setError("Must Enter a Password");
            }
            else {
                boolean valid;
                valid=helper.logincheck(e,e2);
                if(valid==true)
                {
                    global g=global.getInstance();
                    g.setData(e);
                    //Message.message(getApplicationContext(), "Login sucssessfull");
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(login.this);
                    prefs.edit().putString("userid",e).commit();
                    check=ed1.getText().toString();
                    SweetAlertDialog pDialog2 = new SweetAlertDialog(login.this, SweetAlertDialog.SUCCESS_TYPE);
                    pDialog2.setTitleText("Success");
                    pDialog2.setConfirmText("OK");
                    pDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            Intent i1=new Intent(login.this,MainActivity.class);
                            startActivity(i1);
                            finish();
                        }
                    });
                    pDialog2.setCancelable(false);
                    pDialog2.show();
                }
                else
                {
                    //Message.message(getApplicationContext(),"Account Not Found");
                     new SweetAlertDialog(login.this).setTitleText("LogIn Failed..").show();
                }
            }
        }
        catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }

    }
    public void reg(View view) {
        Intent intent = new Intent(this,register.class);
        startActivity(intent);
        finish();
    }
}
