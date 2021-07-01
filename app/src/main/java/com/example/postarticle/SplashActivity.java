package com.example.postarticle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;

import com.example.postarticle.Db.DbManager;

import java.util.List;

public class SplashActivity extends AppCompatActivity {
    DbManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        dbManager = new DbManager(this);
        dbManager.open();
        List userList =  dbManager.getListUser();
        dbManager.close();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(userList.size() > 0){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


                finish();
            }
        }, 3000);
    }
}