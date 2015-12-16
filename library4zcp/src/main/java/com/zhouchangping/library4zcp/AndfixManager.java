package com.zhouchangping.library4zcp;

import android.content.Context;
import android.util.Log;
import com.alipay.euler.andfix.patch.PatchManager;
import java.io.IOException;

/**
 * Created by Administrator on 2015/10/15 0015.
 */
public class AndfixManager {

    private static final String TAG = "Andfix";

    public static PatchManager getPatchManager(Context context, String version, String mPatchPath) {
        PatchManager mPatchManager = new PatchManager(context);
        mPatchManager.init(version);
        Log.d(TAG, "inited.");
        // load patch
        mPatchManager.loadPatch();
        Log.d(TAG, "apatch loaded.");
        // add patch at runtime
        try {
            // .apatch file path
//            String patchFileString = Environment.getExternalStorageDirectory() .getAbsolutePath() + APATCH_PATH;
            mPatchManager.addPatch(mPatchPath);
            Log.d(TAG, "apatch:" + mPatchPath + " added.");
        } catch (IOException e) {
            Log.e(TAG, "", e);
        }
        return mPatchManager;
    }
}
