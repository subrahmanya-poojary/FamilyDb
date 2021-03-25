package com.example.fdms;

import android.app.Application;

public class global extends Application {
    public static global instance;

    // Global variable
    public String eml;

    // Restrict the constructor from being instantiated
    public global(){}

    public void setData(String el){
        this.eml=el;
    }
    public String getData(){
        return this.eml;
    }

    public static synchronized global getInstance(){
        if(instance==null){
            instance=new global();
        }
        return instance;
    }
}
