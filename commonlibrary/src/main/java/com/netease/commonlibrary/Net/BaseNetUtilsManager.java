package com.netease.commonlibrary.Net;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netease.commonlibrary.CallBack.INetworkResultCallback;

import java.lang.reflect.Modifier;

/**
 * 网络请求基础工具类
 * @author zhouchangping
 */
public class BaseNetUtilsManager {

    private Handler mDelivery;
    public static Gson mGson;


    public BaseNetUtilsManager() {
        mDelivery = new Handler(Looper.getMainLooper());
        final int sdk = Build.VERSION.SDK_INT;
        if (sdk >= 23)
        {
            GsonBuilder gsonBuilder = new GsonBuilder()
                    .excludeFieldsWithModifiers(
                            Modifier.FINAL,
                            Modifier.TRANSIENT,
                            Modifier.STATIC);
            mGson = gsonBuilder.create();
        } else
        {
            mGson = new Gson();
        }
    }


    public void sendFailResultCallback(final int requsetCode, final Exception e, final INetworkResultCallback callback) {
        if (callback == null) return;

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onError(requsetCode,e);
            }
        });
    }

    public Handler getDelivery() {
        return mDelivery;
    }


    public void sendSuccessResultCallback(final int requsetCode,final Object object, final INetworkResultCallback callback) {
        if (callback == null) return;

        mDelivery.post(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(requsetCode,object);
            }
        });
    }

}