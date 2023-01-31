package com.ms.dob.textonphoto.activity;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;
import androidx.annotation.Nullable;


import android.os.Handler;

import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.android.gms.ads.RequestConfiguration;
import com.ms.dob.textonphoto.utility.FolderUtility;
import com.ms.dob.textonphoto.R;

import java.util.Arrays;

public class SplashActivity extends Activity {

    ProgressBar splash_loader;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);

//        ActionbarUtility.Statusbarcolor(this);
        FolderUtility.DeleteFolder(FolderUtility.ResizeimageFolder());
        FolderUtility.DeleteFolder(FolderUtility.ShareFolder());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
        splash_loader = findViewById(R.id.splash_loader);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splash_loader.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
            }
        },1000);

    }



}
