package com.ms.dob.textonphoto.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;

import com.ms.dob.textonphoto.utility.SaveImage;

public abstract class SaveStickerAsyncTask extends AsyncTask<Uri, Void, String> {
    Bitmap bitmap;
    Activity mconContext;
    ProgressDialog progressDialog ;
    SaveImage saveImage = new SaveImage();

    public abstract void Ontick();

    public abstract void onRecived(String str);

    public SaveStickerAsyncTask(Activity activity, Bitmap bitmap2) {
        this.mconContext = activity;
        this.bitmap = bitmap2;
        progressDialog = new ProgressDialog(this.mconContext);
        this.progressDialog.setIndeterminate(true);
        this.progressDialog.setMessage("Saving image...");
        this.progressDialog.show();
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Uri... uriArr) {
        return SaveImage.createDirectoryAndSaveFile(this.mconContext, this.bitmap);
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Ontick();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(final String str) {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                SaveStickerAsyncTask.this.onRecived(str);
                SaveStickerAsyncTask.this.progressDialog.dismiss();
            }
        }, 200);
    }
}
