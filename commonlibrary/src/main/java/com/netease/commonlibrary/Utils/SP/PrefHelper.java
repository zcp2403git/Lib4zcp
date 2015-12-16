package com.netease.commonlibrary.Utils.SP;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.netease.commonlibrary.Constant.LibraryConstant;


/**
 * 本类处理SharePreference相关.
 * 
 * @author zhouchangping
 */
public class PrefHelper {

    public static boolean getBoolean(Context context, String prefName, String prefKey, boolean defaultValue) {
        try {
            return getSharedPreferences(context, prefName).getBoolean(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static boolean getBoolean(Context context, String prefName, String prefKey) {
        try {
            return getSharedPreferences(context, prefName).getBoolean(prefKey, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static float getFloat(Context context, String prefName, String prefKey, float defaultValue) {
        try {
            return getSharedPreferences(context, prefName).getFloat(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static float getFloat(Context context, String prefName, String prefKey) {
        try {
            return getSharedPreferences(context, prefName).getFloat(prefKey, 0.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public static int getInt(Context context, String prefName, String prefKey, int defaultValue) {
        try {
            return getSharedPreferences(context, prefName).getInt(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static int getInt(Context context, String prefName, String prefKey) {
        try {
            return getSharedPreferences(context, prefName).getInt(prefKey, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getLong(Context context, String prefName, String prefKey, long defaultValue) {
        try {
            return getSharedPreferences(context, prefName).getLong(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static long getLong(Context context, String prefName, String prefKey) {
        try {
            return getSharedPreferences(context, prefName).getLong(prefKey, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getString(Context context, String prefName, String prefKey, String defaultValue) {
        try {
            return getSharedPreferences(context, prefName).getString(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static void putBoolean(Context context, String prefName, String prefKey, boolean value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context, prefName).edit().putBoolean(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putFloat(Context context, String prefName, String prefKey, float value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context, prefName).edit().putFloat(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putInt(Context context, String prefName, String prefKey, int value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context, prefName).edit().putInt(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putLong(Context context, String prefName, String prefKey, long value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context, prefName).edit().putLong(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putString(Context context, String prefName, String prefKey, String value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context, prefName).edit().putString(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(Context context, String prefName, String prefKey) {
        try {
            getSharedPreferences(context, prefName).edit().remove(prefKey).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean getBoolean(Context context, String prefKey, boolean defaultValue) {
        try {
            return getSharedPreferences(context).getBoolean(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static boolean getBoolean(Context context, String prefKey ) {
        try {
            return getSharedPreferences(context).getBoolean(prefKey, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public static float getFloat(Context context, String prefKey, float defaultValue) {
        try {
            return getSharedPreferences(context).getFloat(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static float getFloat(Context context, String prefKey) {
        try {
            return getSharedPreferences(context).getFloat(prefKey, 0.0f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0f;
    }

    public static int getInt(Context context, String prefKey, int defaultValue) {
        if(context==null|| TextUtils.isEmpty(prefKey))
            return defaultValue;
        try {
            return getSharedPreferences(context).getInt(prefKey, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    public static int getInt(Context context, String prefKey) {
        if(context==null|| TextUtils.isEmpty(prefKey))
            return 0;
        try {
            return getSharedPreferences(context).getInt(prefKey, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static long getLong(Context context, String prefKey, long defaultValue) {
        if (context != null|| !TextUtils.isEmpty(prefKey))
            try {
                return getSharedPreferences(context).getLong(prefKey, defaultValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return defaultValue;
    }

    public static long getLong(Context context, String prefKey) {
        if (context != null|| !TextUtils.isEmpty(prefKey))
            try {
                return getSharedPreferences(context).getLong(prefKey, 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }

    public static String getString(Context context, String prefKey, String defaultValue) {
        if (context != null|| !TextUtils.isEmpty(prefKey))
            try {
                return getSharedPreferences(context).getString(prefKey, defaultValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return defaultValue;
    }

    public static String getString(Context context, String prefKey) {
        if (context != null|| !TextUtils.isEmpty(prefKey))
            try {
                return getSharedPreferences(context).getString(prefKey, "");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return "";
    }

    public static void putBoolean(Context context, String prefKey, boolean value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        if (context != null)
            try {
                getSharedPreferences(context).edit().putBoolean(prefKey, value).commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        return;
    }

    public static void putFloat(Context context, String prefKey, float value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context).edit().putFloat(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putInt(Context context, String prefKey, int value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context).edit().putInt(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putLong(Context context, String prefKey, long value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context).edit().putLong(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void putString(Context context, String prefKey, String value) {
        if (TextUtils.isEmpty(prefKey)) {
            return;
        }
        try {
            getSharedPreferences(context).edit().putString(prefKey, value).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void remove(Context context, String prefKey) {
        try {
            getSharedPreferences(context).edit().remove(prefKey).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 得到默认SharePreference
     * 
     * @param context
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context) {
            return getSharedPreferences(context, LibraryConstant.APP_NAME);
    }

    /**
     * 根据名字得到SharePreference
     * 
     * @param context
     * @param prefName
     * @return
     */
    public static SharedPreferences getSharedPreferences(Context context, String prefName) {
//        if (context == null) {
//            context = APP.getApp();
//        }
        if (TextUtils.isEmpty(prefName)) {
            return PreferenceManager.getDefaultSharedPreferences(context);
        } else {
            return context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        }
    }
}
