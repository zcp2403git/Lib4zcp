package com.netease.commonlibrary.CallBack;

import java.lang.reflect.Type;

/**
 * 结果回调接口
 * @author zhouchangping
 */
public interface INetworkResultCallback<T>
{
    public Type mType=String.class;

    public  void onError(int requsetCode,Exception e);

    public  void onResponse(int requsetCode,T response);

}