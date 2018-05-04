package com.ocn.kewanandroid.app;

import java.io.File;

/**
 * Created by kevin on 2018/4/11.
 */

public class Constants {
    static final String  BUGLY_ID = "3840f5b8fa";
    static final String DB_NAME = "ke_wan_android.db";
    /**
     * Shared Preference key
     */
    public static final String CURRENT_PAGE = "current_page";

    public static final String PROJECT_CURRENT_PAGE = "project_current_page";

    public static final String ACCOUNT = "account";

    public static final String PASSWORD = "password";

    public static final String LOGIN_STATUS = "login_status";

    public static final String AUTO_CACHE_STATE = "auto_cache_state";

    public static final String NO_IMAGE_STATE = "no_image_state";

    public static final String NIGHT_MODE_STATE = "night_mode_state";


    /**
     * Intent params
     */
    public static final String ARG_PARAM1 = "param1";

    public static final String ARG_PARAM2 = "param2";


    /**
     * Path
     */
    public static final String PATH_DATA=MyApp.getInstance().getCacheDir().getAbsolutePath()+ File.separatorChar+"data";
    public static final String PATH_CACHE = PATH_DATA +"/NetCache";

    /**
     * Tag fragment classify
     */
    public static final int TYPE_MAIN_PAGER = 0;

    public static final int TYPE_KNOWLEDGE = 1;

    public static final int TYPE_NAVIGATION = 2;

    public static final int TYPE_PROJECT = 3;

    public static final int TYPE_COLLECT = 4;

    public static final int TYPE_SETTING = 5;

    /**
     * Main Pager
     */
    public static final String SEARCH_TEXT = "search_text";

    public static final String MENU_BUILDER = "MenuBuilder";

    public static final String LOGIN_DATA = "login_data";

    public static final String BANNER_DATA = "banner_data";

    public static final String ARTICLE_DATA = "article_data";
}
