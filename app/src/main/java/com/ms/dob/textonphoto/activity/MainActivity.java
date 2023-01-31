package com.ms.dob.textonphoto.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.util.Log;
import android.view.View;
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
import com.ms.dob.textonphoto.R;


public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_CODE = 101;
    private static final String TAG = "MainActivity";
    private InterstitialAd mInterstitialAd;
//    static HomeScreenActivity homeScreenActivity = null;
private Clicked_item selectedItem;

    private enum Clicked_item {
        DISCOVER,QUOTES,PIC,CREATION
    }
    View mainlayoutmycreation, mainlayoutquotesonpic, mainlayoutquotes, mainlayoutdiscover;
    private boolean isShown = true;
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.home_activity);

        LinearLayout ly_adcontainer_home = findViewById(R.id.ly_adcontainer_home);
        View  ad_default_layout = findViewById(R.id.ad_default_layout);
        this.mainlayoutdiscover = findViewById(R.id.mainlayoutdiscover);
        this.mainlayoutquotes = findViewById(R.id.mainlayoutquotes);
        this.mainlayoutquotesonpic = findViewById(R.id.mainlayoutquotesonpic);
        this.mainlayoutmycreation = findViewById(R.id.mainlayoutmycreation);
loadInterstitialAd();
        mainlayoutdiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check permission
                if (!isAllPermationAllowed(getApplicationContext())) {
                    requestAllPermationPermission(MainActivity.this,PERMISSION_CODE);

                } else {
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.DISCOVER;
                        setInterListener();
                        mInterstitialAd.show(MainActivity.this);
                    }else {
                        startActivity(new Intent(MainActivity.this, QuotesListActivity.class));
                        loadInterstitialAd();

                    }

                }

            }
        });
        mainlayoutquotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check permission
                if (!isAllPermationAllowed(getApplicationContext())) {
                    requestAllPermationPermission(MainActivity.this,PERMISSION_CODE);

                } else {
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.QUOTES;
                        setInterListener();
                        mInterstitialAd.show(MainActivity.this);
                    }else {
                        startActivity(new Intent(MainActivity.this, QuoteCategoryListActivity.class));
                        loadInterstitialAd();

                    }
                }

            }
        });
        mainlayoutquotesonpic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check permission
                if (!isAllPermationAllowed(getApplicationContext())) {
                    requestAllPermationPermission(MainActivity.this,PERMISSION_CODE);

                } else {

                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.PIC;
                        setInterListener();
                        mInterstitialAd.show(MainActivity.this);
                    }else {
                        Intent intent = new Intent(MainActivity.this, SelectBackGroundActivity.class);
                        intent.putExtra("STATUSTEXT", "");
                        startActivity(intent);
                        loadInterstitialAd();

                    }
                }
            }
        });
        mainlayoutmycreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO check permission
                if (!isAllPermationAllowed(getApplicationContext())) {
                    requestAllPermationPermission(MainActivity.this,PERMISSION_CODE);

                } else {
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.CREATION;
                        setInterListener();
                        mInterstitialAd.show(MainActivity.this);
                    }else {
                        startActivity(new Intent(MainActivity.this, MyCreationActivity.class));
                        loadInterstitialAd();

                    }
                }

            }
        });
        loadBanner(ly_adcontainer_home, ad_default_layout);


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
    private void setInterListener() {
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
                switch (selectedItem) {
                    case DISCOVER:
                        startActivity(new Intent(MainActivity.this, QuotesListActivity.class));

                        break;
                    case QUOTES:
                        startActivity(new Intent(MainActivity.this, QuoteCategoryListActivity.class));

                        break;
                    case PIC:
                        Intent intent = new Intent(MainActivity.this, SelectBackGroundActivity.class);
                        intent.putExtra("STATUSTEXT", "");
                        startActivity(intent);
                        break;
                    case CREATION:
                        startActivity(new Intent(MainActivity.this, MyCreationActivity.class));
                        break;

                }
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

    public boolean isAllPermationAllowed(Context context) {
        return ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") == 0 && ContextCompat.checkSelfPermission(context, "android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    public void requestAllPermationPermission(Activity activity, int i) {
        if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.READ_EXTERNAL_STORAGE")) {
            ActivityCompat.shouldShowRequestPermissionRationale(activity, "android.permission.WRITE_EXTERNAL_STORAGE");
        }
        ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"}, i);
    }
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
//        if (i != PERMISSION_CODE) {
//            return;
//        }
//        if (i == PERMISSION_CODE && iArr.length > 0 && iArr[0] == 0) {
////            startActivity(new Intent(this, HomeScreenActivity.class));
////            finish();
//        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") && i == this.PERMISSION_CODE) {
////            this.assessbutton.setText(getResources().getString(R.string.GOTOSETTINGS));
////            Saveboolean.savebooleandata(this, "DONT_ASK_AGAIN", true);
//        }
    }


    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();

    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();

    }

    private void loadBanner(LinearLayout adcontainer, View ad_default_layout) {
        AdView adView = new AdView(MainActivity.this);
        adView.setAdSize(AdSize.MEDIUM_RECTANGLE);
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
                ad_default_layout.setVisibility(View.INVISIBLE);

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

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}
