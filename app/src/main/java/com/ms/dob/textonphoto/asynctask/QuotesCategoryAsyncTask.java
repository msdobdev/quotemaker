package com.ms.dob.textonphoto.asynctask;

import android.app.Activity;
import android.os.AsyncTask;

import com.ms.dob.textonphoto.model.CategoryData;
import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.MyApplication;

import java.util.ArrayList;

public abstract class QuotesCategoryAsyncTask extends AsyncTask<ArrayList<CategoryData>, Void, ArrayList<CategoryData>> {
    Activity mconContext;

    public abstract void Ontick();

    public abstract void onRecived(ArrayList<CategoryData> arrayList);

    public QuotesCategoryAsyncTask(Activity activity) {
        this.mconContext = activity;
    }

    /* access modifiers changed from: protected */
    public ArrayList<CategoryData> doInBackground(ArrayList<CategoryData>... arrayListArr) {
        if (MyApplication.getInstance().getCategoryDataArrayList().size() == 0) {
            return ReadJsonFromAssect.getcategoryListFromAssect(this.mconContext);
        }
        return MyApplication.getInstance().getCategoryDataArrayList();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Ontick();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(ArrayList<CategoryData> arrayList) {
        onRecived(arrayList);
    }
}
