package com.project.avanikan_pc_003.taskslist.DataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBTasksOpenHelper extends SQLiteOpenHelper{

    public static final String TAG = "===>" ;

    public DBTasksOpenHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);


    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.i(TAG, "onCreate: open helper");
        sqLiteDatabase.execSQL(Constants.TasksInfoTable.CREATE_TASKS_TABLE_SYNTAX);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }



}
