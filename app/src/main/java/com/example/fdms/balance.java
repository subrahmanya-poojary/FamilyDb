package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class balance extends AppCompatActivity {

    myDbAdapter helper;
    EditText e1,e2;
    double bal;
    String sa="Savings Account";
    String ca="Current Account";
    String fa="Fixed Account";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        helper = new myDbAdapter(this);
        e1 = (EditText) findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);



    }
    /*public void bal(View view)
    {

                String d1=dd.format(ds);
                String d2 = dd.format(c);
                finance fc=new finance();
            Message.message(getApplicationContext(),""+d1);
            Message.message(getApplicationContext(),""+d2);
                //String d1=fc.ds;

               /* Date date1 = null;
                Date date2 = null;
                date1 = dd.parse(d1);
                date2 = dd.parse(d2);

                long diff =date2.getTime()-date1.getTime();

                Message.message(getApplicationContext(),"Difference "+diff);
               // Message.message(getApplicationContext(),""+d1);
               // Message.message(getApplicationContext(),""+d2);
                //long diffDays = diff / (24 * 60 * 60 * 1000);

          /*  }catch (Exception e)
            {
                Message.message(getApplicationContext(),""+e);
            }*/

    public void bal(View view) {
        String mid = e1.getText().toString();
        Boolean b1 = helper.balance(mid);
        if (b1 == true) {
            double amt=Double.parseDouble(helper.bamt);
            double rate=Double.parseDouble(helper.bint);
            e2.setText(""+helper.AType);
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
            String dte1 = dd.format(c);
            String ds = helper.bdate;
            Date date1, date2,date3,date4;
            date1 = null;
            date2 = null;
            date3=null;
            date4=null;
            try {
                date1 = dd.parse(dte1);
                date2 = dd.parse(ds);
                date3=dd.parse(dte1);
                date4=dd.parse(ds);

            } catch (ParseException e) {

                e.printStackTrace();
            }
            long diff=date1.getTime()-date2.getTime();
            long days=diff / (24 * 60 * 60 * 1000);
            long diff2=date4.getTime()-date3.getTime();
            long days2=diff2 / (24 * 60 * 60 * 1000);
            long months=(long)days2/30;
            //.message(getApplicationContext(),""+months);
            if(helper.AType.equals("Savings Account"))
            {
                bal = (amt) * (rate) * (days / 365);
            }
            else if(helper.AType.equals("Fixed Account"))
            {
                bal=(amt)*(1+(rate/25));
                double x=Math.round(bal*100.0)/100.0;
                //String bal2=String.valueOf(x);
                long fd=(long)x;
                double fd2=(4*days);
                double y=Math.round(fd2*100.0)/100.0;
                //String fd3=String.valueOf(y);
                long bal4=(long)y;
                long fdb=fd^bal4;
                Message.message(getApplicationContext(),""+fdb);
                //E = P x r x (1+r)^n/((1+r)^n – 1)
            }
            else if(helper.AType.equals("Loan"))
            {
                double rte=(double)rate;
                double x=(double) amt;
                double y=(double)rate;
                //long z=(y/12)*100;
                //long EMI=x*y*(1+y)^months/((1+y)^months-1);
                double ls=(double)(y/months)*x;
                long Total=(long)(ls+x);
                long EMI=(long)(Total/12);
                //double ls2=ls*x;
                Message.message(getApplicationContext(),""+EMI);
                //Message.message(getApplicationContext(),""+y);
                //Message.message(getApplicationContext(),""+months);
                //Message.message(getApplicationContext(),""+LI);
            }
            /*String ch=e2.getText().toString();
                bal = (amt) * (rate) * (days / 365);
                Message.message(getApplicationContext(), "" + bal);
           /* Message.message(getApplicationContext(),""+amt);
            Message.message(getApplicationContext(),""+rate);
            Message.message(getApplicationContext(),""+days);
            Message.message(getApplicationContext(),""+bal);
            //Message.message(getApplicationContext(),""+dte1);*/

            /*String d2 = dd.format(ds);
            try {
                date1 = dd.parse(dte1);
                date2 = dd.parse(d2);
            } catch (ParseException e) {

                e.printStackTrace();
            }
            Message.message(getApplicationContext(), "" + dte1);
            Message.message(getApplicationContext(), "" + d2);*/
        }
    }
}
















            /*String d2 = dd.format(ds);
            Date date1=null;
            date1=dd.parse(d1);
            String d1 = dd.format(ds);
            String d2 = dd.format(c);
            //finance fc = new finance();
            Date date1 = null;
            Date date2 = null;
            date1=dd.
            date1 = dd.parse(d1);
            date2 = dd.parse(d2);
            Message.message(getApplicationContext(), "" + d1);
            Message.message(getApplicationContext(), "" + d2);
        }
    }*/


