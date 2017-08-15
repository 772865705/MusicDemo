package com.handjoy.musicdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView tv;

    Timer timer;
    TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        startService(new Intent(this,MyService.class));
//        finish();


        tv = (TextView) findViewById(R.id.tv);

        timer = new Timer("appendtext");
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.append("厉害了");
                    }
                });
            }
        };

        Handler handler = new Handler();

    }

    @Override
    protected void onResume() {
        super.onResume();

        timer.schedule(task,0,2000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
