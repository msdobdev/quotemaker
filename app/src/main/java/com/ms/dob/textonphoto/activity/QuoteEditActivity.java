package com.ms.dob.textonphoto.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
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
import com.ms.dob.textonphoto.fragment.AddImage;
import com.ms.dob.textonphoto.asynctask.LoadAssetImageAsyncTask;
import com.ms.dob.textonphoto.adapter.EditStatusCategoryAdapter;
import com.ms.dob.textonphoto.utility.FolderUtility;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import com.ms.dob.textonphoto.dialog.InputTextDialog;
import com.ms.dob.textonphoto.utility.MultiColorEffectDetails;
import com.ms.dob.textonphoto.model.Multieffectpossition;
import com.ms.dob.textonphoto.utility.SaveImage;
import com.ms.dob.textonphoto.asynctask.SaveStickerAsyncTask;
import com.ms.dob.textonphoto.model.ShadowDetails;
import com.ms.dob.textonphoto.dialog.StatusDialog;
import com.ms.dob.textonphoto.model.WriteText;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.ms.dob.textonphoto.adapter.AddImageAdapter;
import com.ms.dob.textonphoto.adapter.BackgroundAdapter;
import com.ms.dob.textonphoto.adapter.ColorListAdapter;
import com.ms.dob.textonphoto.adapter.EditingOptionAdapter;
import com.ms.dob.textonphoto.adapter.FontAdapter;
import com.ms.dob.textonphoto.adapter.MultiColorAdapter;
import com.ms.dob.textonphoto.adapter.RatioAdapter;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.ratiolayout.FixedAttribute;
import com.ms.dob.textonphoto.ratiolayout.RatioRelativeLayout;
import com.ms.dob.textonphoto.utility.GradientColorUtility;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
import com.rtugeek.android.colorseekbar.ColorSeekBar;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.warkiz.widget.IndicatorSeekBar;
import com.warkiz.widget.OnSeekChangeListener;
import com.warkiz.widget.SeekParams;

import java.io.File;
import java.util.ArrayList;
import jp.wasabeef.blurry.Blurry;

public class QuoteEditActivity extends AppCompatActivity implements EditingOptionAdapter.EditingClickListener, MultiTouchListener.OnClicklisner, ColorListAdapter.ColorClickListener, FontAdapter.FontClickListener, MultiColorAdapter.MultiColorClickListener, MultiTouchListener.OnClicklisnerImage, AddImageAdapter.AddImageClickListener, BackgroundAdapter.BackgroundClickListener, EditStatusCategoryAdapter.EditstatusClickListener, RatioAdapter.RatioClickListener {
    private static final int MAX_CLICK_DURATION = 200;
    private static final String TAG = "QuoteEditActivity";
    boolean IsSpacebg = false;
    boolean IsSpaceemoji = false;
    AddImageAdapter addImageAdapter;
    int addcount = 0;
//    AddimageLayoutId addimageLayoutId;
//    AlignmentLayoutId alignmentLayoutId;
//    BackGroundLayoutId backGroundLayoutId;
    BackgroundAdapter backgroundAdapter;
    Drawable bitmapblur;
//    BlurImageLayoutId blurImageLayoutId;
    int clickCount = 0;
//    ColorLayoutid colorLayoutid;
    ColorListAdapter colorListAdapter;
    private long duration;
//    EditingLayoutId editingLayoutId;
//    File file;
    FontAdapter fontAdapter;
//    FountLayoutId fountLayoutId;
    int gradiaintcolotpo;
    String imagepath = null;
    boolean islock = true;
//    String ismoveimage = "Yes";
//    boolean isnotifaction;
    Bitmap m_bitmap;
    MultiColorAdapter multiColorAdapter;
    MultiColorEffectDetails multiColorEffectDetails;
//    MultiColorLayoutId multiColorLayoutId;
    MultiTouchListener.OnClicklisner onClicklisner;
    MultiTouchListener.OnClicklisnerImage onClicklisnerImage;
    int ratio = -1;
//    RatioLayoutId ratioLayoutId;
    RatioAdapter ratioAdapter;
//    private long startClickTime;
    private long startTime;
    EditStatusCategoryAdapter statusAdpter;
//    StatusLayoutId statusLayoutId;
    String statustext;
//    TextSizeLayoutid textSizeLayoutid;
    public View deleteoptionlaypot;
    public View deletimageoptionlayout;
    //    private static EditingLayoutId editingLayoutId;
//    public static ProgressDialog mProgressDialog;
     MultiColorEffectDetails multiColorEffectDetails1;
    public ImageView selectedImageview;
    public TextView selectedTextview;
    View Edit_Option_View;
    ImageView backbuttonActionbar;
//    ColorLayoutid colorLayoutid1;
    ImageView deletebutton;
    ImageView editImageview;
    RecyclerView editRecylview;
    ImageView editbutton;
    //    RelativeLayout fastscrollbg;
    FrameLayout frameLayout;
//    ImageView lockunlockimageview;
    ImageView backbutton;
    TextView makegifButton;
    RatioRelativeLayout ratiolayout;
    //    View recyclerFastScroller;
    SaveImage saveImage;
    RecyclerView colorRecylview;
    View color_View;
    ImageView cutbutton;
    ImageView donebutton;
    IndicatorSeekBar indicatorSeekBar;
     Activity mactivity;
    ImageView cutbuttontext;
    ImageView donebuttontext;
//    EditingLayoutId editingLayoutId;
    View textsize_View;
//    shadowview
ColorSeekBar colorSeekBar;
    ImageView cutbuttonshadow;
    ImageView donebuttonshadow;
//    EditingLayoutId editingLayoutId;
//    Activity mActivity;
    SeekBar seekbarRadious;
    SeekBar seekbarX;
    SeekBar seekbarY;
    ShadowDetails shadowDetails;
    View shadow_View;

//    font
ImageView cutbuttonfont;
    ImageView donebuttonfont;
//    EditingLayoutId editingLayoutId;
    RecyclerView fontRecylview;
    View font_View;

//    multicolor
ImageView cutbuttonmulticolor;
    ImageView donebuttonmulticolor;
//    EditingLayoutId editingLayoutId;
    RecyclerView multicolorRecylview;
    View multicolor_View;
    TextView titlename;

//    addimage
 View deleteTextoptionlaypot;
     View deleteimage_View;
     ImageView dltimagebutton;
    //    static ImageView editimagebutton;
     TextView selectedtext;
     ImageView slectedimage;
     TextView titlenametextview;
    RecyclerView addimageRecylview;
    View adimage_View;
    ImageView cutbuttonaddimage;
    ImageView donebuttonaddimage;
    ImageView editbuttonaddimage;

