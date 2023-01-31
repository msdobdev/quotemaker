package com.ms.dob.textonphoto.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;


//
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ms.dob.textonphoto.adapter.QuoteCategoryAdapter;
import com.ms.dob.textonphoto.model.CategoryData;
import com.ms.dob.textonphoto.asynctask.QuotesCategoryAsyncTask;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuoteCategoryListActivity extends AppCompatActivity implements QuoteCategoryAdapter.QuotecatClickListener {


    private static final String TAG = "QuoteCatListActivity";
    ImageView backbtn;


    QuoteCategoryAdapter quoteCategoryAdapter;
    RecyclerView mreRecyclerView;
    private InterstitialAd mInterstitialAd;
    private boolean isShown = true;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.quotes_cat_layout);

        this.mreRecyclerView = (RecyclerView) findViewById(R.id.list_item_recyleview);
        loadInterstitialAd();
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_quotes_cat);
        if (MyApplication.getInstance().getCategoryDataArrayList().size() == 0) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    new QuotesCategoryAsyncTask(QuoteCategoryListActivity.this) {
                        public void Ontick() {
                        }

                        public void onRecived(ArrayList<CategoryData> arrayList) {
                            QuoteCategoryListActivity.this.calllayout();
                        }
                    }.execute(new ArrayList[0]);
                }
            }, 0);
        } else {
            calllayout();
        }
        this.backbtn = (ImageView) findViewById(R.id.backbutton);
        this.backbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QuoteCategoryListActivity.this.onBackPressed();
            }
        });
        loadBanner(ly_adcontainer, ad_default_layout);

    }

    private void loadBanner(LinearLayout adcontainer, View ad_default_layout) {
        AdView adView = new AdView(QuoteCategoryListActivity.this);
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
    private void setInterListener(int pos) {
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
                Intent intent = new Intent(QuoteCategoryListActivity.this, CategoryListActivity.class);
                intent.putExtra("pos", pos);
                startActivity(intent);
                loadInterstitialAd();

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

    public void onItemClicked_Category(int i) {
        if (mInterstitialAd != null) {
            setInterListener(i);
            mInterstitialAd.show(QuoteCategoryListActivity.this);
        }else {
            Intent intent = new Intent(this, CategoryListActivity.class);
            intent.putExtra("pos", i);
            startActivity(intent);
            loadInterstitialAd();
        }



    }

    private void CategotyAdpterCall() {
        this.quoteCategoryAdapter = new QuoteCategoryAdapter(this, this);
        this.mreRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        this.mreRecyclerView.setAdapter(this.quoteCategoryAdapter);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void calllayout() {
        CategotyAdpterCall();

    }

}
