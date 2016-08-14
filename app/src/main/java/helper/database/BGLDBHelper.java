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

    private static final  String  KEY_ID = "_id";
    private  static  final String KEY_TIME_STAMP ="timeStamp";
    private static final String KEY_BGL_READING = "bgReading";
    private static final String KEY_COMMENT = "comment";



    public BGLDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TIME_STAMP + " TEXT , "
                + KEY_BGL_READING + "  INTEGER, "
                + KEY_COMMENT + " TEXT " + " )";

        db.execSQL(sqlStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    public  void addBGL(BGL bgl){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_TIME_STAMP, bgl.getTimeStamp());
        cv.put(KEY_BGL_READING, bgl.getBgReading());
        cv.put(KEY_COMMENT, bgl.getComment());
        long rowInserted = 0;

        try {
            rowInserted = db.insert(DATABASE_TABLE, null, cv);
        }
        catch (Exception e){
            Log.e("Error", e.toString());
            System.out.println("This is it" + e.getCause());
        }
        if(rowInserted != -1)
            Log.i("INFO", "Row inserted successfully");

        else {
            Log.e("ERROR", "Row is not inserted");
        }
           db.close();
    }

    public BGL getBGLByTime(String bglTime){
        SQLiteDatabase db = this.getReadableDatabase();
//
        Cursor cursor = db.rawQuery("SELECT * FROM "+ DATABASE_TABLE + " WHERE " + KEY_TIME_STAMP + " = '" + bglTime+  "'", null);
        Log.i("INFO", "After rawQuery executed");
        BGL bgl = null;
        if(cursor != null){
            cursor.moveToFirst();
            bgl =  new BGL(
                    cursor.getString(1),
                    cursor.getInt(2),
                    cursor.getString(3));
        }
        cursor.close();
        db.close();
        return bgl;
    }

    public List<BGL> getAllBGL(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<BGL> bglList = new ArrayList<BGL>();
        Cursor cursor = db.query(DATABASE_TABLE,null,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            BGL bgl = cursorToBGL(cursor);
            bglList.add(bgl);
            cursor.moveToNext();
        }

        cursor.close();
        db.close();


        return bglList;
    }
    private BGL cursorToBGL(Cursor cursor) {
        BGL  bgl =  new BGL(
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3));
        return bgl;
    }

}
