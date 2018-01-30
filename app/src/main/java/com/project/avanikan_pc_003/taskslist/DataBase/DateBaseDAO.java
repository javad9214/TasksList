package com.project.avanikan_pc_003.taskslist.DataBase;


import com.project.avanikan_pc_003.taskslist.Data.Tasks;

import java.util.ArrayList;

public interface DateBaseDAO {


    long addTask(Tasks tasks) ;

    ArrayList<Tasks> get_tasks_form_db ();

    ArrayList<Tasks> get_checked_tasks_form_db ();

    void deleteTask(int UID);

    int searchByUID(int U_id);

    int UpdateTasks(int U_id , int UpdateMode  , Tasks tasks ) ;



}
