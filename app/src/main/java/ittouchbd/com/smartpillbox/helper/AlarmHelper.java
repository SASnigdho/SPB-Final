package ittouchbd.com.smartpillbox.helper;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ittouchbd.com.smartpillbox.R;
import ittouchbd.com.smartpillbox.activitys.AlarmActivity;

public class AlarmHelper extends BroadcastReceiver {
    private ChangeFragment changeFragment;

    @Override
    public void onReceive(Context context, Intent intent) {

//        Toast.makeText(context, "Alarm Is Working...", Toast.LENGTH_SHORT).show();
//
//        //Vibrator
//        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//        vibrator.vibrate(1000);
//        NotificationHelper notificationHelper = new NotificationHelper(context);
//        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
//        notificationHelper.getManager().notify(1, nb.build());

//        context.startActivity(new Intent(context, AlarmActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));

        Intent goAlarmActivityIntent = new Intent(context, AlarmActivity.class);
        goAlarmActivityIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        goAlarmActivityIntent.putExtra("hour", intent.getStringExtra("hour"));
        goAlarmActivityIntent.putExtra("minute", intent.getStringExtra("minute"));
        goAlarmActivityIntent.putExtra("reqCode", intent.getStringExtra("reqCode"));
        goAlarmActivityIntent.putExtra("medicineName", intent.getStringExtra("medicineName"));
        goAlarmActivityIntent.putExtra("key", intent.getStringExtra("key"));
        context.startActivity(goAlarmActivityIntent);
    }

}
