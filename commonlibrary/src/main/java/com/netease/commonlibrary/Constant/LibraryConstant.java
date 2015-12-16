
package com.netease.commonlibrary.Constant;

import android.os.Environment;
/**
 * 常量的定义类
 *
 * @author zhouchangping
 */
public class LibraryConstant {
    public static final String  APP_NAME="liverecorder";
    public static final boolean  L_DEBUG=true;
    public static final String EXTERNAL_STORAGE_DIRECTORY = Environment.getExternalStorageDirectory().getPath();
    public static final String LOG_FILE_PATH = EXTERNAL_STORAGE_DIRECTORY + "/"+APP_NAME+"/log/";
    public static final String TEMP_FILE_PATH = EXTERNAL_STORAGE_DIRECTORY +"/"+APP_NAME+"/temp/";
    public static final String IMAGE_LIVERECORDER_PATH = EXTERNAL_STORAGE_DIRECTORY +  "/"+APP_NAME+"/image/";
    public static final String CRASH_FILE_PATH = EXTERNAL_STORAGE_DIRECTORY + "/"+APP_NAME+"/crash/";
    public static final String HEAP_FILE_PATH = EXTERNAL_STORAGE_DIRECTORY + "/"+APP_NAME+"/heap/";
}
