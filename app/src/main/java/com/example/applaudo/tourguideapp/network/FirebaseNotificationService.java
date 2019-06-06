package com.example.applaudo.tourguideapp.network;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.applaudo.tourguideapp.R;
import com.example.applaudo.tourguideapp.TourApp;
import com.example.applaudo.tourguideapp.activities.DetailsActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseNotificationService extends FirebaseMessagingService {

    private static String CHANNEL_ID = "FirebaseNotifications";
    private static String CHANNEL_NAME = "Firebase Notifications";
    private static String CHANNEL_DESCRIPTION = "Channel to get Firebase notifications";

    private static int NOTIFICATION_ID = 1337;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (!TourApp.getPreferences().shouldDisplayNotifications()) {
            return;
        }

        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String placeId = remoteMessage.getData().get("idLugar");
        String deleted = remoteMessage.getData().get("deleted");

        createNotificationChannel();
        showNotification(title, body, placeId, deleted);

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESCRIPTION);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String body, String placeId, String placeWasDeleted) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_logo)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if(TourApp.getPreferences().shouldForceVibration()){
            builder.setVibrate(new long[] { 0, 1000, 1000, 1000, 1000 });
        }

        if(placeWasDeleted == null || !placeWasDeleted.equals("true")){
            builder.setContentIntent(createPendingIntent(placeId));
        }

        NotificationManagerCompat manager = NotificationManagerCompat.from(this);
        manager.notify(NOTIFICATION_ID, builder.build());
    }

    private PendingIntent createPendingIntent(String placeId) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_PLACE_ID, placeId);
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
        taskStackBuilder.addNextIntentWithParentStack(intent);
        return taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }


}
