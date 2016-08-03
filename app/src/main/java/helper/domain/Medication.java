package helper.domain;

/**
 * Created by owner on 7/31/16.
 */
public class Medication {
    private String medicationName;
    private String timeToTake;
    private int    doseage;   // interms of miligram or oz;
    private int frequency;  //per day
    private String comment;

    public Medication() {
    }

    public Medication(String medicationName,String timeToTake,  int doseage, int frequency, String comment) {
        this.medicationName = medicationName;
        this.timeToTake = timeToTake;
        this.doseage = doseage;
        this.frequency = frequency;
        this.comment = comment;
    }



    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public int getDoseage() {
        return doseage;
    }

    public void setDoseage(int doseage) {
        this.doseage = doseage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTimeToTake() {
        return timeToTake;
    }

    public void setTimeToTake(String timeToTake) {
        this.timeToTake = timeToTake;
    }

    @Override
    public String toString() {
        return "Medication{" +
                "medicationName='" + medicationName + '\'' +
                ", timeToTake='" + timeToTake + '\'' +
                ", doseage=" + doseage +
                ", frequency=" + frequency +
                ", comment='" + comment + '\'' +
                '}';
    }
}
