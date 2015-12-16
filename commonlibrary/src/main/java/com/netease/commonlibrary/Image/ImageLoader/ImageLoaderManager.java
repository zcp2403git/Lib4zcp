package com.netease.commonlibrary.Image.ImageLoader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;

import com.netease.commonlibrary.CallBack.ImageResultCallback;
import com.netease.commonlibrary.Constant.LibraryConstant;
import com.netease.commonlibrary.Image.ImageManagerStack;
import com.netease.commonlibrary.R;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

public class ImageLoaderManager implements ImageManagerStack{
    private static ImageLoader imageLoader;
    private static DisplayImageOptions options;

    private static ImageLoaderManager instance = null;

    public static synchronized ImageLoaderManager getInstance(Context context) {
        if (instance == null) {
            instance = new ImageLoaderManager(context);
        }
        return instance;
    }

    private ImageLoaderManager(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 1).tasksProcessingOrder(QueueProcessingType.LIFO)
                .denyCacheImageMultipleSizesInMemory().memoryCache(new LruMemoryCache(10 * 1024 * 1024))
                .diskCache(new LruDiscCache(new File(LibraryConstant.IMAGE_LIVERECORDER_PATH), new Md5FileNameGenerator(), 20 * 1024 * 1024)).build();
        ImageLoader.getInstance().init(config);
    }

    public synchronized static ImageLoader getImageLoader(Context AppContext) {
        if (imageLoader == null) {
            ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(AppContext)
                    .threadPoolSize(5)
                    .threadPriority(Thread.NORM_PRIORITY + 2)
                    .diskCache(
                            new UnlimitedDiscCache(new File(LibraryConstant.IMAGE_LIVERECORDER_PATH)))
                    .memoryCacheExtraOptions(640, 640)
                    .denyCacheImageMultipleSizesInMemory()
                    .tasksProcessingOrder(QueueProcessingType.LIFO).build();
            if (ImageLoader.getInstance().isInited()) {
                ImageLoader.getInstance().destroy();
            }
            ImageLoader.getInstance().init(config);
            imageLoader = ImageLoader.getInstance();
        }

        return imageLoader;
    }


    public synchronized static DisplayImageOptions getCommonOptions() {
        if (options == null) {
            options = new DisplayImageOptions.Builder()
                    .bitmapConfig(Config.RGB_565)
                    .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                    .showImageOnLoading(R.mipmap.default_loading_item)
                    .showImageForEmptyUri(R.mipmap.default_loading_item)
                    .showImageOnFail(R.mipmap.default_loading_item)
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
                    .build();
        }
        return options;
    }

    @Override
    public void displayImage(ImageAware view, String url, ImageResultCallback callback) {
        imageLoader.displayImage(url, view, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String s, View view) {

            }
            @Override
            public void onLoadingFailed(String s, View view, FailReason failReason) {

            }
            @Override
            public void onLoadingComplete(String s, View view, Bitmap bitmap) {

            }
            @Override
            public void onLoadingCancelled(String s, View view) {

            }
        });
    }
}
