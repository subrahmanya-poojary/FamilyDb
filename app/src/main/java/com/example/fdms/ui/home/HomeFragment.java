package com.example.fdms.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.fdms.Message;
import com.example.fdms.R;
import com.example.fdms.academic;
import com.example.fdms.academic_delete;
import com.example.fdms.academic_update;
import com.example.fdms.achieve;
import com.example.fdms.achieve_delete;
import com.example.fdms.achieve_update;
import com.example.fdms.assets;
import com.example.fdms.assets_delete;
import com.example.fdms.assets_update;
import com.example.fdms.basic;
import com.example.fdms.basic_delete;
import com.example.fdms.basic_update;
import com.example.fdms.document;
import com.example.fdms.document_delete;
import com.example.fdms.document_update;
import com.example.fdms.finance;
import com.example.fdms.finance_delete;
import com.example.fdms.finance_update;
import com.example.fdms.global;

public class HomeFragment extends Fragment {
    public String s;
    TextView t1;
    ImageView im2,im3,im4,im5;
    CardView c1,c2,c3,c4,c5,c6;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageView im=rootView.findViewById(R.id.imageView2);
        im2=rootView.findViewById(R.id.imageView6);
        im3=rootView.findViewById(R.id.imageView8);
        im4=rootView.findViewById(R.id.imageView10);
        im5=rootView.findViewById(R.id.imageView12);
        c1=(CardView)rootView.findViewById(R.id.cd1);
        c2=(CardView)rootView.findViewById(R.id.cd2);
        c3=(CardView)rootView.findViewById(R.id.cd3);
        c4=(CardView)rootView.findViewById(R.id.cd4);
        c5=(CardView)rootView.findViewById(R.id.cd5);
        c6=(CardView)rootView.findViewById(R.id.cd6);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),basic.class);
                startActivity(i1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),academic.class);
                startActivity(i1);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),document.class);
                startActivity(i1);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),finance.class);
                startActivity(i1);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),assets.class);
                startActivity(i1);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1=new Intent(getActivity(),achieve.class);
                startActivity(i1);
            }
        });
        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), basic_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), basic_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });

        ImageView im1=rootView.findViewById(R.id.imageView4);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), document_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), document_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), academic_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), academic_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), finance_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), finance_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), assets_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), assets_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.menu_main, popup.getMenu());
                popup.show();
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.popUpdate:
                                //getActivity().finish();
                                Intent i=new Intent(getContext(), achieve_update.class);
                                startActivity(i);
                                break;
                            case R.id.popDelete:
                                Intent i2=new Intent(getContext(), achieve_delete.class);
                                startActivity(i2);
                                break;

                        }
                        return true;
                    }
                });
            }
        });
        return rootView;

    }
 /*  public void option1(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }*/
    public void option2(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }
    public void option3(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }
    public void option4(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }
    public void option5(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }
    public void option6(View view)
    {
        PopupMenu popup = new PopupMenu(getContext(), view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_main, popup.getMenu());
        popup.show();
    }

   /* public void basic(View view)
    {
        try {
            Intent i1 = new Intent(getActivity().getApplication(), basic.class);
            startActivity(i1);
        }catch (Exception e)
        {
            Message.message(getContext(),""+e);
        }
    }*/
}