package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class Nutrition {
    private String nameOfDiet;
    private int size;
    private String comment;

    public Nutrition() {
    }

    public Nutrition(String nameOfDiet, int size, String comment) {
        this.nameOfDiet = nameOfDiet;
        this.size = size;
        this.comment = comment;
    }

    public String getNameOfDiet() {
        return nameOfDiet;
    }

    public void setNameOfDiet(String nameOfDiet) {
        this.nameOfDiet = nameOfDiet;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Nutrition{" +
                "nameOfDiet='" + nameOfDiet + '\'' +
                ", size=" + size +
                ", comment='" + comment + '\'' +
                '}';
    }
}
