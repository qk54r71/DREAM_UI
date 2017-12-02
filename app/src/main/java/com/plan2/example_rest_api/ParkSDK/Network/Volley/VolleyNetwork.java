package com.plan2.example_rest_api.ParkSDK.Network.Volley;

import android.content.Context;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by park on 2016-11-07.
 */
public class VolleyNetwork {

    //private final static String LOG_NAME = "VolleyNetwork";

   /* public static void volleyHttp(
            Context context,
            String url,
            final Class mClass,
            final String paramsName,
            final HashMap<String, String> strRequest,
            final VolleyCallback volleyCallback
    ) {
        RequestQueue requestQueue = CustomVolleyRequestQueue.getInstance(context).getRequestQueue();

        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Loging.i(LOG_NAME, "response : " + response);

                        Object resultObject = new Gson().fromJson(response, mClass);

                        volleyCallback.onSuccessResponse(resultObject);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Loging.e(LOG_NAME, "error : " + error);
                    }
                }
        ) {
            protected Map<String, String> getParams() throws com.android.volley.AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.putAll(strRequest);
                return params;
            }


            *//*@Override
            public byte[] getBody() throws AuthFailureError {

                String params = null;
                CommonJava.Loging.i(LOG_NAME, "getBody()");
                try {
                    params = paramsName + "=" + URLEncoder.encode(new Gson().toJson(strRequest), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                CommonJava.Loging.i(LOG_NAME, "params.getBytes() : " + params.getBytes());
                CommonJava.Loging.i(LOG_NAME, "params : " + params);

                return params.getBytes();
            }*//*
        };

        requestQueue.add(stringRequest);
    }*/

/*    public interface VolleyCallback {
        void onSuccessResponse(Object response);
    }*/

    public static class CustomVolleyRequestQueue {

        private static CustomVolleyRequestQueue mInstance;
        private static Context mContext;
        private static RequestQueue mRequestQueue;

        public CustomVolleyRequestQueue(Context context) {
           mContext = context;

        }

        public static synchronized CustomVolleyRequestQueue getInstance(Context context) {

            if (mInstance == null) {

                mInstance = new CustomVolleyRequestQueue(context);

            }

            return mInstance;
        }

        public RequestQueue getRequestQueue() {

            if (mRequestQueue == null) {

                Cache cache = new DiskBasedCache(mContext.getCacheDir(), 10 * 1024 * 1024);
                Network network = new BasicNetwork(new HurlStack());
                mRequestQueue = new RequestQueue(cache, network);
                // Don't forget to start the volley request queue
                mRequestQueue.start();

            }

            return mRequestQueue;
        }
    }
}