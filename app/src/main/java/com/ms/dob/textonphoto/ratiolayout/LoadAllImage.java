package com.ms.dob.textonphoto.ratiolayout;

import android.app.Activity;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;
import java.io.IOException;
import java.util.ArrayList;

public class LoadAllImage {
    public static ArrayList<String> getAllShownImagesPath(Activity activity) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "mime_type"}, "mime_type!=? and mime_type!=?", new String[]{"image/gif", "image/webp"}, "date_modified desc");
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("_data"));
            if (!string.contains("cache")) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    public static ArrayList<String> LoadAssectimage_1(Activity activity, String str) {
        String[] strArr;
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            strArr = activity.getAssets().list(str);
        } catch (IOException e) {
            Log.e("tag", e.getMessage());
            strArr = null;
        }
        for (int i = 0; i < strArr.length; i++) {
            arrayList.add("file:///android_asset/" + str + "/" + strArr[i]);
        }
        return arrayList;
    }
}
