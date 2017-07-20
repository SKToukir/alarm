package showcase.adplay.com.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by toukirul on 20/7/2017.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    static MediaPlayer mp;
    int i =0;
    @Override
    public void onReceive(Context context, Intent intent) {

        mp=MediaPlayer.create(context, R.raw.old);
        mp.start();
        MainActivity.setTime.remove(i);
        if (MainActivity.setTime.size()!=0) {
            i++;
            setAgai(context, MainActivity.setTime.get(i));
            Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();
        }
    }

    public void setAgai(Context context, int time){
        Log.d("timelist+1",String.valueOf(MainActivity.setTime.get(0)));
        MainActivity.setTime.remove(0);
        Log.d("timelist+2",String.valueOf(MainActivity.setTime.get(0)));
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context, 234324243, intent, 0);


//        for (int u = 0; u < time.length; u++) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + (time * 1000), pendingIntent);
        Toast.makeText(context, "Alarm set in " + time + " seconds", Toast.LENGTH_LONG).show();
    }
}