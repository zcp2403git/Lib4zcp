package com.netease.commonlibrary.Net;

import com.google.gson.Gson;
import com.netease.commonlibrary.CallBack.INetworkResultCallback;
import com.nostra13.universalimageloader.utils.L;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求通用类
 * @author zhouchangping
 */
public class NetworkManager {

    private NetEngine engine;
    private static volatile NetworkManager instance;

    public NetworkManager() {
    }

    public  Gson getGson() {
        if(this.engine == null) {
            throw new IllegalArgumentException("engine can not be initialized with null");
        }
        return this.engine.gsonParse;
    }

    public static NetworkManager getInstance() {
        if(instance == null) {
            Class var0 = NetworkManager.class;
            synchronized(NetworkManager.class) {
                if(instance == null) {
                    instance = new NetworkManager();
                }
            }
        }
        return instance;
    }

    public synchronized void init(NetEngine engine) {
        if(engine == null) {
            throw new IllegalArgumentException("engine can not be initialized with null");
        } else {
            if(this.engine == null) {
                this.engine = engine;
            } else {
                L.w("Try to initialize NetworkManager which had already been initialized before. To re-init NetworkManager with new configuration call NetworkManager.destroy() at first.", new Object[0]);
            }
        }
    }

    public boolean isInited() {
        return this.engine != null;
    }

    /**
     * 获取通用bean数据结果,可以在UI线程中执行
     * @param requestCode  请求编号
     * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     *  @param callback  结果回调
     */
    public void requestNetworkData(int requestCode,Map<String, String> headers,String url,HashMap<String, String> postParams,int method,INetworkResultCallback callback){
        if(!isInited())
            return;;
        this.engine.managerStack.requestNetworkData(requestCode,headers,url,postParams,method,callback);
    }

    /**
     * 默认Post方式获取通用bean数据结果,可以在UI线程中执行
     * @param requestCode  请求编号
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     *  @param callback  结果回调
     */
    public void postNetworkData(int requestCode,String url,HashMap<String, String> postParams,INetworkResultCallback callback){
        if(!isInited())
            return;;
        this.engine.managerStack.postNetworkData(requestCode,url, postParams, callback);
    }

    /**
     * 默认Get方式获取通用bean数据结果,可以在UI线程中执行
     * @param requestCode  请求编号
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     *  @param callback  结果回调
     */
    public void getNetworkData(int requestCode,String url,HashMap<String, String> postParams,INetworkResultCallback callback){
        if(!isInited())
            return;;
        this.engine.managerStack.getNetworkData(requestCode, url, postParams, callback);
    }
    /**
     * 取消指定请求
     * @param requestCode  请求编号
     *  @param callback  结果回调
     */
    public void cancelSingle(int requestCode,INetworkResultCallback callback){
        if(!isInited())
            return;;
        this.engine.managerStack.cancelSingle(requestCode, callback);
    }
    /**
     * 取消所有指定请求
     *  @param callback  结果回调
     */
    public void cancelAll(INetworkResultCallback callback){
        if(!isInited())
            return;;
        this.engine.managerStack.cancelAll(callback);
    }
}