package com.example.admin.medicare.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.admin.medicare.R;

/**
 * Created by Admin on 14-03-2016.
 */
public class SplashScreenActivity extends AppCompatActivity {
    private static final int THREAD_SLEEP_TIME = 1200;//in milliseconds
    private boolean isActivityStopped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startThread();
    }

    private void startThread() {
        Thread sleeper = new Thread(sleepRunnable);
        sleeper.start();
    }

    public Runnable sleepRunnable = new Runnable() {
        public void run() {
            try {
                Thread.sleep(THREAD_SLEEP_TIME);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!isActivityStopped) {
                goToNextActivity(MainActivity.class);
            }
        }
    };

    protected void goToNextActivity(Class nextActivity) {
        Intent intent = new Intent();
        intent.setClass(this, nextActivity);
        startActivity(intent);
    }

}
