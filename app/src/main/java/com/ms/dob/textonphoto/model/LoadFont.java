package com.ms.dob.textonphoto.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Typeface;

public class LoadFont {
    public Typeface custamfount;

    public LoadFont(String str, Activity activity) {
        AssetManager assets = activity.getAssets();
        this.custamfount = Typeface.createFromAsset(assets, "font/" + str);
        setCustamfount(this.custamfount);
    }

    public void setCustamfount(Typeface typeface) {
        this.custamfount = typeface;
    }

    public Typeface getCustamfount() {
        return this.custamfount;
    }
}
