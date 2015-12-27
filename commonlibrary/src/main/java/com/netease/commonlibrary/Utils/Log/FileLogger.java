package com.netease.commonlibrary.Utils.Log;


import android.database.Cursor;
import android.provider.MediaStore;

import com.netease.commonlibrary.Constant.LibraryConstant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileLogger {

    public static BufferedWriter mWriter;

    public static final boolean LOG_TO_FILE = LibraryConstant.L_DEBUG;

    public static synchronized void init() {
        if (!LOG_TO_FILE) {
            return;
        }
        try {
            File dir = new File(LibraryConstant.LOG_FILE_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
            String logName = LibraryConstant.LOG_FILE_PATH + date + ".txt";
            mWriter = new BufferedWriter(new FileWriter(logName, true));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static synchronized void writeLine(String tag, String text) {
        if (mWriter == null) {
            return;
        }
        try {
            String time = new SimpleDateFormat("MM-dd hh:mm:ss:SSS").format(new Date(System.currentTimeMillis()));
            text = time + " >>> " + tag + " : " + text;
            mWriter.write(text);
            mWriter.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static synchronized void close() {
        if (mWriter != null) {
            try {
                mWriter.close();
            } catch (IOException e) {}
            mWriter = null;
        }
    }

}
