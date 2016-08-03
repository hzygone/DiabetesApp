package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class Exercise {
    private  String timeStamp;
    private String activityName;
    private int duration;  // in minutes
    private String comment ;

    public Exercise() {
    }

    public Exercise(String timeStamp, String activityName, int duration, String comment) {
        this.timeStamp = timeStamp;
        this.activityName = activityName;
        this.duration = duration;
        this.comment = comment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
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
        return "Exercise{" +
                "timeStamp='" + timeStamp + '\'' +
                ", activityName='" + activityName + '\'' +
                ", duration=" + duration +
                ", comment='" + comment + '\'' +
                '}';
    }
}
