package com.ocn.kewanandroid.core.event;

/**
 * Created by kevin on 2018/4/19.
 */

public class NightModeEvent {
    private boolean isNightMode;

    public boolean isNightMode(){
        return isNightMode;
    }
    public void setNightMode(boolean nightMode){
        isNightMode = nightMode;
    }
}
