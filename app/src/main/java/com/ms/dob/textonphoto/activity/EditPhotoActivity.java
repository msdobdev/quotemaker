package com.ms.dob.textonphoto.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.listener.DoubleClickListener;
import com.ms.dob.textonphoto.edit.EditDegineEightIdAndSize;
import com.ms.dob.textonphoto.edit.EditDegineFiveIDAndSize;
import com.ms.dob.textonphoto.edit.EditDegineFourIDAndSize;
import com.ms.dob.textonphoto.edit.EditDegineNineIdAndSize;
import com.ms.dob.textonphoto.edit.EditDegineOneIdAndSize;
import com.ms.dob.textonphoto.edit.EditDegineSevenIdAndSize;
import com.ms.dob.textonphoto.edit.EditDegineSixIDAndSize;
import com.ms.dob.textonphoto.edit.EditDegineTenIdAndSize;
import com.ms.dob.textonphoto.edit.EditDegineThreeIDAndSize;
import com.ms.dob.textonphoto.edit.EditDegineTwoIDAndSize;
import com.bumptech.glide.Glide;

import com.ms.dob.textonphoto.adapter.DesigneEditStatusCategoryAdapter;
import com.ms.dob.textonphoto.adapter.DesigneEditingOptionAdapter;
import com.ms.dob.textonphoto.adapter.EditColorListAdapter;
import com.ms.dob.textonphoto.adapter.EditFontAdapter;
import com.ms.dob.textonphoto.dialog.EditQuoetsDialog;
import com.ms.dob.textonphoto.dialog.InputTextDialogDegine;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.utility.FolderUtility;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.utility.MultiColorEffectDetails;
import com.ms.dob.textonphoto.utility.SaveImage;
import com.ms.dob.textonphoto.asynctask.SaveStickerAsyncTask;

//
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.io.File;
import java.util.Random;

import id.zelory.compressor.Compressor;
import jp.wasabeef.blurry.Blurry;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class EditPhotoActivity extends AppCompatActivity implements DesigneEditingOptionAdapter.DesignClickListener, EditColorListAdapter.EditClickListener, EditFontAdapter.EditFontClickListener, DesigneEditStatusCategoryAdapter.DesignEditClickListener {
    private static final String TAG = "EditPhotoActivity";
    public static View view;
    EditDegineOneIdAndSize allDegineLayoutSize = new EditDegineOneIdAndSize();
    ImageView backgroudimageview;
    EditColorListAdapter colorListAdpter;
    EditDegineEightIdAndSize editDegineEightIdAndSize = new EditDegineEightIdAndSize();
    EditDegineFiveIDAndSize editDegineFiveIDAndSize = new EditDegineFiveIDAndSize();
    EditDegineFourIDAndSize editDegineFourIDAndSize = new EditDegineFourIDAndSize();
//    EditDegineID editDegineID = new EditDegineID();
    EditDegineNineIdAndSize editDegineNineIdAndSize = new EditDegineNineIdAndSize();
    EditDegineSevenIdAndSize editDegineSevenIdAndSize = new EditDegineSevenIdAndSize();
    EditDegineSixIDAndSize editDegineSixIDAndSize = new EditDegineSixIDAndSize();
    EditDegineTenIdAndSize editDegineTenIdAndSize = new EditDegineTenIdAndSize();
    EditDegineThreeIDAndSize editDegineThreeIDAndSize = new EditDegineThreeIDAndSize();
    EditDegineTwoIDAndSize editDegineTwoIDAndSize = new EditDegineTwoIDAndSize();
    int fid;
    File file;
    String frameid;
//    GestureDetector gestureDetector;
    ImageView profileim;
//    SetTextColorFontSize setTextColorFontSize = new SetTextColorFontSize();
//    String text;
static IndicatorSeekBar indicatorSeekBar = null;
    private   boolean isseeking = false;
    private   ImageView resetbutton;
    View Edit_Option_View;
    ImageView backbutton;
    RelativeLayout bottomeditlayout;
    RecyclerView colorRecylview;
    View color_View;
    ImageView cutbutton;
    ImageView cutbutton_Size;
    ImageView cutbutton_font;
    ImageView cutbutton_quotes;
    ImageView donebutton;
    ImageView donebutton_Size;
    ImageView donebutton_font;
    ImageView donebutton_quets;
    RecyclerView editRecylview;
//    EditingLayoutId editingLayoutId;
    RecyclerView fontRecylview;
    View font_View;
    TextView savebtn;
    SaveImage saveImage;
    View status_View;
    RecyclerView stausRecylview;
    View textsize_View;
    TextView titlename;
    RelativeLayout upderlayout;

    static MultiColorEffectDetails multiColorEffectDetails;
    private InterstitialAd mInterstitialAd;
    private Clicked_item selectedItem;

    private enum Clicked_item {
         SAVE,CANCEL
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_edit_photo);
        getWindow().setSoftInputMode(32);

        multiColorEffectDetails = new MultiColorEffectDetails();
        this.saveImage = new SaveImage();

        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_editphoto);
        this.frameid = getIntent().getExtras().getString("FRAMEID");
        this.fid = Integer.parseInt(this.frameid);
        loadInterstitialAd();
