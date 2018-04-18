package com.ocn.kewanandroid.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ocn.kewanandroid.R;
import com.ocn.kewanandroid.app.MyApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by kevin on 2018/4/11.
 */

public class CommonUtils {
    /**
     * 判断2个对象是否相等
     *
     * @param a Object a
     * @param b Object b
     * @return isEqual
     */
    public static boolean isEquals(Object a, Object b) {
        return (a == null) ? (b == null) : a.equals(b);
    }

    public static String getProcessName(int pid){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc"+pid+"/cmdline"));
            String processName = reader.readLine();
            if(!TextUtils.isEmpty(processName)){
                processName =processName.trim();
            }
            return processName;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (reader!=null){
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void showSnackMessage(Activity activity,String msg){
        Snackbar snackbar  = Snackbar.make(activity.getWindow().getDecorView(),msg,Snackbar.LENGTH_SHORT);
        View view = snackbar.getView();
        ((TextView)view.findViewById(R.id.snackbar_text)).setTextColor(ContextCompat.getColor(activity,R.color.white));
        snackbar.show();
    }

    public static boolean isNetworkConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApp.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
        assert connectivityManager != null;
        return connectivityManager.getActiveNetworkInfo() != null;
    }
}
