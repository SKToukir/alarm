package showcase.adplay.com.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Integer> setTime = new ArrayList<>();
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    private Context context;
    Button b1;
    int[] time = {50, 70, 20, 90};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        b1 = (Button) findViewById(R.id.button1);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                setTime.add(6);
                setTime.add(60);
                setTime.add(10);

                startAlert(setTime,context);
            }
        });

    }

    public void startAlert(ArrayList<Integer>  time, Context context) {
        //EditText text = (EditText) findViewById(R.id.time);
        //int i = Integer.parseInt(text.getText().toString());
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(
                context, 234324243, intent, 0);


//        for (int u = 0; u < time.length; u++) {
            alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                    + (time.get(0) * 1000), pendingIntent);
            Toast.makeText(this, "Alarm set in " + 7 + " seconds", Toast.LENGTH_LONG).show();
        //}
    }


////    private final static int ICE_CREAM_BUILD_ID = 14;
////
////    private void addEvent(String selectedYear, String selectedMonth, String selectedDate, String title) {
////
////        Intent intent = new Intent(Intent.ACTION_EDIT);
////        int sdk = android.os.Build.VERSION.SDK_INT;
////        if(sdk < ICE_CREAM_BUILD_ID) {
////            // all SDK below ice cream sandwich
////            intent.setType("com.android.cursor.item/event");
////            intent.putExtra("beginTime", timeFormat("Jul 10 2017  6:25PM"));
////            intent.putExtra("endTime", timeFormat("Jul 10 2017  6:35PM"));
////            intent.putExtra("title", "title");
////            intent.putExtra("description", "description");
////            intent.putExtra("eventLocation", "Dhaka");
////            intent.putExtra("allDay", false);
////
//////          intent.putExtra("rrule", "FREQ=YEARLY");
////        } else {
////            // ice cream sandwich and above
////            intent.setType("com.android.cursor.item/event");
////            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeFormat("Jul 10 2017  6:25PM"));
////            intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, timeFormat("Jul 10 2017  6:35PM"));
////            intent.putExtra(CalendarContract.Events.TITLE, "title");
////            intent.putExtra(CalendarContract.Events.ACCESS_LEVEL, CalendarContract.Events.ACCESS_PRIVATE);
////            intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY , false);
////            intent.putExtra(CalendarContract.Events.DESCRIPTION, "description");
////            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Dhaka");
////
//////          intent.putExtra(Events.RRULE, "FREQ=DAILY;COUNT=10")
////        }
////        try {
////            this.startActivity(intent);
////
////        } catch(Exception e) {
////
////        }
//
//    }

    public String timeFormat(String time) {

        String strCurrentDate = time;

        SimpleDateFormat format = new SimpleDateFormat("MMM dd yyyy hh:mma");
        Date newDate = null;
        try {
            newDate = format.parse(strCurrentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(newDate);

        Log.d("dateyear", date);
        return date;

    }

    public void stopAlarm(View view) {
      alarmManager.cancel(pendingIntent);
    }
}
