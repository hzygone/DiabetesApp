package home.diabetesapp;

/**
 * Created by Andrew on 8/2/16.
 */

//TODO HAVE GETTERS AND SETTERS INTERACT WITH DATABASE?

public class Regimen {

    private String dietString = "";
    private String medicationScheduleString = "";
    private String exerciseString = "";

    public Regimen(){}

    public Regimen(String dietString, String medicationScheduleString, String exerciseString) {
        this.dietString = dietString;
        this.medicationScheduleString = medicationScheduleString;
        this.exerciseString = exerciseString;
    }

    public String getDietString() {
        return dietString;
    }

    public void setDietString(String dietString) {
        this.dietString = dietString;
    }

    public String getMedicationScheduleString() {
        return medicationScheduleString;
    }

    public void setMedicationScheduleString(String medicationScheduleString) {
        this.medicationScheduleString = medicationScheduleString;
    }

    public String getExerciseString() {
        return exerciseString;
    }

    public void setExerciseString(String exerciseString) {
        this.exerciseString = exerciseString;
    }
}
