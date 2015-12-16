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

package com.netease.commonlibrary.Image;

import com.netease.commonlibrary.CallBack.ImageResultCallback;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

/**
 * 图片请求接口
 * @author zhouchangping
 */
public interface ImageManagerStack {
    /**
     * 获取通用bean数据结果,可以在UI线程中执行
     * * @param headers      http请求头
     * @param url         请求地址
     * @param view        控件
     *  @param callback  结果回调
     */
    public void displayImage(ImageAware view, String url, ImageResultCallback callback);

}
