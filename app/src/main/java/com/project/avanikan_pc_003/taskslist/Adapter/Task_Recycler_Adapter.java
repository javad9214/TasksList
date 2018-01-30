package com.project.avanikan_pc_003.taskslist.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.project.avanikan_pc_003.taskslist.App;
import com.project.avanikan_pc_003.taskslist.Data.CalendarTool;
import com.project.avanikan_pc_003.taskslist.Data.Tasks;
import com.project.avanikan_pc_003.taskslist.DataBase.Constants;
import com.project.avanikan_pc_003.taskslist.DataBase.DBTasksOpenHelper;
import com.project.avanikan_pc_003.taskslist.DataBase.DataBaseOperation;
import com.project.avanikan_pc_003.taskslist.MainTask;
import com.project.avanikan_pc_003.taskslist.R;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;


public class Task_Recycler_Adapter extends RecyclerView.Adapter<Task_Recycler_Adapter.Recycler_viewHolder> {

    Tasks tasks ;
    List<Tasks> list_task_data ;

    public static final String TAG = "===>" ;
    public Context context ;
    public Activity activity ;

    public Task_Recycler_Adapter(List<Tasks> data , Context context  , Activity activity) {
        this.list_task_data = data;
        this.context = context;
        this.activity = activity ;
    }

    @Override
    public Recycler_viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskslist_content , parent , false);

        final Recycler_viewHolder recyclerView_holder = new Recycler_viewHolder(view, list_task_data);

        return recyclerView_holder ;

    }

    @Override
    public void onBindViewHolder(Recycler_viewHolder holder, int position) {

        Log.i(TAG, "onBindViewHolder: " + String.valueOf(position));
        Recycler_viewHolder holder1 = holder ;
        tasks = list_task_data.get(position);
        holder1.textView_task.setText(tasks.task);
        holder1.textView_deadline.setText(tasks.deadline_day + "/" + tasks.deadline_month + "/" + tasks.deadline_year);



    }

    @Override
    public int getItemCount() {
        return list_task_data.size();
    }


    public class Recycler_viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView_task , textView_deadline   ;
        CheckBox checkBox_status ;
        Button deleteTask ;
        List<Tasks> LIST;

        public Recycler_viewHolder(View itemView , List<Tasks> lst) {
            super(itemView);

            textView_task = itemView.findViewById(R.id.textview_task);
            textView_deadline = itemView.findViewById(R.id.textview_deadline);
            checkBox_status = itemView.findViewById(R.id.checkbox_status);
            deleteTask = itemView.findViewById(R.id.deleteTask);
            deleteTask.setOnClickListener(this);
            textView_task.setOnClickListener(this);
            textView_deadline.setOnClickListener(this);
            checkBox_status.setOnClickListener(this);
            LIST=lst;
        }

        @Override
        public void onClick(View view) {

            final MainTask  mainTask = new MainTask();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            switch (view.getId()){

                case R.id.deleteTask :

                    Log.i(TAG, "onClick: " + getLayoutPosition());
                    Log.i(TAG, "want delete real ID : " +LIST.get(getLayoutPosition()).UID);

                    DataBaseOperation db = App.getDataBaseOperation();
                    db.deleteTask(LIST.get(getLayoutPosition()).UID);
                    Log.i(TAG, "onClick:    context : "   + context.toString());

                    mainTask.getTaskFromDB(activity , context );
                    break;


                case R.id.textview_task :


                    View dialog = inflater.inflate(R.layout.dialog_edit_task  , null);

                    final EditText editText_taskEditing  ;
                    editText_taskEditing = dialog.findViewById(R.id.editText_editing_task);
                    final ArrayList<Tasks> list = new ArrayList<>() ;

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setView(dialog);
                    editText_taskEditing.setHint(LIST.get(getLayoutPosition()).task);
                    builder.setTitle("Editing your Task ...");
                    builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Tasks tasks = new Tasks();
                            if (editText_taskEditing.getText().toString().trim().equals("")){
                                tasks.task = "New Task";
                            }else {
                                tasks.task = editText_taskEditing.getText().toString();
                            }

                            list.add(tasks);
                            Toast.makeText(context , "task was edited  ", Toast.LENGTH_SHORT).show();
                            DataBaseOperation db = App.getDataBaseOperation();
                            db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 0 , tasks );
                            mainTask.getTaskFromDB(activity , context );

                        }
                    });
                    builder.setNegativeButton("Cancel" , null);
                    builder.show();
                    break;


                case R.id.textview_deadline :

                    View dialog1 = inflater.inflate(R.layout.dialog_edit_deadline  , null);

                    final EditText editingText_deadline_day , editingText_deadline_month , editingText_deadline_year   ;
                    editingText_deadline_day = dialog1.findViewById(R.id.editing_deadline_day);
                    editingText_deadline_month = dialog1.findViewById(R.id.editing_deadline_month);
                    editingText_deadline_year = dialog1.findViewById(R.id.editing_deadline_year);
                    final ArrayList<Tasks> list1 = new ArrayList<>() ;

                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setView(dialog1);

                    editingText_deadline_day.setHint(String.valueOf(LIST.get(getLayoutPosition()).deadline_day));
                    editingText_deadline_month.setHint(String.valueOf(LIST.get(getLayoutPosition()).deadline_month));
                    editingText_deadline_year.setHint(String.valueOf(LIST.get(getLayoutPosition()).deadline_year));
                    builder1.setTitle("Editing The DeadLine ...");
                    builder1.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Tasks tasks = new Tasks();
                            CalendarTool calendarTool = new CalendarTool();
                            DataBaseOperation db = App.getDataBaseOperation();

                            if (editingText_deadline_day.getText().toString().trim().equals("")){
                                tasks.deadline_day = calendarTool.getIranianDay();
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 1 , tasks );
                            }else {
                                tasks.deadline_day = Integer.parseInt(editingText_deadline_day.getText().toString());
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 1 , tasks );
                            }

                            if (editingText_deadline_month.getText().toString().trim().equals("")){
                                tasks.deadline_month = calendarTool.getIranianMonth();
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 2 , tasks );
                            }else {
                                tasks.deadline_month = Integer.parseInt(editingText_deadline_month.getText().toString());
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 2 , tasks );
                            }

                            if (editingText_deadline_year.getText().toString().trim().equals("")){
                                tasks.deadline_year = calendarTool.getIranianYear();
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 3 , tasks );
                            }else {
                                tasks.deadline_year = Integer.parseInt(editingText_deadline_year.getText().toString());
                                list1.add(tasks);
                                db.UpdateTasks(LIST.get(getLayoutPosition()).UID , 3 , tasks );
                            }




                            Toast.makeText(context , "DeadLine Was Edited  ", Toast.LENGTH_SHORT).show();
                            mainTask.getTaskFromDB(activity , context );

                        }
                    });
                    builder1.setNegativeButton("Cancel" , null);
                    builder1.show();
                    break;


                case R.id.checkbox_status :

                    DataBaseOperation db1 = App.getDataBaseOperation();
                    Tasks tasks1 = new Tasks();
                    if (db1.searchByUID(LIST.get(getLayoutPosition()).UID) == 0){
                        tasks1.status = 1 ;
                    }else {
                        tasks1.status = 0 ;
                    }


                    db1.UpdateTasks(LIST.get(getLayoutPosition()).UID , 4 , tasks1) ;
                    mainTask.getTaskFromDB(activity ,context);
                    mainTask.checked_items(activity , context);
                    break;


            }



        }





    }


}
