/**
 * @(#)FixSizeImageAware.java, 2015年1月28日. 
 * 
 * Copyright 2015 netease, Inc. All rights reserved.
 * Netease PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.netease.commonlibrary.Image.ImageLoader.view;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;

/**
 *
 * @author huangyifei
 *
 */
public class FixSizeImageAware extends ImageViewAware {

    private int mWidth;

    private int mHeigth;

    /**
     * @param imageView
     */
    public FixSizeImageAware(ImageView imageView, int w, int h) {
        super(imageView, false);
        mWidth = w;
        mHeigth = h;
    }

    @Override
    public int getWidth() {
        return mWidth;
    };

    @Override
    public int getHeight() {
        return mHeigth;
    }

}
