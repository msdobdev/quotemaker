package com.ms.dob.textonphoto.viewholder;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;
import jp.wasabeef.blurry.Blurry;

public class DegineEightViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    RelativeLayout imagemainlayout;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview_blur;
    ImageView profileImageview_uper;
    TextView quoetsTextview;
    RelativeLayout textmainlayout;

    public DegineEightViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_8);
        this.profileImageview_uper = (ImageView) view.findViewById(R.id.iv_profile_8_uper);
        this.profileImageview_blur = (ImageView) view.findViewById(R.id.iv_profile_8_blur);
        this.imagemainlayout = (RelativeLayout) view.findViewById(R.id.profilelayout_8);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_8);
        this.textmainlayout = (RelativeLayout) view.findViewById(R.id.textcircellayout_8);
        layoutsize(activity);
        setData(activity, str);
    }

    private void layoutsize(Activity activity) {
        double weight = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * DegineOneViewHolder.layoutpercnt));
        Double.isNaN(weight);
        Double.isNaN(weight);
        double d = (double) ((int) (weight - (weight / 10.0d)));
        this.mainLayout.getLayoutParams().width = (int) weight;
        this.mainLayout.getLayoutParams().height = (int) d;
        ViewGroup.LayoutParams layoutParams = this.profileImageview_blur.getLayoutParams();
        Double.isNaN(d);
        int i = (int) (d / 1.7d);
        layoutParams.height = i;
        this.imagemainlayout.getLayoutParams().height = i;
        this.profileImageview_uper.getLayoutParams().height = i;
    }

    private void setData(final Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setTextColor(Color.parseColor(this.TextColor));
        this.quoetsTextview.setText(DegineOneViewHolder.TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID));
        if (this.ImagePath.equalsIgnoreCase("")) {
            Blurry.with(activity).radius(5).async().from(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image_found)).into(this.profileImageview_blur);
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview_uper);
        } else if (!str.equalsIgnoreCase("")) {
            Glide.with(activity).load(Integer.valueOf(Integer.parseInt(this.ImagePath))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(new SimpleTarget<Bitmap>() {
                public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                    DegineEightViewHolder.this.profileImageview_uper.setImageBitmap(bitmap);
                    Blurry.with(activity).radius(25).async().animate().from(bitmap).into(DegineEightViewHolder.this.profileImageview_blur);
                }
            });
        } else {
            Glide.with(activity).load(Uri.fromFile(new File(this.ImagePath)).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview_uper);
            Bitmap decodeFile = BitmapFactory.decodeFile(this.ImagePath);
            if (decodeFile != null) {
                Blurry.with(activity).radius(25).async().animate().from(decodeFile).into(this.profileImageview_blur);
            }
        }
    }

    private void getFramedetialdata(String str) {
//        if (!str.equalsIgnoreCase("")) {
//            QuotesFrameDetails quotesFrameDetails = ArrayListUtility.autherQuotesdetails.get(7);
//            this.Quotes = quotesFrameDetails.getQuotes();
//            this.QuotesFrameID = quotesFrameDetails.getQuotesFrameID();
//            this.ImagePath = quotesFrameDetails.getImagePath();
//            this.FontName = quotesFrameDetails.getFontName();
//            this.TextColor = quotesFrameDetails.getTextColor();
//            return;
//        }
        QuotesFrameDetails quotesFrameDetails2 = MyApplication.getInstance().getQuotesframdetails().get(7);
        this.Quotes = quotesFrameDetails2.getQuotes();
        this.QuotesFrameID = quotesFrameDetails2.getQuotesFrameID();
        this.ImagePath = quotesFrameDetails2.getImagePath();
        this.FontName = quotesFrameDetails2.getFontName();
        this.TextColor = quotesFrameDetails2.getTextColor();
    }
}
