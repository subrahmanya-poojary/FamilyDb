package com.example.fdms.ui.share;

import android.content.Intent;
import android.graphics.drawable.Drawable;
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

import com.example.fdms.Horoscope;
import com.example.fdms.MainActivity;
import com.example.fdms.Message;
import com.example.fdms.R;
import com.example.fdms.global;
import com.example.fdms.myDbAdapter;

import bot.box.horology.annotation.DURATION;
import bot.box.horology.annotation.SUNSIGN;
import bot.box.horology.delegate.Response;
import bot.box.horology.hanshake.HorologyController;
import bot.box.horology.pojo.Zodiac;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ShareFragment extends Fragment {
    EditText e1;
    myDbAdapter helper;
    String mid;
    TextView t1,t2;
    ImageView im;
    Button b1,b2;
    String z;
    String[] zodiac={"Aries","Taurus","Gemini","Cancer","Leo","Virgo","Libra","Scorpio","Sagittarius","Capricorn","Aquarius","Pisces"};

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        e1=(EditText)root.findViewById(R.id.editText1);
        t1=(TextView)root.findViewById(R.id.textView2);
        t2=(TextView)root.findViewById(R.id.textView1);
        im=(ImageView)root.findViewById(R.id.imageView1);
        b1=(Button)root.findViewById(R.id.button1);
        //b2=(Button)root.findViewById(R.id.button2);
        helper=new myDbAdapter(getContext());
        global g=global.getInstance();
        String s=g.getData();
        e1.setText(""+s);
        e1.setKeyListener(null);
        mid=e1.getText().toString();
        String sign=helper.getZod(mid);
        if(sign!=null)
        {
            if(sign.equals("Aries"))
            {
                t1.setText(""+sign+"(ಮೇಷ)");
                im.setImageResource(R.drawable.h_aries);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hAries = new bot.box.horology.api.Horoscope.Zodiac(getContext())
                                .showLoader(true).showLoader(true)
                                .requestSunSign(SUNSIGN.ARIES).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cAries = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 01");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 01");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cAries.requestConstellations(hAries);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Taurus"))
            {
                t1.setText(""+sign+"(ವೃಷಭ)");
                im.setImageResource(R.drawable.h_taurus);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.TAURUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 02");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 02");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Gemini"))
            {
                t1.setText(""+sign+"(ಮಿಥುನ)");
                im.setImageResource(R.drawable.h_gemini);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hGemini = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.GEMINI).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cGemini = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 03");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 03");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cGemini.requestConstellations(hGemini);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });

            }
            else if(sign.equals("Cancer"))
            {
                t1.setText(""+sign+"(ಕರ್ಕಾಟಕ)");
                im.setImageResource(R.drawable.h_cancer);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hCancer = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.CANCER).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cCancer = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 04");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 03");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cCancer.requestConstellations(hCancer);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });

            }
            else if(sign.equals("Leo"))
            {
                t1.setText(""+sign+"(ಸಿಂಹ)");
                im.setImageResource(R.drawable.h_leo);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hLeo = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.LEO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cLeao = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 05");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 05");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cLeao.requestConstellations(hLeo);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Virgo"))
            {
                t1.setText(""+sign+"(ಕನ್ಯಾ)");
                im.setImageResource(R.drawable.h_virgo);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hVirgo = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.VIRGO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cVirgo = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 06");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 06");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cVirgo.requestConstellations(hVirgo);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Libra"))
            {
                t1.setText(""+sign+"(ತುಲಾ)");
                im.setImageResource(R.drawable.h_libra);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hLibra = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.LIBRA).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cLibra = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 07");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 07");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cLibra.requestConstellations(hLibra);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Scorpio"))
            {
                t1.setText(""+sign+"(ವೃಶ್ಚಿಕ)");
                im.setImageResource(R.drawable.h_scorpio);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.SCORPIO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 08");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 08");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Sagittarius"))
            {
                t1.setText(""+sign+"(ಧನು)");
                im.setImageResource(R.drawable.h_sagittarius);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.SAGITTARIUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 09");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 09");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Capricorn"))
            {
                t1.setText(""+sign+"(ಮಕರ)");
                im.setImageResource(R.drawable.h_capricorn);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.CAPRICORN).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 10");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 10");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Aquarius"))
            {
                t1.setText(""+sign+"(ಕುಂಭ)");
                im.setImageResource(R.drawable.h_aquarius);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.AQUARIUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 11");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 11");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }
            else if(sign.equals("Pisces"))
            {
                t1.setText(""+sign+"(ಮೀನ)");
                im.setImageResource(R.drawable.h_pisces);
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(getContext()).showLoader(true)
                                .requestSunSign(SUNSIGN.PISCES).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                        HorologyController cTaurus = new HorologyController(new Response() {
                            @Override
                            public void onResponseObtained(Zodiac zodiac) {
                                System.out.println("here is the aries 12");
                                showHoroscopeDialog(zodiac, true, "");
                            }

                            @Override
                            public void onErrorObtained(String errormsg) {
                                System.out.println("here is the aries 12");
                                showHoroscopeDialog(null, false, errormsg);
                            }
                        });
                        cTaurus.requestConstellations(hTaurus);
                    }
                    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {
                        if (isSuccsess) {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.SUCCESS_TYPE);
                            sp.setTitleText(""+z.getSunSign()+"||"+z.getDate());
                            sp.setContentText(""+z.getHoroscope());
                            sp.setConfirmText("OK");
                            sp.show();
                        } else {
                            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
                            sp.setTitleText("Error");
                            sp.setContentText("Poor Internet Connection");
                            sp.show();
                        }

                    }
                });
            }

        }
        else
        {
           //t1.setVisibility(View.INVISIBLE);
           //t2.setVisibility(View.INVISIBLE);
           //im.setVisibility(View.INVISIBLE);
            SweetAlertDialog sp=new SweetAlertDialog(getContext(),SweetAlertDialog.ERROR_TYPE);
            sp.setTitleText("Error");
            sp.setContentText("Information Not Found");
            sp.setCancelable(false);
            sp.setConfirmText("OK");
            sp.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent i=new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                    getActivity().finish();
                }
            });
            sp.show();
        }
        return root;
    }
}