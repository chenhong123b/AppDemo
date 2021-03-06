package com.example.appdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.appdemo.R;
import com.example.appdemo.control.ControlCenter;

import java.util.TimerTask;

public class LoadingActivity extends AppCompatActivity {
    MyTimer timer = new MyTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        ControlCenter.getGameControl().initNewGame();
        timer.schedule(this::turn, 2000);
    }

    private void turn() {
        ControlCenter.getActivityControl().turnActivity(LoadingActivity.this, GameActivity.class);
        LoadingActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        timer.cancel();
        super.onBackPressed();
    }
}
