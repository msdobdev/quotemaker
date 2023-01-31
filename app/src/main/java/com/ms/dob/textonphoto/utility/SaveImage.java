package com.ms.dob.textonphoto.utility;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
//
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.FolderUtility;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class SaveImage {
    public static String createDirectoryAndSaveFile(Activity activity, Bitmap bitmap) {
        File file = new File(FolderUtility.MyCreationFolder(), "im" + System.currentTimeMillis() + ".png");
        MediaScannerConnection.scanFile(activity, new String[]{file.getPath()}, (String[]) null, new MediaScannerConnection.OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
            }
        });

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Bitmap takescreen(Activity activity) {
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(R.id.imagetextadview);
        frameLayout.setDrawingCacheEnabled(true);
//        frameLayout.buildDrawingCache();
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(frameLayout.getDrawingCache(), frameLayout.getWidth(), frameLayout.getHeight(), true);
        frameLayout.setDrawingCacheEnabled(false);
        return createScaledBitmap;
    }

    public Bitmap takescreen_Degine(Activity activity, View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(view.getDrawingCache(), view.getWidth(), view.getHeight(), true);
        view.setDrawingCacheEnabled(false);
        return createScaledBitmap;
    }

    public Bitmap takescreen_List(Activity activity, View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(view.getDrawingCache(), view.getWidth(), view.getHeight(), true);
        view.setDrawingCacheEnabled(false);
        return createScaledBitmap;
    }

    public static ArrayList<String[]> getSaveImagePath() {
        File MyCreationFolder = FolderUtility.MyCreationFolder();
        ArrayList<String[]> arrayList = new ArrayList<>();
        if (MyCreationFolder.isDirectory()) {
            File[] listFiles = MyCreationFolder.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                arrayList.add(new String[]{listFiles[i].getAbsolutePath(), ""});
            }
        }
        Collections.reverse(arrayList);
        return arrayList;
    }
}
