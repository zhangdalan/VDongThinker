package com.thinker.vdongthinker.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

public class MyApplication extends Application {
    private static MyApplication myApplication;
    // 页面集合
    private ArrayList<Activity> activityList = new ArrayList<>();
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        context = this;

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    //开启新页面，需添加到集合中，方便退出统一管理
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    //退出清空所有的activity
    public void clearAllActivities() {
        if (activityList.size() > 0) {
            for (Activity activity : activityList) {
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
    }
}