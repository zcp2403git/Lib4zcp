package com.netease.commonlibrary.Image;

import com.netease.commonlibrary.CallBack.ImageResultCallback;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.utils.L;

/**
 * 图片请求通用类
 * @author zhouchangping
 */
public class ImageManager {

    private ImageEngine engine;
    private static volatile ImageManager instance;

    public ImageManager() {
    }

    public static ImageManager getInstance() {
        if(instance == null) {
            Class var0 = ImageManager.class;
            synchronized(ImageManager.class) {
                if(instance == null) {
                    instance = new ImageManager();
                }
            }
        }
        return instance;
    }

    public synchronized void init(ImageEngine engine) {
        if(engine == null) {
            throw new IllegalArgumentException("engine can not be initialized with null");
        } else {
            if(this.engine == null) {
                this.engine = engine;
            } else {
                L.w("Try to initialize NetworkManager which had already been initialized before. To re-init NetworkManager with new configuration call NetworkManager.destroy() at first.", new Object[0]);
            }

        }
    }

    public boolean isInited() {
        return this.engine != null;
    }

    /**
     * 加载图片
     * * @param headers      http请求头
     * @param url         请求地址
     * @param view        控件
     *  @param callback  结果回调
     */
    public void displayImage(ImageAware view,String url,ImageResultCallback callback){

    }
}