package com.ms.dob.textonphoto.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ms.dob.textonphoto.adapter.MyCreationAdapter;
import com.ms.dob.textonphoto.utility.FolderUtility;

import com.ms.dob.textonphoto.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MyCreationActivity extends AppCompatActivity implements MyCreationAdapter.ClickListener {
    private static final String TAG = "MyCreationActivity";
    MyCreationAdapter myCreationAdapter;
    private InterstitialAd mInterstitialAd;
    private boolean isShown = true;

    ImageView backbutton;
    RecyclerView mRecyclerView;
    View noimagetext;
    public  ArrayList<String[]> createdimagepath ;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_my_creation);

        LinearLayout ly_adcontainer_creationlist = findViewById(R.id.ly_adcontainer_creationlist);
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        loadInterstitialAd();

//        myCreationActivity = this;
//        ActionbarUtility.Statusbarcolor(this);
//        ActionbarUtility.HideActionBar(getSupportActionBar());
//        this.tittlename = (TextView) findViewById(R.id.tittlename);
//        this.tittlename.setTypeface(FontUtility.getfont());
        createdimagepath = new ArrayList<>();
        createdimagepath = getSaveImagePath();

//        MyApplication.getInstance().setCreatedimagepath(new ArrayList<>());
//        MyApplication.getInstance().setCreatedimagepath(SaveImage.getSaveImagePath());

        this.mRecyclerView = (RecyclerView) findViewById(R.id.mycreationrecyleview);
        this.backbutton = (ImageView) findViewById(R.id.backbutton);
        this.noimagetext =  findViewById(R.id.noimageicon);
//        this.myCreationLayoutId = new MyCreationLayoutId();
//        this.myCreationLayoutId.GetMyCreationId(this);
//        this.myCreationLayoutId.ButtonClickMycreation(this);
        this.backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
        if (createdimagepath.size() == 0) {
            this.noimagetext.setVisibility(0);
        } else {
            this.noimagetext.setVisibility(8);
        }
        MyCreationAdpterCall();
        loadBanner(ly_adcontainer_creationlist, ad_default_layout);

    }
    private void loadInterstitialAd() {

        if (isShown && mInterstitialAd == null) {
            AdRequest adRequest = new AdRequest.Builder().build();
            InterstitialAd.load(this, getResources().getString(R.string.interstitial_id), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            isShown = false;
                            //                        Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            //                        Log.d(TAG, loadAdError.toString());
                            mInterstitialAd = null;
                            isShown = true;

                        }
                    });
        }


    }
    private void loadBanner(LinearLayout adcontainer, View ad_default_layout) {
        AdView adView = new AdView(MyCreationActivity.this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(getResources().getString(R.string.banner_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                adcontainer.removeAllViews();
                adcontainer.addView(adView);
                ad_default_layout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                ad_default_layout.setVisibility(View.GONE);

                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });


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

    @Override
    public void noCreation() {
        this.noimagetext.setVisibility(0);

    }

    public void onItemClicked_Mycreation(String path) {
        if (mInterstitialAd != null) {
            setInterListener(path);
            mInterstitialAd.show(MyCreationActivity.this);
        }else {
            Intent intent = new Intent(this, ShareActivity.class);
            intent.putExtra("shared_file", path);
            intent.putExtra("is_from_list", true);
//        intent.putExtra("IM_PO", i);
            startActivity(intent);
            loadInterstitialAd();
        }
//        Intent intent = new Intent(this, GalleryExample.class);
//        intent.putExtra("IM_PO", i);
//        startActivity(intent);
    }
    private void setInterListener(String savedFilepath) {
        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
            @Override
            public void onAdClicked() {
                // Called when a click is recorded for an ad.
                Log.d(TAG, "Ad was clicked.");
            }

            @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.
                Log.d(TAG, "Ad dismissed fullscreen content.");
                mInterstitialAd = null;
                isShown = true;
                Intent intent = new Intent(MyCreationActivity.this, ShareActivity.class);
                intent.putExtra("shared_file", savedFilepath);
                intent.putExtra("is_from_list", true);
//        intent.putExtra("IM_PO", i);
                startActivity(intent);

            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
                isShown = true;

            }

            @Override
            public void onAdImpression() {
                // Called when an impression is recorded for an ad.
                Log.d(TAG, "Ad recorded an impression.");
            }

            @Override
            public void onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.");
            }
        });
    }

    public void MyCreationAdpterCall() {
        myCreationAdapter = new MyCreationAdapter(this, this,createdimagepath);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.mRecyclerView.setAdapter(myCreationAdapter);
    }

//    public static void nodifieAdpter(int i) {
//        try {
//            myCreationAdpter.notifyItemRemoved(i);
//        } catch (IndexOutOfBoundsException unused) {
//            myCreationAdpter.notifyDataSetChanged();
//        }
//    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
