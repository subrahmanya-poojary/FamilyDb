package com.example.fdms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class document_update extends AppCompatActivity {

    EditText e1, e2, e3, e4,e5;
    int x=0;
    Spinner s1;
    ImageView b1;
    myDbAdapter helper;
    String mid;
    int Id;
    byte[] pictures;
    String[] doc;
    String[] docs;
    ImageView myImg,myImg2;
    final int REQUEST_CODE_GALLERY=999;
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
        setContentView(R.layout.activity_document_update);
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        e3=(EditText)findViewById(R.id.editText3);
        e4=(EditText)findViewById(R.id.editText4);
        e5=(EditText)findViewById(R.id.editText5);
        b1=(ImageView) findViewById(R.id.button1);
        myImg=(ImageView)findViewById(R.id.imageView);
       // myImg2=(ImageView)findViewById(R.id.imageView14);
        s1=(Spinner)findViewById(R.id.spinner1);
        helper=new myDbAdapter(this);
        myImg.setVisibility(View.INVISIBLE);
        e1.setKeyListener(null);
        e2.setKeyListener(null);
        getSupportActionBar().setTitle("Document Details Update");
        global g=global.getInstance();
        final String s=g.getData();
        doc=helper.dupdate();

        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,doc);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=doc[position];
                Id=s1.getSelectedItemPosition();
                //Message.message(getApplicationContext(),""+Id);
                if(Id==0)
                {
                    Message.message(getApplicationContext(),"Select MemID");
                    e1.setText("");
                    e2.setText("");
                    e3.setText("");
                    e4.setText("");
                    myImg.setImageBitmap(null);
                    e1.setVisibility(View.INVISIBLE);
                    e2.setVisibility(View.INVISIBLE);
                    e3.setVisibility(View.INVISIBLE);
                    e4.setVisibility(View.INVISIBLE);
                    e5.setVisibility(View.INVISIBLE);
                    myImg.setVisibility(View.INVISIBLE);
                }
                else
                {
                    boolean d=helper.dupdate2(mid,Id);
                    //pictures=helper.getpic(mid,Id);
                    if(d==true)
                    {
                        //Message.message(getApplicationContext(),""+Id);
                        e1.setVisibility(View.VISIBLE);
                        e2.setVisibility(View.VISIBLE);
                        e3.setVisibility(View.VISIBLE);
                        e4.setVisibility(View.VISIBLE);
                        e5.setVisibility(View.VISIBLE);
                        myImg.setVisibility(View.VISIBLE);
                        myImg.setImageBitmap(null);
                        /*e1.setText(""+s);
                        e2.setText(""+helper.ar2[1]);
                        e3.setText(""+helper.ar2[2]);
                        e4.setText(""+helper.ar2[3]);*/
                        //byte[] bg=pictures;
                        e1.setText(""+s);
                        e2.setText(""+helper.dname);
                        e3.setText(""+helper.dts);
                        e4.setText(""+helper.dlc);
                        Bitmap bmp= BitmapFactory.decodeByteArray(helper.img,0,helper.img.length);
                        ByteArrayOutputStream stm=new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.JPEG,20,stm);
                       // Bitmap resize=Bitmap.createScaledBitmap(bmp,(int)(bmp.getWidth()*0.8), (int)(bmp.getHeight()*0.8), true);
                        myImg.setImageBitmap(bmp);
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

        e5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(document_update.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_GALLERY);
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
                        SweetAlertDialog sp=new SweetAlertDialog(document_update.this,SweetAlertDialog.ERROR_TYPE);
                        sp.setTitleText("Error");
                        sp.setContentText("Enter Mandatory Fields");
                        sp.setConfirmText("Ok");
                    }
                    else
                    {
                        boolean Add=helper.AddData2(mid,dname,idate,dloc,NewEntry);
                        if(Add==true)
                        {
                            SweetAlertDialog pDialog2 = new SweetAlertDialog(document_update.this, SweetAlertDialog.SUCCESS_TYPE);
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
                                    Intent i1=new Intent(document_update.this,MainActivity.class);
                                    startActivity(i1);
                                    finish();
                                }
                            });
                            pDialog2.setCancelable(false);
                            pDialog2.show();
                        }
                        else
                        {
                            //Message.message(getApplicationContext(),"Member Document Exist");
                            SweetAlertDialog pDialog2 = new SweetAlertDialog(document_update.this, SweetAlertDialog.ERROR_TYPE);
                            pDialog2.setTitleText("Error");
                            pDialog2.setContentText("Document Information Not Stored");
                            pDialog2.setConfirmText("OK");
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

}
