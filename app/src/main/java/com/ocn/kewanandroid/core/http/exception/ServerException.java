package com.ocn.kewanandroid.core.http.exception;

/**
 * Created by kevin on 2018/4/20.
 */

public class ServerException extends Exception {

    private int code;

    public ServerException(String message) {
        super(message);
    }
    public ServerException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
