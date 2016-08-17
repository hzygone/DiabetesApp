package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class Exercise {
    private int id;
    private String activityName;
    private  String dateStamp;
    private String timeStamp;
    private int duration;  // in minutes
    private String comment;

    public Exercise() {
    }

    public Exercise(String activityName, String dateStamp, String timeStamp, int duration, String comment) {
        this.activityName = activityName;
        this.dateStamp = dateStamp;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.comment = comment;
    }

    public Exercise(int id, String activityName, String dateStamp, String timeStamp, int duration, String comment) {
        this.id = id;
        this.activityName = activityName;
        this.dateStamp = dateStamp;
        this.timeStamp = timeStamp;
        this.duration = duration;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return " ID=" + id +
                ", Activity=" + activityName  +
                ", Date=" + dateStamp  +
                ", Time=" + timeStamp +
                ", Duration=" + duration +
                ", Comment=" + comment ;

    }
}
