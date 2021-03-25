package com.example.fdms.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fdms.MainActivity;
import com.example.fdms.Message;
import com.example.fdms.R;
import com.example.fdms.academic;
import com.example.fdms.myDbAdapter;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class SlideshowFragment extends Fragment {
    EditText e1;
    ImageView b1;
    myDbAdapter helper;
    double bal;
    public long EMI;

    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        e1=(EditText)root.findViewById(R.id.editText1);
        b1=(ImageView) root.findViewById(R.id.button1);
        helper = new myDbAdapter(getContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mid = e1.getText().toString();
                Boolean b1 = helper.balance(mid);
                if (b1 == true) {
                    double amt = Double.parseDouble(helper.bamt);
                    double rate = Double.parseDouble(helper.bint);
                    //e2.setText(""+helper.AType);
                    Date c = Calendar.getInstance().getTime();
                    SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
                    String dte1 = dd.format(c);
                    String ds = helper.bdate;
                    Date date1, date2, date3, date4;
                    date1 = null;
                    date2 = null;
                    date3 = null;
                    date4 = null;
                    try {
                        date1 = dd.parse(dte1);
                        date2 = dd.parse(ds);
                        date3 = dd.parse(dte1);
                        date4 = dd.parse(ds);

                    } catch (ParseException e) {

                        e.printStackTrace();
                    }
                    long diff = date1.getTime() - date2.getTime();
                    long days = diff / (24 * 60 * 60 * 1000);
                    long diff2 = date4.getTime() - date3.getTime();
                    long days2 = diff2 / (24 * 60 * 60 * 1000);
                    long months = (long) days2 / 30;
                    long year=(long)months/12;
                    Integer yy=(int)(long)year;
                    if (helper.AType.equals("Savings Account")) {
                        //double amt = Double.parseDouble(helper.bamt);
                        //double rate = Double.parseDouble(helper.bint);
                        //double rate2=rate/100;
                        /*long x=Long.parseLong(helper.bamt);
                        long y=Long.parseLong(helper.bint);*/
                        double day=(double)days;
                        double time=(double)(day/365);
                        //Message.message(getContext(),""+time);
                        bal =((amt) * ((rate)/100) * (time));
                        double roundOff = Math.round(bal*100)/100;
                        double tot=amt+roundOff;
                        double total=Math.round(tot*100)/100;
                        SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                        pDialog.setTitleText("Savings Balance is:");
                        pDialog.setContentText(""+amt+"+"+roundOff+"="+total);
                        pDialog.setConfirmText("OK");
                        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                Intent i1=new Intent(getActivity(), MainActivity.class);
                                startActivity(i1);
                                getActivity().finish();
                            }
                        });
                        pDialog.setCancelable(false);
                        pDialog.show();
                        //Message.message(getContext(),""+roundOff);
                    } else if (helper.AType.equals("Fixed Account")) {
                        if(year==0)
                        {
                            Message.message(getContext(),"Minimum 1 Year Gap Required");
                        }
                        else
                        {
                            amt = Double.parseDouble(helper.bamt);
                            rate = Double.parseDouble(helper.bint);
                            Double rate2=rate/100;
                            Double A= Math.pow((1+(rate2/1)),yy);
                            Double B=amt*A;
                            double fds=Math.round(B*100)/100;
                            //Double fds=A*yy;
                           /* String B=(A).toString();
                            Integer D=(B);
                            Integer E=(D)^yy;
                            String fds=E.toString();*/
                            //Message.message(getContext(),""+inst);
                            SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                            pDialog.setTitleText("Fixed Account Balance");
                            pDialog.setContentText(""+fds);
                            pDialog.setConfirmText("OK");
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent i1=new Intent(getActivity(), MainActivity.class);
                                    startActivity(i1);
                                    getActivity().finish();
                                }
                            });
                            pDialog.setCancelable(false);
                            pDialog.show();
                           // Message.message(getContext(),""+Tot);
                        }

                        //Message.message(getApplicationContext(),""+fdb);
                        //E = P x r x (1+r)^n/((1+r)^n – 1)
                    } else if (helper.AType.equals("Loan")) {
                        //double rte = (double) rate;
                        if(months==0)
                        {
                            Message.message(getContext(),"Minimum 1 Month Gap Required");
                        }
                        else
                        {
                            double x = (double) amt;
                            double y = (double) rate;
                            double z=y/100;
                            //long z=(y/12)*100;
                            //long EMI=x*y*(1+y)^months/((1+y)^months-1);
                            double ls = (double) (z/ months)*amt;

                            double Total = (double) (ls + x);
                            double Total2=Math.round(Total*100.0)/100.0;
                            EMI = (long) (Total2 / months);
                            final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                            pDialog.setTitleText("Total Payable Loan Amount is");
                            pDialog.setContentText(""+Total2);
                            pDialog.setConfirmText("Check Monthly Payment");
                            pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    pDialog.dismiss();
                                    SweetAlertDialog pDialog2 = new SweetAlertDialog(getContext(), SweetAlertDialog.NORMAL_TYPE);
                                    pDialog2.setTitleText("Loan EMI");
                                    pDialog2.setContentText(""+EMI+".00");
                                    pDialog2.setConfirmText("OK");
                                    pDialog2.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                            Intent i1=new Intent(getActivity(), MainActivity.class);
                                            startActivity(i1);
                                            getActivity().finish();
                                        }
                                    });
                                    pDialog2.show();
                                    pDialog2.setCancelable(false);
                                }
                            });
                            pDialog.show();
                        }

                        //double ls2=ls*x;
                        //Message.message(getApplicationContext(),""+EMI);
                        //Message.message(getApplicationContext(),""+y);
                        //Message.message(getApplicationContext(),""+months);
                        //Message.message(getApplicationContext(),""+LI);
                    }
                    else
                    {
                        Message.message(getContext(),"please check your account type");
                    }
                }
                else
                {
                    SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Error");
                    pDialog.setContentText("Member Finance Details Not Found");
                    pDialog.setConfirmText("OK");
                    pDialog.setCancelable(false);
                    pDialog.show();
                }
            }
        });
        return root;
    }
}
