package helper.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import helper.domain.BGL;

/**
 * Created by owner on 7/31/16.
 */
public class NutritionDBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DiabetesApp";
    private static final String DATABASE_TABLE = "BGL";

    private static final  String  KEY_ID = "_id";
    private  static  final String KEY_TIME_STAMP ="_timeStamp";
    private static final String KEY_BG_READING = "bgReading";
    private static final String KEY_COMMENT = "comment";



    public NutritionDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStatement = "CREATE TABLE " + DATABASE_TABLE + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_TIME_STAMP + " TEXT , "
                + KEY_BG_READING + "  INTEGER, "
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
        cv.put(KEY_BG_READING, bgl.getBgReading());
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
            Log.i("Error", "Row inserted successfully");

        else {
            Log.e("Error", "Row is not inserted");
        }
        db.close();
    }

    public BGL getBGLByTime(String bglTime){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[] {KEY_TIME_STAMP, KEY_BG_READING, KEY_COMMENT},
                null, null, null, null, null);
//        Cursor cursor = db.query(DATABASE_TABLE, new String[]{KEY_TIME_STAMP, KEY_BG_READING, KEY_COMMENT},
//                KEY_TIME_STAMP + " =? ", new String[] {String.valueOf(bglTime)}, null , null,null,null);
        BGL bgl = null;
        if(cursor != null){
            cursor.moveToFirst();
            bgl =  new BGL(
                    cursor.getString(0),
                    cursor.getInt(1),
                    cursor.getString(2));
        }


        db.close();
        return bgl;


    }
}
