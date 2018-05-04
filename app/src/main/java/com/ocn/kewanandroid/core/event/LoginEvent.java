package com.ocn.kewanandroid.core.event;

/**
 * Created by kevin on 2018/4/20.
 */

public class LoginEvent {
    private boolean isLogin;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public LoginEvent(boolean isLogin) {

        this.isLogin = isLogin;
    }
}
