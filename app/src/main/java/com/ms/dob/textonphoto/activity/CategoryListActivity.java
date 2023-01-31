package com.ms.dob.textonphoto.activity;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.ms.dob.textonphoto.fragment.EnglishStatusFragement;
import com.ms.dob.textonphoto.fragment.HindiStatusFragement;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {
    ImageView backbutton;
    int possition;
    CategoryListActivity categoryListActivity;
    private TabLayout tabLayout;
    TextView tv_title;
//    TextView tittlename;
    private ViewPager viewPager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_category_list);
        this.categoryListActivity = this;
        this.possition = getIntent().getExtras().getInt("pos");
        this.viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(this.viewPager);
        this.tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tabLayout.setupWithViewPager(this.viewPager);
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_catlist);
        actionbar();
        loadNative(ly_adcontainer, ad_default_layout);
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

    private void setupViewPager(ViewPager viewPager2) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(new EnglishStatusFragement(this.possition), "English");
        viewPagerAdapter.addFragment(new HindiStatusFragement(this.possition), "Hindi");
        viewPager2.setAdapter(viewPagerAdapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            return this.mFragmentList.get(i);
        }

        public int getCount() {
            return this.mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String str) {
            this.mFragmentList.add(fragment);
            this.mFragmentTitleList.add(str);
        }

        public CharSequence getPageTitle(int i) {
            return this.mFragmentTitleList.get(i);
        }
    }

    private void actionbar() {
        this.backbutton = (ImageView) findViewById(R.id.backbutton);
        this.tv_title = (TextView) findViewById(R.id.tv_title);
        this.tv_title.setText(MyApplication.getInstance().getCategoryDataArrayList().get(this.possition).getCategoryName());
        this.backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CategoryListActivity.this.onBackPressed();
            }
        });
    }

    public void setNativeAd(NativeAd nativeAd,NativeAdView nativeAdView ) {
//        this.nativeAd = nativeAd;
//         NativeAdView nativeAdView = (NativeAdView)getLayoutInflater().inflate(layoutId, null);
       TextView  primaryView = (TextView) nativeAdView.findViewById(R.id.primary);
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
