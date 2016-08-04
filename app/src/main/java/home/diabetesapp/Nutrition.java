package home.diabetesapp;

import java.sql.Time;
import java.util.Date;

/**
 * Created by Andrew on 8/3/16.
 */

//TODO HAVE GETTERS AND SETTERS INTERACT WITH DATABASE?

public class Nutrition {

    private Date date;
    private Time time;
    private String notes;

    public Nutrition(Date date, Time time, String notes) {
        this.notes = notes;
        this.date = date;
        this.time = time;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