    //alignment
    View Aliment_View;
    View center;
    ImageView cutbuttonalign;
    ImageView donebuttonalign;
    View left;
    View right;

    //background
    View BG_View;
    RecyclerView bgRecylview;
    ImageView cutbuttonbg;
    ImageView donebuttonbg;
    TextView titlenametextviewbg;
    //status
    ImageView cutbuttonstatus;
    ImageView donebuttonstatus;
    View status_View;
    RecyclerView stausRecylview;
    TextView titlenamestatus;
    //blurimage
     IndicatorSeekBar indicatorSeekBarblur;
    ImageView cutbuttonblur;
    ImageView donebuttonblur;
    View textsize_Viewblur;
    View view = null;

    //ratio
    ImageView cutbuttonratio;
    ImageView donebuttonratio;
    RecyclerView ratioRecylview;
    View ratio_View;
    private InterstitialAd mInterstitialAd;
    private Clicked_item selectedItem;

    private enum Clicked_item {
        SAVE,CANCEL
    }
    private void showLockintro() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_edit_quote);
        Multieffectpossition.textViewArrayListshadowId = new ArrayList<>();
        WriteText.weitetextview = null;
        AddImage.imageViewsArrylist = new ArrayList<>();
        AddImage.selectedimageview = null;
        this.IsSpacebg = false;
        this.IsSpaceemoji = false;
        this.onClicklisner = this;
        this.onClicklisnerImage = this;

        this.multiColorEffectDetails = new MultiColorEffectDetails();

        getWindow().setSoftInputMode(32);
        View ad_default_layout = findViewById(R.id.ad_default_layout);
        LinearLayout ly_adcontainer = findViewById(R.id.ly_adcontainer_editquote);
        loadInterstitialAd();

        multiColorEffectDetails1 = new MultiColorEffectDetails();
        this.saveImage = new SaveImage();
        this.Edit_Option_View = findViewById(R.id.includeOptionlayout);
        this.editRecylview = (RecyclerView) this.Edit_Option_View.findViewById(R.id.recycler_view_option);
        this.makegifButton = (TextView) this.Edit_Option_View.findViewById(R.id.donebutton);
        this.Edit_Option_View.setVisibility(0);
        this.editImageview = (ImageView) findViewById(R.id.editimage);
        this.backbuttonActionbar = (ImageView) findViewById(R.id.backbutton_edit);
        this.frameLayout = (FrameLayout) findViewById(R.id.imagetextadview);
        deleteoptionlaypot = findViewById(R.id.deleteoptionlaout);
        deletimageoptionlayout = findViewById(R.id.deletimageoptionlaout);
        this.ratiolayout = (RatioRelativeLayout) findViewById(R.id.ratiolayout);
        selectedTextview = (TextView) deleteoptionlaypot.findViewById(R.id.tv_selected);
        selectedImageview = (ImageView) deleteoptionlaypot.findViewById(R.id.iv_selected);
        this.backbutton =  findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
//        this.lockunlockimageview = (ImageView) findViewById(R.id.lockunlockbtn);
//        this.lockunlockimageview.setVisibility(8);
        this.deletebutton = (ImageView) deleteoptionlaypot.findViewById(R.id.btn_delete);
        this.editbutton = (ImageView) deleteoptionlaypot.findViewById(R.id.btn_edit);
        deleteoptionlaypot.setVisibility(8);
        deletimageoptionlayout.setVisibility(8);
        this.ButtonClickHandel();
//        this.colorLayoutid.GetColorlayoutId(this);
        this.color_View = findViewById(R.id.includecolorlayout);
        this.colorRecylview = (RecyclerView) this.color_View.findViewById(R.id.recycler_view_option);
        this.color_View.setVisibility(4);
        this.cutbutton = (ImageView) this.color_View.findViewById(R.id.btn_cut);
        this.donebutton = (ImageView) this.color_View.findViewById(R.id.okbutton);
        Hide_Unhide_color_Layout(true);
        colorscreenButtonclick();
//        this.textSizeLayoutid.GetTextSizeId(this, this.editingLayoutId);
//        this.editingLayoutId = editingLayoutId2;
        this.textsize_View = findViewById(R.id.includestextizelayout);
        this.cutbuttontext = (ImageView) this.textsize_View.findViewById(R.id.btn_cut);
        this.donebuttontext = (ImageView) this.textsize_View.findViewById(R.id.okbutton);
        indicatorSeekBar = (IndicatorSeekBar) this.textsize_View.findViewById(R.id.seekbarspeed);
        this.textSizeButtonclick();
