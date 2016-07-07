package com.example.notifications.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.notifications.R;
import com.example.notifications.utile.InformManager;

public class ProgressAcitivty extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        findViewById(R.id.btn_show_progress).setOnClickListener(this);
        findViewById(R.id.btn_show_un_progress).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InformManager maneger = InformManager.getInstance(this);
        maneger.setText("进度条104", "进度条", "进度条通知来啦");
        maneger.setIcon(R.drawable.icon_1_test, R.drawable.icon_2_test);
        switch (v.getId()) {
            case R.id.btn_show_progress:
                maneger.setProgress(100, 10, false);
                break;
            case R.id.btn_show_un_progress:
                maneger.setProgress(100, 10, true);
                break;

        }
        maneger.show(104);
    }
}
