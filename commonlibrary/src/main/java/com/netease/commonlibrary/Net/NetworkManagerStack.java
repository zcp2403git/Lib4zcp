/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netease.commonlibrary.Net;

import com.netease.commonlibrary.CallBack.INetworkResultCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * 网络请求接口
 * @author zhouchangping
 */
public interface NetworkManagerStack {
    /**
     * 获取通用bean数据结果,可以在UI线程中执行
     * * @param headers      http请求头
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     * @param method      POST或者GET方式
     *                    POST: HTTP_POST
     *                    GET:HTTP_GET
     *  @param callback  结果回调
     */
    public void requestNetworkData(int requsetCode, Map<String, String> headers,String url,HashMap<String, String> postParams,int method,INetworkResultCallback callback);

    /**
     * 默认Post方式获取通用bean数据结果,可以在UI线程中执行
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     *  @param callback  结果回调
     */
    public void postNetworkData(int requsetCode,String url,HashMap<String, String> postParams,INetworkResultCallback callback);

    /**
     * 默认Get方式获取通用bean数据结果,可以在UI线程中执行
     * @param url         请求地址
     * @param postParams  POST 请求的键值对
     *  @param callback  结果回调
     */
    public void getNetworkData(int requsetCode,String url,HashMap<String, String> postParams,INetworkResultCallback callback);

    /**
     * 取消指定requestCode的任务
     */
    public void cancelSingle(int requestCode,INetworkResultCallback callback);

    /**
     * 取消所有网络任务
     */
    public void cancelAll(INetworkResultCallback callback);
}
