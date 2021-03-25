package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;

public class grid extends AppCompatActivity {
    GridView g1;
    myDbAdapter helper;
    EditText e1;
    String mid;
    //EditText e1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        //g1=(GridView)findViewById(R.id.gridView1);
        e1=(EditText)findViewById(R.id.editText1);
        Horoscope h=new Horoscope();
        e1.setText(""+h.hp);
        /*helper=new myDbAdapter(this);
        Context ct;
        ct = this;
        //g1.setNumColumns(10);
        /*String[] d=helper.grd;
        g1.setPadding(10, 10, 10, 10);
        g1.setBackgroundColor(Color.CYAN);
        BookAdapter booksAdapter = new BookAdapter(ct,d);
        g1.setAdapter(booksAdapter);
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        mid=e1.getText().toString();
        helper=new myDbAdapter(this);*/

        //e1=(EditText)findViewById(R.id.editText1);

    }
    /*public void Grid(View view)
    {
        String sign=helper.getZod(mid);
        if(sign!=null)
        {
            Message.message(getApplicationContext(),""+helper.zs);
        }
        else
        {
            Message.message(getApplicationContext(),"Error");
        }
    }
   /* public void Grid(View view)
    {
        String mid=e1.getText().toString();
        if(mid.isEmpty())
        {
            Message.message(getApplicationContext(),"Enter mid");
        }
        else
        {
            Boolean b=helper.bgrid(mid);
            if(b==true)
            {


            }
        }

    }*/
}
