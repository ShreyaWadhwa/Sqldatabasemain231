package com.example.shreya.sqldatabasemain;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Shryaa on 7/25/2017.
 */
public class HotOrNot
{
    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME = "persons_name";
    public static final String KEY_HOTNESS = "persons_hotness";
    private static final String DATABASE_NAME = "HotOrNotdb";
    private static final String DATABASE_TABLE = "peopleTable";
    private static final int DATABASE_VWESION = 1;
    private dbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public HotOrNot(Context c)
    {
        ourContext = c;
    }
    public long createEntry(String n, String h)
    {
       ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,n);
        cv.put(KEY_HOTNESS,h);
      return  (ourDatabase.insert(DATABASE_TABLE,null,cv));
    }
    private static class dbHelper extends SQLiteOpenHelper
    {
        public dbHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VWESION);
        }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL("CREATE TABLE" + DATABASE_TABLE + "(" +
                    KEY_ROWID + "INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_NAME + "TEXT NOT NULL," +
                    KEY_HOTNESS + "TEXT NOT NULL);"
            );
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            db.execSQL("DROP TABLE IF EXIST" + DATABASE_TABLE);
            onCreate(db);
        }
    }
    public HotOrNot open() throws SQLException
    {
        ourHelper = new dbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }
    public void close()
    {
        ourHelper.close();
    }
}