//        this.saveImage = new SaveImage();
        this.upderlayout = (RelativeLayout) findViewById(R.id.uperimageeditlayout);
        this.bottomeditlayout = (RelativeLayout) findViewById(R.id.bottomeditoptionlayout);
        this.Edit_Option_View = findViewById(R.id.includeOptionlayout);
        this.backbutton = (ImageView) findViewById(R.id.backbutton);
        resetbutton = (ImageView) findViewById(R.id.resetedit);
        this.editRecylview = (RecyclerView) this.Edit_Option_View.findViewById(R.id.recycler_view_option);
        this.savebtn = (TextView) this.Edit_Option_View.findViewById(R.id.donebutton);
        this.Edit_Option_View.setVisibility(0);
        clickSavebutton();
        SetLayoutSize();
        ColorLayoutID();
        GetTextSizeId();
        GetFontId();
        GetStatusID();
        Set_Degine_layout();
        EditingAdpterCall();
        getTextviewClick();
        resetbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = EditPhotoActivity.this.getIntent();
                EditPhotoActivity.this.finish();
                EditPhotoActivity.this.startActivity(intent);
            }
        });
        float convertPixelsToDp = ImageSizeUtility.convertPixelsToDp(SetTextColorFontSize.getInstance().getTextView().getTextSize(), this);
        isseeking = false;
        indicatorSeekBar.setProgress(convertPixelsToDp);
        loadBanner(ly_adcontainer, ad_default_layout);

    }
    private void loadInterstitialAd() {
        AdRequest adRequest = new AdRequest.Builder().build();

        if (mInterstitialAd == null) {
            InterstitialAd.load(this, getResources().getString(R.string.interstitial_id), adRequest,
                    new InterstitialAdLoadCallback() {
                        @Override
                        public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                            // The mInterstitialAd reference will be null until
                            // an ad is loaded.
                            mInterstitialAd = interstitialAd;
                            //                        Log.i(TAG, "onAdLoaded");
                        }

                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            // Handle the error
                            //                        Log.d(TAG, loadAdError.toString());
                            mInterstitialAd = null;
                        }
                    });
        }


    }

    private void loadBanner(LinearLayout adcontainer, View ad_default_layout) {
        AdView adView = new AdView(EditPhotoActivity.this);
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
    public  void Set_Delete_EditText(int i) {
//        selectedTextview.setText(WriteText.GetText(WriteText.weitetextview));
//        selectedTextview.setTextColor(WriteText.GetTextColor(WriteText.weitetextview));
//        selectedTextview.setTypeface(WriteText.GetTextFont(WriteText.weitetextview));
//        deletimageoptionlayout.setVisibility(8);
//        deleteoptionlaypot.setVisibility(0);
//        selectedTextview.setVisibility(0);
//        selectedImageview.setVisibility(8);
//        if (i != -1) {
//            multiColorEffectDetails.Setmulticolor_Effect(selectedTextview, i, false);
//        } else {
//            selectedTextview.getPaint().setShader((Shader) null);
//        }
    }
    public void clickSavebutton() {
        this.backbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
        this.savebtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                new SaveStickerAsyncTask(EditPhotoActivity.this, saveImage.takescreen_Degine(EditPhotoActivity.this, EditPhotoActivity.view)) {
                    public void Ontick() {
                    }

                    public void onRecived(String str) {
                        if (!str.equalsIgnoreCase("")) {
                            if (mInterstitialAd != null) {
                                selectedItem = Clicked_item.SAVE;
                                setInterListener(str);
                                mInterstitialAd.show(EditPhotoActivity.this);
                            }else {
                                Intent intent = new Intent(EditPhotoActivity.this, ShareActivity.class);
                                intent.putExtra("shared_file",str);
                                startActivity(intent);
                                finish();
                            }

                        }else {
                            Toast.makeText(EditPhotoActivity.this, "Save Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(new Uri[0]);
            }
        });
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
                switch (selectedItem) {
                    case SAVE:
                        Intent intent = new Intent(EditPhotoActivity.this, ShareActivity.class);
                        intent.putExtra("shared_file",savedFilepath);
                        startActivity(intent);
                        break;
                    case CANCEL:
                        finish();
                        break;


                }

            }

            @Override
            public void onAdFailedToShowFullScreenContent(AdError adError) {
                // Called when ad fails to show.
                Log.e(TAG, "Ad failed to show fullscreen content.");
                mInterstitialAd = null;
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

    public void SetLayoutSize() {
        double height = (double) ScreenDimension.getHeight(this);
        Double.isNaN(height);
        double convertDpToPixel = (double) ImageSizeUtility.convertDpToPixel(50.0f, this);
        Double.isNaN(convertDpToPixel);
        this.upderlayout.getLayoutParams().height = (int) ((height / 1.5d) - convertDpToPixel);
    }

    public void Hide_Unhide_EditingOp_Layout(boolean z) {
        if (z) {
            this.Edit_Option_View.setVisibility(0);
        } else {
            this.Edit_Option_View.setVisibility(8);
        }
    }

    public void ColorLayoutID() {
        this.color_View = findViewById(R.id.includecolorlayout);
        this.colorRecylview = (RecyclerView) this.color_View.findViewById(R.id.recycler_view_option);
        this.color_View.setVisibility(4);
        this.cutbutton = (ImageView) this.color_View.findViewById(R.id.btn_cut);
        this.donebutton = (ImageView) this.color_View.findViewById(R.id.okbutton);
        colorscreenButtonclick();
    }

    public void colorscreenButtonclick() {
        this.cutbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_color_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_color_Layout(false);
               Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_color_Layout(boolean z) {
        if (z) {
            this.color_View.setVisibility(0);
        } else {
            this.color_View.setVisibility(8);
        }
    }

    public void GetTextSizeId() {
        this.textsize_View = findViewById(R.id.includestextizelayout);
        this.cutbutton_Size = (ImageView) this.textsize_View.findViewById(R.id.btn_cut);
        this.donebutton_Size = (ImageView) this.textsize_View.findViewById(R.id.okbutton);
        indicatorSeekBar = (IndicatorSeekBar) this.textsize_View.findViewById(R.id.seekbarspeed);
        textSizeButtonclick();
    }

    public void textSizeButtonclick() {
        this.cutbutton_Size.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_TextSize_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebutton_Size.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_TextSize_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            public void onSeeking(SeekParams seekParams) {
                if (isseeking) {
                    SetTextColorFontSize.getInstance().getTextView().setMaxLines(Integer.MAX_VALUE);
                    SetTextColorFontSize.getInstance().getTextView().setEllipsize((TextUtils.TruncateAt) null);
                    SetTextColorFontSize.getInstance().getTextView().setTextSize((float) seekParams.progress);
                }
            }

            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
                if (isseeking) {
                    SetTextColorFontSize.getInstance().getTextView().setMaxLines(Integer.MAX_VALUE);
                    SetTextColorFontSize.getInstance().getTextView().setEllipsize((TextUtils.TruncateAt) null);
                    SetTextColorFontSize.getInstance().getTextView().setTextSize((float) indicatorSeekBar.getProgress());
                }
            }

            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
                if (isseeking) {
                    SetTextColorFontSize.getInstance().getTextView().setMaxLines(Integer.MAX_VALUE);
                    SetTextColorFontSize.getInstance().getTextView().setEllipsize((TextUtils.TruncateAt) null);
                    SetTextColorFontSize.getInstance().getTextView().setTextSize((float) indicatorSeekBar.getProgress());
                }
            }
        });
    }

    public void Hide_Unhide_TextSize_Layout(boolean z) {
        if (z) {
            this.textsize_View.setVisibility(0);
        } else {
            this.textsize_View.setVisibility(8);
        }
    }

    public void GetFontId() {
        this.font_View = findViewById(R.id.includefontlayout);
        this.fontRecylview = (RecyclerView) this.font_View.findViewById(R.id.recycler_view_option);
        this.font_View.setVisibility(4);
        this.cutbutton_font = (ImageView) this.font_View.findViewById(R.id.btn_cut);
        this.donebutton_font = (ImageView) this.font_View.findViewById(R.id.okbutton);
        Hide_Unhide_font_Layout(true);
        FontlayoutButtonclick();
    }

    public void FontlayoutButtonclick() {
        this.cutbutton_font.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_font_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebutton_font.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_font_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_font_Layout(boolean z) {
        if (z) {
            this.font_View.setVisibility(0);
        } else {
            this.font_View.setVisibility(8);
        }
    }

    public void GetStatusID() {
        this.status_View = findViewById(R.id.includestatuslayout);
        this.stausRecylview = (RecyclerView) this.status_View.findViewById(R.id.recycler_view_option);
        this.status_View.setVisibility(4);
        this.cutbutton_quotes = (ImageView) this.status_View.findViewById(R.id.btn_cut);
        this.donebutton_quets = (ImageView) this.status_View.findViewById(R.id.okbutton);
        this.titlename = (TextView) this.status_View.findViewById(R.id.tv_name);
        this.titlename.setText("Status");
        Hide_Unhide_status_Layout(true);
        StatuslayoutButtonclick();
    }

    public void StatuslayoutButtonclick() {
        this.cutbutton_quotes.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_status_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebutton_quets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_status_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_status_Layout(boolean z) {
        if (z) {
            this.status_View.setVisibility(0);
        } else {
            this.status_View.setVisibility(8);
        }
    }

    public void OnEditOptionIteamClick(int i) {
        if (i == 0) {
            InputTextDialogDegine.inputdialogcall(this, true, this.fid);
        } else if (i == 1) {
            StatusCategoryAdpter();
            Hide_Unhide_status_Layout(true);
            Hide_Unhide_color_Layout(false);
            Hide_Unhide_TextSize_Layout(false);
            Hide_Unhide_font_Layout(false);
            Hide_Unhide_EditingOp_Layout(false);
            isseeking = false;
        } else if (i == 2) {
            ColorAdpterCall();
            Hide_Unhide_status_Layout(false);
            Hide_Unhide_color_Layout(true);
            Hide_Unhide_TextSize_Layout(false);
            Hide_Unhide_font_Layout(false);
            Hide_Unhide_EditingOp_Layout(false);
            isseeking = false;
        } else if (i == 3) {
            isseeking = true;
            Hide_Unhide_status_Layout(false);
            Hide_Unhide_color_Layout(false);
            Hide_Unhide_TextSize_Layout(true);
            Hide_Unhide_font_Layout(false);
            Hide_Unhide_EditingOp_Layout(false);
        } else if (i == 4) {
            isseeking = false;
            callfontAdpter();
            Hide_Unhide_status_Layout(false);
            Hide_Unhide_color_Layout(false);
            Hide_Unhide_TextSize_Layout(false);
            Hide_Unhide_font_Layout(true);
            Hide_Unhide_EditingOp_Layout(false);
        } else if (i == 5) {
            isseeking = false;
            CropImage.startPickImageActivity(this);
        }
    }

    public void onColorItemClicked(int i) {
        SetTextColorFontSize.getInstance().getTextView().setTextColor(Color.parseColor(EditColorListAdapter.colorname[i]));
        this.colorListAdpter.clear_all_Selection();
        this.colorListAdpter.notifyDataSetChanged();
        this.colorListAdpter.toggleSelection(i);
    }

    public void onItemClicked_Font(int i) {
        AssetManager assets = getAssets();
        SetTextColorFontSize.getInstance().getTextView().setTypeface(Typeface.createFromAsset(assets, "font/" + EditFontAdapter.fontname[i]));
    }

    public void onItemClicked_StatusCategory(int i) {
        EditQuoetsDialog.QuoetsDialog(this, DesigneEditStatusCategoryAdapter.catname[i], i, this.fid);
    }

    private void Set_Degine_layout() {
        this.allDegineLayoutSize.GetDegine_OneId(this, this.fid);
        if (this.fid == 0) {
            this.allDegineLayoutSize.GetDegine_OneId(this, this.fid);
            view = this.allDegineLayoutSize.mainLayout;
        } else if (this.fid == 1) {
            this.editDegineTwoIDAndSize.GetDegineTwoID(this, this.fid);
            view = this.editDegineTwoIDAndSize.mainLayout;
        } else if (this.fid == 2) {
            this.editDegineThreeIDAndSize.GetDegineThreeID(this, this.fid);
            view = this.editDegineThreeIDAndSize.mainLayout;
        } else if (this.fid == 3) {
            this.editDegineFourIDAndSize.GetDegineFourID(this, this.fid);
            view = this.editDegineFourIDAndSize.mainLayout;
        } else if (this.fid == 4) {
            this.editDegineFiveIDAndSize.GetDegineFiveID(this, this.fid);
            view = this.editDegineFiveIDAndSize.mainLayout;
        } else if (this.fid == 5) {
            this.editDegineSixIDAndSize.GetDegineSixID(this, this.fid);
            view = this.editDegineSixIDAndSize.mainLayout;
        } else if (this.fid == 6) {
            this.editDegineSevenIdAndSize.GetDegineSevenID(this, this.fid);
            view = this.editDegineSevenIdAndSize.mainLayout;
        } else if (this.fid == 7) {
            this.editDegineEightIdAndSize.GetDegineEight(this, this.fid);
            view = this.editDegineEightIdAndSize.mainLayout;
        } else if (this.fid == 8) {
            this.editDegineNineIdAndSize.GetDegineNine(this, this.fid);
            view = this.editDegineNineIdAndSize.mainLayout;
        } else if (this.fid == 9) {
            this.editDegineTenIdAndSize.GetDegineTen(this, this.fid);
            view = this.editDegineTenIdAndSize.mainLayout;
        }
    }

    private void changeProfileimage_Bacground(Uri uri) {
        this.profileim = SetTextColorFontSize.getInstance().getProfileimageview();
        this.backgroudimageview = SetTextColorFontSize.getInstance().getBackgroudimageview();
        if (this.profileim != null) {
            Glide.with((FragmentActivity) this).load(new File(uri.getPath())).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileim);
        }
        if (this.backgroudimageview != null) {
            this.file = new File(uri.getPath());
            try {
                new Compressor.Builder(this)
                        //                        .setMaxWidth(640)
                        //                        .setMaxHeight(480)
                        .setQuality(50)
                        //                        .setCompressFormat(Bitmap.CompressFormat.WEBP)
                        .setDestinationDirectoryPath(FolderUtility.ResizeimageFolder().getAbsolutePath())
                        .build()
                        .compressToFileAsObservable(this.file)
                        .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<File>() {
                            @Override
                            public void call(File file) {
//                                    Log.e(TAG, "call: file+++++++++"+file.getAbsolutePath() );
                                Blurry.with(EditPhotoActivity.this).radius(25).async().animate().from(BitmapFactory.decodeFile(file.getAbsolutePath())).into(EditPhotoActivity.this.backgroudimageview);

//                                    compressedImage = file;
                            }
                        }, new Action1<Throwable>() {
                            @Override
                            public void call(Throwable throwable) {

                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
//            Tiny.FileCompressOptions fileCompressOptions = new Tiny.FileCompressOptions();
//            fileCompressOptions.quality = 50;
//            fileCompressOptions.compressDirectory = FolderUtility.ResizeimageFolder().getAbsolutePath();
//            Tiny.getInstance().source(this.file).asFile().withOptions(fileCompressOptions).compress((FileCallback) new FileCallback() {
//                public void callback(boolean z, String str, Throwable th) {
//                    if (z) {
//                        Blurry.with(EditPhotoActivity.this).radius(25).async().animate().from(BitmapFactory.decodeFile(str)).into(EditPhotoActivity.this.backgroudimageview);
//                    }
//                }
//            });
        }
    }

    private void getTextviewClick() {
        SetTextColorFontSize.getInstance().getTextView().setOnClickListener(new DoubleClickListener() {
            public void onSingleClick(View view) {
            }

            public void onDoubleClick(View view) {
                InputTextDialogDegine.inputdialogcall(EditPhotoActivity.this, true, EditPhotoActivity.this.fid);
            }
        });
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 200) {
            if (i2 == -1) {
                CropImage.activity(CropImage.getPickImageResultUri(this, intent)).setGuidelines(CropImageView.Guidelines.ON).setActivityTitle(getString(R.string.app_name)).setCropShape(CropImageView.CropShape.RECTANGLE).setCropMenuCropButtonTitle(getResources().getString(R.string.crop_image_menu_crop)).setAllowFlipping(false).setAllowFlipping(false).setAllowRotation(true).start(this);
            }
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
//                HomeScreenActivity.full_add(this);
                changeProfileimage_Bacground(activityResult.getUri());
            }
        }
    }

    public void onBackPressed() {
//        Savealertsialog savealertsialog = new Savealertsialog();
        if (Edit_Option_View.isShown()) {
            ShowSaveAlertOnBackPress(this, new SaveImage().takescreen_Degine(this, view));
        } else {
            Hide_Unhide_EditingOp_Layout(true);
        }
    }

    public void ShowSaveAlertOnBackPress(final Activity activity, final Bitmap bitmap) {
//        this.saveImage = new SaveImage();
        new AlertDialog.Builder(activity).setMessage((CharSequence) "Do want to save ?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                new SaveStickerAsyncTask(activity, bitmap) {
                    public void Ontick() {
                    }

                    public void onRecived(String str) {
                        if (!str.equalsIgnoreCase("")) {
                            if (mInterstitialAd != null) {
                                selectedItem = Clicked_item.SAVE;
                                setInterListener(str);
                                mInterstitialAd.show(EditPhotoActivity.this);
                            }else {
                                Intent intent = new Intent(EditPhotoActivity.this, ShareActivity.class);
                                intent.putExtra("shared_file",str);
                                startActivity(intent);
                                finish();

                            }
//                            HomeScreenActivity.full_add(activity);
                        }
                    }
                }.execute(new Uri[0]);
            }
        }).setNegativeButton((CharSequence) "No", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
//                if (!z) {
//                    HomeScreenActivity.full_add(activity);
//                }
                if (mInterstitialAd != null) {
                    selectedItem = Clicked_item.CANCEL;
                    setInterListener(null);
                    mInterstitialAd.show(EditPhotoActivity.this);
                }else {
                    activity.finish();
                }
            }
        }).show();
    }

    private void EditingAdpterCall() {
        Hide_Unhide_EditingOp_Layout(true);
        editRecylview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        editRecylview.setAdapter(new DesigneEditingOptionAdapter(this, this));
    }

    private void ColorAdpterCall() {
        colorRecylview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.colorListAdpter = new EditColorListAdapter(this, this);
        colorRecylview.setAdapter(this.colorListAdpter);
        this.colorListAdpter.toggleSelection(0);
    }

    public void callfontAdpter() {
        fontRecylview.setAdapter(new EditFontAdapter(this, this));
        fontRecylview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void StatusCategoryAdpter() {
        stausRecylview.setAdapter(new DesigneEditStatusCategoryAdapter(this, this));
        stausRecylview.setLayoutManager(new GridLayoutManager(this, 3));
    }
}
