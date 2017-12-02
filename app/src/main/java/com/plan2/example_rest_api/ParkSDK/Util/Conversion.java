package com.plan2.example_rest_api.ParkSDK.Util;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.util.TypedValue;

import java.lang.annotation.Target;
import java.text.DecimalFormat;

/**
 * Created by park on 2017-01-04.
 */
public class Conversion {

    public static Integer dp_to_px(float dp, Context context) {
        Integer resultPx = null;
        resultPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return resultPx;
    }

    public static Integer dp_to_px(int dp, Context context) {
        Integer resultPx = null;
        resultPx = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        return resultPx;
    }

    /**
     * 금액에 , 넣는 함수
     *
     * @param input
     * @return
     */
    public String patternMoney(String input) {
        try {
            String value = "";

            long number = Long.parseLong(input.replaceAll(",", ""));
            DecimalFormat format = new DecimalFormat("#,###");
            value = format.format(number);
            return value;
        } catch (Exception e) {
            return input;
        }
    }

    /**
     * N 버전 부터 deprecated 되는 fromHtml 을 그대로 쓰기위한 함수
     *
     * @param source
     * @return
     */
    public static Spanned fromHtml(String source) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
            // noinspection deprecation
            return Html.fromHtml(source);
        }
        return Html.fromHtml(source, Html.FROM_HTML_MODE_LEGACY);
    }

}
