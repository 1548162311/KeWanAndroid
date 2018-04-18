package com.ocn.kewanandroid.core.http.cookies;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;

import com.ocn.kewanandroid.app.MyApp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Cookie;
import okhttp3.HttpUrl;

/**
 * Created by kevin on 2018/4/16.
 */

public class PersistentCookieStore {
    private static final String LOG_TAG = "PersistentCookieStore";
    private static final String COOKIE_PREFS= "Cookies_Prefs";
    private final Map<String,ConcurrentHashMap<String, Cookie>> cookies;
    private final SharedPreferences cookiePrefs;

    PersistentCookieStore(){
        cookiePrefs = MyApp.getInstance().getSharedPreferences(COOKIE_PREFS, Context.MODE_PRIVATE);
        cookies = new HashMap<>();
        //将持久化的cookies缓存到内存中 即map cookies
        Map<String,?> prefsMap = cookiePrefs.getAll();
        for (Map.Entry<String,?> entry:prefsMap.entrySet()){
         String[] cookieNames = TextUtils.split((String) entry.getValue(),",");
         for(String name :cookieNames){
             String encodedCookie = cookiePrefs.getString(name,null);
             if (encodedCookie != null){
                 Cookie decodedCookie = decodedCookie(encodedCookie);
                 if(decodedCookie!= null){
                     if (!cookies.containsKey(entry.getKey())){
                         cookies.put(entry.getKey(),new ConcurrentHashMap<String, Cookie>());
                     }
                     cookies.get(entry.getKey()).put(name,decodedCookie);
                 }

             }
         }
        }
    }
    public void add(HttpUrl url,Cookie cookie){
        String name = getCookieToken(cookie);
        //将cookies缓存到内存中 如果缓存过期 就重置此cookie
        if (!cookie.persistent()){
            if (!cookies.containsKey(url.host())){
                cookies.put(url.host(),new ConcurrentHashMap<String, Cookie>(10));
            }
            cookies.get(url.host()).put(name,cookie);
        }else{
            if (cookies.containsKey(url.host())){
                cookies.get(url.host()).remove(name);
            }
        }
        //将cookies持久化到本地
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.putString(url.host(),TextUtils.join(",",cookies.get(url.host()).entrySet()));
        prefsWriter.putString(name, encodeCookie(new OkHttpCookies(cookie)));
        prefsWriter.apply();
    }
    public List<Cookie> get(HttpUrl url){
        ArrayList<Cookie> ret = new ArrayList<>();
        if (cookies.containsKey(url.host())){
            ret.addAll(cookies.get(url.host()).values());
        }
        return ret;
    }
    void removeAll(){
        SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
        prefsWriter.clear();
        prefsWriter.apply();
        cookies.clear();
    }
    boolean remove(HttpUrl url,Cookie cookie){
        String name = getCookieToken(cookie);
        if (cookies.containsKey(url.host()) && cookies.get(url.host()).containsKey(name)) {
            cookies.get(url.host()).remove(name);
            SharedPreferences.Editor prefsWriter = cookiePrefs.edit();
            if (cookiePrefs.contains(name)){
                prefsWriter.remove(name);
            }
            prefsWriter.putString(url.host(),TextUtils.join(",",cookies.get(url.host()).keySet()));
            prefsWriter.apply();
            return true;
        }else{
            return false;
        }
    }
    List<Cookie> getCookies(){
        ArrayList<Cookie> ret = new ArrayList<>();
        for(String key :cookies.keySet()){
            ret.addAll(cookies.get(key).values());
        }
        return ret;
    }

    /**
     * cookies 序列化成 string
     * @param cookie
     * @return
     */
    private String encodeCookie(OkHttpCookies cookie) {
        if (cookie==null){
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(os);
            outputStream.writeObject(cookie);
        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in encodeCookie", e);
            return null;
        }
        return byteArrayToHexString(os.toByteArray());
    }

    /**
     * 二进制数组转十六进制字符串
     * @param bytes
     * @return
     */
    private String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length*2);
        for(byte element :bytes){
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase(Locale.US);
    }


    private String getCookieToken(Cookie cookie) {
        return cookie.name() +"@"+cookie.domain();
    }

    /**
     * 将字符串反序列化成cookies
     * @param cookieString
     * @return
     */
    private Cookie decodedCookie(String cookieString) {
        byte[] bytes = hexStringToByteArray(cookieString);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Cookie cookie = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            cookie = ((OkHttpCookies)objectInputStream.readObject()).getCookies();

        } catch (IOException e) {
            Log.d(LOG_TAG, "IOException in decodeCookie", e);
        } catch (ClassNotFoundException e) {
            Log.d(LOG_TAG, "ClassNotFoundException in decodeCookie", e);
        }
        return cookie;
    }

    /**
     * 十六进制字符串转二进制数组
     * @param hexString
     * @return
     */
    private byte[] hexStringToByteArray(String hexString) {
        int len  = hexString.length();
        byte[] data = new byte[len/2];
        for (int i=0;i<len;i+=2){
            data[i/2] =(byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character.digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

}
