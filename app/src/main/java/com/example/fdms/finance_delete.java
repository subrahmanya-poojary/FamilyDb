package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class finance_delete extends AppCompatActivity {
    EditText e1;
    myDbAdapter helper;
    public String mid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance_delete);
        e1 = (EditText) findViewById(R.id.editText1);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        getSupportActionBar().setTitle("Financial Details Delete");
        helper = new myDbAdapter(this);
    }

    public void delete(View view) {
        mid = e1.getText().toString();
        if (mid.isEmpty()) {
            Message.message(getApplicationContext(), "Select a Member ID");
        } else {
            final SweetAlertDialog pDialog = new SweetAlertDialog(finance_delete.this, SweetAlertDialog.WARNING_TYPE);
            pDialog.setTitleText("Are You Sure?");
            pDialog.setContentText("Won't be able to recover this info!");
            pDialog.setConfirmText("Delete");
            pDialog.setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent i1 = new Intent(finance_delete.this, MainActivity.class);
                    startActivity(i1);
                    finish();
                }
            });
            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    int count = helper.fdelete(mid);
                    if (count <= 0) {
                        //Message.message(getApplicationContext(), "Member Account Not Exist");
                        SweetAlertDialog pDialog = new SweetAlertDialog(finance_delete.this, SweetAlertDialog.ERROR_TYPE);
                        pDialog.setTitleText("Error");
                        pDialog.setContentText("Member Information Not Exist");
                        pDialog.setConfirmText("OK");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i1 = new Intent(finance_delete.this, MainActivity.class);
                                startActivity(i1);
                                finish();
                            }
                        });
                        pDialog.setCancelable(false);
                        pDialog.show();
                    } else {
                        SweetAlertDialog pDialog = new SweetAlertDialog(finance_delete.this, SweetAlertDialog.SUCCESS_TYPE);
                        pDialog.setTitleText("Success");
                        pDialog.setContentText("Member Information Deleted");
                        pDialog.setConfirmText("OK");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i1 = new Intent(finance_delete.this, MainActivity.class);
                                startActivity(i1);
                                finish();
                            }
                        });
                        pDialog.setCancelable(false);
                        pDialog.show();
                    }
                }
                // pDialog.setCancelable(false);
                //pDialog.show();
            });pDialog.show();;
        }
    }
}
