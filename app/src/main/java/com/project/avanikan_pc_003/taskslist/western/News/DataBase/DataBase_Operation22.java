package com.project.avanikan_pc_003.taskslist.western.News.DataBase;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.project.avanikan_pc_003.taskslist.western.News.datas.User;
import com.project.avanikan_pc_003.taskslist.western.News.datas.ActiveUser;

import java.util.ArrayList;

public class DataBase_Operation22 implements DataBase_DAO2 {


    private static final String TAG = "===>";
    private SQLiteDatabase db_user ;


    public DataBase_Operation22(Context context) {

        DataBase_OpenHelper2 dataBase_openHelper2 = new DataBase_OpenHelper2(context);
        db_user = dataBase_openHelper2.getWritableDatabase();
    }

    @Override
    public long addUser(User user) {

        ContentValues values = new ContentValues();
        values.put(DB_Constants2.UsersInfoTable.USERNAME , user.UserName);
        values.put(DB_Constants2.UsersInfoTable.Last_Name , user.LastName);
        values.put(DB_Constants2.UsersInfoTable.ToTAL_MONEY , user.TotalMoney);
        values.put(DB_Constants2.UsersInfoTable.LEFT_MONEY , user.LeftMoney);
        values.put(DB_Constants2.UsersInfoTable.NAME , user.Name);
        values.put(DB_Constants2.UsersInfoTable.PHONE , user.Phone);
        values.put(DB_Constants2.UsersInfoTable.DATE , user.Date);
        return db_user.insert(DB_Constants2.UsersInfoTable.USER_TABLE_NAME ,null , values) ;

    }

