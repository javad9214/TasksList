package com.project.avanikan_pc_003.taskslist.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.avanikan_pc_003.taskslist.Data.Tasks;


import java.util.ArrayList;


import static android.R.attr.id;

public class DataBaseOperation  implements DateBaseDAO {


    private static final String TAG = "===>";
    private SQLiteDatabase db_tasks ;
    private ArrayList<Tasks> tasksList ;
    Context context ;


    public DataBaseOperation(Context context){

        this.context = context ;
        DBTasksOpenHelper dbTasksOpenHelper = new DBTasksOpenHelper(context);
        db_tasks = dbTasksOpenHelper.getWritableDatabase();
    }

    @Override
    public long addTask(Tasks tasks) {

        ContentValues values = new ContentValues();
        values.put(Constants.TasksInfoTable.TASK , tasks.task);
        values.put(Constants.TasksInfoTable.DEADLINE_DAY , tasks.deadline_day);
        values.put(Constants.TasksInfoTable.DEADLINE_MONTH , tasks.deadline_month);
        values.put(Constants.TasksInfoTable.DEADLINE_YEAR , tasks.deadline_year);
        values.put(Constants.TasksInfoTable.STATUS , tasks.status);

        return db_tasks.insert(Constants.TasksInfoTable.TASKS_TABLE_NAME, null , values) ;
    }

    @Override
    public ArrayList<Tasks> get_tasks_form_db() {



        Cursor cursor = db_tasks.query(Constants.TasksInfoTable.TASKS_TABLE_NAME , null , null , null , null , null , null);
        Log.i(TAG, "get_tasks_form_db: ");

        tasksList = new ArrayList<>();
        if (cursor != null ){
            Log.i(TAG, "get_tasks_form_db: first if");
            cursor.moveToFirst();
            if (cursor.isFirst()){
                Log.i(TAG, "get_tasks_form_db: is first " +  cursor.getString(cursor.getColumnIndex(Constants.TasksInfoTable.TASK)));
                while (true){
                    Tasks tasks = new Tasks();
                    if (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.STATUS)) == 0 ){
                        Log.i(TAG, "get_tasks_form_db: is not null  " + cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.TASKS_UID)));
                        tasks.UID = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.TASKS_UID)));
                        tasks.task = (cursor.getString(cursor.getColumnIndex(Constants.TasksInfoTable.TASK)));
                        tasks.deadline_day = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_DAY)));
                        tasks.deadline_month = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_MONTH)));
                        tasks.deadline_year = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_YEAR)));
                        tasksList.add(tasks);
                    }

                    cursor.moveToNext();
                    if (cursor.isAfterLast()){
                        break;
                    }
                }

                cursor.close();
            }else {
                Log.i(TAG, "get_tasks_form_db: is not first");
                cursor.close();
            }

        }else   {
            Log.i(TAG, "get_tasks_form_db:  is null ");
        }


        return tasksList ;
    }

    @Override
    public ArrayList<Tasks> get_checked_tasks_form_db() {
        Cursor cursor = db_tasks.query(Constants.TasksInfoTable.TASKS_TABLE_NAME , null , null , null , null , null , null);
        Log.i(TAG, "get_tasks_form_db: ");

        tasksList = new ArrayList<>();
        if (cursor != null ){
            Log.i(TAG, "get_tasks_form_db: first if");
            cursor.moveToFirst();
            if (cursor.isFirst()){
                Log.i(TAG, "get_tasks_form_db: is first " +  cursor.getString(cursor.getColumnIndex(Constants.TasksInfoTable.TASK)));
                while (true){
                    Tasks tasks = new Tasks();
                    if (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.STATUS)) == 1 ){
                        Log.i(TAG, "get_tasks_form_db: is not null  " + cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.TASKS_UID)));
                        tasks.UID = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.TASKS_UID)));
                        tasks.task = (cursor.getString(cursor.getColumnIndex(Constants.TasksInfoTable.TASK)));
                        tasks.deadline_day = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_DAY)));
                        tasks.deadline_month = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_MONTH)));
                        tasks.deadline_year = (cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.DEADLINE_YEAR)));
                        tasksList.add(tasks);
                    }

                    cursor.moveToNext();
                    if (cursor.isAfterLast()){
                        break;
                    }
                }

                cursor.close();
            }else {
                Log.i(TAG, "get_tasks_form_db: is not first");
                cursor.close();
            }

        }else   {
            Log.i(TAG, "get_tasks_form_db:  is null ");
        }


        return tasksList ;
    }

    @Override
    public void deleteTask(int UID) {

        String [] args = {String.valueOf(UID)};

        db_tasks.delete(Constants.TasksInfoTable.TASKS_TABLE_NAME , Constants.TasksInfoTable.TASKS_UID  + " = ?" , args );
        //context.startActivity(new Intent(context , MainTask.class));
    }

    @Override
    public int searchByUID(int U_id) {


        String selections = Constants.TasksInfoTable.TASKS_UID + " like ?";
        String[] selectionArgs = {"%" + U_id + "%"};
        Cursor cursor = db_tasks.query(Constants.TasksInfoTable.TASKS_TABLE_NAME , null , selections , selectionArgs , null , null ,null);
            cursor.moveToFirst();
        int status ;
            while (true){
                Log.i(TAG, "searchByTask: " + cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.STATUS)));
                 status =  cursor.getInt(cursor.getColumnIndex(Constants.TasksInfoTable.STATUS));
                cursor.moveToNext();
                if (cursor.isAfterLast()){
                    break;
                }
            }

            cursor.close();

        return status ;
    }

    @Override
    public int UpdateTasks(int U_id, int UpdateMode , Tasks tasks) {


        ContentValues values = new ContentValues();
        String where = "" ;
        String[] args = new String[1];

        switch (UpdateMode){

            case 0 :  //update task
                values.put(Constants.TasksInfoTable.TASK , tasks.task);
                break;

            case 1 : //update deadline day
                values.put(Constants.TasksInfoTable.DEADLINE_DAY , tasks.deadline_day);
                break;

            case 2 : // update deadline month
                values.put(Constants.TasksInfoTable.DEADLINE_MONTH , tasks.deadline_month);
                break;

            case 3 : //update deadline year
                values.put(Constants.TasksInfoTable.DEADLINE_YEAR , tasks.deadline_year);

            case 4 : //update  check status
                values.put(Constants.TasksInfoTable.STATUS , tasks.status);
                break;

        }

        where = Constants.TasksInfoTable.TASKS_UID + " = ?" ;
        args[0] = String.valueOf(U_id);



        return db_tasks.update(Constants.TasksInfoTable.TASKS_TABLE_NAME , values , where , args );
    }


}
