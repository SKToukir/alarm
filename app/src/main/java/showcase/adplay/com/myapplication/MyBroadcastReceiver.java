package showcase.adplay.com.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by toukirul on 20/7/2017.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    static MediaPlayer mp;
    static int i = 0;

    @Override
    public void onReceive(Context context, Intent intent) {

        mp = MediaPlayer.create(context, R.raw.old);
        mp.start();

        ArrayList<String> timeList = SharedPref.getList(context);

        if (timeList.size() > 0){
            setAgai(context, Long.parseLong(timeList.get(i)));
            Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
            timeList.remove(i);
            SharedPref.clearListShared(context);
            SharedPref.SaveList(context,timeList);
        }else {
            MyBroadcastReceiver.this.abortBroadcast();
        }
    }

    public void setAgai(Context context, long time){
        Log.d("timelist+1",String.valueOf(SharedPref.getList(context).get(i)));
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 234324243, intent, 0);


//        for (int u = 0; u < time.length; u++) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        Toast.makeText(context, "Alarm set in " + time + " seconds", Toast.LENGTH_LONG).show();
    }
}