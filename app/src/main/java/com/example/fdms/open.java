package com.example.fdms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class open extends AppCompatActivity {
    Handler handler;
    ImageView i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        i1=(ImageView)findViewById(R.id.imageView1);
        Glide.with(this).load(R.drawable.boost).into(i1);
        getSupportActionBar().hide();

    handler=new Handler();
        handler.postDelayed(new Runnable() {
        @Override
        public void run() {
            Intent intent=new Intent(open.this,login.class);
            startActivity(intent);
            finish();
        }
    },5000);

}
}
