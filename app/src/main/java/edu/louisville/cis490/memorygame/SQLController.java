package edu.louisville.cis490.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Kevin Le on 12/15/2014.
 */
public class SQLController {

    private DatabaseHandler dbhelper;
    private Context oContext;
    private SQLiteDatabase database;

    public SQLController(Context c) {
        oContext = c;
    }

    public SQLController open() throws SQLException {
        dbhelper = new DatabaseHandler(oContext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbhelper.close();
    }

    // Adding new score
    public void addScore(int score, String username) {
        ContentValues values = new ContentValues();

        values.put(DatabaseHandler.KEY_SCORE, score); // score value
        values.put(DatabaseHandler.KEY_USERNAME, username); //username

        // Inserting Values
        database.insert(DatabaseHandler.TABLE_SCORE, username, values);

        database.close();
    }

    // Getting All Scores
    public Cursor getAllScores() {

        String[] selectAll = new String[]{DatabaseHandler.KEY_USERNAME, DatabaseHandler.KEY_SCORE};


        Cursor c = database.query(DatabaseHandler.TABLE_SCORE, selectAll, null, null, null, null, DatabaseHandler.KEY_SCORE, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}
