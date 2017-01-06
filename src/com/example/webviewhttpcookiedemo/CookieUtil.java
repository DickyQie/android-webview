package com.example.webviewhttpcookiedemo;

import android.content.Context;
import android.content.SharedPreferences;

public class CookieUtil {
    public static void setParam(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("WaterHoney", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("logincookie", key);
        editor.commit();
    }

    public static Object getParam(Context context) {
        SharedPreferences sp = context.getSharedPreferences("WaterHoney", Context.MODE_PRIVATE);
        return sp.getString("logincookie", "");
    }

    public static void clear(Context context) {
        SharedPreferences sp = context.getSharedPreferences("WaterHoney", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
    
    
    
   
}
