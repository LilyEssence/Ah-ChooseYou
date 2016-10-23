package com.ahchooseyou.ah_chooseyou;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Lily on 10/22/2016.
 */

public class Sneeze {
    String user_id;
    String timestamp;

    public String getTimestamp() {
        return timestamp;
    }

    public Sneeze(String user_id, Date date){
        this.user_id = user_id;
        this.timestamp = getISO8601StringForDate(date);
    }

    @Override
    public String toString() {
        return "Sneeze{" +
                "user_id='" + user_id + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    /**
     * Taken From: https://gist.github.com/kristopherjohnson/6124652
     * Return an ISO 8601 combined timestamp and time string for specified timestamp/time
     *
     * @param date
     *            Date
     * @return String with format "yyyy-MM-dd'T'HH:mm:ss'Z'"
     */
    public static String getISO8601StringForDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }

}
