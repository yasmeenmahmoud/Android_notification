package com.example.android_notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    @RequiresApi(api = 26)
    public void addNotify(View view) {
        /**Id for Notification**/
        int notifyId = 001;

        /**Gets an instance of the NotificationManager service**/
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        /**Get an instance of NotificationManager**/
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setAutoCancel(true)
                        .setContentText("Hello Developers!, Click to Dismiss");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**For Android Oreo or Greater**/

            /**We need to Create Channel for Android O to Display Notification**/
            String channelId1 = "1";
            String channelName1 = "channel1";

            /**Initialize NotificationChannel**/
            NotificationChannel channel = new NotificationChannel(channelId1, channelName1, NotificationManager.IMPORTANCE_DEFAULT);

            /**Enable Notify Light, Vibration, and show Badge to true**/
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.enableVibration(true);

            /**Set the ChannelID to Notification.Builder**/
            builder.setChannelId(channelId1);

            /**Create Channel if Not null**/
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }

        } else {
            /**Android O or Lesser**/

            /**below statement use for notification tone, notification lights, vibration**/
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        }

        /**Use getIntent if you want to Open Current Activity
         * Else Use
         * Intent intent = new Intent(getApplicationContext, MyClass.class);
         81	         * Use Your Class Name instead of MyClass**/
        Intent intent = getIntent();

        /**Create a TaskStackBuilder**/
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());

        /**Add NextIntent**/
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(001, PendingIntent.FLAG_UPDATE_CURRENT);

        /**Set a Content Intent to Open on Notification Click**/
        builder.setContentIntent(pendingIntent);

        /**Notify if not Null**/
        if (mNotificationManager != null) {
            mNotificationManager.notify(notifyId, builder.build());
        }
    }

    @RequiresApi(api = 26)
    public void messageNotify(View view) {
        int notifyId = 002;
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Title")
                .setContentText("Message Received")
                .setAutoCancel(true);

        /**Set a Notification Style to Inbox**/
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

        /**Set a Title When Notification Expanded**/
        inboxStyle.setBigContentTitle("Message Expanded");

        /**Add your All messages here or use Loop to generate messages**/
        inboxStyle.addLine("Message 1");
        inboxStyle.addLine("Message 2");
        inboxStyle.addLine("Message 3");
        inboxStyle.addLine("Message 4");
        inboxStyle.addLine("Message 5");

        /**Set the NotificationCompat object to builder**/
        mBuilder.setStyle(inboxStyle);

        Intent intent = getIntent();
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);

        stackBuilder.addNextIntent(intent);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String channelId2 = "2";
            String channelName2 = "channel2";

            NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.enableVibration(true);

            mBuilder.setChannelId(channelId2);

            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(channel);
            }
        } else {
            mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        }

        if (mNotificationManager != null) {
            mNotificationManager.notify(notifyId, mBuilder.build());
        }
    }

    @RequiresApi(api = 26)
    public void largeTextNotification(View view) {

        int notifyId = 003;

        Bitmap bigIcon = BitmapFactory.decodeResource(getResources(), R.drawable.tweet);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Welcome to Android Studio Tutorial, it provides a tutorials related to " +
                "all Android Programming. It helps enhance your knowledge in Android");
        bigText.setSummaryText("Android Studio Tutorials");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Big Text Notification")
                .setLargeIcon(bigIcon)
                .setStyle(bigText);

        Intent intent = getIntent();
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId2 = "2";
            String channelName2 = "channel2";

            NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.enableVibration(true);

            mBuilder.setChannelId(channelId2);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        } else {
            mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        }

        if (notificationManager != null) {
            notificationManager.notify(notifyId, mBuilder.build());
        }
    }

    /*******************
     * Image Notification
     ***********************/
    @RequiresApi(api = 26)
    public void imageNotification(View view) {

        int notifyId = 004;

        NotificationCompat.BigPictureStyle bpStyle = new NotificationCompat.BigPictureStyle();
        bpStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.tweet)).build();

        Intent rIntent = getIntent();
        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, rIntent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Big Picture Notification")
                .addAction(android.R.drawable.ic_menu_share, "Share", pendingIntent)
                .setStyle(bpStyle);

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId2 = "2";
            String channelName2 = "channel2";

            NotificationChannel channel = new NotificationChannel(channelId2, channelName2, NotificationManager.IMPORTANCE_DEFAULT);

            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.setShowBadge(true);
            channel.enableVibration(true);

            mBuilder.setChannelId(channelId2);

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        } else {
            mBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE);
        }

        if (notificationManager != null) {
            notificationManager.notify(notifyId, mBuilder.build());
        }
    }


}

