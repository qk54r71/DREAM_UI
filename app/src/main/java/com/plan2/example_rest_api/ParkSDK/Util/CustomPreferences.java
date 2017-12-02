package com.plan2.example_rest_api.ParkSDK.Util;

import android.content.Context;
import android.content.SharedPreferences;

import com.plan2.example_rest_api.ParkSDK.Debug.Loging;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by park on 2017-01-07.
 */

public class CustomPreferences {
    /**
     * 저장된 값을 요청해서 String으로 불러옴
     *
     * @param context    : 현재 화면
     * @param strRequest : 불러올 값
     * @return
     */
    public static String loadSharedPreferences_String(Context context, String strRequest) {
        SharedPreferences prefs = context.getSharedPreferences("dialUser", MODE_PRIVATE);

        Loging.i(context.getClass().getName(), strRequest + " : " + prefs.getString(strRequest, ""));

        return prefs.getString(strRequest, "");
    }

    /**
     * 저장된 값을 요청해서 int으로 불러옴
     *
     * @param context    : 현재 화면
     * @param strRequest : 불러올 값
     * @return
     */
    public static int loadSharedPreferences_int(Context context, String strRequest) {
        SharedPreferences prefs = context.getSharedPreferences("dialUser", MODE_PRIVATE);

        Loging.i(context.getClass().getName(), strRequest + " : " + prefs.getInt(strRequest, 0));

        return prefs.getInt(strRequest, 0);
    }

    /**
     * 저장된 값을 요청해서 Boolean으로 불러옴
     *
     * @param context    : 현재 화면
     * @param strRequest : 불러올 값
     * @return
     */
    public static Boolean loadSharedPreferences_Boolean(Context context, String strRequest) {
        SharedPreferences prefs = context.getSharedPreferences("dialUser", MODE_PRIVATE);

        Loging.i(context.getClass().getName(), strRequest + " : " + prefs.getBoolean(strRequest, false));

        return prefs.getBoolean(strRequest, false);
    }

    /**
     * 요청하는 String 값을 저장
     *
     * @param context         : 현재 화면
     * @param strRequestName  : 저장될 이름
     * @param strRequestValue : 저장될 값
     * @return
     */
    public static Boolean saveSharedPreferences_String(Context context, String strRequestName, String strRequestValue) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("dialUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(strRequestName);

        editor.putString(strRequestName, strRequestValue);

        editor.commit();

        Loging.i(context.getClass().getName(), "strRequestName : " + strRequestName + " // strRequestValue : " + strRequestValue);

        return true;
    }

    /**
     * 요청하는 Boolean 값을 저장
     *
     * @param context        : 현재 화면
     * @param strRequestName : 저장될 이름
     * @param blRequestValue : 저장될 값
     * @return
     */
    public static Boolean saveSharedPreferences_String(Context context, String strRequestName, Boolean blRequestValue) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("dialUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(strRequestName);

        editor.putBoolean(strRequestName, blRequestValue);

        editor.commit();

        Loging.i(context.getClass().getName(), "strRequestName : " + strRequestName + " // blRequestValue : " + blRequestValue);

        return true;
    }

    /**
     * 요청하는 int 값을 저장
     *
     * @param context         : 현재 화면
     * @param strRequestName  : 저장될 이름
     * @param intRequestValue : 저장될 값
     * @return
     */
    public static Boolean saveSharedPreferences_String(Context context, String strRequestName, int intRequestValue) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("dialUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(strRequestName);

        editor.putInt(strRequestName, intRequestValue);

        editor.commit();

        Loging.i(context.getClass().getName(), "strRequestName : " + strRequestName + " // intRequestValue : " + intRequestValue);

        return true;
    }

    /**
     * 요청하는 값을 저장
     *
     * @param context        : 현재 화면
     * @param strRequestName : 저장될 이름
     * @param obRequestValue : 저장될 값
     * @return
     */
    public static Boolean saveSharedPreferences_Object(Context context, String strRequestName, Object obRequestValue) {

        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("dialUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(strRequestName);
        if (obRequestValue instanceof Integer) {
            editor.putInt(strRequestName, (int) obRequestValue);
        } else if (obRequestValue instanceof String) {
            editor.putString(strRequestName, (String) obRequestValue);
        } else if (obRequestValue instanceof Boolean) {
            editor.putBoolean(strRequestName, (Boolean) obRequestValue);
        }

        editor.commit();

        Loging.i(context.getClass().getName(), "strRequestName : " + strRequestName + " // obRequestValue : " + obRequestValue);

        return true;
    }

    /**
     * 저장된 값을 지운다.
     *
     * @param context
     * @param strRequestName
     */
    public static void removeSharedPreferences(Context context, String strRequestName) {
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("dialUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(strRequestName);
        editor.commit();

        Loging.i(context.getClass().getName(), "removeSharedPreferences() strRequestName : " + strRequestName);
    }

}
