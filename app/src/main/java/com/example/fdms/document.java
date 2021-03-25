package com.example.fdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.os.Trace.isEnabled;

public class document extends AppCompatActivity {

    EditText e1,e2,e3,e4,e5;
    myDbAdapter helper;
    ImageView b1,b2,b3;
    final int REQUEST_CODE_GALLERY=999;
    ImageView myImg;
    ProgressDialog dialog;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_GALLERY)
        {
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Intent i=new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i,REQUEST_CODE_GALLERY);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"Permission Denied",Toast.LENGTH_LONG).show();
                return;
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int reqcode,int resultcode,Intent data)
    {
        if(data==null)
        {
            Message.message(getApplicationContext(),"Select a file/image");
        }
        else {
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bm = BitmapFactory.decodeStream(inputStream);
                myImg.setVisibility(View.VISIBLE);
                myImg.setImageBitmap(bm);
            } catch (Exception e) {
                Message.message(getApplicationContext(), "" + e);
            }
            super.onActivityResult(reqcode, resultcode, data);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_document);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        e3.setKeyListener(null);
        b1=(ImageView) findViewById(R.id.button6);
        myImg=(ImageView)findViewById(R.id.imageView);
        helper=new myDbAdapter(this);
        myImg.setVisibility(View.INVISIBLE);
        getSupportActionBar().setTitle("Document Details");


        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(document.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    byte[] NewEntry=imgtobyte(myImg);
                    String mid,dname,idate,dloc,copy;
                    copy=null;
                    mid=e1.getText().toString();
                    dname=e2.getText().toString();
                    idate=e3.getText().toString();
                    dloc=e4.getText().toString();
                    /*if(myImg.isSelected() ==false)
                    {
                        Message.message(getApplicationContext(),"select image");
                    }*/
                    if(mid.isEmpty()||dname.isEmpty()||idate.isEmpty()||dloc.isEmpty())
                    {
                        //Message.message(getApplicationContext(),"Enter Mandatory Fields");
                        SweetAlertDialog sp=new SweetAlertDialog(document.this,SweetAlertDialog.ERROR_TYPE);
                        sp.setTitleText("Error");
                        sp.setContentText("Enter Mandatory Fields");
                        sp.setConfirmText("Ok");
                        sp.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i=new Intent(document.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                        sp.show();
                    }
                    else
                    {
                        boolean Add=helper.AddData(mid,dname,idate,dloc,NewEntry);
                        if(Add==true)
                        {
                           // sp.dismiss();
                            //Message.message(getApplicationContext(),"Member Document are saved");
                            SweetAlertDialog pDialog2 = new SweetAlertDialog(document.this, SweetAlertDialog.SUCCESS_TYPE);
                            pDialog2.setTitleText("Success");
                            pDialog2.setContentText("Document Information is Stored");
                            pDialog2.setConfirmText("OK");
                            pDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    e1.setText("");
                                    e2.setText("");
                                    e3.setText("");
                                    e4.setText("");
                                    myImg.setVisibility(View.INVISIBLE);
                                    Intent i1=new Intent(document.this,MainActivity.class);
                                    startActivity(i1);
                                    finish();
                                }
                            });
                            pDialog2.setCancelable(false);
                            pDialog2.show();
                        }
                        else
                        {
                            //Message.message(getApplicationContext(),"Information Not Stored");
                            SweetAlertDialog pDialog2 = new SweetAlertDialog(document.this, SweetAlertDialog.ERROR_TYPE);
                            pDialog2.setTitleText("Error");
                            pDialog2.setContentText("Document Information Not Stored");
                            pDialog2.setConfirmText("OK");
                            pDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    e1.setText("");
                                    e2.setText("");
                                    e3.setText("");
                                    e4.setText("");
                                    myImg.setImageBitmap(null);
                                    myImg.setVisibility(View.INVISIBLE);
                                    Intent i1=new Intent(document.this,MainActivity.class);
                                    startActivity(i1);
                                    finish();
                                }
                            });
                            pDialog2.setCancelable(false);
                            pDialog2.show();
                        }
                    }

                    }catch (Exception e)
                {
                 Message.message(getApplicationContext(),""+e);
                }
            }

            private byte[] imgtobyte(ImageView myImg) {
                Bitmap bm=((BitmapDrawable) myImg.getDrawable()).getBitmap();
                ByteArrayOutputStream stream=new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG,20,stream);
                byte[] byteArray=stream.toByteArray();
                return byteArray;
            }
        });

    }
    public void date(View view) {
        if (view == e3) {


            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            e3.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        }
    }
    public void delete(View view)
    {
        Intent i1=new Intent(this,document_delete.class);
        startActivity(i1);
    }
    public void dupdate(View view)
    {
        Intent i2=new Intent(this,document_update.class);
        startActivity(i2);
        finish();

    }
}
