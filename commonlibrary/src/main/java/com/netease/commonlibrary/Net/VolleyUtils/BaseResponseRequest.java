package com.netease.commonlibrary.Net.VolleyUtils;

import android.os.SystemClock;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.netease.commonlibrary.Net.VolleyUtils.volley.OkRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求
 * @author zhouchangping
 */
public class BaseResponseRequest extends OkRequest<NetworkResponse> {
    private Map<String, String> extraParams;
    private long mRequestBeginTime = 0;

    public BaseResponseRequest(int method, String url, Response.Listener<NetworkResponse> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        setReseponseListener(listener);
        acceptGzipEncoding();
    }

    @Override
    //解析返回的数据
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
            return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public void addMarker(String tag) {
        super.addMarker(tag);
        if (mRequestBeginTime == 0) {
            mRequestBeginTime = SystemClock.elapsedRealtime();
        }
    }

    @Override
    protected void deliverResponse(NetworkResponse value) {
        super.deliverResponse(value);
        //请求用掉的总时间
        long requestTime = SystemClock.elapsedRealtime() - mRequestBeginTime;
    }

    @Override
    //可以自己在这里完成错误的自定义处理
    public void deliverError(VolleyError error) {
        super.deliverError(error);
        //错误发生时候response的数据 byte[]
        //error.networkResponse.data;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        if (extraParams != null) {
            params.putAll(extraParams);
        }
        return params;
    }
}
