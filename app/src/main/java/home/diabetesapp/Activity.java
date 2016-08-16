package home.diabetesapp;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Andrew on 8/2/16.
 */
public class Activity {

    //TODO HAVE GETTERS AND SETTERS INTERACT WITH DATABASE?

    private Date date;
    private Time time;
    private int duration;

    public Activity() {

    }

    public Activity(Date d, Time t, int dura) {
        date = d;
        time = t;
        duration = dura;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
