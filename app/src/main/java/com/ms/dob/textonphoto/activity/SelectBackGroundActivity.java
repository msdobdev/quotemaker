package com.ms.dob.textonphoto.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.ms.dob.textonphoto.asynctask.LoadAssetImageAsyncTask;
import com.ms.dob.textonphoto.utility.FolderUtility;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.adapter.SelectBackGroundAdapter;
//
import java.util.ArrayList;

public class SelectBackGroundActivity extends AppCompatActivity implements SelectBackGroundAdapter.SelectClickListener {
    private static final String TAG = "SelectBackActivity";
    //    int addcount = 0;
    ImageView backbtn;
    RecyclerView recyclerView;
//    RelativeLayout relativeLayout;
    SelectBackGroundAdapter selectBackGroundAdapter;
    private InterstitialAd mInterstitialAd;
    private boolean isShown = true;

//    TextView tittlename;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.select_background_image);
//        ActionbarUtility.Statusbarcolor(this);
//        ActionbarUtility.HideActionBar(getSupportActionBar());
        this.recyclerView = (RecyclerView) findViewById(R.id.list_item_recyleview);
//        this.relativeLayout = (RelativeLayout) findViewById(R.id.recyleviewlayout);
//        this.relativeLayout.getLayoutParams().width = (int) ImageSizeUtility.wright;
        this.backbtn = (ImageView) findViewById(R.id.backbutton);
        this.backbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectBackGroundActivity.this.onBackPressed();
            }
        });
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_selectbag);
        loadInterstitialAd();
