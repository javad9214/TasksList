package com.project.avanikan_pc_003.taskslist;


import android.app.Application;
import android.util.Log;

import com.project.avanikan_pc_003.taskslist.DataBase.DataBaseOperation;
import com.project.avanikan_pc_003.taskslist.western.News.DataBase.DataBase_Operation22;

public class App extends Application {

    private static DataBaseOperation dataBaseOperation ;
    private static DataBase_Operation22 dataBase_operation2 ;

    @Override
    public void onCreate() {
        super.onCreate();

        dataBaseOperation = new DataBaseOperation(this);
        dataBase_operation2 = new DataBase_Operation22(this);
        Log.i("===>" , "onCreate: ");

    }

    public static DataBaseOperation getDataBaseOperation(){
        return dataBaseOperation ;
    }

    public static DataBase_Operation22 getDataBase_operation2 (){
        return dataBase_operation2 ;
    }
}
