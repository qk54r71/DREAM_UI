package com.plan2.example_rest_api.ParkSDK.Network.Volley;

import android.content.Context;
import android.os.Handler;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import com.plan2.example_rest_api.ParkSDK.Debug.Loging;

/**
 * Created by park on 2017-01-12.
 */

public class CustomVolley {
    private static Context mContext;
    private static JSONObject mJsonObject;
    private static Integer mRequestMethod;
    private static String mURL;
    private static String currentURL;
    private static int requestStack;

    private static final String LOG_NAME = "CustomVolley";
    private static String SUPER_LOG_NAME;

    public CustomVolley(Context context, String log_name) {
        mContext = context;
        mJsonObject = new JSONObject();
        mRequestMethod = Request.Method.POST;
        SUPER_LOG_NAME = log_name;
        requestStack = 0;
    }

    public void setUrlMethod(Integer method) {
        mRequestMethod = method;
    }

    public void setGetData(String strRequestName, String strRequestValue) {
        if (requestStack++ == 0) {
            mURL += "?" + strRequestName + "=" + strRequestValue;
        } else {
            mURL += "&" + strRequestName + "=" + strRequestValue;
        }
    }

    public void setJsonData(String strRequestName, String strReqeustValue) {
        try {
            mJsonObject.put(strRequestName, strReqeustValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setJsonData(String strRequestName, Integer intReqeustValue) {
        try {
            mJsonObject.put(strRequestName, intReqeustValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setJsonData(String strRequestName, Object objReqeustValue) {
        try {
            mJsonObject.put(strRequestName, objReqeustValue);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setURL(Integer strUrlId) {
        mURL = mContext.getResources().getString(strUrlId);
    }

    public  void    setURL(String   strUrl) {
        mURL    =   strUrl;
    }


    public void networkConnect(Response.Listener<JSONObject> jsonObjectListener) {
        Loging.i(SUPER_LOG_NAME, LOG_NAME + "jsonObjectRequest mURL : " + mURL);
        Loging.i(SUPER_LOG_NAME, LOG_NAME + "jsonObjectRequest jsonObject : " + mJsonObject.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                mRequestMethod,
                mURL,
                mJsonObject,
                jsonObjectListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Loging.e(SUPER_LOG_NAME, " " + LOG_NAME + "jsonObjectRequest VolleyError :" + error);

                    }
                }) {
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                //Loging.e(LOG_NAME, "parseNetworkResponse " + response.headers);
                String byteString = new String(response.data, 0, response.data.length);
                Loging.e(SUPER_LOG_NAME, " " + LOG_NAME + "parseNetworkResponse byteString : " + byteString);
                mURL = null;
                currentURL = null;
                return super.parseNetworkResponse(response);
            }
        };
        if (mURL != currentURL || mURL == null) {
            currentURL = mURL;
            Loging.i(SUPER_LOG_NAME, LOG_NAME + "jsonObjectRequest networkConnect");
            VolleyNetwork.CustomVolleyRequestQueue.getInstance(mContext).getRequestQueue().add(jsonObjectRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mURL = null;
                    currentURL = null;
                }
            }, 3000);
        }


    }

    public JSONObject getNetworkData() {
        Loging.i(SUPER_LOG_NAME, LOG_NAME + "mJsonObject : " + mJsonObject);
        return mJsonObject;
    }

    public void networkConnect_GET(Response.Listener<String> stringListener) {
        Loging.i(SUPER_LOG_NAME, LOG_NAME + "stringRequest networkConnect");
        Loging.i(SUPER_LOG_NAME, LOG_NAME + "stringRequest stringRequest : " + mURL.toString());


        StringRequest stringRequest = new StringRequest(
                mURL,
                stringListener,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Loging.e(SUPER_LOG_NAME, " " + LOG_NAME + "onErrorResponse(VolleyError error)  VolleyError : " + error);

                    }
                }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {

                String byteString = new String(response.data, 0, response.data.length);
                Loging.e(SUPER_LOG_NAME, " " + LOG_NAME + "parseNetworkResponse byteString : " + byteString);
                return super.parseNetworkResponse(response);
            }
        };
        VolleyNetwork.CustomVolleyRequestQueue.getInstance(mContext).getRequestQueue().add(stringRequest);
    }



}
