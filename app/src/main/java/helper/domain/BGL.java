package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class BGL {
    private int id;
    private String timeStamp;
    private String dateStamp;
    private int bgReading;
    private String comment;

    public BGL() {
    }


    public BGL(int id, String timeStamp, String dateStamp, int bgReading, String comment) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.dateStamp = dateStamp;
        this.bgReading = bgReading;
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

    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
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
                "Id=" + id +
                        ", Time=" + timeStamp +
                        ", Date=" + dateStamp +
                        ", BGL=" + bgReading +
                        ", Comment=" + comment
                ;
    }
}
