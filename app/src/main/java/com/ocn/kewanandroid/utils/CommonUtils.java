package com.ocn.kewanandroid.utils;

import android.text.TextUtils;

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
}
