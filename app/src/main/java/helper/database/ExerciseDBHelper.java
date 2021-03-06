package helper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import helper.domain.Exercise;

/**
 * Created by owner on 7/31/16.
 */
public class ExerciseDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiabetesApp";
    private static final String DATABASE_TABLE = "ACTIVITY";

    private static final String KEY_ID = "_id";     // primary key of the Activity table
    private static final String KEY_ACTIVITY_NAME = "activityName"; // type of name of the exercise
    private static final String KEY_DATE_STAMP = "dateStamp";
    private static final String KEY_TIME_STAMP = "timeStamp";  //can be entered manually
    private static final String KEY_DURATION = "duration";   // activity duration in minutes
    private static final String KEY_COMMENT = "comment";     // notes about the activity
///commit

    public ExerciseDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_ACTIVITY_NAME + "  TEXT, "
                + KEY_DATE_STAMP + " TEXT NOT NULL ,"
                + KEY_TIME_STAMP + " TEXT   NOT NULL , "
                + KEY_DURATION + " INTEGER NOT NULL , "
                + KEY_COMMENT + " TEXT " + " )";

        db.execSQL(sqlStatement);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public void insertActivity(Exercise activity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TIME_STAMP, activity.getTimeStamp());
        cv.put(KEY_DATE_STAMP, activity.getDateStamp());
        cv.put(KEY_ACTIVITY_NAME, activity.getActivityName());
        cv.put(KEY_DURATION, activity.getDuration());
        cv.put(KEY_COMMENT, activity.getComment());
        Log.i("INFO", activity.getComment() + "Before raw inserted");

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

    //from and to  arguments should be formatted as YYYY-MM-DD
    public List<Exercise> getExerciseBetweenDates(String fromDate, String toDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Exercise> bglList = new ArrayList<Exercise>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_DATE_STAMP + " BETWEEN '" + fromDate + " ' AND '" + toDate + "'", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            bglList.add(exercise);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();


        return bglList;
    }

    public List<Exercise> getAllActivity() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);
        List<Exercise> allActivities = new ArrayList<Exercise>();
        cursor.moveToFirst();
        while (cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            allActivities.add(exercise);
            Log.d("DEBUG", "After add an exercise to the list");
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return allActivities;


    }

    public Exercise cursorToExercise(Cursor cursor) {
        Exercise activity = new Exercise(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5));
        return activity;

    }


}
