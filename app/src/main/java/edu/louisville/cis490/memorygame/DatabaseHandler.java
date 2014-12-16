package edu.louisville.cis490.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Tuan Ngo on 12/13/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    public static final int DATABASE_VERSION = 1;

    // Database Name
    public static final String DATABASE_NAME = "game";

    // Table name
    public static final String TABLE_SCORE = "score";

    // Score Table Columns names
    public static final String KEY_ID_SCORE = "_id";
    public static final String KEY_SCORE = "score_value";
    public static final String KEY_USERNAME = "username";

    // Creating Tables
    public static final String CREATE_SCORE_TABLE = "CREATE TABLE " + TABLE_SCORE + "("
            + KEY_ID_SCORE + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_SCORE + " TEXT NOT NULL" + KEY_USERNAME + " TEXT NOT NULL" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_SCORE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORE);
        // Create tables again
        onCreate(db);
    }


}