//        this.fountLayoutId.GetFontId(this);
        this.font_View = findViewById(R.id.includefontlayout);
        this.fontRecylview = (RecyclerView) this.font_View.findViewById(R.id.recycler_view_option);
        this.font_View.setVisibility(4);
        this.cutbuttonfont = (ImageView) this.font_View.findViewById(R.id.btn_cut);
        this.donebuttonfont = (ImageView) this.font_View.findViewById(R.id.okbutton);
        Hide_Unhide_font_Layout(true);
        this.FontlayoutButtonclick();

//        this.shadowLayoutId.GetShadowId(this);
        this.shadowDetails = new ShadowDetails();
//        this.mActivity = activity;
        this.shadow_View = findViewById(R.id.includeshadowlayout);
        this.cutbuttonshadow = (ImageView) this.shadow_View.findViewById(R.id.btn_cut);
        this.donebuttonshadow = (ImageView) this.shadow_View.findViewById(R.id.okbutton);
        this.seekbarX = (SeekBar) this.shadow_View.findViewById(R.id.seekbar_x);
        this.seekbarY = (SeekBar) this.shadow_View.findViewById(R.id.seekbar_y);
        this.seekbarRadious = (SeekBar) this.shadow_View.findViewById(R.id.seekbar_blur);
        this.colorSeekBar = (ColorSeekBar) this.shadow_View.findViewById(R.id.colorSlider);
        Hide_Unhide_shadow_Layout(false);
        this.shadoelayoutButtonclick();

        this.multicolor_View = findViewById(R.id.includemulticolorlayout);
        this.multicolorRecylview = (RecyclerView) this.multicolor_View.findViewById(R.id.recycler_view_option);
        this.multicolor_View.setVisibility(4);
        this.cutbuttonmulticolor = (ImageView) this.multicolor_View.findViewById(R.id.btn_cut);
        this.donebuttonmulticolor = (ImageView) this.multicolor_View.findViewById(R.id.okbutton);
        this.titlename = (TextView) this.multicolor_View.findViewById(R.id.tv_name);
        this.titlename.setText("Multi Color");
        Hide_Unhide_multicolor_Layout(true);
        this.MultiColorlayoutButtonclick();

//        this.addimageLayoutId.GetAdimageId(this);
        this.adimage_View = findViewById(R.id.includeimagelayout);
        this.addimageRecylview = (RecyclerView) this.adimage_View.findViewById(R.id.recycler_view_option);
        this.adimage_View.setVisibility(4);
        this.cutbuttonaddimage = (ImageView) this.adimage_View.findViewById(R.id.btn_cut);
        this.donebuttonaddimage = (ImageView) this.adimage_View.findViewById(R.id.okbutton);
        titlenametextview = (TextView) this.adimage_View.findViewById(R.id.tv_name);
        titlenametextview.setText("Add Emoji");
        Hide_Unhide_addimage_Layout(true);
        deleteTextoptionlaypot = findViewById(R.id.deleteoptionlaout);
        deleteimage_View = findViewById(R.id.deletimageoptionlaout);
        dltimagebutton = (ImageView) deleteimage_View.findViewById(R.id.btn_delete);
        this.editbuttonaddimage = (ImageView) deleteimage_View.findViewById(R.id.btn_edit);
        slectedimage = (ImageView) deleteimage_View.findViewById(R.id.iv_selected);
        selectedtext = (TextView) deleteimage_View.findViewById(R.id.tv_selected);
        deleteTextoptionlaypot.setVisibility(8);
        deleteimage_View.setVisibility(8);
        this.editbuttonaddimage.setVisibility(8);
        this.AddimagelayoutButtonclick();
//        this.backGroundLayoutId.GetFontId(this);
        this.BG_View = findViewById(R.id.includeBGlayout);
        this.bgRecylview = (RecyclerView) this.BG_View.findViewById(R.id.recycler_view_option);
        this.cutbuttonbg = (ImageView) this.BG_View.findViewById(R.id.btn_cut);
        this.donebuttonbg = (ImageView) this.BG_View.findViewById(R.id.okbutton);
        this.titlenametextviewbg = (TextView) this.BG_View.findViewById(R.id.tv_name);
        this.titlenametextviewbg.setText("BackGround");
        Hide_Unhide_BG_Layout(true);
        this.BGlayoutButtonclick();
//        this.alignmentLayoutId.GetAlimentId(this);
        this.Aliment_View = findViewById(R.id.includeAlimentlayout);
        this.left =  this.Aliment_View.findViewById(R.id.laligment);
        this.right =  this.Aliment_View.findViewById(R.id.raligment);
        this.center =  this.Aliment_View.findViewById(R.id.caligment);
        this.cutbuttonalign = (ImageView) this.Aliment_View.findViewById(R.id.btn_cut);
        this.donebuttonalign = (ImageView) this.Aliment_View.findViewById(R.id.okbutton);
        Hide_Unhide_Aliment_Layout(true);
        this.alimentButtonclick();
        this.status_View = findViewById(R.id.includestatuslayout);
        this.stausRecylview = (RecyclerView) this.status_View.findViewById(R.id.recycler_view_option);
        this.status_View.setVisibility(4);
        this.cutbuttonstatus = (ImageView) this.status_View.findViewById(R.id.btn_cut);
        this.donebuttonstatus = (ImageView) this.status_View.findViewById(R.id.okbutton);
        this.titlenamestatus = (TextView) this.status_View.findViewById(R.id.tv_name);
        this.titlenamestatus.setText("Status");
        Hide_Unhide_status_Layout(true);
        this.StatuslayoutButtonclick();
