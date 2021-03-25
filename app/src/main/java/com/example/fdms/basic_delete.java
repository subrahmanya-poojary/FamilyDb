package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class basic_delete extends AppCompatActivity  {
    EditText e1;
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    Button b1;
    ImageView b2;
    myDbAdapter helper;
    public String mid;
    public String tt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_delete);

        e1=(EditText)findViewById(R.id.editText1);

        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        t4=(TextView)findViewById(R.id.textView4);
        t5=(TextView)findViewById(R.id.textView5);
        t6=(TextView)findViewById(R.id.textView6);
        t7=(TextView)findViewById(R.id.textView7);
        t8=(TextView)findViewById(R.id.textView8);
        t9=(TextView)findViewById(R.id.textView9);
        b1=(Button)findViewById(R.id.button4);
        b2=(ImageView) findViewById(R.id.button2);
        b2.setVisibility(View.INVISIBLE);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        getSupportActionBar().setTitle("Basic Details Delete");

        helper=new myDbAdapter(this);

    }
    public void check(View view)
    {
        try {
            mid=e1.getText().toString();
            if(mid.isEmpty())
            {
                Message.message(getApplicationContext(),"Enter a Member ID");
            }
            else
            {
                Boolean check=helper.bcheck(mid);
                if(check==true) {
                    t1.setText("" + helper.md);
                    t2.setText("" + helper.name);
                    t3.setText("" + helper.DOB);
                    t4.setText("" + helper.DOT);
                    t5.setText("" + helper.DOP);
                    t6.setText("" + helper.Mn);
                    t7.setText("" + helper.Fn);
                    t8.setText("" + helper.Pno);
                    t9.setText("" + helper.Bmail);
                    b2.setVisibility(View.VISIBLE);
                }
                else
                {
                    //Message.message(getApplicationContext(),"Member Account Not Exist");
                    new SweetAlertDialog(basic_delete.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error...")
                            .setContentText("Member Account Not Exist")
                            .setConfirmText("OK")
                            .show();
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");
                    t9.setText("");
                    b2.setVisibility(View.INVISIBLE);
                }
            }
        }catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }
    }
    public void delete(View view)
    {
        try {
            mid = e1.getText().toString();
            if (mid.isEmpty()) {
                Message.message(getApplicationContext(), "Select Member ID");
            } else {
                final SweetAlertDialog pDialog = new SweetAlertDialog(basic_delete.this,SweetAlertDialog.WARNING_TYPE);
                pDialog.setTitleText("Are You Sure?");
                pDialog.setContentText("Won't be able to recover this info!");
                pDialog.setConfirmText("Delete");
                pDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                pDialog.dismissWithAnimation();
                            }
                        });
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                int count = helper.bdelete(mid);
                                if (count <= 0) {
                                    //Message.message(getApplicationContext(), "Member Account Not Exist");
                                    new SweetAlertDialog(basic_delete.this, SweetAlertDialog.ERROR_TYPE)
                                            .setTitleText("Error...")
                                            .setContentText("Member Account Not Exist")
                                            .setConfirmText("OK")
                                            .show();
                                } else {
                                    //Message.message(getApplicationContext(), "Member Account Deleted");
                                    SweetAlertDialog pDialog = new SweetAlertDialog(basic_delete.this, SweetAlertDialog.SUCCESS_TYPE);
                                    pDialog.setTitleText("Success");
                                    pDialog.setContentText("Member Information Deleted");
                                    pDialog.setConfirmText("OK");
                                    pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {

                                            t1.setText("");
                                            t2.setText("");
                                            t3.setText("");
                                            t4.setText("");
                                            t5.setText("");
                                            t6.setText("");
                                            t7.setText("");
                                            t8.setText("");
                                            t9.setText("");
                                            e1.setText("");
                                            b2.setVisibility(View.INVISIBLE);
                                            Intent i1 = new Intent(basic_delete.this, MainActivity.class);
                                            startActivity(i1);
                                            finish();
                                        }
                                    });
                                    pDialog.setCancelable(false);
                                    pDialog.show();

                                }
                            }
                        });
                pDialog.setCancelable(false);
                pDialog.show();

            }
        }
        catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }

    }

}