package com.kiovee.utils;

import android.util.Log;

import com.kiovee.BuildConfig;


public class Logger {
    private static final String TAG = "TAG";
    private static final boolean BUILD_MODE = BuildConfig.DEBUG;
    private static int LOG_LEVEL = 6;
    private static int VERBOS = 1;
    private static int DEBUG = 2;
    private static int INFO = 3;
    private static int WARN = 4;
    private static int ERROR = 5;


    public static void e(String msg) {
        if (BUILD_MODE && LOG_LEVEL > ERROR)
            Log.e(TAG, msg);
    }

    public static void w(String msg) {
        if (BUILD_MODE && LOG_LEVEL > WARN)
            Log.w(TAG, msg);
    }

    public static void i(String msg) {
        if (BUILD_MODE && LOG_LEVEL > INFO)
            Log.i(TAG, msg);
    }

    public static void d(String msg) {
        if (BUILD_MODE && LOG_LEVEL > DEBUG)
            Log.d(TAG, msg);
    }

    public static void v(String msg) {
        if (BUILD_MODE && LOG_LEVEL > VERBOS)
            Log.v(TAG, msg);
    }
}
