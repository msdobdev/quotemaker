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

public class DegineThreeViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    QuotesFrameDetails autherquotesDetails;
    ImageView bgblureimageview;
    View lineView;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;

    public DegineThreeViewHolder(View view, Activity activity, String str) {
        super(view);
        this.loadFont = new LoadFont("f17.ttf", activity);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_3);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_3);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_3);
        this.lineView = view.findViewById(R.id.lineview_3);
        this.bgblureimageview = (ImageView) view.findViewById(R.id.blurimageview);
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
        ViewGroup.LayoutParams layoutParams = this.profileImageview.getLayoutParams();
        Double.isNaN(weight);
        layoutParams.width = (int) (weight / 1.6d);
        ViewGroup.LayoutParams layoutParams2 = this.profileImageview.getLayoutParams();
        Double.isNaN(d);
        layoutParams2.height = (int) (d / 1.6d);
    }

    private void setData(final Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setText(DegineOneViewHolder.TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID));
        this.quoetsTextview.setTextColor(Color.parseColor(this.TextColor));
        if (this.ImagePath.equalsIgnoreCase("")) {
            Blurry.with(activity).radius(25).async().animate().from(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image_found)).into(this.bgblureimageview);
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        } else if (!str.equalsIgnoreCase("")) {
            Glide.with(activity).load(Integer.valueOf(Integer.parseInt(this.ImagePath))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(new SimpleTarget<Bitmap>() {
                public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                    DegineThreeViewHolder.this.profileImageview.setImageBitmap(bitmap);
                    Blurry.with(activity).radius(25).async().animate().from(bitmap).into(DegineThreeViewHolder.this.bgblureimageview);
                }
            });
        } else {
            Glide.with(activity).load(Uri.fromFile(new File(this.ImagePath)).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
            Bitmap decodeFile = BitmapFactory.decodeFile(this.ImagePath);
            if (decodeFile != null) {
                Blurry.with(activity).radius(25).async().animate().from(decodeFile).into(this.bgblureimageview);
            }
        }
    }

    private void getFramedetialdata(String str) {
//        if (!str.equalsIgnoreCase("")) {
//            this.autherquotesDetails = ArrayListUtility.autherQuotesdetails.get(2);
//            this.Quotes = this.autherquotesDetails.getQuotes();
//            this.QuotesFrameID = this.autherquotesDetails.getQuotesFrameID();
//            this.ImagePath = this.autherquotesDetails.getImagePath();
//            this.FontName = this.autherquotesDetails.getFontName();
//            this.TextColor = this.autherquotesDetails.getTextColor();
//            return;
//        }
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(2);
        this.Quotes = this.quotesFrameDetails.getQuotes();
        this.QuotesFrameID = this.quotesFrameDetails.getQuotesFrameID();
        this.ImagePath = this.quotesFrameDetails.getImagePath();
        this.FontName = this.quotesFrameDetails.getFontName();
        this.TextColor = this.quotesFrameDetails.getTextColor();
    }
}