//        this.blurImageLayoutId.GetImageBlurId(this, this.editingLayoutId);
        this.textsize_Viewblur = findViewById(R.id.includesblurelayout);
        this.cutbuttonblur = (ImageView) this.textsize_Viewblur.findViewById(R.id.btn_cut);
        this.donebuttonblur = (ImageView) this.textsize_Viewblur.findViewById(R.id.okbutton);
        indicatorSeekBarblur = (IndicatorSeekBar) this.textsize_Viewblur.findViewById(R.id.seekbarspeed);
        if (this.view == null) {
            this.view = editImageview;
        }
        indicatorSeekBarblur.setMin(0.0f);
        indicatorSeekBarblur.setMax(25.0f);
        indicatorSeekBarblur.setProgress(1.0f);
        indicatorSeekBarblur.setTickCount(5);
        this.ImageBlurButtonclick();
//        this.ratioLayoutId.GetFontId(this);
        this.ratio_View = findViewById(R.id.includeration);
        this.ratioRecylview = (RecyclerView) this.ratio_View.findViewById(R.id.recycler_view_option);
        this.ratio_View.setVisibility(4);
        this.cutbuttonratio = (ImageView) this.ratio_View.findViewById(R.id.btn_cut);
        this.donebuttonratio = (ImageView) this.ratio_View.findViewById(R.id.okbutton);
        Hide_Unhide_ratio_Layout(true);
        this.RatiolayoutButtonclick();
        Bundle extras = getIntent().getExtras();
        this.statustext = extras.getString("STATUSTEXT");
        this.imagepath = extras.getString("IMAGEPATH");
        this.gradiaintcolotpo = extras.getInt("pos");
