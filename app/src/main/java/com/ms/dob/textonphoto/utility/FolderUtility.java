package com.ms.dob.textonphoto.utility;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;

public class FolderUtility {
    private static final String TAG = "folderUtils";
    public static String BackgroundFolderName = "Background";
    public static String EmojiFolderName = "Emoji";

    public static File mainfolder() {
//        Log.e(TAG, "mainfolder: +++++"+Build.VERSION.SDK_INT );
//        Log.e(TAG, "app name: +++++"+MyApplication.getInstance().getResources().getString(R.string.app_name) );
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.R){
            File file =new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), MyApplication.getInstance().getResources().getString(R.string.app_name));
            file.mkdirs();
            return file;
        }
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/"+ MyApplication.getInstance().getResources().getString(R.string.app_name));
        file.mkdirs();
        return file;
    }

    public static void DeleteFiles(String str) {
        if (new File(str).exists()) {
            new File(str).delete();
            System.out.print("");
        }
    }

    public static void DeleteFolder(File file) {
        String[] list;
        if (file.isDirectory() && (list = file.list()) != null) {
            for (String file2 : list) {
                new File(file, file2).delete();
            }
        }
    }

    public static File MystickerFolder() {
        File file = new File(mainfolder().getPath());
        file.mkdirs();
        return file;
    }

    public static File StatusSaveFolder() {
        File file = new File(mainfolder() + "/Status");
        file.mkdirs();
        return file;
    }

    public static File MyCreationFolder() {
        File file = new File(mainfolder() + "/Mycreations");
        file.mkdirs();
        return file;
    }

    public static File ResizeimageFolder() {
        File file = new File(mainfolder() + "/.Temp");
        file.mkdirs();
        return file;
    }

    public static File ShareFolder() {
        File file = new File(mainfolder() + "/.Share");
        file.mkdirs();
        return file;
    }

    public static void ScaneGallery(final Activity activity, String str, final boolean z) {
        try {
            MediaScannerConnection.scanFile(activity, new String[]{str}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String str, Uri uri) {
                    if (z && uri != null) {
                        activity.getContentResolver().delete(uri, (String) null, (String[]) null);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
