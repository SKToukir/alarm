package showcase.adplay.com.myapplication;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by toukirul on 20/7/2017.
 */

public class AlarmTimeClass {


    public String getDateTimeInmilSec(String dateTime){
        String myDate = dateTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = sdf.parse(myDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long millis = date.getTime();
        Log.d("timeInmil",String.valueOf(millis));
        return String.valueOf(millis);
    }

}
