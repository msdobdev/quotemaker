package com.ms.dob.textonphoto.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.text.ClipboardManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.ms.dob.textonphoto.adapter.DesigneAdapter;
import com.ms.dob.textonphoto.listener.RecyclerItemClickListener;
import com.ms.dob.textonphoto.utility.ReadJsonFromAssect;
import com.ms.dob.textonphoto.utility.FolderUtility;
import com.ms.dob.textonphoto.asynctask.SaveStickerAsyncTask;

import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import id.zelory.compressor.Compressor;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class QuotesListActivity extends AppCompatActivity {

    private static final String TAG = "QuotesListActivity";
    private final int NUMBER_OF_FREAME = 10;
    public static int[] poss;
    boolean IsRefresh = false;
    ImageView backbtn;
    DesigneAdapter designeAdapter;
    View progress_layout;
    private InterstitialAd mInterstitialAd;

    RecyclerView recyclerView;
    ImageView refreshbtn;
    public SwipeRefreshLayout swipeContainer;
    private Clicked_item selectedItem;
    private boolean isShown = true;

    private enum Clicked_item {
        EDIT, SHARE, COPY, SAVE
    }


    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_quoteslist);
        getWindow().setSoftInputMode(32);
        this.progress_layout = findViewById(R.id.loader_view);
        this.progress_layout.setVisibility(0);
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_design);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.swipeContainer = (SwipeRefreshLayout) findViewById(R.id.refreshlistlayout);
        this.refreshbtn = (ImageView) findViewById(R.id.refreshbtn);
        this.refreshbtn.setVisibility(0);
        this.swipeContainer.getLayoutParams().width = ScreenDimension.getWeight(this) - ((ScreenDimension.getWeight(this) / 100) * 6);
        Bundle extras = getIntent().getExtras();
        if (extras != null ? extras.getBoolean("ISLOAD") : true) {
            loadimageinbackground(this);
        } else {
            CategotyAdpterCall();
        }
        loadInterstitialAd();
        this.swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                if (!QuotesListActivity.this.IsRefresh) {
                    QuotesListActivity.this.IsRefresh = true;
                    QuotesListActivity.this.loadimageinbackground(QuotesListActivity.this);
                    return;
                }
                QuotesListActivity.this.swipeContainer.setRefreshing(false);
            }
        });
        this.recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, this.recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            public void onItemLongClick(View view, int i) {
            }

            public void onItemClick(View view, int i) {
                if (i < NUMBER_OF_FREAME) {
                    showoptionDialog(QuotesListActivity.this, i, view, false, "");
                }
            }
        }));
        this.refreshbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!QuotesListActivity.this.IsRefresh) {
                    QuotesListActivity.this.swipeContainer.setRefreshing(true);
                    QuotesListActivity.this.IsRefresh = true;
                    QuotesListActivity.this.loadimageinbackground(QuotesListActivity.this);
                    return;
                }
                QuotesListActivity.this.swipeContainer.setRefreshing(false);
            }
        });
        this.backbtn = (ImageView) findViewById(R.id.backbutton);
        this.backbtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QuotesListActivity.this.onBackPressed();
            }
        });
        loadBanner(ly_adcontainer, ad_default_layout);

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
        AdView adView = new AdView(QuotesListActivity.this);
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

    public void CategotyAdpterCall() {
        poss = RandomizePossition();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(false);
                progress_layout.setVisibility(8);
                IsRefresh = false;
//        this.adlayout.setVisibility(0);
                designeAdapter = new DesigneAdapter(QuotesListActivity.this, MyApplication.getInstance().getQuotesframdetails());
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(designeAdapter);
            }
        });

    }

    /* access modifiers changed from: private */
    public void loadimageinbackground(final Activity activity) {
        new AllImageVideoFileAsyncTask(activity) {
            public void Ontick() {
            }

            public void onRecived(ArrayList<String> arrayList) {
                ResizeimageFile(activity, arrayList);
            }
        }.execute(new Uri[0]);
    }

    public abstract class AllImageVideoFileAsyncTask extends AsyncTask<Uri, Void, ArrayList<String>> {
        Activity mconContext;

        public abstract void Ontick();

        public abstract void onRecived(ArrayList<String> arrayList);

        public AllImageVideoFileAsyncTask(Activity activity) {
            this.mconContext = activity;
        }

        /* access modifiers changed from: protected */
        public ArrayList<String> doInBackground(Uri... uriArr) {
            return getAllShownImagesPath(this.mconContext);
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            Ontick();
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(ArrayList<String> arrayList) {
            onRecived(arrayList);
        }
    }

    public ArrayList<String> getAllShownImagesPath(Activity activity) {
        ArrayList<String> arrayList = new ArrayList<>();
        Cursor query = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new String[]{"_data", "mime_type"}, "mime_type!=? and mime_type!=?", new String[]{"image/gif", "image/webp"}, "date_modified desc");
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("_data"));
            if (!string.contains("cache") && !string.contains("Apps Funtime Status")) {
                arrayList.add(string);
            }
        }
        return arrayList;
    }

    public String ResizeimageFile(final Activity activity, ArrayList<String> arrayList) {
        MyApplication.getInstance().setAllgalleryimagepath(new ArrayList<>());
        FolderUtility.DeleteFolder(FolderUtility.ResizeimageFolder());
        for (int i = 0; i <= NUMBER_OF_FREAME - 1; i++) {
            int size = arrayList.size();
            if (size != 0) {
                try {
                    new Compressor.Builder(this)
                            //                        .setMaxWidth(640)
                            //                        .setMaxHeight(480)
                            .setQuality(50)
                            //                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                            .setDestinationDirectoryPath(FolderUtility.ResizeimageFolder().getAbsolutePath())
                            .build()
                            .compressToFileAsObservable(new File(arrayList.get(new Random().nextInt(((size - 1) - 0) + 1) + 0)))
                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Action1<File>() {
                                @Override
                                public void call(File file) {
//                                    Log.e(TAG, "call: file+++++++++"+file.getAbsolutePath() );
                                        MyApplication.getInstance().getAllgalleryimagepath().add(file.getAbsolutePath());

                                    if (MyApplication.getInstance().getAllgalleryimagepath().size() == NUMBER_OF_FREAME) {
                                        ReadJsonFromAssect.GetQuotesListFromAssect(activity);

                                        CategotyAdpterCall();
                                    }
//                                    compressedImage = file;
                                }
                            }, new Action1<Throwable>() {
                                @Override
                                public void call(Throwable throwable) {
                                    Log.e(TAG, "call: file+++++++++"+throwable.getMessage() );
//                                    showError(throwable.getMessage());
                                    MyApplication.getInstance().getAllgalleryimagepath().add("");

                                    if (MyApplication.getInstance().getAllgalleryimagepath().size() == NUMBER_OF_FREAME) {
                                        ReadJsonFromAssect.GetQuotesListFromAssect(activity);
                                        CategotyAdpterCall();
                                    }
                                }
                            });
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Tiny.FileCompressOptions fileCompressOptions = new Tiny.FileCompressOptions();
//                fileCompressOptions.quality = 50;
//                fileCompressOptions.compressDirectory = FolderUtility.ResizeimageFolder().getAbsolutePath();
//                Tiny.getInstance().source(arrayList.get(new Random().nextInt(((size - 1) - 0) + 1) + 0)).asFile().withOptions(fileCompressOptions).compress((FileCallback) new FileCallback() {
//                    public void callback(boolean z, String str, Throwable th) {
//                        if (z) {
//                            MyApplication.getInstance().getAllgalleryimagepath().add(str);
//                        } else {
//                            MyApplication.getInstance().getAllgalleryimagepath().add("");
//                        }
//                        if (MyApplication.getInstance().getAllgalleryimagepath().size() == NUMBER_OF_FREAME) {
//                            ReadJsonFromAssect.GetQuotesListFromAssect(activity);
//                            CategotyAdpterCall();
//                        }
//                    }
//                });
            } else {
                MyApplication.getInstance().getAllgalleryimagepath().add("");
                if (MyApplication.getInstance().getAllgalleryimagepath().size() == NUMBER_OF_FREAME) {
                    ReadJsonFromAssect.GetQuotesListFromAssect(activity);
                    CategotyAdpterCall();
                }
            }
        }
        return "OK";
    }


    public static int[] RandomizePossition() {
        int[] iArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] iArr2 = new int[iArr.length];
        System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
        Random random = new Random();
        for (int i = 0; i < iArr2.length; i++) {
            int nextInt = random.nextInt(iArr2.length);
            int i2 = iArr2[i];
            iArr2[i] = iArr2[nextInt];
            iArr2[nextInt] = i2;
        }
        return iArr2;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    CustomOptionDialog customOptionDialog;

    public void showoptionDialog(Activity activity2, int i, View view, boolean z, String str) {
        customOptionDialog = new CustomOptionDialog(activity2, i, view, z, str);
        customOptionDialog.getWindow().requestFeature(1);
        customOptionDialog.show();
        customOptionDialog.setCanceledOnTouchOutside(false);
        customOptionDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
    }

    public class CustomOptionDialog extends Dialog {
        String Copytext;
        boolean IsAuther;
        public Activity activity;
        View copyquoteslayout;
        View editlayout;
        int possition;
        //        SaveImage saveImage = new SaveImage();
        View savelayout;
        View sharelayout;
        View viewbitmap;
        String savePath;

        public CustomOptionDialog(Activity activity2, int i, View view, boolean z, String str) {
            super(activity2);
            this.activity = activity2;
            this.viewbitmap = view;
            this.possition = i;
            this.IsAuther = z;
            this.Copytext = str;
        }

        /* access modifiers changed from: protected */
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            setContentView(R.layout.moreoptiondialog);
            this.editlayout = findViewById(R.id.ly_edit);
            this.savelayout = findViewById(R.id.ly_save);
            this.sharelayout = findViewById(R.id.ly_share);
            this.copyquoteslayout = findViewById(R.id.ly_copytext);
            if (this.IsAuther) {
                this.editlayout.setVisibility(8);
                this.copyquoteslayout.setVisibility(0);
            } else {
                this.editlayout.setVisibility(0);
                this.copyquoteslayout.setVisibility(8);
            }
            this.editlayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //TODO show interstitial ad
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.EDIT;
                        setInterListener();
                        mInterstitialAd.show(QuotesListActivity.this);
                    } else {
                        Intent intent = new Intent(CustomOptionDialog.this.activity, EditPhotoActivity.class);
                        intent.putExtra("FRAMEID", "" + QuotesListActivity.poss[CustomOptionDialog.this.possition]);
                        CustomOptionDialog.this.activity.startActivity(intent);
                    }
                    CustomOptionDialog.this.dismiss();

                }
            });
            this.copyquoteslayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    //TODO show interstitial ad
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.COPY;

                        setInterListener();
                        mInterstitialAd.show(QuotesListActivity.this);
                    } else {
                        if (Build.VERSION.SDK_INT < 11) {
                            ((ClipboardManager) CustomOptionDialog.this.activity.getSystemService("clipboard")).setText(CustomOptionDialog.this.Copytext);
                        } else {
                            ((android.content.ClipboardManager) CustomOptionDialog.this.activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label", CustomOptionDialog.this.Copytext));
                        }
                        Toast.makeText(CustomOptionDialog.this.activity, "Quotes Copy", Toast.LENGTH_SHORT).show();

                    }
                    CustomOptionDialog.this.dismiss();
                }
            });
            this.savelayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new SaveStickerAsyncTask(CustomOptionDialog.this.activity, takescreen_List(CustomOptionDialog.this.activity, CustomOptionDialog.this.viewbitmap)) {
                        public void Ontick() {
                        }

                        public void onRecived(String str) {
                            if (!str.equalsIgnoreCase("")) {
                                //TODO show interstitial ad
                                if (mInterstitialAd != null) {
                                    selectedItem = Clicked_item.SAVE;
                                    savePath = str;
                                    setInterListener();
                                    mInterstitialAd.show(QuotesListActivity.this);
                                } else {
                                    Intent intent1 = new Intent(QuotesListActivity.this, ShareActivity.class);
                                    intent1.putExtra("shared_file", str);
                                    CustomOptionDialog.this.activity.startActivity(intent1);
                                    CustomOptionDialog.this.activity.finish();
//                                    CustomOptionDialog.this.activity.startActivity(new Intent(CustomOptionDialog.this.activity, MyCreationActivity.class));
//                                    CustomOptionDialog.this.activity.finish();
                                }
//                            HomeScreenActivity.full_add(MoreOptionDialog.this.activity);
                            }
                        }
                    }.execute(new Uri[0]);
                    CustomOptionDialog.this.dismiss();
                }
            });
            this.sharelayout.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    selectedItem = Clicked_item.SHARE;
                    if (mInterstitialAd != null) {
                        selectedItem = Clicked_item.SAVE;
                        setInterListener();
                        mInterstitialAd.show(QuotesListActivity.this);
                    } else {
                        Bitmap takescreen_List = takescreen_List(CustomOptionDialog.this.activity, CustomOptionDialog.this.viewbitmap);
                        storeImage(takescreen_List, "" + System.currentTimeMillis(), CustomOptionDialog.this.activity, "");
                    }
                    CustomOptionDialog.this.dismiss();
                }
            });
        }


    }

    public Bitmap takescreen_List(Activity activity, View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(view.getDrawingCache(), view.getWidth(), view.getHeight(), true);
        view.setDrawingCacheEnabled(false);
        return createScaledBitmap;
    }

    public String storeImage(Bitmap bitmap, String str, Activity activity, String str2) {
        File ShareFolder = FolderUtility.ShareFolder();
        if (!ShareFolder.exists()) {
            ShareFolder.mkdirs();
        }
        try {
            String str3 = ShareFolder.toString() + File.separator + str + ".png";
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(str3));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            new Intent();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", "" + str2);
            intent.setType("image/png");
            intent.putExtra("android.intent.extra.STREAM", Uri.parse(str3));
            intent.setFlags(268435456);
            activity.startActivity(intent);
            return str3;
        } catch (FileNotFoundException e) {
            Log.w("TAG", "Error saving image file: " + e.getMessage());
            return "";
        } catch (IOException e2) {
            Log.w("TAG", "Error saving image file: " + e2.getMessage());
            return "";
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
                    case COPY:
                        if (Build.VERSION.SDK_INT < 11) {
                            ((ClipboardManager) getSystemService("clipboard")).setText(customOptionDialog.Copytext);
                        } else {
                            ((android.content.ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("text label", customOptionDialog.Copytext));
                        }
                        Toast.makeText(QuotesListActivity.this, "Quotes Copy", Toast.LENGTH_SHORT).show();
                        break;
                    case EDIT:
                        Intent intent = new Intent(QuotesListActivity.this, EditPhotoActivity.class);
                        intent.putExtra("FRAMEID", "" + QuotesListActivity.poss[customOptionDialog.possition]);
                        startActivity(intent);
                        break;

                    case SAVE:
                        Intent intent1 = new Intent(QuotesListActivity.this, ShareActivity.class);
                        intent1.putExtra("shared_file", customOptionDialog.savePath);
                        startActivity(intent1);
//                        startActivity(new Intent(QuotesListActivity.this, MyCreationActivity.class));
                        finish();
                        break;

                    case SHARE:
                        Bitmap takescreen_List = takescreen_List(QuotesListActivity.this, customOptionDialog.viewbitmap);
                        storeImage(takescreen_List, "" + System.currentTimeMillis(), QuotesListActivity.this, "");
                        break;

                }
                //TODO load ad again
//                loadInterstitialAd();
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
