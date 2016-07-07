package com.example.notifications.activity;


import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.notifications.R;
import com.example.notifications.utile.InformManager;

public class CustomActivity extends Activity implements OnClickListener {

    /**
     * 是否在播放
     */
    public boolean isPlay = false;
    /**
     * 通知栏按钮广播
     */
    public ButtonBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom);
        findViewById(R.id.btn_show_custom_button).setOnClickListener(this);
        initButtonReceiver();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_custom_button:
                showButtonNotify();
                break;
        }
    }

    /**
     * 带按钮的通知栏
     */
    public void showButtonNotify() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.view_custom_button);
        remoteViews.setImageViewResource(R.id.custom_song_icon, R.drawable.icon_3_test);
        //API3.0 以上的时候显示按钮，否则消失
        remoteViews.setTextViewText(R.id.tv_custom_song_singer, "周杰伦");
        remoteViews.setTextViewText(R.id.tv_custom_song_name, "七里香");
        remoteViews.setViewVisibility(R.id.ll_custom_button, View.VISIBLE);
        //
        if (isPlay) {
            remoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_pause);
        } else {
            remoteViews.setImageViewResource(R.id.btn_custom_play, R.drawable.btn_play);
        }
        //点击的事件处理
        Intent intent = new Intent(ACTION_MUSIC);
        /* 上一首按钮 */
        intent.putExtra(INTENT_MUSIC_ACTION, MUSIC_PREV_ID);
        PendingIntent intent_prev = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_prev, intent_prev);
        /* 播放/暂停  按钮 */
        intent.putExtra(INTENT_MUSIC_ACTION, MUSIC_PALY_ID);
        PendingIntent intent_paly = PendingIntent.getBroadcast(this, 2, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_play, intent_paly);
        /* 下一首 按钮  */
        intent.putExtra(INTENT_MUSIC_ACTION, MUSIC_NEXT_ID);
        //PendingIntent intent_next = PendingIntent.getBroadcast(this, 3, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        //-----------跳转activity
        Intent it = new Intent(Intent.ACTION_MAIN);
        it.addCategory(Intent.CATEGORY_LAUNCHER);
        it.setClass(this, ProgressAcitivty.class);
        PendingIntent intent_next = PendingIntent.getActivity(this, 100, it, 0);
        remoteViews.setOnClickPendingIntent(R.id.btn_custom_next, intent_next);
        //
        InformManager maneger = InformManager.getInstance(this);
        maneger.setCustomization(remoteViews, R.drawable.icon_2_test, "音乐盛典");
        maneger.show(200);

    }


    /**
     * 带按钮的通知栏点击广播接收
     */
    public void initButtonReceiver() {
        receiver = new ButtonBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_MUSIC);
        registerReceiver(receiver, intentFilter);
    }

    public final static String INTENT_MUSIC_ACTION = "music_id";
    /**
     * 上一首 按钮点击 ID
     */
    public final static int MUSIC_PREV_ID = 1;
    /**
     * 播放/暂停 按钮点击 ID
     */
    public final static int MUSIC_PALY_ID = 2;
    /**
     * 下一首 按钮点击 ID
     */
    public final static int MUSIC_NEXT_ID = 3;
    public final static String ACTION_MUSIC = "music";

    /**
     * 广播监听按钮点击时间
     */
    public class ButtonBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals(ACTION_MUSIC)) {
                return;
            }
            //通过传递过来的ID判断按钮点击属性或者通过getResultCode()获得相应点击事件
            int buttonId = intent.getIntExtra(INTENT_MUSIC_ACTION, 0);
            switch (buttonId) {
                case MUSIC_PREV_ID:
                    Toast.makeText(getApplicationContext(), "上一首", Toast.LENGTH_SHORT).show();
                    break;
                case MUSIC_PALY_ID:
                    String play_status = "";
                    isPlay = !isPlay;
                    if (isPlay) {
                        play_status = "开始播放";
                    } else {
                        play_status = "已暂停";
                    }
                    showButtonNotify();
                    Toast.makeText(getApplicationContext(), play_status, Toast.LENGTH_SHORT).show();
                    break;
                case MUSIC_NEXT_ID:
                    Toast.makeText(getApplicationContext(), "下一首", Toast.LENGTH_SHORT).show();
                    break;

            }
        }

    }

    @Override
    protected void onDestroy() {
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        super.onDestroy();
    }
}
