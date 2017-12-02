package com.plan2.example_rest_api.ParkSDK.Debug;

import android.content.Context;
import android.util.Log;

/**
 * Created by park on 2017-01-03.
 */

public class Loging {
    public static Boolean logingCheck = true;

    public static void i(String className, String strContent) {
        if (logingCheck) {
            Log.i(className, strContent);
        }
    }

    public static void i(Context context, String strContent) {
        if (logingCheck) {

            String strSimpleName = context.getClass().getSimpleName();
            //strPackageName = strPackageName.substring(strPackageName.lastIndexOf("."));

            Log.i(strSimpleName, strContent);
        }
    }

    public static void d(String className, String strContent) {
        if (logingCheck) {
            Log.d(className, strContent);
        }
    }

    public static void d(Context context, String strContent) {
        if (logingCheck) {

            String strSimpleName = context.getClass().getSimpleName();
            //strPackageName = strPackageName.substring(strPackageName.lastIndexOf("."));

            Log.d(strSimpleName, strContent);
        }
    }

    public static void e(String className, String strContent) {
        if (logingCheck) {
            Log.e(className, strContent);
        }
    }

    public static void e(Context context, String strContent) {
        if (logingCheck) {

            String strSimpleName = context.getClass().getSimpleName();
            //strPackageName = strPackageName.substring(strPackageName.lastIndexOf("."));

            Log.e(strSimpleName, strContent);
        }
    }

    public static void w(String className, String strContent) {
        if (logingCheck) {
            Log.w(className, strContent);
        }
    }

    public static void w(Context context, String strContent) {
        if (logingCheck) {

            String strSimpleName = context.getClass().getSimpleName();
            //strPackageName = strPackageName.substring(strPackageName.lastIndexOf("."));

            Log.w(strSimpleName, strContent);
        }
    }
}