//        this.isnotifaction = extras.getBoolean("ISNOTIFACTION");
        if (this.imagepath == null) {
            GradientColorUtility.changeGradientColor(this, this.gradiaintcolotpo, this.editImageview, 0);
        } else {
            Glide.with((FragmentActivity) this).load(Uri.parse(this.imagepath)).centerCrop().into(this.editImageview);
            this.bitmapblur = null;
            blurbitmap(Uri.parse(this.imagepath));
        }
        EditingAdpterCall();
        this.EditDeleteTextButtonClick(this, this.onClicklisner);

        if (!this.statustext.equalsIgnoreCase("")) {
            WriteText.addtext(this, this.onClicklisner, this.statustext);
            Set_Delete_EditText(-1);
        }
        this.ratiolayout.setRatio(FixedAttribute.WIDTH, RatioAdapter.rationH[2], RatioAdapter.rationW[2], true);
        loadBanner(ly_adcontainer, ad_default_layout);

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
                        Intent intent = new Intent(QuoteEditActivity.this, ShareActivity.class);
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
        AdView adView = new AdView(QuoteEditActivity.this);
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

    public void RatiolayoutButtonclick() {
        this.cutbuttonratio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_ratio_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonratio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_ratio_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_ratio_Layout(boolean z) {
        if (z) {
            this.ratio_View.setVisibility(0);
        } else {
            this.ratio_View.setVisibility(8);
        }
    }

    public void ImageBlurButtonclick() {
        this.cutbuttonblur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_Blur_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonblur.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_Blur_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        indicatorSeekBarblur.setOnSeekChangeListener(new OnSeekChangeListener() {
            public void onSeeking(SeekParams seekParams) {
            }

            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
            }

            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
                Blurry.with(QuoteEditActivity.this).radius((int) indicatorSeekBar.getProgressFloat()).async().animate().capture(editImageview).into(editImageview);
            }
        });
    }

    public void Hide_Unhide_Blur_Layout(boolean z) {
        if (z) {
            this.textsize_Viewblur.setVisibility(0);
        } else {
            this.textsize_Viewblur.setVisibility(8);
        }
    }

    public  void Sizebarpossitionblur(float f) {
        indicatorSeekBar.setProgress(ImageSizeUtility.convertPixelsToDp(f, QuoteEditActivity.this));
    }

    public void StatuslayoutButtonclick() {
        this.cutbuttonstatus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_status_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonstatus.setOnClickListener(new View.OnClickListener() {
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

    public void BGlayoutButtonclick() {
        this.cutbuttonbg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_BG_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonbg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_BG_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_BG_Layout(boolean z) {
        if (z) {
            this.BG_View.setVisibility(0);
        } else {
            this.BG_View.setVisibility(8);
        }
    }


    public void alimentButtonclick() {
        this.cutbuttonalign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_Aliment_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonalign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_Aliment_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.left.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WriteText.weitetextview.setGravity(3);
            }
        });
        this.right.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WriteText.weitetextview.setGravity(5);
            }
        });
        this.center.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WriteText.weitetextview.setGravity(17);
            }
        });
    }

    public void Hide_Unhide_Aliment_Layout(boolean z) {
        if (z) {
            this.Aliment_View.setVisibility(0);
        } else {
            this.Aliment_View.setVisibility(8);
        }
    }


    public void AddimagelayoutButtonclick() {
        this.cutbuttonaddimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_addimage_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonaddimage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_addimage_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        dltimagebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AddImage.selectedimageview.setVisibility(8);
                deleteTextoptionlaypot.setVisibility(8);
                deleteimage_View.setVisibility(8);
                AddImage.selectedimageview = null;
            }
        });
    }

    public void Hide_Unhide_addimage_Layout(boolean z) {
        if (z) {
            this.adimage_View.setVisibility(0);
        } else {
            this.adimage_View.setVisibility(8);
        }
    }

    public  void Set_Delete_Image( ImageView imageView) {
        slectedimage.setImageBitmap(((BitmapDrawable) imageView.getDrawable()).getBitmap());
        slectedimage.setVisibility(0);
        selectedtext.setVisibility(8);
        deleteTextoptionlaypot.setVisibility(8);
        deleteimage_View.setVisibility(0);
    }


    public void MultiColorlayoutButtonclick() {
        this.cutbuttonmulticolor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_multicolor_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonmulticolor.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_multicolor_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
    }

    public void Hide_Unhide_multicolor_Layout(boolean z) {
        if (z) {
            this.multicolor_View.setVisibility(0);
        } else {
            this.multicolor_View.setVisibility(8);
        }
    }

    public void FontlayoutButtonclick() {
        this.cutbuttonfont.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_font_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonfont.setOnClickListener(new View.OnClickListener() {
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

    public void shadoelayoutButtonclick() {
        this.cutbuttonshadow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_shadow_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttonshadow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_shadow_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.seekbarX.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), i, shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
               shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(),shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), seekBar.getProgress(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), seekBar.getProgress(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }
        });
        this.seekbarY.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), i, shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                }
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), seekBar.getProgress(), shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), seekBar.getProgress(), shadowDetails.getShadowRadious(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                }
            }
        });
        this.seekbarRadious.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), i, iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                if (WriteText.weitetextview != null) {
                    int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), seekBar.getProgress(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), seekBar.getProgress(), iteampo, shadowDetails.getColorbarPossition());
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }
        });
        this.colorSeekBar.setOnColorChangeListener(new ColorSeekBar.OnColorChangeListener() {
            public void onColorChangeListener(int i, int i2, int i3) {
                int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
                if (iteampo != -1) {
                    ShadowDetails shadowDetails = ShadowDetails.shadowdetails.get(iteampo);
                    ShadowDetails shadowDetails2 = shadowDetails;
                    shadowDetails2.UpdateShadowiteam(shadowDetails, WriteText.weitetextview, i3, shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), iteampo, i);
                    shadowDetails2.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
                    return;
                }
                shadowDetails.SetShadowOnText(WriteText.weitetextview, shadowDetails.getShadowcolor(), shadowDetails.getShadowX(), shadowDetails.getShadowY(), shadowDetails.getShadowRadious(), shadowDetails.getColorbarPossition());
            }
        });
    }

    public void Hide_Unhide_shadow_Layout(boolean z) {
        if (z) {
            this.shadow_View.setVisibility(0);
        } else {
            this.shadow_View.setVisibility(8);
        }
    }

    public void SetShadowVaulrInSeekbar(TextView textView) {
        int iteampo = ShadowDetails.getIteampo(WriteText.weitetextview);
        if (iteampo != -1) {
            ShadowDetails shadowDetails2 = ShadowDetails.shadowdetails.get(iteampo);
            this.seekbarRadious.setProgress(shadowDetails2.getShadowRadious());
            this.seekbarX.setProgress(shadowDetails2.getShadowX());
            this.seekbarY.setProgress(shadowDetails2.getShadowY());
            this.colorSeekBar.setColorBarPosition(shadowDetails2.getColorbarPossition());
        }
    }
    public void textSizeButtonclick() {
        this.cutbuttontext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_TextSize_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        this.donebuttontext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Hide_Unhide_TextSize_Layout(false);
                Hide_Unhide_EditingOp_Layout(true);
            }
        });
        indicatorSeekBar.setOnSeekChangeListener(new OnSeekChangeListener() {
            public void onSeeking(SeekParams seekParams) {
                WriteText.SetTextSize(WriteText.weitetextview, (float) seekParams.progress);
            }

            public void onStartTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
                WriteText.SetTextSize(WriteText.weitetextview, (float) indicatorSeekBar.getProgress());
            }

            public void onStopTrackingTouch(IndicatorSeekBar indicatorSeekBar) {
                WriteText.SetTextSize(WriteText.weitetextview, (float) indicatorSeekBar.getProgress());
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

    public  void Sizebarpossition(float f) {
        indicatorSeekBar.setProgress(ImageSizeUtility.convertPixelsToDp(f, QuoteEditActivity.this));
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
    public void ButtonClickHandel() {
        this.makegifButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
//                QuoteEditActivity.this.lockunlockimageview.setVisibility(8);
                QuoteEditActivity.this.backbutton.setVisibility(8);
                new SaveStickerAsyncTask(QuoteEditActivity.this, QuoteEditActivity.this.saveImage.takescreen(QuoteEditActivity.this)) {
                    public void Ontick() {
                    }

                    public void onRecived(String str) {
                        if (!str.equalsIgnoreCase("")) {
                            if (mInterstitialAd != null) {
                                selectedItem = Clicked_item.SAVE;
                                setInterListener(str);
                                mInterstitialAd.show(QuoteEditActivity.this);
                            }else {
                                Intent intent = new Intent(QuoteEditActivity.this, ShareActivity.class);
                                intent.putExtra("shared_file",str);
                                startActivity(intent);
                                finish();
                            }
//                            HomeScreenActivity.full_add(this.mconContext);
                        }else {
                            Toast.makeText(QuoteEditActivity.this, "Save Fail!!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }.execute(new Uri[0]);
            }
        });
        this.backbuttonActionbar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void Hide_Unhide_EditingOp_Layout(boolean z) {
        if (z) {
            this.Edit_Option_View.setVisibility(0);
        } else {
            this.Edit_Option_View.setVisibility(8);
        }
    }

    public  void Set_Delete_EditText(int i) {
        selectedTextview.setText(WriteText.GetText(WriteText.weitetextview));
        selectedTextview.setTextColor(WriteText.GetTextColor(WriteText.weitetextview));
        selectedTextview.setTypeface(WriteText.GetTextFont(WriteText.weitetextview));
        deletimageoptionlayout.setVisibility(8);
        deleteoptionlaypot.setVisibility(0);
        selectedTextview.setVisibility(0);
        selectedImageview.setVisibility(8);
        if (i != -1) {
            multiColorEffectDetails1.Setmulticolor_Effect(selectedTextview, i, false);
        } else {
            selectedTextview.getPaint().setShader((Shader) null);
        }
    }

    public void EditDeleteTextButtonClick(final Activity activity2, final MultiTouchListener.OnClicklisner onClicklisner) {
        this.deletebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WriteText.weitetextview != null) {
                    int parseInt = Integer.parseInt(WriteText.weitetextview.getTag().toString());
                    WriteText.weitetextview.setVisibility(8);
                    deleteoptionlaypot.setVisibility(8);
                    deletimageoptionlayout.setVisibility(8);
                    QuoteEditActivity.this.Hide_Unhide_EditingOp_Layout(true);
                    int indexOf = Multieffectpossition.textViewArrayListshadowId.indexOf(WriteText.weitetextview);
                    if (indexOf != -1) {
                        Multieffectpossition.additeaminlist(activity2, WriteText.weitetextview, indexOf, true);
                    }
                    WriteText.weitetextview = null;
                    return;
                }
                Toast.makeText(activity2, "Select Text to edit", Toast.LENGTH_SHORT).show();

            }
        });
        this.editbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                InputTextDialog.inputdialogcall(onClicklisner, QuoteEditActivity.this, true);
            }
        });
    }
    public void OnEditOptionIteamClick(int i) {
        if (i == 2) {
            InputTextDialog.inputdialogcall(this.onClicklisner, this, false);
        } else if (i == 3 && WriteText.weitetextview != null) {
            ColorAdpterCall();
            this.Hide_Unhide_color_Layout(true);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 4 && WriteText.weitetextview != null) {
            this.Hide_Unhide_TextSize_Layout(true);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            Sizebarpossition(WriteText.weitetextview.getTextSize());
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 5 && WriteText.weitetextview != null) {
            callfontAdpter();
            this.Hide_Unhide_font_Layout(true);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 6 && WriteText.weitetextview != null) {
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_shadow_Layout(true);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 7 && WriteText.weitetextview != null) {
            multiColorAdpter();
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(true);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 9) {
            AddimageAdpter();
            this.Hide_Unhide_addimage_Layout(true);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 0) {
            AddBgAdpter();
            this.Hide_Unhide_BG_Layout(true);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 8 && WriteText.weitetextview != null) {
            this.Hide_Unhide_Aliment_Layout(true);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 1) {
            StatusCategoryAdpter();
            this.Hide_Unhide_status_Layout(true);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
            this.Hide_Unhide_ratio_Layout(false);
        } else if (i == 10) {
            RatioAdpterCall();
            this.Hide_Unhide_ratio_Layout(true);
            this.Hide_Unhide_status_Layout(false);
            this.Hide_Unhide_Aliment_Layout(false);
            this.Hide_Unhide_BG_Layout(false);
            this.Hide_Unhide_addimage_Layout(false);
            this.Hide_Unhide_EditingOp_Layout(false);
            this.Hide_Unhide_color_Layout(false);
            this.Hide_Unhide_TextSize_Layout(false);
            this.Hide_Unhide_font_Layout(false);
            this.Hide_Unhide_shadow_Layout(false);
            this.Hide_Unhide_multicolor_Layout(false);
            this.Hide_Unhide_Blur_Layout(false);
        } else {
            Toast.makeText(this, "Write Text to edit", Toast.LENGTH_SHORT).show();
        }
    }

    public void onColorItemClicked(int i) {
        int multieffectcolorpo = Multieffectpossition.getMultieffectcolorpo(WriteText.weitetextview);
        if (multieffectcolorpo != -1) {
            Multieffectpossition.additeaminlist(this, WriteText.weitetextview, multieffectcolorpo, true);
        }
        WriteText.SetTextColor(WriteText.weitetextview, ColorListAdapter.colorname[i]);
        this.colorListAdapter.clear_all_Selection();
        this.colorListAdapter.notifyDataSetChanged();
        this.colorListAdapter.toggleSelection(i);
        Set_Delete_EditText(-1);
        WriteText.weitetextview.getPaint().setShader((Shader) null);
    }

    public void onItemClicked_Font(int i) {
        if (WriteText.weitetextview != null) {
            AssetManager assets = getAssets();
            WriteText.SetTextFont(WriteText.weitetextview, Typeface.createFromAsset(assets, "font/" + FontAdapter.fontname[i]));
            int multieffectcolorpo = Multieffectpossition.getMultieffectcolorpo(WriteText.weitetextview);
            if (multieffectcolorpo != -1) {
                Set_Delete_EditText(Multieffectpossition.textViewArrayListshadowId.get(multieffectcolorpo).getPossition());
                return;
            }
            Set_Delete_EditText(-1);
        }
    }

    public void onItemClicked_MulticolorEffect(int i) {
        Multieffectpossition.additeaminlist(this, WriteText.weitetextview, i, false);
        this.multiColorEffectDetails.Setmulticolor_Effect(WriteText.weitetextview, i, false);
        Set_Delete_EditText(i);
    }

    public void onItemClicked_Addimage(int i) {
        AddImage.addImage(this, this.onClicklisnerImage, i);
    }

    public void onItemClicked_AddBG(int i) {
        if (i == 0) {
            CropImage.startPickImageActivity(this);
        } else if (i == 1) {
            this.imagepath = "";
            ColorpickerDialog();
        } else {
            int size = MyApplication.getInstance().getBackgroundarrylist().size() + 1;
            int i2 = i - size;
            if (i < size) {
                this.m_bitmap = null;
                changebg(i - 2);
                return;
            }
            this.imagepath = "";
            GradientColorUtility.changeGradientColor(this, i2, this.editImageview, 0);
        }
    }

    public void onItemClicked_StatusCategory(int i) {
        StatusDialog.inputdialogcall(this, EditStatusCategoryAdapter.catname[i], i, this.onClicklisner);
    }

    public void onRatioItemClicked(int i) {
        this.ratioAdapter.clear_all_Selection();
        this.ratioAdapter.notifyDataSetChanged();
        this.ratioAdapter.toggleSelection(i);
        if (i == 0) {
            this.ratiolayout.setRatio(FixedAttribute.WIDTH, RatioAdapter.rationH[2], RatioAdapter.rationW[2], true);
            this.editImageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            this.ratiolayout.setRatio(FixedAttribute.WIDTH, RatioAdapter.rationH[i], RatioAdapter.rationW[i], true);
            this.editImageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }
        if (i == 4) {
            this.ratiolayout.getLayoutParams().width = (int) ((((float) ScreenDimension.getWeight(this)) / 100.0f) * 78.0f);
            this.ratiolayout.getLayoutParams().height = (int) ((((float) ScreenDimension.getHeight(this)) / 100.0f) * 100.0f);
            return;
        }
        this.ratiolayout.getLayoutParams().width = (int) ((((float) ScreenDimension.getWeight(this)) / 100.0f) * 100.0f);
        this.ratiolayout.getLayoutParams().height = (int) ((((float) ScreenDimension.getHeight(this)) / 100.0f) * 100.0f);
    }

    private void RatioAdpterCall() {
        this.Hide_Unhide_ratio_Layout(true);
        this.ratioRecylview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.ratioAdapter = new RatioAdapter(this, this);
        this.ratioRecylview.setAdapter(this.ratioAdapter);
        this.ratioAdapter.toggleSelection(0);
    }

    private void EditingAdpterCall() {
        this.Hide_Unhide_EditingOp_Layout(true);
        this.editRecylview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.editRecylview.setAdapter(new EditingOptionAdapter(this, this));
    }

    private void ColorAdpterCall() {
        this.colorRecylview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), 0, false));
        this.colorListAdapter = new ColorListAdapter(this, this);
        this.colorRecylview.setAdapter(this.colorListAdapter);
        this.colorListAdapter.toggleSelection(0);
    }

    public void callfontAdpter() {
        this.fontAdapter = new FontAdapter(this, this);
        this.fontRecylview.setAdapter(this.fontAdapter);
        this.fontRecylview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void multiColorAdpter() {
        this.multiColorAdapter = new MultiColorAdapter(this, this);
        this.multicolorRecylview.setAdapter(this.multiColorAdapter);
        this.multicolorRecylview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void AddimageAdpter() {
        if (MyApplication.getInstance().getAddEmojiarrylist().size() == 0) {
            new LoadAssetImageAsyncTask(this, FolderUtility.EmojiFolderName) {
                public void Ontick() {
                }

                public void onRecived(ArrayList<String> arrayList) {
                    MyApplication.getInstance().setAddEmojiarrylist(arrayList);
                    QuoteEditActivity.this.addImageAdapter = new AddImageAdapter(QuoteEditActivity.this, MyApplication.getInstance().getAddEmojiarrylist(), QuoteEditActivity.this);
                    QuoteEditActivity.this.addimageRecylview.setAdapter(QuoteEditActivity.this.addImageAdapter);
                    QuoteEditActivity.this.addimageRecylview.setLayoutManager(new GridLayoutManager(QuoteEditActivity.this, 4));
                }
            }.execute(new Uri[0]);
            return;
        }
        this.addImageAdapter = new AddImageAdapter(this, MyApplication.getInstance().getAddEmojiarrylist(), this);
        this.addimageRecylview.setAdapter(this.addImageAdapter);
        this.addimageRecylview.setLayoutManager(new GridLayoutManager(this, 4));
        if (!this.IsSpaceemoji) {
            this.IsSpaceemoji = true;
        }
    }

    public void AddBgAdpter() {
        if (MyApplication.getInstance().getBackgroundarrylist().size() == 0) {
            new LoadAssetImageAsyncTask(this, FolderUtility.BackgroundFolderName) {
                public void Ontick() {
                }

                public void onRecived(ArrayList<String> arrayList) {
                    MyApplication.getInstance().setBackgroundarrylist(arrayList);
                    QuoteEditActivity.this.backgroundAdapter = new BackgroundAdapter(QuoteEditActivity.this, QuoteEditActivity.this);
                    QuoteEditActivity.this.bgRecylview.setAdapter(QuoteEditActivity.this.backgroundAdapter);
                    QuoteEditActivity.this.bgRecylview.setLayoutManager(new GridLayoutManager(QuoteEditActivity.this, 4));
                }
            }.execute(new Uri[0]);
            return;
        }
        this.backgroundAdapter = new BackgroundAdapter(this, this);
        this.bgRecylview.setLayoutManager(new GridLayoutManager(this, 4));
        this.bgRecylview.setAdapter(this.backgroundAdapter);
        if (!this.IsSpacebg) {
            this.IsSpacebg = true;
        }
    }

    public void StatusCategoryAdpter() {
        this.statusAdpter = new EditStatusCategoryAdapter(this, this);
        this.stausRecylview.setAdapter(this.statusAdpter);
        this.stausRecylview.setLayoutManager(new GridLayoutManager(this, 3));
    }

    public void TextOnClicklisner(View view, MotionEvent motionEvent) {
        WriteText.weitetextview = WriteText.textViewArrayList.get(Integer.parseInt(view.getTag().toString()));
        int multieffectcolorpo = Multieffectpossition.getMultieffectcolorpo(WriteText.weitetextview);
        if (multieffectcolorpo != -1) {
            Set_Delete_EditText(Multieffectpossition.textViewArrayListshadowId.get(multieffectcolorpo).getPossition());
        } else {
            Set_Delete_EditText(-1);
        }
        this.SetShadowVaulrInSeekbar(WriteText.weitetextview);
        Sizebarpossition(WriteText.weitetextview.getTextSize());
        switch (motionEvent.getAction()) {
            case 0:
                this.startTime = System.currentTimeMillis();
                this.clickCount++;
                return;
            case 1:
                this.duration += System.currentTimeMillis() - this.startTime;
                if (this.clickCount == 2) {
                    if (this.duration <= 200) {
                        InputTextDialog.inputdialogcall(this.onClicklisner, this, true);
                    }
                    this.clickCount = 0;
                    this.duration = 0;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void ImageOnClicklisner(View view, MotionEvent motionEvent) {
        AddImage.selectedimageview = AddImage.imageViewsArrylist.get(Integer.parseInt(view.getTag().toString()));
        Set_Delete_Image( AddImage.selectedimageview);
    }

    public void onBackPressed() {
//        Savealertsialog savealertsialog = new Savealertsialog();
        if (this.Edit_Option_View.isShown()) {
            ShowSaveAlertOnBackPress(this, new SaveImage().takescreen(this));
        } else {
            this.Hide_Unhide_EditingOp_Layout(true);
        }
    }
    public void ShowSaveAlertOnBackPress(final Activity activity, final Bitmap bitmap) {
//        this.saveImage = new SaveImage();
        new AlertDialog.Builder(activity).setMessage((CharSequence) "Do want to save ?").setPositiveButton((CharSequence) "Yes", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                backbutton.setVisibility(View.GONE);
                new SaveStickerAsyncTask(activity, bitmap) {
                    public void Ontick() {
                    }

                    public void onRecived(String str) {
                        if (!str.equalsIgnoreCase("")) {
                            if (mInterstitialAd != null) {
                                selectedItem = Clicked_item.SAVE;
                                setInterListener(str);
                                mInterstitialAd.show(QuoteEditActivity.this);
                            }else {
                                Intent intent = new Intent(QuoteEditActivity.this, ShareActivity.class);
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
                activity.finish();
            }
        }).show();
    }



    public void changebg(int i) {
//        this.lockunlockimageview.setVisibility(0);
        Glide.with((FragmentActivity) this).load(Uri.parse(MyApplication.getInstance().getBackgroundarrylist().get(i))).asBitmap().fitCenter().into(this.editImageview);
        this.bitmapblur = null;
        blurbitmap(Uri.parse(MyApplication.getInstance().getBackgroundarrylist().get(i)));
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 200) {
            if (i2 == -1) {
                CropImage.activity(CropImage.getPickImageResultUri(this, intent)).setGuidelines(CropImageView.Guidelines.ON).setActivityTitle(getString(R.string.app_name)).setCropShape(CropImageView.CropShape.RECTANGLE).setCropMenuCropButtonTitle(getResources().getString(R.string.crop_image_menu_crop)).setAllowFlipping(false).setAllowFlipping(false).setAllowRotation(true).start(this);
            }
        } else if (i == 203) {
            CropImage.ActivityResult activityResult = CropImage.getActivityResult(intent);
            if (i2 == -1) {
//                HomeScreenActivity.full_add(this);
                Uri uri = activityResult.getUri();
                Glide.with((FragmentActivity) this).load(new File(uri.getPath())).asBitmap().fitCenter().placeholder((int) R.drawable.ic_loader).into(this.editImageview);
//                this.lockunlockimageview.setVisibility(0);
                this.bitmapblur = null;
                blurbitmap(uri);
            }
        }
    }

    private void ColorpickerDialog() {
        ColorPickerDialogBuilder.with(this, R.style.ColorPickerDialogTheme).setTitle("Choose BG color").initialColor(getResources().getColor(R.color.white)).wheelType(ColorPickerView.WHEEL_TYPE.FLOWER).density(12).setOnColorSelectedListener(new OnColorSelectedListener() {
            public void onColorSelected(int i) {
//                QuoteEditActivity.this.lockunlockimageview.setVisibility(8);
                QuoteEditActivity.this.editImageview.setImageDrawable((Drawable) null);
                QuoteEditActivity.this.frameLayout.setBackgroundColor(i);
            }
        }).setPositiveButton((CharSequence) "ok", (ColorPickerClickListener) new ColorPickerClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, Integer[] numArr) {
            }
        }).setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).build().show();
    }

    public Drawable blurbitmap(Uri uri) {
        if (this.bitmapblur == null) {
            this.imagepath = uri.toString();
            Glide.with((FragmentActivity) this).load(uri).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(new SimpleTarget<Bitmap>() {
                public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                    Blurry.with(QuoteEditActivity.this).radius(25).sampling(2).async(new Blurry.ImageComposer.ImageComposerListener() {
                        public void onImageReady(BitmapDrawable bitmapDrawable) {
                            QuoteEditActivity.this.bitmapblur = bitmapDrawable;
                            QuoteEditActivity.this.frameLayout.setBackground(QuoteEditActivity.this.bitmapblur);
                        }
                    }).animate().from(bitmap).into(QuoteEditActivity.this.editImageview);
                }
            });
        } else {
            this.frameLayout.setBackground(this.bitmapblur);
        }
        return this.bitmapblur;
    }
}
