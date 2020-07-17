package com.example.app_api.Utils;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ApiSingleton  {

    private static ApiSingleton mSingleton;
    private RequestQueue mRequestQueue;

    private ApiSingleton(Context context) {
        mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }


    public static synchronized ApiSingleton getInstance(Context context) {

        if (mSingleton == null) {

            mSingleton = new ApiSingleton(context);

        }

        return mSingleton;
    }


    public RequestQueue getRequestQueue() {

        return mRequestQueue;

    }
}
