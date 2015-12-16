package com.netease.commonlibrary.Utils.Log;

import android.util.Log;

import com.netease.commonlibrary.Constant.LibraryConstant;
import com.netease.commonlibrary.Utils.File.FileUtils;
import com.netease.commonlibrary.Utils.File.MountedSDCard;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author wanghb
 */
public class L {

    private static final String TAG = "liveRecorder";

    public static boolean USER_LOG = false;

    private static int LOG_LEVEL = 0;

    private static final int LEVEL_VERBOSE = 0;

    private static final int LEVEL_DEBUG = 1;

    private static final int LEVEL_INFO = 2;

    private static final int LEVEL_WARN = 3;

    private static final int LEVEL_ERROR = 4;

    public static final String USER_LOG_FILE = "/liveRecorderUser.log";

    public static void user(String content) {
        if (!USER_LOG) {
            return;
        }
        String fileName = MountedSDCard.getInstance().getExternalSdPath(false) + USER_LOG_FILE;
        FileUtils.appendFile(fileName, content);
    }

    public static void user(Throwable t) {
        if (!USER_LOG) {
            return;
        }
        String fileName = MountedSDCard.getInstance().getExternalSdPath(false) + USER_LOG_FILE;
        try {
            t.printStackTrace(new PrintWriter(new File(fileName)));
        } catch (FileNotFoundException e) {}
        FileUtils.appendFile(fileName, "");
    }

    public static void v(Object tag, Object... m) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_VERBOSE) {
            Log.v(getTagName(tag), join(m));
        }
        logToFile(tag, m);
    }
 
    public static void w(Object tag, Object... m) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_WARN) {
            Log.w(getTagName(tag), join(m));
        }
        logToFile(tag, m);
    }

    public static void d(Object tag, Object... m) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_DEBUG) {
            Log.d(getTagName(tag), join(m));
        }
        logToFile(tag, m);
    }

    public static void i(Object tag, Object... m) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_INFO) {
            Log.i(getTagName(tag), join(m));
        }
        logToFile(tag, m);
    }

    public static void e(Object tag, String m) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_ERROR) {
            Log.e(getTagName(tag), String.valueOf(m));
        }
        logToFile(tag, m);
    }

    public static void e(Object tag, String m, Throwable t) {
        if (LibraryConstant.L_DEBUG && LOG_LEVEL <= LEVEL_ERROR) {
            Log.e(getTagName(tag), String.valueOf(m));
            t.printStackTrace();
        }
        logToFile(tag, m);
    }

    private static String getTagName(Object tag) {
        if (tag instanceof String) {
            return String.valueOf(tag);
        } else if (tag instanceof Class<?>) {
            return ((Class<?>) tag).getSimpleName();
        } else if (tag != null) {
            return getTagName(tag.getClass());
        } else {
            return TAG;
        }
    }

    private static String join(Object[] arr) {
        if (arr == null || arr.length == 0) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Object o: arr) {
            if (o != null) {
                sb.append(String.valueOf(o));
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static void logToFile(Object tag, Object... m) {
        if (FileLogger.LOG_TO_FILE) {
            FileLogger.writeLine(getTagName(tag), join(m));
        }
    }
}
