package edu.louisville.cis490.memorygame;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

/**
 * Created by Kevin Le on 12/12/2014.
 */
public class DBTools extends SQLiteOpenHelper {

    private final static int DB_VERSION = 10;

    public DBTools(Context context) {

        super(context, "myApp.db", null, DB_VERSION);


    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

        String query = "create table logins (userID Integer primary key autoincrement, "+
                            " username text, password text)";
                sqLiteDatabase.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {
            System.out.println("UPGRADE DB oldVersion=" + oldVersion + " - newVersion=" + newVersion);
            recreateDb(sqLiteDatabase);
            if (oldVersion < 10) {
                String query = "create table logins (userID Integer primary key autoincrement, " +
                        " username text, password text)";
                sqLiteDatabase.execSQL(query);
            }
        }
        catch (Exception e){e.printStackTrace();}


    }

    private void recreateDb(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            System.out.println("DOWNGRADE DB oldVersion="+oldVersion+" - newVersion="+newVersion);
        }
    public User insertUser (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userID=database.insert("logins", null, values);
        database.close();
        return queryValues;
    }

    public int updateUserPassword (User queryValues){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", queryValues.username);
        values.put("password", queryValues.password);
        queryValues.userID=database.insert("logins", null, values);
        database.close();
        return database.update("logins", values, "userID = ?", new String[] {String.valueOf(queryValues.userID)});
    }

    public User getUser (String username){
        String query = "Select userId, password from logins where username ='"+username+"'";
        User myUser = new User(0,username,"");
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToFirst()){
            do {
                myUser.userID=cursor.getLong(0);
                myUser.password=cursor.getString(1);
            } while (cursor.moveToNext());
        }
        return myUser;
    }
}
