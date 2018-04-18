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
     * Path
     */
    public static final String PATH_DATA=MyApp.getInstance().getCacheDir().getAbsolutePath()+ File.separatorChar+"data";
    public static final String PATH_CACHE = PATH_DATA +"/NetCache";
}
