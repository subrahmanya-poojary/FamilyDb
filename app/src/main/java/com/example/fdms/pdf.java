package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fdms.myDbAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class pdf extends AppCompatActivity {
    Button b1;
    EditText e1,e2;
    myDbAdapter helper;
    String Rname,Rdate,Rplace,sometext;
    //String cs1;
    //String cs2="2PUC";
    //String cs3="Degree";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        b1=(Button)findViewById(R.id.button);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText1);
        helper=new myDbAdapter(this);
        sometext="aa";
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mid=e1.getText().toString();
                //String cs1=e2.getText().toString();
                if(mid.isEmpty())
                {
                    Message.message(getApplicationContext(),"Error");
                }
                else
                {
                    Boolean res=helper.resume(mid);
                    Boolean res2=helper.resume2(mid);
                    Boolean res3=helper.resume3(mid);
                    Boolean res4=helper.resume4(mid);
                    Boolean res5=helper.resume5(mid);
                    /*if(res2==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }
                    Boolean res3=helper.resume3(mid);
                    if(res3==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }
                    Boolean res4=helper.resume4(mid);
                    if(res4==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }
                    Boolean res5=helper.resume5(mid);
                    if(res5==true)
                    {
                        Message.message(getApplicationContext(),""+res5);
                    }
                    /*Boolean res6=helper.resume6(mid);
                    if (res6 == true)
                    {

                    }
                    else
                    {

                    }*/
                    if((res&&res2&&res3&&res4&&res5)==true)
                    {
                        Rname=helper.name;
                        Rdate=helper.DOB;
                        Rplace=helper.DOP;
                        Date c = Calendar.getInstance().getTime();
                        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        String dt = df.format(c);
                        String t= DateFormat.getDateTimeInstance().format(new Date());
                        PdfDocument document=new PdfDocument();
                        PdfDocument.PageInfo pageInfo=new PdfDocument.PageInfo.Builder(300,600,1).create();
                        PdfDocument.Page page=document.startPage(pageInfo);
                        Canvas canvas=page.getCanvas();
                        Paint paint=new Paint();
                        paint.setColor(Color.BLACK);
                        Rect r=new Rect(5,5,295,590);
                        paint.setStyle(Paint.Style.STROKE);
                        canvas.drawRect(r,paint);
                        paint.setColor(Color.BLACK);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("SIMPLE RESUME",90,20,paint);
                        canvas.drawLine(5,25,295,24,paint);
                        paint.setColor(Color.BLACK);
                        paint.setTextSize(12);
                        canvas.drawText("BASIC DETAILS",8,40,paint);
                        canvas.drawText("NAME  :"+Rname,8,60,paint);
                        canvas.drawText("DOB     :"+Rdate,8,80,paint);
                        canvas.drawText("PLACE :"+Rplace,8,100,paint);
                        canvas.drawText("MOB NO:"+helper.Pno,8,120,paint);
                        canvas.drawText("EMAIL :"+helper.Bmail,8,140,paint);
                        canvas.drawLine(5,160,295,160,paint);
                        canvas.drawText("ACADEMIC DETAILS",8,176,paint);
                        paint.setStyle(Paint.Style.STROKE);
                        Rect r2=new Rect(20,196,280,306);
                        canvas.drawRect(r2,paint);
                        canvas.drawLine(20,216,280,216,paint);
                        canvas.drawLine(20,246,280,246,paint);
                        canvas.drawLine(20,276,280,276,paint);
                        canvas.drawLine(102,196,102,306,paint);
                        canvas.drawLine(188,196,188,306,paint);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("Degre",24,210,paint);
                        canvas.drawText("SSLC",28,240,paint);
                        canvas.drawText("PUC",28,270,paint);
                        canvas.drawText("DEGREE",28,300,paint);
                        canvas.drawText(""+helper.sboard,110,240,paint);
                        canvas.drawText(""+helper.smscr,196,240,paint);
                        canvas.drawText(""+helper.pboard,110,270,paint);
                        canvas.drawText(""+helper.pscr,196,270,paint);
                        canvas.drawText(""+helper.dboard,110,300,paint);
                        canvas.drawText(""+helper.dscr,196,300,paint);
                        canvas.drawText("BOARD",106,210,paint);
                        canvas.drawText("% OR CGPA",192,210,paint);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawLine(5,316,295,316,paint);
                        canvas.drawText("ACHIEVEMENTS",8,330,paint);
                        paint.setTextSize(8);
                        int x=8;
                        int y=350;
                        //Message.message(getApplicationContext(),String.valueOf(helper.ar[1]));
                        for(int i=0;i<helper.count;i++)
                        {
                            canvas.drawText("* Participated in "+helper.ar2[i]+" "+helper.ar[i]+" And got "+helper.ar3[i]+" Place",x,y,paint);
                            y=y+20;
                        }
                        /*canvas.drawText("* Participated in "+helper.ar2[0]+" "+helper.ar[0]+" And got "+helper.ar3[0]+" Place",8,350,paint);
                        /*canvas.drawText("* Participated in "+helper.ar2[1]+" "+helper.ar[1]+" And got "+helper.ar3[1]+" Place",8,370,paint);
                        canvas.drawText("* Participated in "+helper.ar2[2]+" "+helper.ar[2]+" And got "+helper.ar3[2]+" Place",8,390,paint);
                        canvas.drawText("* Participated in "+helper.ar2[3]+" "+helper.ar[3]+" And got "+helper.ar3[3]+" Place",8,410,paint);*/
                        canvas.drawLine(5,420,295,420,paint);
                        paint.setTextSize(12);
                        canvas.drawText("OTHER DETAILS",8,435,paint);
                        paint.setTextSize(10);
                        canvas.drawText("MOTHER NAME :"+helper.MNA,8,455,paint);
                        canvas.drawText("FATHER NAME :"+helper.FNA,8,475,paint);
                        canvas.drawText("ASSETS AREAs:",8,495,paint);
                        canvas.drawLine(5,510,295,510,paint);
                        //canvas.drawText("DATE :"+c,8,530,paint);
                        canvas.drawText("DATE AND TIME :"+t,8,550,paint);
                        document.finishPage(page);
                        pageInfo=new PdfDocument.PageInfo.Builder(300,600,2).create();
                        page=document.startPage(pageInfo);
                        canvas=page.getCanvas();
                        paint=new Paint();
                        paint.setColor(Color.BLUE);
                        /*Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.black);
                        canvas.drawBitmap(bmp,400, 400, null);*/
                        document.finishPage(page);

                        String directory_path= Environment.getExternalStorageDirectory().getPath()+"/mypdf/";
                        File file=new File(directory_path);
                        if(!file.exists()) {
                            file.mkdirs();
                        }
                        String targetPdf=directory_path+Rname+".pdf";
                        File filePath=new File(targetPdf);
                        try {
                            document.writeTo(new FileOutputStream(filePath));
                            Message.message(getApplicationContext(),"Done");
                        }
                        catch(IOException e){
                            Log.e("main","error"+e.toString());
                            Message.message(getApplicationContext(),"Error");
                        }
                        document.close();
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"Member Account not Found");

                    }

                }

            }
        });

    }
}
