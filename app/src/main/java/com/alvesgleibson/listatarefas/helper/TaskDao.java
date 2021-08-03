package com.alvesgleibson.listatarefas.helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.alvesgleibson.listatarefas.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDao implements InterfaceTaskDAO{
    private SQLiteDatabase insertTask;
    private SQLiteDatabase readTask;


    public TaskDao(Context context) {
        DBHelper db = new DBHelper( context );
        insertTask = db.getWritableDatabase();
        readTask = db.getReadableDatabase();
    }


    @Override
    public boolean saveTask(Task task) {

        ContentValues cv = new ContentValues();
        cv.put("name", task.getName());

        try {
            insertTask.insert(DBHelper.TABLE_TASK, null, cv);

        }catch (Exception e){
            Log.e("INFO DB", "Error saving :"+e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean upgradeTask(Task task) {
        ContentValues cv = new ContentValues();
        cv.put("name", task.getName());
        String[] myArgs = { task.getId().toString() };

        try {

            insertTask.update(DBHelper.TABLE_TASK, cv, "id=?", myArgs );


        }catch (Exception e){

            Log.e("LogSQL", "Error Upgrade" + e.getMessage());
            return false;

        }



        return true;
    }

    @Override
    public boolean deleteTask(Task task) {

        String[] myArgs = {task.getId().toString()};

            try {

            insertTask.delete(DBHelper.TABLE_TASK, "id=?", myArgs);

            }catch (Exception e){
                e.printStackTrace();
                return false;
            }



        return true;
    }

    @Override
    public List<Task> listTask() {

        List<Task> taskLists = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABLE_TASK + " ;";

        Cursor c = readTask.rawQuery(sql, null);

        while ( c.moveToNext() ){

            Task task = new Task();

            Long id = c.getLong( c.getColumnIndexOrThrow("id") );
            String name = c.getString( c.getColumnIndexOrThrow("name") );

            task.setId( id );
            task.setName( name );

            taskLists.add(task);

        }


        return taskLists;
    }
}
