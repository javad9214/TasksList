package com.project.avanikan_pc_003.taskslist.western.News;


import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.RemoteInput;
import android.support.v4.view.ViewPager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;

import com.project.avanikan_pc_003.taskslist.MainTask;
import com.project.avanikan_pc_003.taskslist.R;
import com.project.avanikan_pc_003.taskslist.western.Custom_Adapter;
import com.project.avanikan_pc_003.taskslist.western.Fragment1;
import com.project.avanikan_pc_003.taskslist.western.Fragment2;
import com.project.avanikan_pc_003.taskslist.western.Fragment3;
import com.project.avanikan_pc_003.taskslist.western.News.Profile.Profile_info;
import com.project.avanikan_pc_003.taskslist.western.ZoomOutPageTransformer;

public class Profile_Dialog extends DialogFragment {

    Fragment1 fragment1 ;
    Fragment2 fragment2 ;

    Profile_info profile_info ;

    private static final String KEY_TEXT_REPLY = "key_text_reply";

    TabLayout tabLayout ;

    ViewPager viewPager ;

    public static final String TAG = "=====>";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_dialog , container , false);

        init(rootView);


        Custom_Adapter adapter = new Custom_Adapter(getChildFragmentManager());
        fragment1 = new Fragment1() ;
        fragment2 = new Fragment2();
        profile_info = new Profile_info() ;

        adapter.addFragment("Profile" , fragment1 );
        adapter.addFragment("Information" , profile_info );

        tabLayout = rootView.findViewById(R.id.tab_profile_dialog);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(true , new ZoomOutPageTransformer());

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_face_green_50dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black_50dp);



        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                switch (position){

                    case 0 :
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_face_green_50dp);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_black_50dp);

                        Button button = viewPager.getRootView().findViewById(R.id.start);
                        final ProgressBar progressBar = viewPager.getRootView().findViewById(R.id.progress) ;

                        button.setOnClickListener(new View.OnClickListener() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onClick(View view) {
                                Log.i(TAG, "onClick: ");
                                ObjectAnimator animator ;
                                animator = ObjectAnimator.ofInt(progressBar , "progress", 0 , 100);
                                animator.setDuration(10000);
                                animator.setInterpolator(new LinearInterpolator());
                                animator.start();
//                                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
//                                try {
//                                    Ringtone r = RingtoneManager.getRingtone(getContext(), notification);
//                                   // r.play();
//
//
//                                } catch (Exception e) {
//                                    e.printStackTrace();
//                                }


                                // for long click listener
                               // Vibrator vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
                               // vibrator.vibrate(100);


                                //for notifications
                                long[] pattern = { 0, 5000, 100 }; //0 to start now, 200 to vibrate 200 ms, 0 to sleep for 0 ms.
                               // vibrator.vibrate(pattern, 0); // 0 to repeat endlessly.



                                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
                                mBuilder.setSmallIcon(R.drawable.ic_face_green_50dp);
                                mBuilder.setContentTitle("Notification Alert, Click Me!");
                                mBuilder.setOngoing(true);
                                mBuilder.setLights(Color.BLUE, 500, 500);
                                long[] pattern2 = {500,500,500,500,500,500,500,500,500} ;
                               //mBuilder.setSound(notification , RingtoneManager.TYPE_RINGTONE );
                                mBuilder.setContentText("Hi, This is Android Notification Detail!");
                                mBuilder.setPriority(Notification.PRIORITY_HIGH);
                                mBuilder.setCategory(Notification.CATEGORY_CALL);
                                mBuilder.mNotification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR ;
                               // mBuilder.setStyle(new NotificationCompat.InboxStyle());
                                //mBuilder.setVibrate(pattern2);



                                RemoteInput remoteInput = new RemoteInput.Builder(KEY_TEXT_REPLY).setLabel("answer me ").build();

                                //PendingIntent that restarts the current activity instance.
                                Intent resultIntent = new Intent(getContext(), MainTask.class);
                                resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent resultPendingIntent = PendingIntent.getActivity(getContext() , 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                                NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                                        android.R.drawable.sym_action_chat, "REPLY", resultPendingIntent)
                                        .addRemoteInput(remoteInput)
                                        .setAllowGeneratedReplies(true)
                                        .build();


                                mBuilder.addAction(replyAction);


                                Intent intent = new Intent("dismissIntent");
                                intent.putExtra("notificationId", 1);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                PendingIntent dismissIntent = PendingIntent.getActivity(getContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                                mBuilder.addAction(android.R.drawable.ic_menu_close_clear_cancel, "DISMISS", dismissIntent);


                                NotificationManager mNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                                mNotificationManager.notify(1, mBuilder.build());
                                mNotificationManager.cancel(getActivity().getIntent().getIntExtra("notificationId" , 1 ));

                            }
                        });

                        break;

                    case 1 :
                        tabLayout.getTabAt(0).setIcon(R.drawable.ic_face_black_50dp);
                        tabLayout.getTabAt(1).setIcon(R.drawable.ic_info_green_50dp);

                        break;



                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return rootView;
    }



    private  void  init (View view){
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);

    }
}
