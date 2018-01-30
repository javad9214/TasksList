package com.project.avanikan_pc_003.taskslist.DataBase;



public  final class Constants {

    public static final String DATABASE_NAME = "Task.db";
    public static final int DATABASE_VERSION = 1 ;

    public static class TasksInfoTable {

        public static final String TASKS_TABLE_NAME = "tasks_info";
        public static final String TASKS_UID = "_id" ;
        public static final String TASK = "task";
        public static final String DEADLINE_DAY = "deadline_day" ;
        public static final String DEADLINE_MONTH = "deadline_month" ;
        public static final String DEADLINE_YEAR = "deadline_year" ;
        public static final String STATUS = "status";
        public static final String CREATE_TASKS_TABLE_SYNTAX =
                "create table " + TASKS_TABLE_NAME + " ( " +
                        TASKS_UID + " integer primary key autoincrement ," +
                        TASK + " string ," +
                        DEADLINE_DAY + " integer ," +
                        DEADLINE_MONTH + " integer ," +
                        DEADLINE_YEAR + " integer ," +
                        STATUS  + " flag integer default 0 " +
                        ");" ;

    }
}
