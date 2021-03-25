package com.example.fdms.ui.tools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.fdms.BookAdapter;
import com.example.fdms.Message;
import com.example.fdms.R;
import com.example.fdms.doc_view;
import com.example.fdms.myDbAdapter;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ToolsFragment extends Fragment{
    EditText e1;
    String mid;
    ImageView btn;
    Spinner s1;
    GridView g1;
    FrameLayout f1;
    HorizontalScrollView hs;
    myDbAdapter helper;
    public String ch;
    String[] members;
    Spinner s2;
    String[] search={"BASIC DETAILS","ACADEMIC DETAILS","DOCUMENT DETAILS","FINANCE DETAILS","ASSETS DETAILS","ACHIEVEMENT DETAILS"};

    private ToolsViewModel toolsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        g1 = (GridView) root.findViewById(R.id.gridView1);
        f1=(FrameLayout)root.findViewById(R.id.frameLayout);
        hs=(HorizontalScrollView)root.findViewById(R.id.horizontalScrollView1);
        s2=(Spinner)root.findViewById(R.id.spinner2);
        g1.setVisibility(View.INVISIBLE);
        f1.setVisibility(View.INVISIBLE);
        hs.setVisibility(View.INVISIBLE);
        TextView ts=(TextView)root.findViewById(R.id.textView30);
        btn = (ImageView) root.findViewById(R.id.button1);
        e1=(EditText)root.findViewById(R.id.editText1);
        s1=(Spinner)root.findViewById(R.id.spinner1);
        helper=new myDbAdapter(getContext());

        members=helper.search();

        ArrayAdapter a = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,search);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s2.setAdapter(a);
        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ch=search[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter aa = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,members);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        s1.setAdapter(aa);
        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mid=members[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String mid=e1.getText().toString();
                if(mid.equals(""+members[0]))
                {
                   // Message.message(getContext(),"eroor");
                    SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                    pDialog.setTitleText("Error");
                    pDialog.setContentText("Select Mem Id");
                    pDialog.setConfirmText("OK");
                    pDialog.show();;
                }
                else
                {
                    if(ch=="BASIC DETAILS") {
                        Boolean b1 = helper.bgrid(mid);
                        if (b1 == true) {
                            g1.setVisibility(View.VISIBLE);
                            f1.setVisibility(View.VISIBLE);
                            hs.setVisibility(View.VISIBLE);
                            btn.setVisibility(View.INVISIBLE);
                            s2.setVisibility(View.INVISIBLE);
                            //Intent i1=new Intent(getActivity(), grid.class);
                            //startActivity(i1);
                            Context ct;
                            ct = getContext();
                            g1.setNumColumns(10);
                            String[] d = helper.grd;
                            g1.setPadding(10, 10, 10, 10);
                            g1.setBackgroundColor(Color.CYAN);
                            BookAdapter booksAdapter = new BookAdapter(ct, d);
                            g1.setAdapter(booksAdapter);

                        }
                    }else if(ch=="ACADEMIC DETAILS") {
                        g1.setNumColumns(8);
                        g1.setVisibility(View.VISIBLE);
                        f1.setVisibility(View.VISIBLE);
                        hs.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        s2.setVisibility(View.INVISIBLE);
                        try{
                        String[] b1 = helper.egrid(mid);
                        if(b1.length==0) {
                            Message.message(getContext(),"eroor");

                        }
                        else {
                                //Intent i1=new Intent(getActivity(), grid.class);
                                //startActivity(i1);
                                Context ct;
                                ct = getContext();
                                //g1.setNumColumns(10);
                                String[] d = b1;
                                g1.setPadding(10, 10, 10, 10);
                                g1.setBackgroundColor(Color.CYAN);
                                BookAdapter booksAdapter = new BookAdapter(ct, d);
                                g1.setAdapter(booksAdapter);
                            }
                        } catch (Exception e) {
                            SweetAlertDialog s = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                            s.setTitleText("Error");
                            s.setContentText("Academic Details Not Found");
                            s.setConfirmText("OK");
                            btn.setVisibility(View.VISIBLE);
                            s2.setVisibility(View.VISIBLE);
                            g1.setVisibility(View.INVISIBLE);
                            f1.setVisibility(View.INVISIBLE);
                            hs.setVisibility(View.INVISIBLE);
                            s.setCancelable(false);
                            s.show();
                        }
                    }
                    else if(ch=="FINANCE DETAILS")
                    {
                        g1.setNumColumns(6);
                        g1.setVisibility(View.VISIBLE);
                        f1.setVisibility(View.VISIBLE);
                        hs.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        s2.setVisibility(View.INVISIBLE);
                        try{
                        String[] b1 = helper.fgrid(mid);
                        if(b1==null) {
                            Message.message(getContext(),"eroor");

                        }
                        else {
                                Context ct;
                                ct = getContext();
                                String[] d = b1;
                                g1.setPadding(10, 10, 10, 10);
                                g1.setBackgroundColor(Color.CYAN);
                                BookAdapter booksAdapter = new BookAdapter(ct, d);
                                g1.setAdapter(booksAdapter);
                            }
                        }catch (Exception e) {
                            SweetAlertDialog s = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                            s.setTitleText("Error");
                            s.setContentText("Financial Details Not Found");
                            s.setConfirmText("OK");
                            btn.setVisibility(View.VISIBLE);
                            s2.setVisibility(View.VISIBLE);
                            g1.setVisibility(View.INVISIBLE);
                            f1.setVisibility(View.INVISIBLE);
                            hs.setVisibility(View.INVISIBLE);
                            s.setCancelable(false);
                            s.show();
                        }
                    }
                    else if(ch=="ASSETS DETAILS")
                    {
                        g1.setNumColumns(5);
                        g1.setVisibility(View.VISIBLE);
                        f1.setVisibility(View.VISIBLE);
                        hs.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        s2.setVisibility(View.INVISIBLE);
                        try {
                            String[] b1 = helper.agrid(mid);
                            if (b1 == null) {
                                Message.message(getContext(), "eroor");

                            } else {

                                //Intent i1=new Intent(getActivity(), grid.class);
                                //startActivity(i1);
                                Context ct;
                                ct = getContext();
                                //g1.setNumColumns(10);
                                String[] d = b1;
                                g1.setPadding(10, 10, 10, 10);
                                g1.setBackgroundColor(Color.CYAN);
                                BookAdapter booksAdapter = new BookAdapter(ct, d);
                                g1.setAdapter(booksAdapter);
                            }
                        }catch (Exception e)
                        {
                            SweetAlertDialog s=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            s.setTitleText("Error");
                            s.setContentText("Assets Details Not Found");
                            s.setConfirmText("OK");
                            btn.setVisibility(View.VISIBLE);
                            s2.setVisibility(View.VISIBLE);
                            g1.setVisibility(View.INVISIBLE);
                            f1.setVisibility(View.INVISIBLE);
                            hs.setVisibility(View.INVISIBLE);
                            s.setCancelable(false);
                            s.show();
                        }
                    }
                    else if(ch=="ACHIEVEMENT DETAILS")
                    {
                        g1.setNumColumns(6);
                        g1.setVisibility(View.VISIBLE);
                        f1.setVisibility(View.VISIBLE);
                        hs.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.INVISIBLE);
                        s2.setVisibility(View.INVISIBLE);
                        try{
                        String[] b1 = helper.achgrid(mid);
                        if(b1==null) {
                            Message.message(getContext(),"eroor");

                        }
                        else {
                                //Intent i1=new Intent(getActivity(), grid.class);
                                //startActivity(i1);
                                Context ct;
                                ct = getContext();
                                //g1.setNumColumns(10);
                                String[] d = b1;
                                g1.setPadding(10, 10, 10, 10);
                                g1.setBackgroundColor(Color.CYAN);
                                BookAdapter booksAdapter = new BookAdapter(ct, d);
                                g1.setAdapter(booksAdapter);
                            }
                        } catch (Exception e) {
                            SweetAlertDialog s = new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE);
                            s.setTitleText("Error");
                            s.setContentText("Achievement Details Not Found");
                            s.setConfirmText("OK");
                            btn.setVisibility(View.VISIBLE);
                            s2.setVisibility(View.VISIBLE);
                            g1.setVisibility(View.INVISIBLE);
                            f1.setVisibility(View.INVISIBLE);
                            hs.setVisibility(View.INVISIBLE);
                            s.setCancelable(false);
                            s.show();
                        }
                    }
                    else if(ch=="DOCUMENT DETAILS")
                    {
                        String mid2=s1.getSelectedItem().toString();
                        SharedPreferences sd = PreferenceManager.getDefaultSharedPreferences(getContext());
                        sd.edit().putString("MID",mid2).commit();
                        Intent i=new Intent(getActivity(), doc_view.class);
                        startActivity(i);
                    }
                }
            }
        });

        return root;
    }
}