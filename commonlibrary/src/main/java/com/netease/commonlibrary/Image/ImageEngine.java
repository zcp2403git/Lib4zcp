package com.netease.commonlibrary.Image;

import android.content.Context;

import com.netease.commonlibrary.Image.ImageLoader.ImageLoaderManager;

/**
 * 图片请求配置类
 * @author zhouchangping
 */
public class ImageEngine {

    ImageManagerStack managerStack;

    public ImageEngine(ImageEngine.Builder builder) {
        this.managerStack = builder.imageManagerStack;
    }

    public static class Builder {
        private ImageManagerStack imageManagerStack;
        private Context context;

        public ImageEngine.Builder networkManager(ImageManagerStack networkManagerStack) {
            this.imageManagerStack = networkManagerStack;
            return this;
        }

        public Builder(Context context) {
            this.imageManagerStack = null;
            this.context = context;
        }


        public ImageEngine build() {
            this.initEmptyFieldsWithDefaultValues();
            return new ImageEngine(this);
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (this.imageManagerStack == null) {
                this.imageManagerStack = ImageLoaderManager.getInstance(context);
            }
        }
    }
}