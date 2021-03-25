package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class doc_view extends AppCompatActivity {

    TextView t1,t2,t3;
    ImageView i1;
    String[] dview;
    myDbAdapter helper;
    Spinner s1;
    String mid;
    int Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_view);
        t1=(TextView)findViewById(R.id.textView1);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        i1=(ImageView)findViewById(R.id.imageView1);
        s1=(Spinner)findViewById(R.id.spinner1);
        SharedPreferences sd = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        helper=new myDbAdapter(this);
        String memid = sd.getString("MID",null);
        //t1.setText(""+memid);
        String id=sd.getString("MID",null);
        dview=helper.dget(memid);

        ArrayAdapter a = new ArrayAdapter(this,android.R.layout.simple_spinner_item,dview);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=dview[position];
                Id=s1.getSelectedItemPosition();
                if(id==0)
                {
                    t1.setVisibility(View.INVISIBLE);
                   t2.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                   i1.setVisibility(View.INVISIBLE);
                }
                else
                {
                    boolean d=helper.dupdate2(mid,Id);
                    if(d==true)
                    {
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        i1.setVisibility(View.VISIBLE);
                        t1.setText("Document Name :" +helper.dname);
                        t2.setText("Issue Date :" +helper.dts);
                        t3.setText("HardCopy Location :" +helper.dlc);
                        Bitmap bmp= BitmapFactory.decodeByteArray(helper.img,0,helper.img.length);
                        ByteArrayOutputStream stm=new ByteArrayOutputStream();
                        bmp.compress(Bitmap.CompressFormat.PNG,20,stm);
                        Bitmap resize=Bitmap.createScaledBitmap(bmp,(int)(bmp.getWidth()*0.8), (int)(bmp.getHeight()*0.8), true);
                        i1.setImageBitmap(resize);
                        //Message.message(getApplicationContext(),""+helper.dname);
                    }else
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
}
