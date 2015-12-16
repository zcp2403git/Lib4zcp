package com.netease.commonlibrary.CallBack;

import com.netease.commonlibrary.Utils.Log.L;

/**
 * 结果回调
 * @author zhouchangping
 */
public abstract class ImageResultCallback<T>
{
    public ImageResultCallback()
    {

    }


    public abstract void onError(Exception e);

    public abstract void onResponse(T response);

    public static final ImageResultCallback<String> DEFAULT_RESULT_CALLBACK = new ImageResultCallback<String>()
    {
        @Override
        public void onError( Exception e)
        {
            L.e("Error_TAG", "onError , e = " + e.getMessage());
        }
        @Override
        public void onResponse(String response)
        {
            L.i("RESPONSE_MESSAGE", "Response = " + response);
        }
    };
}