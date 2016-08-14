package home.diabetesapp;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Andrew on 8/2/16.
 */
public class BloodGlucoseReading {

    private Date date;
    private Time time;
    private int BloodGlucoseLevel;

    public BloodGlucoseReading(){

    }

    public BloodGlucoseReading(Date d, Time t, int bgl){
        date = d;
        time = t;
        BloodGlucoseLevel = bgl;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getBloodGlucoseLevel() {
        return BloodGlucoseLevel;
    }

    public void setBloodGlucoseLevel(int bloodGlucoseLevel) {
        BloodGlucoseLevel = bloodGlucoseLevel;
    }

}