    @Override
    public int Update_User(User user, int UpdateMode) {

        ContentValues values = new ContentValues();
        String where = "" ;
        String[] args = new String[1];

        User user1 = new User();
        String selections ;
        Cursor cursor ;
        selections = DB_Constants2.UsersInfoTable.USERNAME + " like ?";
        String[] selectionArgs = {"%" + user.UserName + "%"};
        cursor = db_user.query(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , null , selections , selectionArgs , null , null ,null);
        cursor.moveToFirst();
        while (true) {
            user1.TotalMoney = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY));
            user1.LeftMoney = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.LEFT_MONEY));

            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                break;
            }
        }
        cursor.close();



        switch (UpdateMode){

            case 0 : // User Name
                values.put(DB_Constants2.UsersInfoTable.USERNAME , user.UserName);
                break;

            case 1 : // Password
                values.put(DB_Constants2.UsersInfoTable.Last_Name, user.LastName);
                break;

            case 2 : // Total Money
                user.TotalMoney = user.TotalMoney +  user1.TotalMoney ;
                Log.i(TAG, "Update_User: " + user.TotalMoney);
                values.put(DB_Constants2.UsersInfoTable.ToTAL_MONEY , user.TotalMoney);
                break;

            case 3 : //Left Money
                values.put(DB_Constants2.UsersInfoTable.LEFT_MONEY , user.LeftMoney);
                break;

            case 4 : // Name
                values.put(DB_Constants2.UsersInfoTable.NAME , user.Name);
                break;

            case 5 : //Phone
                values.put(DB_Constants2.UsersInfoTable.PHONE , user.Phone);
                break;

            case 6 : // Date
                values.put(DB_Constants2.UsersInfoTable.DATE , user.Date);
                break;

        }

        where = DB_Constants2.UsersInfoTable.USER_UID + " = ?" ;
        args[0] = String.valueOf(user.UID);


        return db_user.update(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , values , where , args );
    }

    @Override
    public int Delete_User(User user) {
        String [] args = {String.valueOf(user.UID)};

        return db_user.delete(DB_Constants2.UsersInfoTable.USER_TABLE_NAME ,
                DB_Constants2.UsersInfoTable.USER_UID + " = ?"  , args );
    }

    @Override
    public User Search_User(User user, int SearchMode) {

        User user1 = new User();
        String selections ;
        Cursor cursor ;
        switch (SearchMode){

            case 0 : // search by username
                Log.i(TAG, "Search_User: username");
                selections = DB_Constants2.UsersInfoTable.USERNAME + " like ?";
                String[] selectionArgs = {"%" + user.UserName + "%"};
                cursor = db_user.query(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , null , selections , selectionArgs , null , null ,null);
                if (cursor.getCount() == 0 ){
                    user1.Name = "NO User Found" ;
                }else {
                    cursor.moveToFirst();
                    while (true) {
                        user1.UID = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USER_UID));
                        user1.UserName = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USERNAME));
                        user1.LastName = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.Last_Name));
                        user1.Phone = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.PHONE));
                        user1.TotalMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY));
                        Log.i(TAG, "Search_User: " +  cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY)));
                        user1.LeftMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.LEFT_MONEY));
                        user1.Name = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.NAME));
                        user1.Date = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.DATE));

                        cursor.moveToNext();
                        if (cursor.isAfterLast()) {
                            break;
                        }
                    }
                    cursor.close();
                    return user1;
                }



            case 1 : //search by phone
                Log.i(TAG, "Search_User: phone");
                selections = DB_Constants2.UsersInfoTable.PHONE + " like ?";
                String[] selectionArgs_Pass = {"%" + user.Phone + "%"};
                cursor = db_user.query(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , null , selections , selectionArgs_Pass , null , null ,null);
                if (cursor.getCount() == 0 ){
                    user1.Name = "NO User Found" ;
                }else {
                    cursor.moveToFirst();
                    while (true){
                        user1.UID = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USER_UID));
                        user1.UserName = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USERNAME));
                        user1.LastName = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.Last_Name));
                        user1.Phone = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.PHONE));
                        user1.TotalMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY));
                        Log.i(TAG, "Search_User: " +  cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY)));
                        user1.LeftMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.LEFT_MONEY));
                        user1.Name = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.NAME));
                        user1.Date = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.DATE));

                        cursor.moveToNext();
                        if (cursor.isAfterLast()){
                            break;
                        }
                    }
                    cursor.close();
                    return user1 ;
                }

        }

        return user1 ;
    }

    @Override
    public User Search_User(int id) {

        User user1 = new User();
        String selections ;
        Cursor cursor ;

        selections = DB_Constants2.UsersInfoTable.USER_UID + " like ?";
        String[] selectionArgs = {"%" + id + "%"};

        cursor = db_user.query(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , null , selections , selectionArgs , null , null ,null);

        cursor.moveToFirst();
        while (true) {
            user1.UID = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USER_UID));
            user1.UserName = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USERNAME));
            user1.LastName = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.Last_Name));
            user1.Phone = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.PHONE));
            user1.TotalMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY));
            user1.LeftMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.LEFT_MONEY));
            user1.Name = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.NAME));
            user1.Date = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.DATE));

            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                break;
            }
        }
        cursor.close();


        return user1;
    }

    @Override
    public long addActiveUser(ActiveUser activeUser) {

        ContentValues values = new ContentValues();
        values.put(DB_Constants2.ActiveUsersTable.USERNAME , activeUser.Username);
        values.put(DB_Constants2.ActiveUsersTable.START_TIME , activeUser.startTime);
        values.put(DB_Constants2.ActiveUsersTable.END_TIME , activeUser.endTime);
        values.put(DB_Constants2.ActiveUsersTable.MONEY , activeUser.money);
        values.put(DB_Constants2.ActiveUsersTable.TIME , activeUser.Time);
        values.put(DB_Constants2.ActiveUsersTable.NUM_JOYSTICK , activeUser.NumJoyStick);
        return db_user.insert(DB_Constants2.ActiveUsersTable.USER_TABLE_NAME ,null , values) ;


    }

    @Override
    public int Update_Active_User(ActiveUser activeUser, int updateMode) {
        return 0;
    }

    @Override
    public int Delete_ActiveUser(ActiveUser activeUser) {
        String [] args = {String.valueOf(activeUser.Username)};

        return db_user.delete(DB_Constants2.ActiveUsersTable.USER_TABLE_NAME ,
                DB_Constants2.ActiveUsersTable.USERNAME + " = ?"  , args );
    }

    @Override
    public ArrayList<ActiveUser> Show_Active_user() {

        ArrayList<ActiveUser> list ;
        ActiveUser activeUser;
        Cursor cursor ;
        cursor = db_user.query(DB_Constants2.ActiveUsersTable.USER_TABLE_NAME , null , null , null , null , null ,null);

        list = new ArrayList<>();

        if (cursor.getCount() == 0 ){
            activeUser = new ActiveUser();
            list.add(activeUser);
        }else {

            cursor.moveToFirst();
            while (true) {
                activeUser = new ActiveUser();

                activeUser.Username = cursor.getInt(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.USERNAME));
                activeUser.NumJoyStick = cursor.getInt(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.NUM_JOYSTICK));
                activeUser.startTime = cursor.getString(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.START_TIME));
                activeUser.endTime = cursor.getString(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.END_TIME));
                activeUser.money = cursor.getLong(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.MONEY));
                activeUser.Time = cursor.getLong(cursor.getColumnIndex(DB_Constants2.ActiveUsersTable.TIME));


                list.add(activeUser);

                cursor.moveToNext();
                if (cursor.isAfterLast()) {
                    break;
                }
            }

        }
            cursor.close();


        return list;
    }

    @Override
    public ArrayList<User> show_user() {
        ArrayList<User> list ;
        User user;
        Cursor cursor ;
        cursor = db_user.query(DB_Constants2.UsersInfoTable.USER_TABLE_NAME , null , null , null , null , null ,null);

        list = new ArrayList<>();

        cursor.moveToFirst();
        while (true) {
            user = new User();

            user.Name = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.NAME));
            user.LeftMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.LEFT_MONEY));
            user.TotalMoney = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.ToTAL_MONEY));
            user.Date = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.DATE));
            user.UserName = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USERNAME));
            user.Phone = cursor.getLong(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.PHONE));
            user.UID = cursor.getInt(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.USER_UID));
            user.LastName = cursor.getString(cursor.getColumnIndex(DB_Constants2.UsersInfoTable.Last_Name));

            list.add(user);

            cursor.moveToNext();
            if (cursor.isAfterLast()) {
                break;
            }
        }
        cursor.close();

        return list ;
    }


}
