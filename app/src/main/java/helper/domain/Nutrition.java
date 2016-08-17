package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class Nutrition {
    private int id;
    private String nameOfDiet;
    private String dateStamp;
    private String timestamp;
    private int quantity;
    private String comment;

    public Nutrition() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Nutrition(int id, String nameOfDiet, String dateStamp, String timestamp, int quantity, String comment) {
        this.id = id;

        this.nameOfDiet = nameOfDiet;
        this.dateStamp = dateStamp;
        this.timestamp = timestamp;
        this.quantity = quantity;
        this.comment = comment;
    }

    public String getNameOfDiet() {
        return nameOfDiet;
    }

    public void setNameOfDiet(String nameOfDiet) {
        this.nameOfDiet = nameOfDiet;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public String getDateStamp() {
        return dateStamp;
    }

    public void setDateStamp(String dateStamp) {
        this.dateStamp = dateStamp;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return  "ID="+ + id+
                "NameOfDiet=" + nameOfDiet+
                ", dateStamp='" + dateStamp  +
                ", timestamp='" + timestamp  +
                ", quantity=" + quantity +
                ", comment='" + comment  +
                '}';
    }

}
