package com.example.fdms;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import android.database.Cursor;
import android.content.ContentValues;
import android.widget.Toast;

import androidx.constraintlayout.solver.widgets.ConstraintWidget;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class myDbAdapter {
    global g=global.getInstance();
    String s=g.getData();
    myDbHelper myhelper;

    public myDbAdapter(Context context) {
        myhelper = new myDbHelper(context);


    }
    public volatile byte[] img;
    public String au;
    public String zs=null;
    public String md=null;
    public String name=null;
    public String DOB=null;
    public String DOT=null;
    public String DOP=null;
    public String Mn=null;
    public String Fn=null;
    public String Pno=null;
    public String Bmail=null;
    public String mailid=null;
    public String FNA=null;
    public String MNA=null;
    public String sboard=null;
    public String pboard=null;
    public String dboard=null;
    public String smscr=null;
    public String pscr=null;
    public String dscr=null;
    byte[] photo;
    public String dname=null;
    public String dts=null;
    public String dlc=null;
    public String resarea=null;

    String[] BASE={"MEM ID","NAME","DOB","DOT","ADDRESS","MOTHER NAME","FATHER NAME","MOB NO","EMAIL"};

    public String[] ar=new String[10];
    public String[] ar2=new String[10];
    public String[] ar3=new String[10];
    public String[] grd;
    String cs1="SSLC";
    String cs2="2PUC";
    String cs3="UG-Degree";
    String level1="FIRST";
    String level2="SECOND";
    public String AType;
    public String bamt;
    public String bint;
    public String bdate;
    public int count;
    public String[] ach1;
    public String[] ach2;
    public String[] ach3;
    public long adduser(String fname, String lname, String email, String password) {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.FNAME, fname);
        contentValues.put(myDbHelper.LNAME, lname);
        contentValues.put(myDbHelper.NAME, email);
        contentValues.put(myDbHelper.PASSWORD, password);
        long id1 = Db.insert(myDbHelper.TABLE_NAME, null, contentValues);
        return id1;
    }
    public long Register(String fname, String lname, String email, String password) {
        SQLiteDatabase Db=myhelper.getReadableDatabase();
        /*ContentValues contentValues = new ContentValues();
        contentValues.get(e1);
        contentValues.get(e2);*/
        Cursor c;
        long id1=0;
        c = Db.rawQuery("SELECT * FROM reg",null);
        if(c.getCount()>=1)
        {
            return id1;

        }
        else {
            Db = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.FNAME, fname);
            contentValues.put(myDbHelper.LNAME, lname);
            contentValues.put(myDbHelper.NAME, email);
            contentValues.put(myDbHelper.PASSWORD, password);
            id1 = Db.insert(myDbHelper.TABLE_NAME, null, contentValues);

        }
        return id1;

    }

    public boolean logincheck(String e1, String e2)
    {
        SQLiteDatabase Db=myhelper.getReadableDatabase();
        /*ContentValues contentValues = new ContentValues();
        contentValues.get(e1);
        contentValues.get(e2);*/
        Cursor c;
        c = Db.rawQuery("SELECT * FROM reg WHERE email='"+e1+"' AND password='"+e2+"'",null);
        if(c.getCount()>=1)
        {
            return  true;

        }
        else
        {
            return  false;
        }
    }
    public long bsave(String mid,String bname,String bdate,String btime,String bplace,String m_name,String f_name,String phno,String bemail,String sign)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.BMID,mid);
        cv.put(myDbHelper.BNAME,bname);
        cv.put(myDbHelper.BDATE,bdate);
        cv.put(myDbHelper.BTIME,btime);
        cv.put(myDbHelper.BPLACE,bplace);
        cv.put(myDbHelper.M_NAME,m_name);
        cv.put(myDbHelper.F_NAME,f_name);
        cv.put(myDbHelper.PHNO,phno);
        cv.put(myDbHelper.BEMAIL,bemail);
        cv.put(myDbHelper.ZSIGN,sign);
        long id2=Db.insert(myDbHelper.TABLE_BASIC,null,cv);
        return id2;
    }
    public long esave(String mid,String cls,String regno,String crc, String clg,String board,String tscr,String mscr)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.EMID,mid);
        cv.put(myDbHelper.CLS,cls);
        cv.put(myDbHelper.REGNO,regno);
        cv.put(myDbHelper.CRC,crc);
        cv.put(myDbHelper.CLG,clg);
        cv.put(myDbHelper.BOARD,board);
        cv.put(myDbHelper.TSCR,tscr);
        cv.put(myDbHelper.MSCR,mscr);
        long id3=Db.insert(myDbHelper.TABLE_ACADEMY,null,cv);
        return id3;
    }

    public long asave(String mid,String des,String cat,String lvl,String plc,String hnr)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.AMID,mid);
        cv.put(myDbHelper.DES,des);
        cv.put(myDbHelper.CAT,cat);
        cv.put(myDbHelper.LVL,lvl);
        cv.put(myDbHelper.PLC,plc);
        cv.put(myDbHelper.HNR,hnr);
        long id4=Db.insert(myDbHelper.TABLE_ACHIEVE,null,cv);
        return id4;

    }

    public long assets(String mid,String ades,String rtc,String area,String type)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.AMID1,mid);
        cv.put(myDbHelper.ADES,ades);
        cv.put(myDbHelper.RTC,rtc);
        cv.put(myDbHelper.AREA,area);
        cv.put(myDbHelper.TYPE,type);
        long id5=Db.insert(myDbHelper.TABLE_ASSETS,null,cv);
        return id5;
    }

    public long fsave(String mid,String acc_no,String acc_type,String amt,String interest,String date)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.FMID,mid);
        cv.put(myDbHelper.ACCNO,acc_no);
        cv.put(myDbHelper.ACCTYPE,acc_type);
        cv.put(myDbHelper.AMT,amt);
        cv.put(myDbHelper.INT,interest);
        cv.put(myDbHelper.DATE,date);
        long id6=Db.insert(myDbHelper.TABLE_FINANCE,null,cv);
        return id6;
    }
    public boolean AddData(String mid,String dname,String idate,String dloc,byte[] NewEntry)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(myDbHelper.DMID,mid);
        cv.put(myDbHelper.DNAME,dname);
        cv.put(myDbHelper.IDATE,idate);
        cv.put(myDbHelper.DLOC,dloc);
        cv.put(myDbHelper.IMG,NewEntry);
        long result=Db.insert(myDbHelper.TABLE_DOC,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Boolean bcheck(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        /*String[] MID={mid};public void check(View view)
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
                    t8.setText("" + helper.Zod);
                    b2.setVisibility(View.VISIBLE);
                }
                else
                {
                    Message.message(getApplicationContext(),"Member Account Not Exist");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    t4.setText("");
                    t5.setText("");
                    t6.setText("");
                    t7.setText("");
                    t8.setText("");
                    b2.setVisibility(View.INVISIBLE);
                }
            }
        }catch (Exception e)
        {
            Message.message(getApplicationContext(),""+e);
        }
    }
        int count=Db.delete(myDbHelper.TABLE_BASIC,myDbHelper.BMID+"=?",MID);
        return  count;*/
        ContentValues cv=new ContentValues();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM basic WHERE mid='"+mid+"'",null);
        if(c.moveToFirst()) {
            md=c.getString(0);
            name = c.getString(1);
            DOB = c.getString(2);
            DOT = c.getString(3);
            DOP = c.getString(4);
            Mn = c.getString(5);
            Fn = c.getString(6);
            Pno = c.getString(7);
            Bmail=c.getString(8);
            c.close();
            return  true;
        }
        else
        {
            return false;
        }


        /*
        DOB=c.getString(2);
        DOT=c.getString(3);
        DOP=c.getString(4);
        Mn=c.getString(5);
        Fn=c.getString(6);
        Zod=c.getString(7);
        basic_delete b=new basic_delete();
        return name;*/

    }

    public  int bdelete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_BASIC,myDbHelper.BMID+"=?",MID);
        return  count;
    }

    public  int edelete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_ACADEMY,myDbHelper.EMID+"=?",MID);
        return  count;
    }

    public  int fdelete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_FINANCE,myDbHelper.FMID+"=?",MID);
        return  count;
    }

    public  int ach_delete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_ACHIEVE,myDbHelper.AMID+"=?",MID);
        return  count;
    }

    public  int adelete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_ASSETS,myDbHelper.AMID1+"=?",MID);
        return  count;
    }
    public  int Ddelete(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        String[] MID={mid};
        int count=Db.delete(myDbHelper.TABLE_DOC,myDbHelper.DMID+"=?",MID);
        return  count;
    }

    public Boolean update()
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM basic WHERE mid='"+s+"'",null);
        if(c.moveToFirst()) {
            md=c.getString(0);
            name = c.getString(1);
            DOB = c.getString(2);
            DOT = c.getString(3);
            DOP = c.getString(4);
            Mn = c.getString(5);
            Fn = c.getString(6);
            Pno = c.getString(7);
            mailid=c.getString(8);
            c.close();
            return  true;
        }
        else
        {
            return false;
        }


    }
    public long bupdate(String mid,String bname,String bdate,String btime,String bplace,String m_name,String f_name,String phno,String bemail,String sign)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String[] whereArgs={mid};
        cv.put(myDbHelper.BMID,mid);
        cv.put(myDbHelper.BNAME,bname);
        cv.put(myDbHelper.BDATE,bdate);
        cv.put(myDbHelper.BTIME,btime);
        cv.put(myDbHelper.BPLACE,bplace);
        cv.put(myDbHelper.M_NAME,m_name);
        cv.put(myDbHelper.F_NAME,f_name);
        cv.put(myDbHelper.PHNO,phno);
        cv.put(myDbHelper.BEMAIL,bemail);
        cv.put(myDbHelper.ZSIGN,sign);
        long id2=Db.update(myDbHelper.TABLE_BASIC,cv,myDbHelper.BMID+"=?",whereArgs);
        return id2;
    }
    public String[] eupdate() {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM academic where mid='"+s+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.EMID));
            i++;
        }
        return ar;
    }
    public String[] eupdate2(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;

        //String cm=c.getColumnName(Id);
        //String cm=c.getColumnName(Id);
        c = Db.rawQuery("SELECT * FROM academic where mid='" + mid + "'", null);
        int count=c.getCount();
        int i = 0;
        ar2=new String[count*8+1];
        while(c.move(Id))
        {
            ar2[i] = c.getString(0);
            i++;
            ar2[i] = c.getString(1);
            i++;
            ar2[i] = c.getString(2);
            i++;
            ar2[i] = c.getString(3);
            i++;
            ar2[i] = c.getString(4);
            i++;
            ar2[i] = c.getString(5);
            i++;
            ar2[i] = c.getString(6);
            i++;
            ar2[i] = c.getString(7);
            i++;
        }
        return ar;
    }
    public long eupdate3(String mid,String cls,String regno,String crc, String clg,String board,String tscr,String mscr,int Id)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        long id3=0;
            ContentValues cv = new ContentValues();
            String[] whereArgs = {mid,regno};
            //int arg2=Id;
            cv.put(myDbHelper.EMID, mid);
            cv.put(myDbHelper.CLS, cls);
            cv.put(myDbHelper.REGNO, regno);
            cv.put(myDbHelper.CRC, crc);
            cv.put(myDbHelper.CLG, clg);
            cv.put(myDbHelper.BOARD, board);
            cv.put(myDbHelper.TSCR, tscr);
            cv.put(myDbHelper.MSCR, mscr);
            id3 = Db.update(myDbHelper.TABLE_ACADEMY, cv, myDbHelper.EMID + "=? AND "+myDbHelper.REGNO+"=?", whereArgs);
        return id3;
    }
    public String[] fupdate() {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM finance where mid='"+s+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.FMID));
            i++;
        }
        return ar;
    }
    public String[] fupdate2(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        //String cm=c.getColumnName(Id);
        //String cm=c.getColumnName(Id);
        c = Db.rawQuery("SELECT * FROM finance where mid='" + mid + "'", null);
        int i = 0;
        int count=c.getCount();
        ar2=new String[count*6+1];
        while(c.move(Id))
        {
            ar2[i] = c.getString(0);
            i++;
            ar2[i] = c.getString(1);
            i++;
            ar2[i] = c.getString(2);
            i++;
            ar2[i] = c.getString(3);
            i++;
            ar2[i] = c.getString(4);
            i++;
            ar2[i] = c.getString(5);
            i++;
        }
        return ar;
    }
    public long fupdate3(String mid,String acc_no,String acc_type,String amt,String interest,String date)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String[] whereArgs={mid,acc_no};
        //String[] wherArgs2={acc_no};
        cv.put(myDbHelper.FMID,mid);
        cv.put(myDbHelper.ACCNO,acc_no);
        cv.put(myDbHelper.ACCTYPE,acc_type);
        cv.put(myDbHelper.AMT,amt);
        cv.put(myDbHelper.INT,interest);
        cv.put(myDbHelper.DATE,date);
        long id6=Db.update(myDbHelper.TABLE_FINANCE,cv,myDbHelper.FMID+"=? AND "+myDbHelper.ACCNO+"=?",whereArgs);
        return id6;
    }

    public String[] astupdate() {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM assets where mid='"+s+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.AMID1));
            i++;
        }
        return ar;
    }
    public String[] astupdate2(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        int i = 0;
        //String cm=c.getColumnName(Id);
        //String cm=c.getColumnName(Id);
        c = Db.rawQuery("SELECT * FROM assets where mid='" + mid + "'", null);
        int count=c.getCount();
        ar2=new String[count*5+1];
        while(c.move(Id))
        {
            ar2[i] = c.getString(0);
            i++;
            ar2[i] = c.getString(1);
            i++;
            ar2[i] = c.getString(2);
            i++;
            ar2[i] = c.getString(3);
            i++;
            ar2[i] = c.getString(4);
            i++;
        }
        return ar;
    }
    public long astupdate3(String mid,String ades,String rtc,String area,String type)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String[] whereArgs={mid,rtc};
        cv.put(myDbHelper.AMID1,mid);
        cv.put(myDbHelper.ADES,ades);
        cv.put(myDbHelper.RTC,rtc);
        cv.put(myDbHelper.AREA,area);
        cv.put(myDbHelper.TYPE,type);
        long id5=Db.update(myDbHelper.TABLE_ASSETS,cv,myDbHelper.AMID1+"=? AND "+myDbHelper.RTC+"=?",whereArgs);
        return id5;
    }
    public String[] achupdate() {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM achieve where mid='"+s+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.AMID));
            i++;
        }
        return ar;
    }

    public String[] achupdate2(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        //String cm=c.getColumnName(Id);
        //String cm=c.getColumnName(Id);
        c = Db.rawQuery("SELECT * FROM achieve where mid='" + mid + "'", null);
        int i = 0;
        int count=c.getCount();
        ar2=new String[count*6+1];
        while(c.move(Id))
        {
            ar2[i] = c.getString(0);
            i++;
            ar2[i] = c.getString(1);
            i++;
            ar2[i] = c.getString(2);
            i++;
            ar2[i] = c.getString(3);
            i++;
            ar2[i] = c.getString(4);
            i++;
            ar2[i] = c.getString(5);
            i++;
        }
        return ar;
    }
    public long achupdate3(String mid,String des,String cat,String lvl,String plc,String hnr)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        String[] whereArgs={mid,des};
        cv.put(myDbHelper.AMID,mid);
        cv.put(myDbHelper.DES,des);
        cv.put(myDbHelper.CAT,cat);
        cv.put(myDbHelper.LVL,lvl);
        cv.put(myDbHelper.PLC,plc);
        cv.put(myDbHelper.HNR,hnr);
        long id4=Db.update(myDbHelper.TABLE_ACHIEVE,cv,myDbHelper.AMID+"=? AND "+myDbHelper.DES+"=?",whereArgs);
        return id4;

    }
    public String[] dupdate() {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM doc where mid='"+s+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.DMID));
            i++;
        }
        return ar;
    }
   /*public byte[] getpic(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        //String cm=c.getColumnName(Id);
        //String cm=c.getColumnName(Id);
        c = Db.rawQuery("SELECT NewEntry FROM doc where mid='" + mid + "'", null);
        int i = 0;
        int count=c.getCount();
        byte[] pic=new byte[count+1];
        while(c.move(Id))
        {
            pic=c.getBlob(0);
        }
        return pic;
    }*/
    public boolean dupdate2(String mid,int Id) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM doc where mid='" + mid + "'", null);
        while(c.move(Id))
        {
            dname=c.getString(1);
            dts=c.getString(2);
            dlc=c.getString(3);
            img=c.getBlob(4);
            return  true;
        }
        return  false;
    }
    public boolean AddData2(String mid,String dname,String idate,String dloc,byte[] NewEntry)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        ContentValues cv=new ContentValues();
        String[] whereArgs={mid,dname};
        cv.put(myDbHelper.DMID,mid);
        cv.put(myDbHelper.DNAME,dname);
        cv.put(myDbHelper.IDATE,idate);
        cv.put(myDbHelper.DLOC,dloc);
        cv.put(myDbHelper.IMG,NewEntry);
        long result=Db.update(myDbHelper.TABLE_DOC,cv,myDbHelper.DMID+"=? AND "+myDbHelper.DNAME+"=?",whereArgs);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Boolean resume(String mid)
    {
        SQLiteDatabase Db = myhelper.getWritableDatabase();
        Cursor c,c2,c3,c4,c5;
        c=Db.rawQuery("SELECT bname,bdate,bplace,m_name,f_name,phno,bemail FROM basic WHERE mid='"+mid+"'",null);
        if(c.moveToFirst()) {
            name = c.getString(0);
            DOB = c.getString(1);
            DOP = c.getString(2);
            MNA=c.getString(3);
            FNA=c.getString(4);
            Pno=c.getString(5);
            Bmail=c.getString(6);
            return  true;
        }
        /*c2=Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs1+"'",null);
        if(c2.moveToFirst())
        {
            sboard=c2.getString(5);
            smscr=c2.getString(7);
        }
        c3=Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs2+"'",null);
        if(c3.moveToFirst())
        {
            pboard=c3.getString(5);
            pscr=c3.getString(7);
        }
        c4=Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs3+"'",null);
        {
            dboard=c4.getString(5);
            dscr=c4.getString(7);
        }
        while (c.getCount()>=1 &&c2.getCount()>=1 &&c3.getCount()>=1 &&c4.getCount()>=1)
        {
            return  true;

        }*/return false;
    }
    public Boolean resume2(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs1+"'",null);
       if(c.moveToFirst()) {
                sboard = c.getString(0);
                smscr = c.getString(1);
                return true;
        }
        return  false;
    }
    public Boolean resume3(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs2+"'",null);
        if(c.moveToFirst()) {
            pboard = c.getString(0);
            pscr = c.getString(1);
            return true;
        }
        return  false;
    }
    public Boolean resume4(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT board,mscr FROM academic WHERE mid='"+mid+"' AND cls='"+cs3+"'",null);
        if(c.moveToFirst()) {
            dboard = c.getString(0);
            dscr = c.getString(1);
            return true;
        }
        return  false;
    }
    public Boolean resume5(String mid) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT cat,lvl,plc FROM achieve WHERE mid='" + mid + "' AND plc='" + level1 + "'", null);
        int i = 0;
        count=c.getCount();
        ach1=new String[count*3+1];
        ach2=new String[count*3+1];
        ach3=new String[count*3+1];
        while (c.moveToNext()) {
            ach1[i] = c.getString(0);
            ach2[i] = c.getString(1);
            ach3[i] = c.getString(2);
            i++;
            if(i==4 )
            {return  true;}
            //c.moveToNext();
        }

        return false;
    }
    public Boolean resume6(String mid) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT area FROM assets WHERE mid='" + mid + "'", null);
        if(c.moveToFirst())
        {
            resarea=c.getString(0);
            return true;
        }
       return false;
    }
        /*if(c.getCount()>=1)
        {
            return true;
        }
        return false;*
    }
    /*Boolean echeck(String mid)
    {
        SQLiteDatabase Db=myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM academic WHERE mid='"+mid+"'",null);
        if(c.moveToFirst())
        {
            for(int i=0;i<=c.getColumnCount();i++) {
                ar[i] = c.getString(i);
            }
            return  true;
        }
        return false;*/
    /*public Boolean resume6(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT NewEntry FROM doc WHERE mid='" + mid + "'", null);
        count=c.getCount();
        while (c.moveToFirst()) {
            photo=c.getBlob(0);
            return true;

        }
        return false;

    }*/
    public Boolean balance(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT acc_type,amt,interest,date FROM finance WHERE acc_no='" + mid + "'", null);
        if(c.moveToFirst())
        {
            AType=c.getString(0);
            bamt=c.getString(1);
            bint=c.getString(2);
            bdate=c.getString(3);
            return true;

        }
        return false;
    }

    public  Boolean bgrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM basic WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*20+1];
        while(c.moveToNext())
        {
            grd[i]="MEM ID";
            i++;
            grd[i]="NAME";
            i++;
            grd[i]="DOB";
            i++;
            grd[i]="DOT";
            i++;
            grd[i]="PLACE";
            i++;
            grd[i]="MOTHER NAME";
            i++;
            grd[i]="FATHER NAME";
            i++;
            grd[i]="PH NO";
            i++;
            grd[i]="EMAIL";
            i++;
            grd[i]="ZODIAC SIGN";
            i++;
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            grd[i]=c.getString(4);
            i++;
            grd[i]=c.getString(5);
            i++;
            grd[i]=c.getString(6);
            i++;
            grd[i]=c.getString(7);
            i++;
            grd[i]=c.getString(8);
            i++;
            grd[i]=c.getString(9);
            i++;
            return true;
        }
        return false;

    }
    public  String[] egrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM academic WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*16+1];
        grd[i]="MEM ID";
        i++;
        grd[i]="CLASS";
        i++;
        grd[i]="ROLL NO";
        i++;
        grd[i]="COURSE";
        i++;
        grd[i]="COLLEGE/SCHOOL";
        i++;
        grd[i]="BOARD/UNIVERSITY";
        i++;
        grd[i]="TOTAL MARKS";
        i++;
        grd[i]="SCORED MARKS";
        i++;
        while(c.moveToNext())
        {
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            grd[i]=c.getString(4);
            i++;
            grd[i]=c.getString(5);
            i++;
            grd[i]=c.getString(6);
            i++;
            grd[i]=c.getString(7);
            i++;

        }
        return grd;
    }
    public  String[] fgrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM finance WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*20+1];
        grd[i]="MEM ID";
        i++;
        grd[i]="ACCOUNT NUMBER";
        i++;
        grd[i]="ACCOUNT TYPE";
        i++;
        grd[i]="BALANCE";
        i++;
        grd[i]="INTEREST";
        i++;
        grd[i]="DATE";
        i++;
        while(c.moveToNext())
        {
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            grd[i]=c.getString(4);
            i++;
            grd[i]=c.getString(5);
            i++;

        }
        return grd;
    }
    public  String[] agrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM assets WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*20+1];
        grd[i]="MEM ID";
        i++;
        grd[i]="DESCRIPTION";
        i++;
        grd[i]="RTC NO";
        i++;
        grd[i]="AREA ADDRESS";
        i++;
        grd[i]="AREA TYPE";
        i++;
        while(c.moveToNext())
        {
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            grd[i]=c.getString(4);
            i++;

        }
        return grd;
    }
    public  String[] achgrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM achieve WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*20+1];
        grd[i]="MEM ID";
        i++;
        grd[i]="DESCRIPTION";
        i++;
        grd[i]="CATEGORY";
        i++;
        grd[i]="LEVEL";
        i++;
        grd[i]="SECURED PLACE";
        i++;
        grd[i]="HONOURED BY";
        i++;
        while(c.moveToNext())
        {
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            grd[i]=c.getString(4);
            i++;
            grd[i]=c.getString(5);
            i++;

        }
        return grd;
    }
    public  String[] dgrid(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM doc WHERE mid='" + mid + "'", null);
        int count=c.getCount();
        int i=0;
        int x=0;
        grd=new String[count*5+1];
        while(c.moveToNext())
        {
            grd[i]=c.getString(0);
            i++;
            grd[i]=c.getString(1);
            i++;
            grd[i]=c.getString(2);
            i++;
            grd[i]=c.getString(3);
            i++;
            //img=c.getBlob(4);

        }
        return grd;
    }
    public String[] search()
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM basic", null);
        int count = c.getCount();
        int i = 0;
        ar=new String[count+1];
        ar[0]="   SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.BMID));
            i++;
        }
        return ar;
    }
    public String getZod(String mid)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT sign FROM basic WHERE mid='"+mid+"'", null);
        if(c.moveToFirst())
        {
           zs=c.getString(0);
        }
        return  zs;
    }
    public String[] dget(String memid) {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c = Db.rawQuery("SELECT * FROM doc where mid='"+memid+"'", null);
        int count = c.getCount();
        int i = 0;
        ar = new String[count + 1];
        ar[0] = "             SELECT";
        i++;
        while (c.moveToNext()) {
            ar[i] = c.getString(c.getColumnIndex(myDbHelper.DMID));
            i++;
        }
        return ar;
    }

    public boolean addcheck(String usr)
    {
        SQLiteDatabase Db = myhelper.getReadableDatabase();
        Cursor c;
        c=Db.rawQuery("SELECT email FROM reg",null);
        c.moveToLast();
        au=c.getString(0);
        usr=s;
        if(au.equals(usr))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    static class myDbHelper extends SQLiteOpenHelper
    {

        private static final String DATABASE_NAME="mydatabase";
        private static final String TABLE_NAME="reg";
        private static final String DATABASE_TYPE="RDBMS";
        private static final String pgm="PRAGMA foreign_keys=ON;";
        private static final int DATABASE_Version=10;
        private static final String FNAME="fname";
        private static final String LNAME="lname";
        private static final String NAME="email";
        private static final String PASSWORD="password";
        private static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+"("+FNAME+" VARCHAR(255) ,"+LNAME+" VARCHAR(255),"+NAME+" VARCHAR(255) PRIMARY KEY,"+PASSWORD+" VARCHAR(255));";
        private static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        private static final String TABLE_BASIC="basic";
        private static final String BMID="mid";
        private static final String BNAME="bname";
        private static final String BDATE="bdate";
        private static final String BTIME="btime";
        private static final String BPLACE="bplace";
        private static final String M_NAME="m_name";
        private static final String F_NAME="f_name";
        private static final String PHNO="phno";
        private static final String BEMAIL="bemail";
        private static final String ZSIGN="sign";
        private static final String CREATE_TABLE2="CREATE TABLE "+TABLE_BASIC+"("+BMID+" VARCHAR(255) PRIMARY KEY,"+BNAME+" VARCHAR(255),"+BDATE+" VARCHAR(255),"+BTIME+" VARCHAR(255),"+BPLACE+" VARCHAR(255), "+M_NAME+" VARCHAR(255),"+F_NAME+" VARCHAR(255),"+PHNO+" VARCHAR(255),"+BEMAIL+" VARCHAR(255),"+ZSIGN+" VARCHAR(255))";
        private static final String DROP_TABLE2="DROP TABLE IF EXISTS "+TABLE_BASIC;

        private static final String TABLE_ACADEMY="academic";
        private static final String EMID="mid";
        private static final String CLS="cls";
        private static final String REGNO="regno";
        private static final String CRC="crc";
        private static final String CLG="clg";
        private static final String BOARD="board";
        private static final String TSCR="tscr";
        private static final String MSCR="mscr";
        private static final String CREATE_TABLE3="CREATE TABLE "+TABLE_ACADEMY+"("+EMID+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+") ON DELETE CASCADE,"+CLS+" VARCHAR(255),"+REGNO+" VARCHAR(255) PRIMARY KEY,"+CRC+" VARCHAR(255),"+CLG+" VARCHAR(255),"+BOARD+" VARCHAR(255),"+TSCR+" VARCHAR(255),"+MSCR+" VARCHAR(255))";
        /*private static final String CREATE_TABLE3 = "CREATE TABLE "+TABLE_ACADEMY+" ("+CLS+ " VARCHAR(255), "+REGNO+ " VARCHAR(255), "+CRC+ " VARCHAR(255), "+CLG+ " VARCHAR(255),"+BOARD+" VARCHAR(255),"+TSCR+" VARCHAR(255),"+MSCR+" VARCHAR(255),"+EMID+" VARCHAR(255)," + " FOREIGN KEY ("+EMID+") REFERENCES "+TABLE_BASIC+"("+BMID+"));";*/
        private static final String DROP_TABLE3="DROP TABLE IF EXISTS "+TABLE_ACADEMY;


        private static final String TABLE_ACHIEVE="achieve";
        private static final String AMID="mid";
        private static final String DES="des";
        private static final String CAT="cat";
        private static final String LVL="lvl";
        private static final String PLC="plc";
        private static final String HNR="hnr";
        private static final String CREATE_TABLE4="CREATE TABLE "+TABLE_ACHIEVE+"("+AMID+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+") ON DELETE CASCADE,"+DES+" VARCHAR(255) PRIMARY KEY,"+CAT+" VARCHAR(255),"+LVL+" VARCHAR(255),"+PLC+" VARCHAR(255),"+HNR+" VARCHAR(255))";

        /*private static final String CREATE_TABLE4 = "CREATE TABLE "+TABLE_ACHIEVE+"("+DES+ " VARCHAR(255), "+CAT+ " VARCHAR(255), "+LVL+ " VARCHAR(255), "+PLC+ " VARCHAR(255),"+HNR+" VARCHAR(255),"+AMID+ " VARCHAR(255)," + " FOREIGN KEY ("+AMID+") REFERENCES "+TABLE_BASIC+"("+BMID+"));";*/
        private static final String DROP_TABLE4="DROP TABLE IF EXISTS "+TABLE_ACHIEVE;

        private static final String TABLE_ASSETS="assets";
        private static final String AMID1="mid";
        private static final String ADES="ades";
        private static final String RTC="rtc";
        private static final String AREA="area";
        private static final String TYPE="type";
       //private static final String CREATE_TABLE5="CREATE TABLE "+TABLE_ASSETS+"("+A_MID+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+"),"+ADES+" VARCHAR(255),"+RTC+" VARCHAR(255),"+AREA+" VARCHAR(255),"+TYPE+" VARCHAR(255))";
        /*private static final String CREATE_TABLE5 = "CREATE TABLE "+ TABLE_ASSETS + " ("+ ADES + " VARCHAR(255), "+ RTC + " VARCHAR(255), "+ AREA + " VARCHAR(255), "+ TYPE + " VARCHAR(255)," + A_MID + " VARCHAR(255)," + " FOREIGN KEY ("+A_MID+") REFERENCES "+TABLE_BASIC+"("+BMID+"));";*/

        /*private static final String CREATE_TABLE5 = "CREATE TABLE "+ TABLE_ASSETS + " ("+ ADES + " VARCHAR(255), "+ RTC + " VARCHAR(255), "+ AREA + " VARCHAR(255), "+ TYPE + " VARCHAR(255)," + A_MID + " VARCHAR(255)," + " FOREIGN KEY ("+A_MID+") REFERENCES "+TABLE_BASIC+"("+BMID+"));";*/
       private static final String CREATE_TABLE5= "CREATE TABLE "
                + TABLE_ASSETS + "(" +AMID1+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+") ON DELETE CASCADE, "+ ADES
                + " VARCHAR(255), " + RTC
                + " VARCHAR(255) PRIMARY KEY, " + AREA + " VARCHAR(255), "+TYPE+ " VARCHAR(255));";
        private static final String DROP_TABLE5="DROP TABLE IF EXISTS "+TABLE_ASSETS;


        private static final String TABLE_FINANCE="finance";
        private static final String FMID="mid";
        private static final String ACCNO="acc_no";
        private static final String ACCTYPE="acc_type";
        private static final String AMT="amt";
        private static final String INT="interest";
        private static final String DATE="date";
        //private static final String CREATE_TABLE6="CREATE TABLE "+TABLE_FINANCE+"("+FMID+" VARCHAR(255),"+ACCNO+" VARCHAR(255),"+ACCTYPE+" VARCHAR(255),"+AMT+" VARCHAR(255),"+INT+" VARCHAR(255),"+DATE+" )";
        private static final String CREATE_TABLE6="CREATE TABLE "+TABLE_FINANCE+"("+FMID+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+") ON DELETE CASCADE,"+ACCNO+" VARCHAR(255) PRIMARY KEY,"+ACCTYPE+" VARCHAR(255),"+AMT+" VARCHAR(255),"+INT+" VARCHAR(255),"+DATE+" VARCHAR(255))";
       /* private static final String CREATE_TABLE6= "CREATE TABLE "+ TABLE_FINANCE + " ("+ACCNO+ " VARCHAR(255), "+ACCTYPE+ " VARCHAR(255), "+AMT+ " VARCHAR(255), "+INT+ " VARCHAR(255),"+DATE+" VARCHAR(255),"+ FMID + " VARCHAR(255)," + " FOREIGN KEY ("+FMID+") REFERENCES "+TABLE_BASIC+"("+BMID+"));";*/
        private static final String DROP_TABLE6="DROP TABLE IF EXISTS "+TABLE_FINANCE;


        private static final String TABLE_DOC="doc";
        private static final String DMID="mid";
        private static final String DNAME="dname";
        private static final String IDATE="idate";
        private static final String DLOC="dloc";
        private static final String IMG="NewEntry";
        private static final String CREATE_TABLE7="CREATE TABLE "+TABLE_DOC+"("+DMID+" VARCHAR(255) REFERENCES "+TABLE_BASIC+"("+BMID+") ON DELETE CASCADE,"+DNAME+" VARCHAR(255) PRIMARY KEY,"+IDATE+" VARCHAR(255),"+DLOC+" VARCHAR(255),"+IMG+" blob)";
        private static final String DROP_TABLE7="DROP TABLE IF EXISTS "+TABLE_DOC;
       // private static final String PGM="PRAGMA foreign_keys=ON";


        public myDbHelper(Context context)
        {
            super(context,DATABASE_NAME,null,DATABASE_Version);
            this.context=context;

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try
            {
                db.execSQL(CREATE_TABLE);
                db.execSQL(CREATE_TABLE2);
                db.execSQL(CREATE_TABLE3);
                db.execSQL(CREATE_TABLE4);
                db.execSQL(CREATE_TABLE5);
                db.execSQL(CREATE_TABLE6);
                db.execSQL(CREATE_TABLE7);

            }
            catch(Exception e)
            {
                Message.message(context,""+e);

            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                db.execSQL(DROP_TABLE2);
                db.execSQL(DROP_TABLE3);
                db.execSQL(DROP_TABLE4);
                db.execSQL(DROP_TABLE5);
                db.execSQL(DROP_TABLE6);
                db.execSQL(DROP_TABLE7);

                onCreate(db);

            }catch(Exception e)
            {
                Message.message(context,""+e);

            }

        }

        @Override
        public void onConfigure(SQLiteDatabase db) {
            super.onConfigure(db);
            db.setForeignKeyConstraintsEnabled(true);
        }
    }
}
