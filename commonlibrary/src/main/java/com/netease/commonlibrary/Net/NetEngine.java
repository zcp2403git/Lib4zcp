package com.netease.commonlibrary.Net;

import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.MediaStore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netease.commonlibrary.Net.VolleyUtils.VolleyNetManager;

import java.lang.reflect.Modifier;

/**
 * 网络请求配置类
 *
 * @author zhouchangping
 */
public class NetEngine {

    NetworkManagerStack managerStack;
    Gson gsonParse;//Gson对象

    public NetEngine(NetEngine.Builder builder) {
        this.managerStack = builder.networkManagerStack;
        this.gsonParse=builder.gson;
    }

    public static class Builder {
        private NetworkManagerStack networkManagerStack;
        private Gson gson;
        private Context context;

        public NetEngine.Builder networkManager(NetworkManagerStack networkManagerStack) {
            this.networkManagerStack = networkManagerStack;
            return this;
        }

        public NetEngine.Builder Gson(Gson gson) {
            this.gson = gson;
            return this;
        }

        public Builder(Context context) {
            this.networkManagerStack = null;
            this.gson=null;
            this.context = context;

        }


        public NetEngine build() {
            this.initEmptyFieldsWithDefaultValues();
            return new NetEngine(this);
        }

        private void initEmptyFieldsWithDefaultValues() {
            if (this.networkManagerStack == null) {
                this.networkManagerStack = VolleyNetManager.getInstance(context);
            }
            if (this.gson == null) {
                initGson();
            }

        }

        private  void initGson() {
            final int sdk = Build.VERSION.SDK_INT;
            if (sdk >= 23)
            {
                GsonBuilder gsonBuilder = new GsonBuilder()
                        .excludeFieldsWithModifiers(
                                Modifier.FINAL,
                                Modifier.TRANSIENT,
                                Modifier.STATIC);
                this.gson = gsonBuilder.create();
            } else
            {
                this.gson = new Gson();
            }
        }
    }


}