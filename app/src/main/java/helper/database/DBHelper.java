package helper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import helper.domain.BGL;
import helper.domain.Exercise;
import helper.domain.Nutrition;

/**
 * Created by Asmamaw on 8/17/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiabetesApp";

    //Table names
    private static final String TABLE_ACTIVITY = "ACTIVITY";
    private static final String TABLE_BGL = "BGL";
    private static final String TABLE_NUTRITION = "NUTRITION";
    private static final String TABLE_MEDICATION = "MEDICATION";

    //Common column names

    private static final String KEY_ID = "_id";
    private static final String KEY_TIME_STAMP = "timeStamp";
    private static final String KEY_DATE_STAMP = "dateStamp";
    private static final String KEY_COMMENT = "comment";

    // BGL column names
    private static final String KEY_BGL_READING = "bgReading";

    //Exercise column names
    private static final String KEY_ACTIVITY_NAME = "activityName"; // type of name of the exercise
    private static final String KEY_DURATION = "duration";   // activity duration in minutes

    // Nutrition column names
    private static final String KEY_NUTRITION_NAME = "nutrotionName";
    private static final String KEY_NUTRITION_QTTY = "nutritionQtty";


    //Medication column names


    //TABLE create statements

    //TABLE_BGL create statement
    private static final String CREATE_TABLE_BGL = "CREATE TABLE " + TABLE_BGL + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_TIME_STAMP + " TEXT NOT NULL, "
            + KEY_DATE_STAMP + " TEXT NOT NULL, "
            + KEY_BGL_READING + "  INTEGER NOT NULL, "
            + KEY_COMMENT + " TEXT " + " )";


    //TABLE_ACTIVITY create statement
    private static final String CREATE_TABLE_ACTIVITY = "CREATE TABLE " + TABLE_ACTIVITY + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_ACTIVITY_NAME + "  TEXT, "
            + KEY_DATE_STAMP + " TEXT NOT NULL ,"
            + KEY_TIME_STAMP + " TEXT   NOT NULL , "
            + KEY_DURATION + " INTEGER NOT NULL , "
            + KEY_COMMENT + " TEXT " + " )";

    //TABLE_NUTRITION create  statement
    private static final String CREATE_TABLE_NUTRITION = "CREATE TABLE " + TABLE_NUTRITION + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_NUTRITION_NAME + "  TEXT, "
            + KEY_DATE_STAMP + " TEXT NOT NULL ,"
            + KEY_TIME_STAMP + " TEXT   NOT NULL , "
            + KEY_NUTRITION_QTTY + " INTEGER NOT NULL ,"
            + KEY_COMMENT + " TEXT " + " )";

    //TABLE_MEDICATION create statement


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BGL);
        db.execSQL(CREATE_TABLE_ACTIVITY);
        db.execSQL(CREATE_TABLE_NUTRITION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BGL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIVITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NUTRITION);

        onCreate(db);
    }

    public void addBGL(BGL bgl) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TIME_STAMP, bgl.getTimeStamp());
        cv.put(KEY_DATE_STAMP, bgl.getDateStamp());
        cv.put(KEY_BGL_READING, bgl.getBgReading());
        cv.put(KEY_COMMENT, bgl.getComment());
        long rowInserted = 0;

        try {
            rowInserted = db.insert(TABLE_BGL, null, cv);
        } catch (Exception e) {
            Log.e("Error", e.toString());
            System.out.println("This is it" + e.getCause());
        }
        if (rowInserted != -1)
            Log.i("INFO", " Row inserted successfully");

        else {
            Log.e("ERROR", "Row is not inserted");
        }
        db.close();
    }

    public BGL getBGLByTime(String bglTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BGL + " WHERE " + KEY_TIME_STAMP + " = '" + bglTime + "'", null);
        Log.i("INFO", "After rawQuery executed");
        BGL bgl = null;
        if (cursor != null) {
            cursor.moveToFirst();
            bgl = new BGL(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4));

        }
        cursor.close();
        db.close();
        return bgl;
    }

    public BGL getBGLByDate(String bglDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BGL + " WHERE " + KEY_DATE_STAMP + " = '" + bglDate + "'", null);
        Log.i("INFO", "After rawQuery executed");
        BGL bgl = null;
        if (cursor != null) {
            cursor.moveToFirst();
            bgl = new BGL(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4));
        }
        cursor.close();
        db.close();
        return bgl;
    }

    public List<BGL> getAllBGL() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BGL> bglList = new ArrayList<BGL>();
        String[] columns = new String[]{KEY_ID, KEY_TIME_STAMP, KEY_DATE_STAMP, KEY_BGL_READING, KEY_COMMENT};
        Cursor cursor = db.query(TABLE_BGL, columns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BGL bgl = cursorToBGL(cursor);
            bglList.add(bgl);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();


        return bglList;
    }

    //from and to  arguments should be formatted as YYYY-MM-DD
    public List<BGL> getBGLBetweenDates(String fromDate, String toDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BGL> bglList = new ArrayList<BGL>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BGL + " WHERE " + KEY_DATE_STAMP + " BETWEEN '" + fromDate + " ' AND '" + toDate + "'", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BGL bgl = cursorToBGL(cursor);
            bglList.add(bgl);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();


        return bglList;
    }

    public int updateBGLByID(int _id, BGL bgl) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TIME_STAMP, bgl.getTimeStamp());
        contentValues.put(KEY_DATE_STAMP, bgl.getDateStamp());
        contentValues.put(KEY_BGL_READING, bgl.getBgReading());
        contentValues.put(KEY_COMMENT, bgl.getComment());

        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.update(TABLE_BGL, contentValues, KEY_ID + "=" + _id, null);
        db.close();
        return i;
    }

    public void deleteBGLByID(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_BGL, KEY_ID + " = " + _id, null);
        db.close();


    }

    private BGL cursorToBGL(Cursor cursor) {
        BGL bgl = new BGL(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4));
        return bgl;
    }


    ///*****************EXERCISEACTIVITY DB HELPER METHODS
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
            rowInserted = db.insert(TABLE_ACTIVITY, null, cv);
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
        List<Exercise> activitylList = new ArrayList<Exercise>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ACTIVITY + " WHERE " + KEY_DATE_STAMP + " BETWEEN '" + fromDate + " ' AND '" + toDate + "'", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = cursorToExercise(cursor);
            activitylList.add(exercise);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();

        return activitylList;
    }

    public List<Exercise> getAllActivity() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ACTIVITY, null, null, null, null, null, null);
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

    public int updateExerciseByID(int _id, Exercise exercise){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ACTIVITY_NAME, exercise.getActivityName());
        contentValues.put(KEY_TIME_STAMP, exercise.getTimeStamp());
        contentValues.put(KEY_DATE_STAMP, exercise.getDateStamp());
        contentValues.put(KEY_DURATION, exercise.getDuration());
        contentValues.put(KEY_COMMENT, exercise.getComment());

        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.update(TABLE_ACTIVITY, contentValues, KEY_ID + "=" + _id, null);
        db.close();
        return i;
    }

    public void deleteExerciseByID(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ACTIVITY, KEY_ID + " = " + _id, null);
        db.close();


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



    ///*****************NUTRITIONACTIVITY DB HELPER METHODS
    public void insertNutrition(Nutrition nutrition) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TIME_STAMP, nutrition.getTimestamp());
        cv.put(KEY_DATE_STAMP, nutrition.getDateStamp());
        cv.put(KEY_NUTRITION_NAME, nutrition.getNameOfDiet());
        cv.put(KEY_NUTRITION_QTTY, nutrition.getQuantity());
        cv.put(KEY_COMMENT, nutrition.getComment());
        Log.i("INFO", nutrition.toString() + "Before raw inserted");

        long rowInserted = 0;

        try {
            rowInserted = db.insert(TABLE_NUTRITION, null, cv);
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
    public List<Nutrition> getNutritionBetweenDates(String fromDate, String toDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Nutrition> dietList = new ArrayList<Nutrition>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NUTRITION + " WHERE " + KEY_DATE_STAMP + " BETWEEN '" + fromDate + " ' AND '" + toDate + "'", null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Nutrition nutrition = cursorToNutrition(cursor);
            dietList.add(nutrition);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();


        return dietList;
    }

    public List<Nutrition> getAllNutrition() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NUTRITION, null, null, null, null, null, null);
        List<Nutrition> allNutrition = new ArrayList<Nutrition>();
        cursor.moveToFirst();
        while (cursor.isAfterLast()) {
            Nutrition nutrition = cursorToNutrition(cursor);
            allNutrition.add(nutrition);
            Log.d("DEBUG", "After add an nutrition to the list");
            cursor.moveToNext();
        }

        cursor.close();
        db.close();
        return allNutrition;
    }

    public int updateNutritionByID(int _id, Nutrition nutrition){
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NUTRITION_NAME, nutrition.getNameOfDiet());
        contentValues.put(KEY_TIME_STAMP, nutrition.getTimestamp());
        contentValues.put(KEY_DATE_STAMP, nutrition.getDateStamp());
        contentValues.put(KEY_NUTRITION_QTTY, nutrition.getQuantity());
        contentValues.put(KEY_COMMENT, nutrition.getComment());

        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.update(TABLE_NUTRITION, contentValues, KEY_ID + "=" + _id, null);
        db.close();
        return i;
    }

    public void deleteNutritionByID(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NUTRITION, KEY_ID + " = " + _id, null);
        db.close();


    }

    public Nutrition cursorToNutrition(Cursor cursor) {
        Nutrition nutrition = new Nutrition(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getInt(4),
                cursor.getString(5));
        return nutrition;

    }


    //close the database
    public void closeDB(){
        SQLiteDatabase db = this.getReadableDatabase();
        if(db!=null && db.isOpen()){
            db.close();
        }
    }

}