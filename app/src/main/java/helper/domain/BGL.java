package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class BGL {
    private String timeStamp;
    private int bgReading;
    private String comment;

    public BGL() {
    }

    public BGL(String timeStamp, int bgReading, String comment) {
        this.timeStamp = timeStamp;
        this.bgReading = bgReading;
        this.comment = comment;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getBgReading() {
        return bgReading;
    }

    public void setBgReading(int bgReading) {
        this.bgReading = bgReading;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return
                "time= '" + timeStamp + '\'' +
                ", bgl= " + bgReading +
                ", comment= '" + comment + '\'' +
                '}';
    }
}
