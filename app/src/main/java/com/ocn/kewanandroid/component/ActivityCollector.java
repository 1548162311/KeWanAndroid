package com.ocn.kewanandroid.component;

import android.app.Activity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by kevin on 2018/4/13.
 */

public class ActivityCollector {
    private static ActivityCollector activityCollector;
    public synchronized static ActivityCollector getInstance(){
        if (activityCollector==null){
            activityCollector = new ActivityCollector();
        }
        return activityCollector;
    }
    private Set<Activity> allActivities;

    public void addActivity(Activity activity){
        if (allActivities==null){
            allActivities = new HashSet<>();
        }
        allActivities.add(activity);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }
    public void exitApp() {
        if (allActivities!=null){
            synchronized (allActivities){
                for (Activity activity : allActivities){
                    activity.finish();
                }
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
