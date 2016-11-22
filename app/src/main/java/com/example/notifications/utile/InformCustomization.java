package com.example.notifications.utile;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * Created by Administrator on 2016/11/22.
 */
//自定义通知栏
class InformCustomization {
    private static InformCustomization inform;
    NotificationCompat.Builder builder;

    public InformCustomization(Context context) {
        builder = new NotificationCompat.Builder(context);
    }

    public static InformCustomization getBuilder(Context context) {
        inform = new InformCustomization(context);
        return inform;
    }

    public InformCustomization setContent(RemoteViews view) {
        builder.setContent(view);
        return this;

    }

    public InformCustomization setSmallIcon(int smallIcon) {
        builder.setSmallIcon(smallIcon);
        return this;
    }

    public InformCustomization setTicker(String ticker) {
        builder.setTicker(ticker);
        return this;
    }

    public Notification builder() {
        Notification notification = builder.build();
        return notification;
    }

}
