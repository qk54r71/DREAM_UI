package com.plan2.example_rest_api.ParkSDK.Util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.plan2.example_rest_api.ParkSDK.Debug.Loging;

import static android.content.Context.LOCATION_SERVICE;

/**
 * Created by park on 2017-01-07.
 */

public class LocalInformation {
    /**
     * 현재 년도 가져오기
     *
     * @return
     */
    public static String getYear(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.KOREA);
        String strYear = df.format(date);

        return strYear;
    }

    /**
     * 현재 월 가져오기
     *
     * @return
     */
    public static String getMonth(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("MM", Locale.KOREA);
        String strMonth = df.format(date);

        return strMonth;
    }

    /**
     * 현재 일 가져오기
     *
     * @return
     */
    public static String getDay(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("dd", Locale.KOREA);
        String strDay = df.format(date);

        return strDay;
    }

    /**
     * 현재 오전,오후 가져오기
     *
     * @return
     */
    public static String getAmPm(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("HH", Locale.KOREA);
        String strHour = df.format(date);
        int intHour = Integer.parseInt(strHour);
        String strAmPm = null;

        if (intHour >= 13) {
            strAmPm = "오후";
        } else {
            strAmPm = "오전";
        }


        return strAmPm;
    }

    /**
     * 현재 시 가져오기
     *
     * @return
     */
    public static String getHour(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("HH", Locale.KOREA);
        String strHour = df.format(date);
        int intHour = Integer.parseInt(strHour);

        strHour = String.valueOf(intHour % 12 == 0 ? 12 : intHour % 12);

        return strHour;
    }

    /**
     * 현재 분 가져오기
     *
     * @return
     */
    public static String getMinute(Date date) {

        SimpleDateFormat df = new SimpleDateFormat("mm", Locale.KOREA);
        String strMinute = df.format(date);

        return strMinute;
    }

    /**
     * 현재 요일 가져오기
     *
     * @return
     */
    public static String getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String strWeek = null;

        int nWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (nWeek == 1) {
            strWeek = "일";
        } else if (nWeek == 2) {
            strWeek = "월";
        } else if (nWeek == 3) {
            strWeek = "화";
        } else if (nWeek == 4) {
            strWeek = "수";
        } else if (nWeek == 5) {
            strWeek = "목";
        } else if (nWeek == 6) {
            strWeek = "금";
        } else if (nWeek == 7) {
            strWeek = "토";
        }

        return strWeek;
    }

    /**
     * 현재 날짜 및 시간 가져오기
     *
     * @return
     */
    public static Date getNowDate() {

        long now = System.currentTimeMillis();
        Date date = new Date(now);

        return date;
    }

    /**
     * 핸드폰 번호 가져오는 함수
     *
     * @param context
     * @return
     */
    public static String getPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        String strPhoneNumber = telephonyManager.getLine1Number();
        Loging.i(context, "strPhoneNumber : " + strPhoneNumber);

        Loging.i(context, "strPhoneNumber.contains(\"+82\") : " + strPhoneNumber.contains("+82"));
        if (strPhoneNumber.contains("+82")) {
            Loging.i(context, "strPhoneNumber.substring(3, strPhoneNumber.length()) : " + strPhoneNumber.substring(3, strPhoneNumber.length()));
            strPhoneNumber = "0" + strPhoneNumber.substring(3, strPhoneNumber.length());
        }

        Loging.i(context, "strPhoneNumber : " + strPhoneNumber);

        return strPhoneNumber;
    }

    /**
     * IMEI 가져오는 함수
     *
     * @param context
     * @return
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId();
    }

    /**
     * AndroidId 가져오는 함수
     *
     * @param context
     * @return
     */
    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static int[] getLatLng(Context mContext) {
        LocationManager locationManager = (LocationManager) mContext.getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
        }
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation != null) {
            double lng = lastKnownLocation.getLatitude();
            double lat = lastKnownLocation.getLatitude();
            Loging.i("Main", "longtitude=" + lng + ", latitude=" + lat);
        }


        return new int[]{};

    }

    /**
     * editText 창 비었는지 체크
     *
     * @param etText
     * @return 비었으면 true, 안비었으면 false
     */
    public static boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    /**
     * editText 창 비었는지 체크
     *
     * @param etText
     * @param context
     * @param strNullText 비었을 시에 나오는 문구
     * @return 비었으면 true, 안비었으면 false
     */
    public static boolean isEmpty(Context context, EditText etText, String strNullText) {
        Boolean resultBl = etText.getText().toString().trim().length() == 0;
        if (resultBl) {
            Toast.makeText(context, strNullText, Toast.LENGTH_SHORT).show();
        }
        return resultBl;
    }

    /**
     * 문자열 길이 체크
     *
     * @param context
     * @param edtText
     * @param limitLength
     * @param strNullText
     * @return 길이 제한 넘으면 true, 안넘으면 false
     */
    public static boolean isLimit(Context context, Editable edtText, int limitLength, String strNullText) {
        Boolean resultBl = edtText.length() > limitLength;
        if (resultBl) {
            Toast.makeText(context, strNullText, Toast.LENGTH_SHORT).show();
        }
        return resultBl;
    }

    /**
     * 현재 service 가 실행하고 있는지 체크
     *
     * @param context
     * @param strServiceName
     * @return
     */
    public static boolean isServiceRunningCheck(Context context, String strServiceName) {

        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);

        strServiceName = "pjb.alltek.badaba.Service." + strServiceName;
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (strServiceName.equals(service.service.getClassName())) {
                return true;
            }
        }

        return false;

    }

    /**
     * 이메일 형식 체크
     *
     * @param email
     * @return 맞으면 true, 아니면 false
     */
    public static boolean checkEmail(String email) {

        String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(mail);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * 이메일 형식 체크
     *
     * @param context
     * @param email
     * @param strNullText 아닐시에 나올 문구
     * @return 맞으면 true, 아니면 false
     */
    public static boolean checkEmail(Context context, String email, String strNullText) {

        String mail = "^[_a-zA-Z0-9-\\.]+@[\\.a-zA-Z0-9-]+\\.[a-zA-Z]+$";
        Pattern p = Pattern.compile(mail);
        Matcher m = p.matcher(email);

        return m.matches();
    }

    /**
     * GPS 설정 체크 후 GPS 창으로 이동
     *
     * @param context
     */
    public static void isOnGPSClick(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
        //GPS가 켜져있는지 체크
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            //GPS 설정화면으로 이동
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            context.startActivity(intent);
        }
    }

    /**
     * 스크롤 안에 있는 리스트뷰의 아이템 개수에 따른 높이 조절
     * 출처 {link :: http://kanzler.tistory.com/33}
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition

            return;
        }

        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);

        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight;
        listView.setLayoutParams(params);
        listView.requestLayout();
    }


    /**
     * 이메일 보내기 함수
     *
     * @param context
     * @param strSendEmail 보낼 이메일 주소
     * @param strTitle     제목
     * @param strContent   내용
     * @param strChooser   이메일 선택시 띄울 메세지
     */
    public static void sendEmail(Context context, String strSendEmail, String strTitle, String strContent, String strChooser) {

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");

        i.putExtra(Intent.EXTRA_EMAIL, new String[]{strSendEmail});

        i.putExtra(Intent.EXTRA_SUBJECT, strTitle);

        i.putExtra(Intent.EXTRA_TEXT, strContent);

        try {
            context.startActivity(Intent.createChooser(i, strChooser));

        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    /**
     * 바다바 서비스 실행 여부 체크 후 반대로 해줌
     */
    public static boolean switchScreenService(Context context) {
        if (CustomPreferences.loadSharedPreferences_Boolean(context, "adOnOff")) {
            Intent intentStopService = new Intent("pjb.alltek.badaba.startService");
            intentStopService.setPackage("pjb.alltek.badaba");
            context.stopService(intentStopService);
            CustomPreferences.saveSharedPreferences_Object(context, "adOnOff", false);
            return false;
        } else {
            Intent intentStartService = new Intent("pjb.alltek.badaba.startService");
            intentStartService.setPackage("pjb.alltek.badaba");
            context.startService(intentStartService);
            CustomPreferences.saveSharedPreferences_Object(context, "adOnOff", true);
            return true;
        }
    }

    public static void switchScreenService(Context context, Boolean switchBl) {
        if (switchBl) {
            Intent intentStartService = new Intent("pjb.alltek.badaba.startService");
            intentStartService.setPackage("pjb.alltek.badaba");
            context.startService(intentStartService);
            CustomPreferences.saveSharedPreferences_Object(context, "adOnOff", true);
        } else {
            Intent intentStopService = new Intent("pjb.alltek.badaba.startService");
            intentStopService.setPackage("pjb.alltek.badaba");
            context.stopService(intentStopService);
            CustomPreferences.saveSharedPreferences_Object(context, "adOnOff", false);
        }
    }

    /**
     * 키보드 내리기
     *
     * @param context
     * @param editText
     */

    public static void keybordDown(Context context, EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
