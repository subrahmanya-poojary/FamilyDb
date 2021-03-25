package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.TextKeyListener;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;

import java.util.regex.Pattern;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class register extends AppCompatActivity {

    EditText fname,lname,email,password,pass2;
    myDbAdapter helper;
    final AwesomeValidation valid=new AwesomeValidation(ValidationStyle.BASIC);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        fname =(EditText)findViewById(R.id.Fname);
        lname =(EditText)findViewById(R.id.Lname);
        email =(EditText)findViewById(R.id.Email);
        password =(EditText)findViewById(R.id.Password);
        pass2=(EditText)findViewById(R.id.Password2);
        helper=new myDbAdapter(this);
        getSupportActionBar().hide();
    }
    public void user(View view)
    {
        try
        {
            String pattern2="[a-zA-Z]";
            String f1=fname.getText().toString();
            String l1=lname.getText().toString();
            String e1=email.getText().toString();
            String e2=password.getText().toString();
            String e3=pass2.getText().toString();
            valid.addValidation(this,R.id.Fname,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.invalidname);
            valid.addValidation(this,R.id.Lname,"^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$",R.string.invalidname);
            valid.addValidation(this,R.id.Email, Patterns.EMAIL_ADDRESS,R.string.invalidemail);
            if(valid.validate()){}
            if(f1.isEmpty())
            {
                fname.setError("Must Enter FirstName");
            }
            else if(l1.isEmpty())
            {
                lname.setError("Must Enter LastName");
            }
            else if(e1.isEmpty())
            {
                email.setError("Must Enter Email");

            }
            else if(e2.isEmpty()&&e2.length()<6)
            {
                password.setError("Password is too short");

            }
            else if(e3.isEmpty())
            {
                pass2.setError("Password is too short");
            }
            else if(e2.matches(e3)==false)
            {
                pass2.setError("Password does not match");
            }
            else
            {
                String pattern = "[a-zA-Z0-9._-]+@[gmail]+\\.+[com]+";
                if(e1.matches(pattern)) {
                    long id1 = helper.Register(f1, l1, e1, e2);
                    if (id1 <= 0) {
                        //Message.message(getApplicationContext(), "Admin is Already Registered");
                        SweetAlertDialog sp=new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE);
                        sp.setTitleText("Error");
                        sp.setContentText("Failed to register");
                        sp.setConfirmText("OK");
                        sp.show();
                        fname.setText("");
                        lname.setText("");
                        email.setText("");
                        password.setText("");
                        pass2.setText("");

                    } else {
                        //Message.message(getApplicationContext(), "Admin registered Succuessfully");
                        SweetAlertDialog sp=new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE);
                        sp.setTitleText("Success");
                        sp.setContentText("Register Success");
                        sp.setConfirmText("OK");
                        sp.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i3= new Intent(register.this, login.class);
                                startActivity(i3);
                                finish();
                            }
                        });
                        sp.setCancelable(false);
                        sp.show();
                        fname.setText("");
                        lname.setText("");
                        email.setText("");
                        password.setText("");
                        pass2.setText("");

                    }
                }
                else
                {
                    email.setError("Email format is wrong");
                }

            }
        }catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }

    }
}
