package com.netease.commonlibrary.View.Toast;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.netease.commonlibrary.R;

import java.util.Calendar;

/**
 * 自定义风格Toast，当需要显示toast时请使用该类
 * 
 * @author zmtian
 */

public class CustomToast {

    private static String lastToastString;
    private static long lastStamp=0;
    private static long currentStamp=0;
    
    private static final int LONG_DELAYED = 5000; // 5 seconds
    
    private static final int SHORT_DELAYED = 5000; // 5 seconds

    public static Toast showToast(Context context, String text) {
        currentStamp = Calendar.getInstance().getTimeInMillis(); //1388806099388
        
        if (TextUtils.equals(lastToastString, text)&&(currentStamp-lastStamp<SHORT_DELAYED)) {
            return null;
        }
        lastStamp=currentStamp;
        if(context==null)
            return null;
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
        textView.setText(text);
        toast.setView(textView);
        toast.setDuration(Toast.LENGTH_SHORT);
        if (!TextUtils.isEmpty(text)) {
            lastToastString = text;
            toast.show();
        }
        return toast;
    }

    private static int lastToastResId;
    public static Toast showToast(Context context, int resId) {
        currentStamp = Calendar.getInstance().getTimeInMillis(); //1388806099388
        if ((lastToastResId == resId)&&(currentStamp-lastStamp<SHORT_DELAYED)) {
            return null;
        }
        lastStamp=currentStamp;
        if(context==null)
            return null;
        try {
            Toast toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
            textView.setText(resId);
            toast.setView(textView);
            toast.setDuration(Toast.LENGTH_SHORT);
            lastToastResId = resId;
            toast.show();
            return toast;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int lastLongToastResId;
    public static Toast showToastLong(Context context, int resId) {
        currentStamp = Calendar.getInstance().getTimeInMillis(); //1388806099388
        if ((lastLongToastResId == resId)&&(currentStamp-lastStamp<LONG_DELAYED)) {
            return null;
        }
        if (context == null)
            return null;
        lastStamp=currentStamp;
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
        textView.setText(resId);
        toast.setView(textView);
        toast.setDuration(Toast.LENGTH_LONG);
        lastLongToastResId = resId;
        toast.show();
        return toast;
    }

    private static String lastLongToastString;
    public static Toast showToastLong(Context context, String text) {
        currentStamp = Calendar.getInstance().getTimeInMillis(); //1388806099388
        if (TextUtils.equals(lastLongToastString, text)&&(currentStamp-lastStamp<LONG_DELAYED)) {
            return null;
        }
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        lastStamp=currentStamp;
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
        textView.setText(text);
        toast.setView(textView);
        toast.setDuration(Toast.LENGTH_LONG);
        if (!TextUtils.isEmpty(text)) {
            lastLongToastString = text;
            toast.show();
        }
        return toast;
    }

    public static Toast showToast(Context context, String resId, int position) {
        if(context==null)
            return null;
        try {
            Toast toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
            textView.setText(resId);
            toast.setView(textView);
            toast.setDuration(Toast.LENGTH_SHORT);
            if (position >= 0) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
            toast.show();
            return toast;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Toast showToastLon(Context context, int resId) {
        if(context==null)
            return null;
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.custom_toast, null);
        textView.setText(resId);
        toast.setView(textView);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    public static Toast showToastLong(Context context, String text, int positin) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        Toast toast = new Toast(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        TextView textView = (TextView) inflater.inflate(R.layout.custom_toast2, null);
        textView.setText(text);
        toast.setView(textView);
        toast.setGravity(positin, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
        return toast;
    }

    public static Toast showToast(Context context, int textId, int imageId, int position) {
        if (context != null) {
            Toast toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast2, null);
            TextView textView = (TextView) view.findViewById(R.id.toast_text);
            textView.setText(textId);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            if (position >= 0) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
            toast.show();
            return toast;
        }
        return null;
    }
    public static Toast showToast(Context context, String textId, int imageId, int position) {
        if (context != null) {
            Toast toast = new Toast(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.custom_toast2, null);
            TextView textView = (TextView) view.findViewById(R.id.toast_text);
            textView.setText(textId);
            toast.setView(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            if (position >= 0) {
                toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
            }
            toast.show();
            return toast;
        }
        return null;
    }

    public static void showToastInThread(final Context context, final int resId) {
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId);
            }
        });
    }

    public static void showToastInThread(final Context context, final String msg) {
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                showToast(context, msg);
            }
        });
    }

    public static void showToastLongInThread(final Context context, final int resId) {
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                showToastLong(context, resId);
            }
        });
    }

    public static void showToastLongInThread(final Context context, final String msg) {
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                showToastLong(context, msg);
            }
        });
    }

}
