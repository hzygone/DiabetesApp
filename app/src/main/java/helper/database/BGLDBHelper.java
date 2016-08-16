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

/**
 * Created by owner on 7/31/16.
 */
public class BGLDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiabetesApp";
    private static final String DATABASE_TABLE = "BGL";

    private static final String KEY_ID = "_id";
    private static final String KEY_TIME_STAMP = "timeStamp";
    private static final String KEY_DATE_STAMP = "dateStamp";
    private static final String KEY_BGL_READING = "bgReading";
    private static final String KEY_COMMENT = "comment";


    public BGLDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TIME_STAMP + " TEXT NOT NULL, "
                + KEY_DATE_STAMP + " TEXT NOT NULL, "
                + KEY_BGL_READING + "  INTEGER NOT NULL, "
                + KEY_COMMENT + " TEXT " + " )";

        db.execSQL(sqlStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
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
            rowInserted = db.insert(DATABASE_TABLE, null, cv);
        } catch (Exception e) {
            Log.e("Error", e.toString());
            System.out.println("This is it" + e.getCause());
        }
        if (rowInserted != -1)
            Log.i("INFO", "Row inserted successfully");

        else {
            Log.e("ERROR", "Row is not inserted");
        }
        db.close();
    }

    public BGL getBGLByTime(String bglTime) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_TIME_STAMP + " = '" + bglTime + "'", null);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_DATE_STAMP + " = '" + bglDate + "'", null);
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

    public Cursor getAllBGLCursor() {
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = new String[]{KEY_ID, KEY_TIME_STAMP, KEY_DATE_STAMP, KEY_BGL_READING, KEY_COMMENT};
        Cursor cursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
        cursor.close();
        db.close();
        return cursor;

    }

    public List<BGL> getAllBGL() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<BGL> bglList = new ArrayList<BGL>();
        String[] columns = new String[]{KEY_ID, KEY_TIME_STAMP, KEY_DATE_STAMP, KEY_BGL_READING, KEY_COMMENT};
        Cursor cursor = db.query(DATABASE_TABLE, columns, null, null, null, null, null);
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
        Cursor cursor = db.rawQuery("SELECT * FROM " + DATABASE_TABLE + " WHERE " + KEY_DATE_STAMP + " BETWEEN '" + fromDate + " ' AND '" + toDate + "'", null);

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

        int i = db.update(DATABASE_TABLE, contentValues, KEY_ID + "=" + _id, null);
        db.close();
        return i;
    }

    public void deleteByID(int _id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, KEY_ID + " = " + _id, null);
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
}
