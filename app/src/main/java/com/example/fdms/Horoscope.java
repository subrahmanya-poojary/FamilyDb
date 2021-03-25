package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import bot.box.horology.annotation.DURATION;
import bot.box.horology.annotation.SUNSIGN;
import bot.box.horology.delegate.Response;
import bot.box.horology.hanshake.HorologyController;
import bot.box.horology.pojo.Zodiac;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Horoscope extends AppCompatActivity {
    public String hp;
    EditText e1;
    LinearLayout l;
    ConstraintLayout c;
    private LinearLayout layoutAries, layoutTaurus, layoutGemini, layoutCancer, layoutLeo, layoutVirgo, layoutLibra,
            layoutScorpio, layoutSagittarius, layoutCapricorn, layoutAquarius, layoutPisces;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horoscope);
        e1=(EditText)findViewById(R.id.editText1);
        initializeViews();

    }
    private void initializeViews() {
        layoutAries = (LinearLayout) findViewById(R.id.layoutAries);
        layoutAries.setOnClickListener(onClickListener);
        layoutTaurus = (LinearLayout) findViewById(R.id.layoutTaurus);
        layoutTaurus.setOnClickListener(onClickListener);
        layoutGemini = (LinearLayout) findViewById(R.id.layoutGemini);
        layoutGemini.setOnClickListener(onClickListener);
        layoutCancer = (LinearLayout) findViewById(R.id.layoutCancer);
        layoutCancer.setOnClickListener(onClickListener);
        layoutLeo = (LinearLayout) findViewById(R.id.layoutLeo);
        layoutLeo.setOnClickListener(onClickListener);
        layoutVirgo = (LinearLayout) findViewById(R.id.layoutVirgo);
        layoutVirgo.setOnClickListener(onClickListener);
        layoutLibra = (LinearLayout) findViewById(R.id.layoutLibra);
        layoutLibra.setOnClickListener(onClickListener);
        layoutScorpio = (LinearLayout) findViewById(R.id.layoutScorpio);
        layoutScorpio.setOnClickListener(onClickListener);
        layoutSagittarius = (LinearLayout) findViewById(R.id.layoutSagittarius);
        layoutSagittarius.setOnClickListener(onClickListener);
        layoutCapricorn = (LinearLayout) findViewById(R.id.layoutCapricorn);
        layoutCapricorn.setOnClickListener(onClickListener);
        layoutAquarius = (LinearLayout) findViewById(R.id.layoutAquarius);
        layoutAquarius.setOnClickListener(onClickListener);
        layoutPisces = (LinearLayout) findViewById(R.id.layoutPisces);
        layoutPisces.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.layoutAries:
                    System.out.println("here is the aries 01");
                    bot.box.horology.api.Horoscope hAries = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this)
                            .showLoader(true).showLoader(true)
                            .requestSunSign(SUNSIGN.AQUARIUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cAries = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            System.out.println("here is the aries 02");
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            System.out.println("here is the aries 03");
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cAries.requestConstellations(hAries);
                    break;

                case R.id.layoutTaurus:
                    bot.box.horology.api.Horoscope hTaurus = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.TAURUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cTaurus = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cTaurus.requestConstellations(hTaurus);
                    break;


                case R.id.layoutGemini:
                    bot.box.horology.api.Horoscope hGemini = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.GEMINI).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cGemini = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cGemini.requestConstellations(hGemini);
                    break;


                case R.id.layoutCancer:
                    bot.box.horology.api.Horoscope hCancer = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.CANCER).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cCancer = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cCancer.requestConstellations(hCancer);
                    break;


                case R.id.layoutLeo:
                    bot.box.horology.api.Horoscope hLeo = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.LEO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cLeo = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cLeo.requestConstellations(hLeo);
                    break;


                case R.id.layoutVirgo:
                    bot.box.horology.api.Horoscope hVirgo = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.VIRGO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cVirgo = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cVirgo.requestConstellations(hVirgo);
                    break;


                case R.id.layoutLibra:
                    bot.box.horology.api.Horoscope hLibra = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.LIBRA).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cLibra = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cLibra.requestConstellations(hLibra);
                    break;


                case R.id.layoutScorpio:
                    bot.box.horology.api.Horoscope hScorpio = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.SCORPIO).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cScorpio = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cScorpio.requestConstellations(hScorpio);
                    break;


                case R.id.layoutSagittarius:
                    bot.box.horology.api.Horoscope hSagittarius = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.SAGITTARIUS).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cSagittarius = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cSagittarius.requestConstellations(hSagittarius);
                    break;


                case R.id.layoutCapricorn:
                    bot.box.horology.api.Horoscope hCapricorn = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.CAPRICORN).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cCapricorn = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cCapricorn.requestConstellations(hCapricorn);
                    break;


                case R.id.layoutAquarius:
                    bot.box.horology.api.Horoscope hAquarius = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.AQUARIUS).requestDuration(DURATION.TODAY).
                                    isDebuggable(true).fetchHoroscope();
                    HorologyController cAquarius = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cAquarius.requestConstellations(hAquarius);
                    break;


                case R.id.layoutPisces:
                    bot.box.horology.api.Horoscope hPisces = new bot.box.horology.api.Horoscope.Zodiac(Horoscope.this).showLoader(true)
                            .requestSunSign(SUNSIGN.PISCES).requestDuration(DURATION.TODAY).isDebuggable(true).fetchHoroscope();
                    HorologyController cPisces = new HorologyController(new Response() {
                        @Override
                        public void onResponseObtained(Zodiac zodiac) {
                            showHoroscopeDialog(zodiac, true, "");
                        }

                        @Override
                        public void onErrorObtained(String errormsg) {
                            showHoroscopeDialog(null, false, errormsg);
                        }
                    });
                    cPisces.requestConstellations(hPisces);
                    break;

            }
        }
    };
    private void showHoroscopeDialog(Zodiac z, boolean isSuccsess, String errormsg) {

       /* final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custom_dialog);

        TextView title = (TextView) dialog.findViewById(R.id.title);
        TextView date = (TextView) dialog.findViewById(R.id.date);
        TextView body = (TextView) dialog.findViewById(R.id.body);*/

        if (isSuccsess) {
            e1.setText(""+z.getHoroscope());
            /*SweetAlertDialog sp=new SweetAlertDialog(Horoscope.this,SweetAlertDialog.SUCCESS_TYPE);
            sp.setTitleText(""+z.getDate()+"||"+z.getSunSign());
            sp.setContentText(""+z.getHoroscope());
            sp.setConfirmText("OK");
            hp=z.getHoroscope();
            sp.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    Intent i=new Intent(Horoscope.this,grid.class);
                    startActivity(i);
                }
            });
            sp.show();
           /* title.setText(z.getSunSign());
            date.setText(z.getDate());
            body.setText(z.getHoroscope());*/
        } else {
            SweetAlertDialog sp=new SweetAlertDialog(Horoscope.this,SweetAlertDialog.ERROR_TYPE);
            sp.setTitleText("Error");
            sp.setContentText(""+errormsg);
            sp.show();
            //body.setText(errormsg);
        }

    }

}
