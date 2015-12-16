package com.netease.commonlibrary.Net.VolleyUtils;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.RequestFuture;
import com.netease.commonlibrary.Net.VolleyUtils.volley.OkRequest;
import com.netease.commonlibrary.Net.VolleyUtils.volley.toolbox.OkVolley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 用Volley网络请求工具类
 * @author zhouchangping
 */
public class VolleyRequestUtils {

    private static final int DefaultCode=100;
    private static VolleyRequestUtils instance = null;

    public static synchronized VolleyRequestUtils getInstance() {
        if (instance == null) {
            instance = new VolleyRequestUtils();
        }
        return instance;
    }

    private VolleyRequestUtils() {
    }


    /**
     * Post方式请求Json数据,不能在UI线程中执行
     * @param url
     * @param postParams
     */
    public static JSONObject sendPostJsonData(String url,HashMap<String, String> postParams) {
        return sendJsonData(DefaultCode, null, url, postParams, Request.Method.POST);
    }

    /**
     * Get方式请求Json数据,不能在UI线程中执行
     * @param url
     * @param postParams
     */
    public static JSONObject  sendGetJsonData(String url,HashMap<String, String> postParams) {
        return sendJsonData(DefaultCode, null, url, postParams, Request.Method.GET);
    }

    /**
     * 获取通用返回Json数据结果,不能在UI线程中执行
     * @param requestCode 请求code
     * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     */
    public static JSONObject sendJsonData(int requestCode, Map<String, String> headers, String url, HashMap<String, String> postParams, int method) {
        JSONObject result=null;
        RequestFuture<JSONObject> future = RequestFuture.newFuture();
        OkRequest request = new BaseJSONRequest(method,url, future, future );
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
        try {
            result = future.get(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Post方式请求数据,不能在UI线程中执行
     * @param url
     * @param postParams
     */
    public static String  sendPostData(String url,HashMap<String, String> postParams) {
        return sendData(DefaultCode, null, url, postParams, Request.Method.POST);
    }

    /**
     * Get方式请求数据,不能在UI线程中执行
     * @param url
     * @param postParams
     */
    public static String  sendGetData(String url,HashMap<String, String> postParams) {
        return sendData(DefaultCode, null, url, postParams, Request.Method.GET);
    }

    /**
     * 获取通用返回数据结果,不能在UI线程中执行
     * @param requestCode 请求code
     * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     */
    public static String sendData(int requestCode, Map<String, String> headers, String url, HashMap<String, String> postParams, int method) {
        String result=null;
        RequestFuture<String> future = RequestFuture.newFuture();
        OkRequest request = new BaseStringRequest(method,url, future, future );
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
        try {
            result = future.get(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Post方式请求数据,可以在UI线程中执行
     * @param url
     * @param postParams
     */
    public static void  requestPostData(String url,HashMap<String, String> postParams, Response.Listener<String > listener,
                                          Response.ErrorListener errorListener) {
        requestData(DefaultCode, null, url, postParams, Request.Method.POST, listener, errorListener);
    }

    /**
     * Get方式请求数据,可以在UI线程中执行
     * @param url
     * @param postParams
     */
    public static void  requestGetData(String url,HashMap<String, String> postParams ,Response.Listener<String> listener,
                                         Response.ErrorListener errorListener) {
        requestData(DefaultCode, null, url, postParams, Request.Method.GET, listener, errorListener);
    }

    /**
     * 获取通用返回数据结果,可以在UI线程中执行
     * @param requestCode 请求code
     * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     */
    public static void requestData(int requestCode, Map<String, String> headers, String url, HashMap<String, String> postParams, int method, Response.Listener<String> listener,
                                     Response.ErrorListener errorListener) {
        OkRequest request = new BaseStringRequest(method,url, listener, errorListener );
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
    }

}