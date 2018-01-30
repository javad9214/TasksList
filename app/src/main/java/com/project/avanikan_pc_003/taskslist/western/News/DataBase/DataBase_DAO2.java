package com.project.avanikan_pc_003.taskslist.western.News.DataBase;


import com.project.avanikan_pc_003.taskslist.western.News.datas.User;
import com.project.avanikan_pc_003.taskslist.western.News.datas.ActiveUser;

import java.util.ArrayList;

public interface DataBase_DAO2 {


    long addUser(User user) ;

    int Update_User(User user, int UpdateMode);

    int Delete_User(User user) ;

    User Search_User(User user, int SearchMode);

    User Search_User(int id);

    long addActiveUser(ActiveUser activeUser);

    int Update_Active_User(ActiveUser activeUser, int updateMode);

    int Delete_ActiveUser(ActiveUser activeUser);

    ArrayList<ActiveUser> Show_Active_user();

    ArrayList<User> show_user();

}
