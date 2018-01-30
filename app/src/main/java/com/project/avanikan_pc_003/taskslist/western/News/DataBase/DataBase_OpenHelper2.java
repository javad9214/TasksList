package com.project.avanikan_pc_003.taskslist.western.News.DataBase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase_OpenHelper2 extends SQLiteOpenHelper{


    public DataBase_OpenHelper2(Context context) {
        super(context, DB_Constants2.DATABASE_NAME, null , DB_Constants2.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_Constants2.UsersInfoTable.CREATE_ALARMS_TABLE_SYNTAX);
        db.execSQL(DB_Constants2.ActiveUsersTable.CREATE_ACTIVE_USER_TABLE_SYNTAX);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
