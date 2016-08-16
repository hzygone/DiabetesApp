package helper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import helper.domain.Medication;

/**
 * Created by owner on 7/31/16.
 */
public class MedicationDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiabetesApp";
    private static final String DATABASE_TABLE = "MEDICATION";

    private static final String KEY_ID = "_id";
    private static final String KEY_TIME = "timeToTake";
    private static final String KEY_NAME = "medicationName";
    private static final String KEY_DOSAGE = "dosage";  // interms of ml , mg or oz
    private static final String KEY_FREQUENCY = "frequency";
    private static final String KEY_COMMENT = "comment";


    public MedicationDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TIME + " TEXT , "
                + KEY_NAME + "  TEXT, "
                + KEY_DOSAGE + " INTEGER"
                + KEY_FREQUENCY + " INTEGER"
                + KEY_COMMENT + " TEXT " + " )";

        db.execSQL(sqlStatement);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void insertMedication(Medication medication) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TIME, medication.getTimeToTake());
        cv.put(KEY_NAME, medication.getMedicationName());
        cv.put(KEY_DOSAGE, medication.getDoseage());
        cv.put(KEY_FREQUENCY, medication.getFrequency());
        cv.put(KEY_COMMENT, medication.getComment());
        long rowInserted = 0;

        try {
            rowInserted = db.insert(DATABASE_TABLE, null, cv);
        } catch (Exception e) {
            Log.e("Error", e.toString());
            System.out.println("This is it" + e.getCause());
        }
        if (rowInserted != -1)
            Log.i("Error", "Row inserted successfully");

        else {
            Log.e("Error", "Row is not inserted");
        }
        db.close();
    }

    public List<Medication> getListOfMedications() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);
        List<Medication> allMedications = new ArrayList<Medication>();


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medication medication = cursorToMedication(cursor);
            allMedications.add(medication);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return allMedications;
    }

    public List<Medication> getMedicationsByTime(String time) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);
        List<Medication> allMedications = new ArrayList<Medication>();


        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Medication medication = cursorToMedication(cursor);
            allMedications.add(medication);
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return allMedications;
    }

    private Medication cursorToMedication(Cursor cursor) {
        Medication medication = new Medication(
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getInt(2),
                cursor.getString(3));
        return medication;
    }
}
