package com.example.notifications.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.notifications.R;
import com.example.notifications.utile.InformManager;

/*
 * 通知栏应用
 */
public class MainActivity extends Activity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        findViewById(R.id.btn_show).setOnClickListener(this);
        findViewById(R.id.btn_show_cz).setOnClickListener(this);
        findViewById(R.id.btn_show_intent_act).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_clear_all).setOnClickListener(this);
        findViewById(R.id.btn_show_custom).setOnClickListener(this);
        findViewById(R.id.btn_show_progress).setOnClickListener(this);
    }


    /**
     * 显示通知栏
     */
    public void showNotify() {
        InformManager maneger = InformManager.getInstance(this);
        maneger.setText("测试标题101", "测试内容", "测试通知来啦");
        maneger.setIcon(R.drawable.icon_1_test,R.drawable.icon_2_test);
        maneger.show(101);
    }


    /**
     * 显示常驻通知栏
     */
    public void showCzNotify() {
        InformManager maneger = InformManager.getInstance(this);
        maneger.setText("常驻通知102", "测试内容", "测试通知来啦");
        maneger.setIcon(R.drawable.icon_1_test,R.drawable.icon_2_test);
        maneger.setResident();
        maneger.show(102);
    }

    /**
     * 显示通知栏点击跳转到指定Activity
     */
    public void showIntentActivityNotify() {
        Intent resultIntent = new Intent(this, CustomActivity.class);
        InformManager maneger = InformManager.getInstance(this);
        maneger.setText("测试标题103", "跳转到CustomActivity", "测试通知来啦");
        maneger.setIntent(resultIntent);
        maneger.setIcon(R.drawable.icon_1_test,R.drawable.icon_2_test);
        maneger.show(103);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show:
                showNotify();
                break;
            case R.id.btn_show_cz:
                showCzNotify();
                break;
            case R.id.btn_show_intent_act:
                showIntentActivityNotify();
                break;

            case R.id.btn_clear:
                InformManager.getInstance(this).clearNotifyId(101);
                break;
            case R.id.btn_clear_all:
                InformManager.getInstance(this).clearNotifyAll();
                break;

            case R.id.btn_show_progress:
                startActivity(new Intent(getApplicationContext(), ProgressAcitivty.class));
                break;
            case R.id.btn_show_custom:
                startActivity(new Intent(getApplicationContext(), CustomActivity.class));
                break;

        }
    }

}
