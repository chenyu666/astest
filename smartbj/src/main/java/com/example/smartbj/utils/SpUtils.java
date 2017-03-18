package com.example.smartbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by HSAEE on 2017/3/17.
 */

public class SpUtils {
    private static final String SP_NAME = "smaltbj_sp";
    public static final String KEY_ISSTARTED = "issplah";
    public static void saveBoolean(Context context, String key ,boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
