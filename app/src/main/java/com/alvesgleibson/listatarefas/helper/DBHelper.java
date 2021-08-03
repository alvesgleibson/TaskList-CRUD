package com.alvesgleibson.listatarefas.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



public class DBHelper extends SQLiteOpenHelper {

     static int VERSION = 1;
     static String NAME_DB = "DB_Tasks", TABLE_TASK = "DB_TASKTABLE";


    public DBHelper(Context context) {
        super(context, NAME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_TASK +" (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL); ";

        try {

            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Table created with Success!!!" );

        }catch (Exception e){
            Log.i("Error creating table ", e.getMessage());

        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
