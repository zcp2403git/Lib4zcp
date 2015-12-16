package com.netease.commonlibrary.Utils.File;

import com.netease.commonlibrary.Constant.LibraryConstant;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    public static void initFileDirs() {// 创建花田sdcard目录
        File file = new File(LibraryConstant.TEMP_FILE_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            File nomedia = new File(LibraryConstant.TEMP_FILE_PATH + "/.nomedia");
            if (!nomedia.exists()) {
                nomedia.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile(String path) {
        new File(path).delete();
    }

    public static void renameFile(String srcPath, String destName) {

        String path = srcPath.substring(0, srcPath.lastIndexOf("/") + 1);
        String end = srcPath.substring(srcPath.lastIndexOf("."), srcPath.length());
        String destPath = path + destName + end;
        new File(srcPath).renameTo(new File(destPath));
    }

    public static void deleteTempFiles() {// 删除部分临时文件
        new Thread() {
            public void run() {
                File path = new File(LibraryConstant.TEMP_FILE_PATH);
                File files[] = path.listFiles(new FileFilter() {

                    @Override
                    public boolean accept(File pathname) {
                        // TODO Auto-generated method stub
                        return pathname.getName().endsWith(".mp4");
                    }
                });

                for (int i = 0; files != null && i < files.length; i++) {
                    files[i].delete();
                }
            }
        }.start();
    }

    public static void writeFile(String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath);
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void appendFile(String filePath, String content) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filePath, true);
            writer.append(content).append('\n').append('\r');
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static String readFile(String filePath) {
        FileReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            char[] buffer = new char[1024];
            reader = new FileReader(filePath);
            int count = reader.read(buffer);
            while (count > -1) {
                if (count == 1024) {
                    sb.append(buffer);
                } else {
                    sb.append(buffer, 0, count);
                }
                count = reader.read(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String ret = sb.toString().trim();
        return ret;
    }

}
