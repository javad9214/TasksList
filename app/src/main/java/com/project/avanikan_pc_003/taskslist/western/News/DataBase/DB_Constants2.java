package com.project.avanikan_pc_003.taskslist.western.News.DataBase;


public class DB_Constants2 {


    public static final String DATABASE_NAME = "User.db" ;
    public static final int DATABASE_VERSION = 1 ;

        public static class UsersInfoTable{

            public static final String USER_TABLE_NAME  = "user_info" ;
            public static final String USER_UID = "_id" ;
            public static final String USERNAME = "user_name" ;
            public static final String Last_Name = "family_name" ;
            public static final String ToTAL_MONEY = "total_money" ;
            public static final String LEFT_MONEY = "left_money" ;
            public static final String DATE = "date" ;
            public static final String NAME = "name" ;
            public static final String PHONE = "phone" ;


            public static final String CREATE_ALARMS_TABLE_SYNTAX =
                    "create table " + USER_TABLE_NAME  + " ( " +
                            USER_UID + " integer primary key autoincrement ," +
                            USERNAME + " integer ," +
                            Last_Name + " text ,"+
                            ToTAL_MONEY + " real ,"+
                            LEFT_MONEY + " real ," +
                            DATE + " text ," +
                            NAME + " text ," +
                            PHONE + " real " +
                            ");";


        }


    public static class ActiveUsersTable {

        public static final String USER_TABLE_NAME = "active_user";
        public static final String USER_UID = "_id";
        public static final String START_TIME = "start_time";
        public static final String END_TIME = "end_time";
        public static final String USERNAME = "username";
        public static final String MONEY = "money";
        public static final String TIME = "time";
        public static final String NUM_JOYSTICK = "num_joystick";


        public static final String CREATE_ACTIVE_USER_TABLE_SYNTAX =
                "create table " + USER_TABLE_NAME  + " ( " +
                        USER_UID + " integer primary key autoincrement ," +
                        USERNAME + " integer ," +
                        START_TIME + " text ,"+
                        END_TIME + " text ,"+
                        NUM_JOYSTICK + " integer ," +
                        MONEY + " real ," +
                        TIME + " real " +
                        ");";
    }

}
