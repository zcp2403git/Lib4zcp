package com.netease.commonlibrary.Net.VolleyUtils;

import android.content.Context;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.netease.commonlibrary.CallBack.INetworkResultCallback;
import com.netease.commonlibrary.CallBack.NetworkResultCallback;
import com.netease.commonlibrary.Constant.LibraryConstant;
import com.netease.commonlibrary.Net.BaseNetUtilsManager;
import com.netease.commonlibrary.Net.NetworkManagerStack;
import com.netease.commonlibrary.Net.VolleyUtils.volley.OkRequest;
import com.netease.commonlibrary.Net.VolleyUtils.volley.toolbox.OkVolley;
import com.netease.commonlibrary.Utils.Log.L;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import im.amomo.volley.BuildConfig;

/**
 * 用Volley网络请求
 * @author zhouchangping
 */
public class VolleyNetManager extends BaseNetUtilsManager implements NetworkManagerStack {

    private static final int DefaultCode=100;
    private static VolleyNetManager instance = null;
    private HashMap<INetworkResultCallback, HashMap<Integer, OkRequest>> taskMap = new HashMap<INetworkResultCallback, HashMap<Integer, OkRequest>>();

    public static synchronized VolleyNetManager getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyNetManager(context);
        }
        return instance;
    }

    private VolleyNetManager(Context context) {
        super();
        OkVolley.getInstance().init(context)
                .setUserAgent(OkVolley.generateUserAgent(context))
                .trustAllCerts();

        VolleyLog.DEBUG = BuildConfig.DEBUG;
    }

    /**
     * Post方式请求数据
     * @param url
     * @param postParams
     * @param callback  结果回调
     */
    public void postNetworkData(int requestCode,String url,HashMap<String, String> postParams,INetworkResultCallback callback) {
        requestNetworkData(requestCode, null, url, postParams, Request.Method.POST, callback);
    }
    /**
     * Get方式请求数据
     * @param url
     * @param postParams
     * @param callback  结果回调
     */
    public void getNetworkData(int requestCode,String url,HashMap<String, String> postParams ,INetworkResultCallback callback) {
        requestNetworkData(requestCode, null, url, postParams, Request.Method.GET, callback);
    }

    /**
     * 获取通用返回bean数据结果
     * @param requestCode 请求code
     * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     * @param callback  结果回调
     */
    public void requestNetworkData(final int requestCode, Map<String, String> headers, String url, HashMap<String, String> postParams, int method, INetworkResultCallback callback) {
        if (callback == null)
            callback = NetworkResultCallback.DEFAULT_RESULT_CALLBACK;
        final INetworkResultCallback resCallBack =callback;

        OkRequest request = new BaseResponseRequest(method, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try
                {

                    final String string =  new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    if (LibraryConstant.L_DEBUG){
                        L.d(requestCode+" Request:",string);
                    }
                    if (resCallBack.mType == String.class)
                    {
                        sendSuccessResultCallback(requestCode,string, resCallBack);
                    } else
                    {
                        Object o = mGson.fromJson(string, resCallBack.mType);
                        sendSuccessResultCallback(requestCode,o , resCallBack);
                    }
                } catch (com.google.gson.JsonParseException e)//Json解析的错误
                {
                    sendFailResultCallback(requestCode,e, resCallBack);
                } catch (UnsupportedEncodingException e) {
                    sendFailResultCallback(requestCode,e, resCallBack);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                sendFailResultCallback(requestCode,error, resCallBack);
            }
        });
        if(headers == null){
            headers = new HashMap<String, String>();
        }
        request.headers(headers);
        if(postParams == null){
            postParams = new HashMap<String, String>();
        }
        request.params(postParams);
        request.setTag(requestCode);
        OkVolley.getInstance().getRequestQueue().add(request);
        HashMap<Integer, OkRequest> map = taskMap.get(callback);
        if (map == null) {
            map = new HashMap<Integer, OkRequest>();
            taskMap.put(callback, map);
        }
        map.put(requestCode, request);
    }
    /**
     * 取消指定requestCode的任务
     */
    public void cancelSingle( int requestCode,INetworkResultCallback callback) {
        if (callback == null)
            return;
        HashMap<Integer, OkRequest> map = taskMap.get(callback);
        if (map == null)
            return;
        OkRequest request = map.get(requestCode);
        if (request != null && !request.isCanceled()) {
            request.cancel();
        }
        map.remove(requestCode);
    }

    /**
     * 取消所有网络任务
     */
    public void cancelAll(INetworkResultCallback callback) {
        HashMap<Integer, OkRequest> map = taskMap.get(callback);
        if (map == null)
            return;
        Iterator it = map.keySet().iterator();
        while (it.hasNext()) {
            Integer requestCode = (Integer) it.next();
            OkRequest request = map.get(requestCode);
            if (request != null && !request.isCanceled()) {
                request.cancel();
            }
            it.remove();
        }
        taskMap.remove(callback);
    }



}