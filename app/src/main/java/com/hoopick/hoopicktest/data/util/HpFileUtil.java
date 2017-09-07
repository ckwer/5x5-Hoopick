package com.hoopick.hoopicktest.data.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by pro on 2016-09-24.
 */
public class HpFileUtil {

    private static final String LOG_TAG = "HpFileUtil";

    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public static File getExternalPublicDownload(Context context, String dirName) {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), dirName);

        if (false == file.exists()) {
            file.mkdirs();
        }

        return file;

    }

    public static File getExternalPrivateDoc(Context context, String dirName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), dirName);

        if (!file.exists()) {
            if (!file.mkdirs()) {
                Log.e(LOG_TAG, "Directory not created");
            }
        }

        return file;
    }

}
