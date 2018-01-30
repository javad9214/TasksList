package com.project.avanikan_pc_003.taskslist.western;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.project.avanikan_pc_003.taskslist.R;


public class Fragment3 extends Fragment {

    EditText edit_name , edit_time ;

    View view1 ;

    public static final String TAG = "====>";

    public Fragment3() {

    }



    public static Fragment3 createInstance(){

        Fragment3 fragment3 = new Fragment3();
        return fragment3;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        Log.i(TAG, "onCreateView: 3");

        View view = inflater.inflate(R.layout.sample_fragmet3 , container , false);



        this.view1 = view ;

        return view ;
    }





}
