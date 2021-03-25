package com.example.fdms.ui.gallery;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fdms.MainActivity;
import com.example.fdms.Message;
import com.example.fdms.R;
import com.example.fdms.basic;
import com.example.fdms.myDbAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class GalleryFragment extends Fragment {
    EditText e1;
    myDbAdapter helper;
    String[] members;
    String mid;
    String Rname,Rdate,Rplace,sometext;


    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        ImageView b1 = (ImageView) root.findViewById(R.id.button1);
        e1=(EditText)root.findViewById(R.id.editText1);
        helper=new myDbAdapter(getContext());
        Spinner s1=(Spinner) root.findViewById(R.id.spinner1);

        members=helper.search();
        ArrayAdapter a = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,members);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(a);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=members[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {

                //String mid=e1.getText().toString();
                //String cs1=e2.getText().toString();
                if(mid.equals(""+members[0]))
                {
                    //Message.message(getContext(),"Error");
                    SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Error");
                    pDialog.setContentText("Select Mem Id");
                    pDialog.setConfirmText("OK");
                    pDialog.show();
                }
                else
                {
                    Boolean res=helper.resume(mid);
                    Boolean res2=helper.resume2(mid);
                    Boolean res3=helper.resume3(mid);
                    Boolean res4=helper.resume4(mid);
                    Boolean res5=helper.resume5(mid);
                    Boolean res6=helper.resume6(mid);
                    /*if(res2==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }

                    if(res3==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }

                    if(res4==true)
                    {
                        Message.message(getApplicationContext(),"true");
                    }
                    else
                    {
                        Message.message(getApplicationContext(),"false");
                    }

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
                    if((res&&res2&&res3&&res4&&res5&&res6)==true)
                    {
                        Rname=helper.name;
                        Rdate=helper.DOB;
                        Rplace=helper.DOP;
                        Date c = Calendar.getInstance().getTime();
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
                        canvas.drawLine(5,25,295,25,paint);
                        paint.setColor(Color.BLACK);
                        paint.setTextSize(12);
                        canvas.drawText("BASIC DETAILS",8,40,paint);
                       canvas.drawLine(5,46,295,46,paint);
                        canvas.drawText("NAME  :"+Rname,8,60,paint);
                        canvas.drawText("DOB     :"+Rdate,8,80,paint);
                        canvas.drawText("PLACE :"+Rplace,8,100,paint);
                        canvas.drawText("MOB NO:"+helper.Pno,8,120,paint);
                        canvas.drawText("EMAIL :"+helper.Bmail,8,140,paint);
                        canvas.drawLine(5,160,295,160,paint);
                        canvas.drawText("ACADEMIC DETAILS",8,176,paint);
                        canvas.drawLine(5,182,295,182,paint);
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
                        canvas.drawLine(5,336,295,336,paint);
                        paint.setTextSize(8);
                        int x=8;
                        int y=350;
                        //Message.message(getApplicationContext(),String.valueOf(helper.ar[1]));
                        for(int i=0;i<helper.count;i++)
                        {
                            canvas.drawText("* Participated in "+helper.ach2[i]+" "+helper.ach1[i]+" And got "+helper.ach3[i]+" Place",x,y,paint);
                            y=y+20;
                        }
                        /*canvas.drawText("* Participated in "+helper.ar2[0]+" "+helper.ar[0]+" And got "+helper.ar3[0]+" Place",8,350,paint);
                        /*canvas.drawText("* Participated in "+helper.ar2[1]+" "+helper.ar[1]+" And got "+helper.ar3[1]+" Place",8,370,paint);
                        canvas.drawText("* Participated in "+helper.ar2[2]+" "+helper.ar[2]+" And got "+helper.ar3[2]+" Place",8,390,paint);
                        canvas.drawText("* Participated in "+helper.ar2[3]+" "+helper.ar[3]+" And got "+helper.ar3[3]+" Place",8,410,paint);*/
                        canvas.drawLine(5,420,295,420,paint);
                        paint.setTextSize(12);
                        canvas.drawText("OTHER DETAILS",8,435,paint);
                        canvas.drawLine(5,441,295,441,paint);
                        paint.setTextSize(10);
                        canvas.drawText("MOTHER NAME :"+helper.MNA,8,455,paint);
                        canvas.drawText("FATHER NAME :"+helper.FNA,8,475,paint);
                        canvas.drawText("ADDRESS:"+helper.resarea,8,495,paint);
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
                        String directory_path= Environment.getExternalStorageDirectory().getAbsolutePath()+"/mypdf/";
                        File file=new File(directory_path);
                        if(!file.exists()) {
                            file.mkdirs();
                        }
                        String targetPdf=directory_path+Rname+".pdf";
                        File filePath=new File(targetPdf);
                        try {
                            document.writeTo(new FileOutputStream(filePath));
                            //Message.message(getContext(),"Done");
                            SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE);
                            pDialog.setTitleText("Success");
                            pDialog.setContentText("Pdf File Created");
                            pDialog.setConfirmText("OK");
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent i1=new Intent(getActivity(), MainActivity.class);
                                    startActivity(i1);
                                    getActivity().finish();
                                }
                            });
                            pDialog.show();
                            pDialog.setCancelable(false);
                        }
                        catch(IOException e){
                            //Log.e("main","error"+e.toString());
                            Message.message(getContext(),"PERMISSION DENIED Please allow storage permission");
                        }
                        document.close();
                    }
                    else
                    {
                        //Message.message(getContext(),"Member Account not Found");
                        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                        pDialog.setTitleText("something went wrong");
                        pDialog.setContentText("Required Information Are Not Found");
                        pDialog.setConfirmText("OK");
                        pDialog.setCancelable(false);
                        pDialog.show();

                    }

                }

            }
        });
        return root;
    }
}