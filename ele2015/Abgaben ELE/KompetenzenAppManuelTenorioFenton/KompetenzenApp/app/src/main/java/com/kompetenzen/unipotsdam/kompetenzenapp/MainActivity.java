package com.kompetenzen.unipotsdam.kompetenzenapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchThread();
    }

    private void launchThread(){
        Thread timer= new Thread(){
                public void run() {
                    try {
                        sleep(2500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        Intent intent = new Intent(MainActivity.this, CompetenceLoader.class);
                        startActivity(intent);
                    }
                }
        };
        timer.start();
    }

    protected void onPause(){
        super.onPause();
        finish();
    }
}