//        this.tittlename = (TextView) findViewById(R.id.tittlename);
//        this.tittlename.setTypeface(FontUtility.getfont());
            CategotyAdpterCall();
        if (MyApplication.getInstance().getBackgroundarrylist().size() == 0) {
            new LoadAssetImageAsyncTask(this, FolderUtility.BackgroundFolderName) {
                public void Ontick() {
                }

                public void onRecived(ArrayList<String> arrayList) {
                    MyApplication.getInstance().setBackgroundarrylist(arrayList);
                    SelectBackGroundActivity.this.CategotyAdpterCall();
                }
            }.execute(new Uri[0]);
        } else {
            CategotyAdpterCall();
        }
        loadNative(ly_adcontainer, ad_default_layout);

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
    /* access modifiers changed from: private */
    public void CategotyAdpterCall() {
        this.selectBackGroundAdapter = new SelectBackGroundAdapter(this, this, MyApplication.getInstance().getBackgroundarrylist());
//        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.setAdapter(this.selectBackGroundAdapter);
    }

    public void onItemClicked_BG(int i) {
//        int i2 = this.addcount;
//        this.addcount = i2 + 1;
//        this.addcount = i2;
//        if (this.addcount == 3) {
//            this.addcount = 0;
//            HomeScreenActivity.full_add(this);
//        }
        if (mInterstitialAd != null) {
            setInterListener(i);
            mInterstitialAd.show(SelectBackGroundActivity.this);
        }else {
            int size = i - MyApplication.getInstance().getBackgroundarrylist().size();
            Intent intent = new Intent(this, QuoteEditActivity.class);
            intent.putExtra("pos", size);
            intent.putExtra("STATUSTEXT", "");
//        intent.putExtra("ISNOTIFACTION", false);
            if (size <= 0) {
                intent.putExtra("IMAGEPATH", MyApplication.getInstance().getBackgroundarrylist().get(i));
            }
            startActivity(intent);
            loadInterstitialAd();
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
                int size = pos - MyApplication.getInstance().getBackgroundarrylist().size();
                Intent intent = new Intent(SelectBackGroundActivity.this, QuoteEditActivity.class);
                intent.putExtra("pos", size);
                intent.putExtra("STATUSTEXT", "");
//        intent.putExtra("ISNOTIFACTION", false);
                if (size <= 0) {
                    intent.putExtra("IMAGEPATH", MyApplication.getInstance().getBackgroundarrylist().get(pos));
                }
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

    private void loadNative(LinearLayout ly_adcontainer, View ad_default_layout) {
        AdLoader adLoader = new AdLoader.Builder(this, getResources().getString(R.string.native_id))
                .withAdListener(new AdListener() {
                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        super.onAdFailedToLoad(loadAdError);
                        ad_default_layout.setVisibility(View.GONE);

                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                    }
                })
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        // Assumes you have a placeholder FrameLayout in your View layout
                        // (with id fl_adplaceholder) where the ad is to be placed.
//                        FrameLayout frameLayout =
//                                findViewById(R.id.fl_adplaceholder);
                        // Assumes that your ad layout is in a file call native_ad_layout.xml
                        // in the res/layout folder
                        NativeAdView adView = (NativeAdView) getLayoutInflater()
                                .inflate(R.layout.google_native_150dp, null);
                        // This method sets the text, images and the native ad, etc into the ad
                        // view.
                        setNativeAd(nativeAd, adView);
                        ly_adcontainer.removeAllViews();
                        ly_adcontainer.addView(adView);
                        ad_default_layout.setVisibility(View.INVISIBLE);

                    }

                }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    public void setNativeAd(NativeAd nativeAd, NativeAdView nativeAdView ) {
//        this.nativeAd = nativeAd;
//         NativeAdView nativeAdView = (NativeAdView)getLayoutInflater().inflate(layoutId, null);
        TextView primaryView = (TextView) nativeAdView.findViewById(R.id.primary);
        TextView  secondaryView = (TextView) nativeAdView.findViewById(R.id.secondary);
        TextView tertiaryView = (TextView) nativeAdView.findViewById(R.id.body);

        RatingBar ratingBar = (RatingBar) nativeAdView.findViewById(R.id.rating_bar);
        ratingBar.setEnabled(false);

        Button callToActionView = (Button) nativeAdView.findViewById(R.id.cta);
        ImageView iconView = (ImageView) nativeAdView.findViewById(R.id.icon);
        MediaView mediaView = (MediaView) nativeAdView.findViewById(R.id.media_view);
//        ConstraintLayout background = (ConstraintLayout) nativeAdView.findViewById(R.id.background);

        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        String headline = nativeAd.getHeadline();
        String body = nativeAd.getBody();
        String cta = nativeAd.getCallToAction();
        Double starRating = nativeAd.getStarRating();
        NativeAd.Image icon = nativeAd.getIcon();
        String secondaryText;

        nativeAdView.setCallToActionView(callToActionView);
        nativeAdView.setHeadlineView(primaryView);
        nativeAdView.setMediaView(mediaView);
        secondaryView.setVisibility(VISIBLE);
        if (adHasOnlyStore(nativeAd)) {
            nativeAdView.setStoreView(secondaryView);
            secondaryText = store;
        } else if (!TextUtils.isEmpty(advertiser)) {
            nativeAdView.setAdvertiserView(secondaryView);
            secondaryText = advertiser;
        } else {
            secondaryText = "";
        }

        primaryView.setText(headline);
        callToActionView.setText(cta);

        //  Set the secondary view to be the star rating if available.
        if (starRating != null && starRating > 0) {
            secondaryView.setVisibility(GONE);
            ratingBar.setVisibility(VISIBLE);
            ratingBar.setRating(starRating.floatValue());

            nativeAdView.setStarRatingView(ratingBar);
        } else {
            secondaryView.setText(secondaryText);
            secondaryView.setVisibility(VISIBLE);
            ratingBar.setVisibility(GONE);
        }

        if (icon != null) {
            iconView.setVisibility(VISIBLE);
            iconView.setImageDrawable(icon.getDrawable());
        } else {
            iconView.setVisibility(GONE);
        }

        if (tertiaryView != null) {
            tertiaryView.setText(body);
            nativeAdView.setBodyView(tertiaryView);
        }

        nativeAdView.setNativeAd(nativeAd);
    }
    private boolean adHasOnlyStore(NativeAd nativeAd) {
        String store = nativeAd.getStore();
        String advertiser = nativeAd.getAdvertiser();
        return !TextUtils.isEmpty(store) && TextUtils.isEmpty(advertiser);
    }
}
