package com.netease.commonlibrary.CallBack;

import com.google.gson.internal.$Gson$Types;
import com.netease.commonlibrary.Utils.Log.L;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/**
 * 结果回调
 * @author zhouchangping
 */
public abstract class NetworkResultCallback<T> implements INetworkResultCallback<T>
{
    public Type mType ;

    public NetworkResultCallback()
    {
        mType = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass)
    {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class)
        {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public static final NetworkResultCallback<String> DEFAULT_RESULT_CALLBACK = new NetworkResultCallback<String>()
    {
        public void onError(int code, Exception e)
        {
            L.e("Error_TAG", "onError , e = " + e.getMessage());
        }

        @Override
        public void onResponse(int code,String response)
        {
            L.i("RESPONSE_MESSAGE", "Response = " + response);
        }
    };
}