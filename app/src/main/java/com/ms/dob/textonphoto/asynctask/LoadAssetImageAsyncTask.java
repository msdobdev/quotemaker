package com.ms.dob.textonphoto.asynctask;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;

import com.ms.dob.textonphoto.ratiolayout.LoadAllImage;

import java.util.ArrayList;

public abstract class LoadAssetImageAsyncTask extends AsyncTask<Uri, Void, ArrayList<String>> {
    String foldername;
    Activity mconContext;

    public abstract void Ontick();

    public abstract void onRecived(ArrayList<String> arrayList);

    public LoadAssetImageAsyncTask(Activity activity, String str) {
        this.mconContext = activity;
        this.foldername = str;
    }

    /* access modifiers changed from: protected */
    public ArrayList<String> doInBackground(Uri... uriArr) {
        return LoadAllImage.LoadAssectimage_1(this.mconContext, this.foldername);
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Ontick();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ArrayList<String> arrayList) {
        onRecived(arrayList);
    }
}
