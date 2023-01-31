package com.ms.dob.textonphoto;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDexApplication;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.appopen.AppOpenAd;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.ms.dob.textonphoto.model.CategoryData;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class MyApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks, LifecycleObserver {
    private AppOpenAdManager appOpenAdManager;
    private Activity currentActivity;
    public static MyApplication getInstance() {
//        if (myApplication==null) {
//            myApplication = this;
//        }
        return myApplication;
    }

   public static MyApplication myApplication ;
    public  ArrayList<String> getAllgalleryimagepath() {
        if (Allgalleryimagepath==null) {
            Allgalleryimagepath = new ArrayList<>();
        }
        return Allgalleryimagepath;
    }

    public  void setAllgalleryimagepath(ArrayList<String> allgalleryimagepath) {
        Allgalleryimagepath = allgalleryimagepath;
    }

    public  ArrayList<String[]> getEngStatusArryLst() {
        if (EngStatusArryLst==null) {
            EngStatusArryLst = new ArrayList<>();
        }
        return EngStatusArryLst;
    }

    public  void setEngStatusArryLst(ArrayList<String[]> engStatusArryLst) {
        EngStatusArryLst = engStatusArryLst;
    }

    public  ArrayList<String[]> getHindiStatusArryLst() {
        if (HindiStatusArryLst==null) {
            HindiStatusArryLst = new ArrayList<>();
        }
        return HindiStatusArryLst;
    }

    public  void setHindiStatusArryLst(ArrayList<String[]> hindiStatusArryLst1) {
        HindiStatusArryLst = hindiStatusArryLst1;
    }

    public  ArrayList<String> getAddEmojiarrylist() {
        if (addEmojiarrylist==null) {
            addEmojiarrylist = new ArrayList<>();
        }
        return addEmojiarrylist;
    }

    public  void setAddEmojiarrylist(ArrayList<String> addEmojiarrylist1) {
        addEmojiarrylist = addEmojiarrylist1;
    }

    public  ArrayList<String> getBackgroundarrylist() {
        if (backgroundarrylist==null) {
            backgroundarrylist = new ArrayList<>();
        }
        return backgroundarrylist;
    }

    public  void setBackgroundarrylist(ArrayList<String> backgroundarrylist1) {
        backgroundarrylist = backgroundarrylist1;
    }

    public  ArrayList<CategoryData> getCategoryDataArrayList() {
        if (categoryDataArrayList==null) {
            categoryDataArrayList = new ArrayList<>();
        }
        return categoryDataArrayList;
    }

    public  void setCategoryDataArrayList(ArrayList<CategoryData> categoryDataArrayList1) {
        categoryDataArrayList = categoryDataArrayList1;
    }

//    public  ArrayList<String[]> getCreatedimagepath() {
//        if (createdimagepath==null) {
//            createdimagepath = new ArrayList<>();
//        }
//        return createdimagepath;
//    }

//    public  void setCreatedimagepath(ArrayList<String[]> createdimagepath1) {
//        createdimagepath = createdimagepath1;
//    }

    public  ArrayList<QuotesFrameDetails> getQuotesframdetails() {
        if (quotesframdetails==null) {
            quotesframdetails = new ArrayList<>();
        }
        return quotesframdetails;
    }

    public  void setQuotesframdetails(ArrayList<QuotesFrameDetails> quotesframdetails1) {
        quotesframdetails = quotesframdetails1;
    }

//    public  ArrayList<String[]> getStatusimagepath() {
//        if (statusimagepath==null) {
//            statusimagepath = new ArrayList<>();
//        }
//        return statusimagepath;
//    }
//
//    public  void setStatusimagepath(ArrayList<String[]> statusimagepath1) {
//        statusimagepath = statusimagepath1;
//    }

    public  ArrayList<String> Allgalleryimagepath ;
    public  ArrayList<String[]> EngStatusArryLst ;
    public  ArrayList<String[]> HindiStatusArryLst ;
    public  ArrayList<String> addEmojiarrylist ;
    public  ArrayList<String> backgroundarrylist ;
    public  ArrayList<CategoryData> categoryDataArrayList ;
//    public  ArrayList<String[]> createdimagepath ;
    public  ArrayList<QuotesFrameDetails> quotesframdetails ;
//    public  ArrayList<String[]> statusimagepath ;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        this.registerActivityLifecycleCallbacks(this);
        MobileAds.initialize(
                this,
                new OnInitializationCompleteListener() {
                    @Override
                    public void onInitializationComplete(
                            @NonNull InitializationStatus initializationStatus) {}
                });
        new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("DA0479AEE77C97740BBEAF1644E8EEED"));

        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
        appOpenAdManager = new AppOpenAdManager();
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    protected void onMoveToForeground() {
        // Show the ad (if available) when the app moves to foreground.
        appOpenAdManager.showAdIfAvailable(currentActivity);
    }

    /** ActivityLifecycleCallback methods. */
    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {}

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        // An ad activity is started when an ad is showing, which could be AdActivity class from Google
        // SDK or another activity class implemented by a third party mediation partner. Updating the
        // currentActivity only when an ad is not showing will ensure it is not an ad activity, but the
        // one that shows the ad.
        if (!appOpenAdManager.isShowingAd) {
            currentActivity = activity;
        }
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {}

    @Override
    public void onActivityPaused(@NonNull Activity activity) {}

    @Override
    public void onActivityStopped(@NonNull Activity activity) {}

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {}

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {}

    /**
     * Shows an app open ad.
     *
     * @param activity the activity that shows the app open ad
     * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
     */
    public void showAdIfAvailable(
            @NonNull Activity activity,
            @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
        // We wrap the showAdIfAvailable to enforce that other classes only interact with MyApplication
        // class.
        appOpenAdManager.showAdIfAvailable(activity, onShowAdCompleteListener);
    }

    /**
     * Interface definition for a callback to be invoked when an app open ad is complete
     * (i.e. dismissed or fails to show).
     */
    public interface OnShowAdCompleteListener {
        void onShowAdComplete();
    }

    /** Inner class that loads and shows app open ads. */
    private class AppOpenAdManager {

        private static final String LOG_TAG = "AppOpenAdManager";
//        private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/3419835294";

        private AppOpenAd appOpenAd = null;
        private boolean isLoadingAd = false;
        private boolean isShowingAd = false;

        /** Keep track of the time an app open ad is loaded to ensure you don't show an expired ad. */
        private long loadTime = 0;

        /** Constructor. */
        public AppOpenAdManager() {}

        /**
         * Load an ad.
         *
         * @param context the context of the activity that loads the ad
         */
        private void loadAd(Context context) {
            // Do not load ad if there is an unused ad or one is already loading.
            if (isLoadingAd || isAdAvailable()) {
                return;
            }

            isLoadingAd = true;
            AdRequest request = new AdRequest.Builder().build();
            AppOpenAd.load(
                    context,
                    getResources().getString(R.string.app_open_id),
                    request,
                    AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT,
                    new AppOpenAd.AppOpenAdLoadCallback() {
                        /**
                         * Called when an app open ad has loaded.
                         *
                         * @param ad the loaded app open ad.
                         */
                        @Override
                        public void onAdLoaded(AppOpenAd ad) {
                            appOpenAd = ad;
                            isLoadingAd = false;
                            loadTime = (new Date()).getTime();

                            Log.d(LOG_TAG, "onAdLoaded.");
                            //Toast.makeText(context, "onAdLoaded", Toast.LENGTH_SHORT).show();
                        }

                        /**
                         * Called when an app open ad has failed to load.
                         *
                         * @param loadAdError the error.
                         */
                        @Override
                        public void onAdFailedToLoad(LoadAdError loadAdError) {
                            isLoadingAd = false;
                            Log.d(LOG_TAG, "onAdFailedToLoad: " + loadAdError.getMessage());
//                            Toast.makeText(context, "onAdFailedToLoad", Toast.LENGTH_SHORT).show();
                        }
                    });
        }

        /** Check if ad was loaded more than n hours ago. */
        private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
            long dateDifference = (new Date()).getTime() - loadTime;
            long numMilliSecondsPerHour = 3600000;
            return (dateDifference < (numMilliSecondsPerHour * numHours));
        }

        /** Check if ad exists and can be shown. */
        private boolean isAdAvailable() {
            // Ad references in the app open beta will time out after four hours, but this time limit
            // may change in future beta versions. For details, see:
            // https://support.google.com/admob/answer/9341964?hl=en
            return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
        }

        /**
         * Show the ad if one isn't already showing.
         *
         * @param activity the activity that shows the app open ad
         */
        private void showAdIfAvailable(@NonNull final Activity activity) {
            showAdIfAvailable(
                    activity,
                    new OnShowAdCompleteListener() {
                        @Override
                        public void onShowAdComplete() {
                            // Empty because the user will go back to the activity that shows the ad.
                        }
                    });
        }

        /**
         * Show the ad if one isn't already showing.
         *
         * @param activity the activity that shows the app open ad
         * @param onShowAdCompleteListener the listener to be notified when an app open ad is complete
         */
        private void showAdIfAvailable(
                @NonNull final Activity activity,
                @NonNull OnShowAdCompleteListener onShowAdCompleteListener) {
            // If the app open ad is already showing, do not show the ad again.
            if (isShowingAd) {
                Log.d(LOG_TAG, "The app open ad is already showing.");
                return;
            }

            // If the app open ad is not available yet, invoke the callback then load the ad.
            if (!isAdAvailable()) {
                Log.d(LOG_TAG, "The app open ad is not ready yet.");
                onShowAdCompleteListener.onShowAdComplete();
                loadAd(activity);
                return;
            }

            Log.d(LOG_TAG, "Will show ad.");

            appOpenAd.setFullScreenContentCallback(
                    new FullScreenContentCallback() {
                        /** Called when full screen content is dismissed. */
                        @Override
                        public void onAdDismissedFullScreenContent() {
                            // Set the reference to null so isAdAvailable() returns false.
                            appOpenAd = null;
                            isShowingAd = false;

                            Log.d(LOG_TAG, "onAdDismissedFullScreenContent.");
//                            Toast.makeText(activity, "onAdDismissedFullScreenContent", Toast.LENGTH_SHORT).show();

                            onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        /** Called when fullscreen content failed to show. */
                        @Override
                        public void onAdFailedToShowFullScreenContent(AdError adError) {
                            appOpenAd = null;
                            isShowingAd = false;

                            Log.d(LOG_TAG, "onAdFailedToShowFullScreenContent: " + adError.getMessage());
//                            Toast.makeText(activity, "onAdFailedToShowFullScreenContent", Toast.LENGTH_SHORT)
//                                    .show();

                            onShowAdCompleteListener.onShowAdComplete();
                            loadAd(activity);
                        }

                        /** Called when fullscreen content is shown. */
                        @Override
                        public void onAdShowedFullScreenContent() {
                            Log.d(LOG_TAG, "onAdShowedFullScreenContent.");
//                            Toast.makeText(activity, "onAdShowedFullScreenContent", Toast.LENGTH_SHORT).show();
                        }
                    });

            isShowingAd = true;
            appOpenAd.show(activity);
        }
    }
}
