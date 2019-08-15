package com.example.hellouninstall;

import android.app.ActivityManager;
import android.app.Service;
import android.content.*;
import android.os.*;
import android.widget.Toast;

import java.util.List;

public class BackgroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {

                ActivityManager mActivityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
                List<ActivityManager.RunningTaskInfo> RunningTask = mActivityManager.getRunningTasks(1);
                ActivityManager.RunningTaskInfo ar = RunningTask.get(0);
                String activityOnTop =ar.topActivity.getClassName();

                Toast.makeText(context, "Service is still running :"+activityOnTop, Toast.LENGTH_LONG).show();
                handler.postDelayed(runnable, 10000);
            }
        };


        handler.postDelayed(runnable, 15000);
    }

    @Override
    public void onDestroy() {
        /* IF YOU WANT THIS SERVICE KILLED WITH THE APP THEN UNCOMMENT THE FOLLOWING LINE */
        //handler.removeCallbacks(runnable);
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart(Intent intent, int startid) {
        Toast.makeText(this, "Service started by user.", Toast.LENGTH_LONG).show();
    }
}