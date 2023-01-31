package com.ms.dob.textonphoto.viewholder;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;

public class DegineSixViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    ImageView profileImageviewBg;
    RelativeLayout profilelayout;
    TextView quoetsTextview;
    RelativeLayout textbglayout;
    RelativeLayout textmainlayout;

    public DegineSixViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_6);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_6);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_6);
        this.textmainlayout = (RelativeLayout) view.findViewById(R.id.textcircellayout_6);
        this.profilelayout = (RelativeLayout) view.findViewById(R.id.profilelayout_6);
        this.profileImageviewBg = (ImageView) view.findViewById(R.id.profilebg);
        this.textbglayout = (RelativeLayout) view.findViewById(R.id.textlayoutbg);
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
        ViewGroup.LayoutParams layoutParams = this.profilelayout.getLayoutParams();
        Double.isNaN(weight);
        layoutParams.width = (int) (weight / 1.1d);
        ViewGroup.LayoutParams layoutParams2 = this.profilelayout.getLayoutParams();
        Double.isNaN(d);
        layoutParams2.height = (int) (d / 1.1d);
        ViewGroup.LayoutParams layoutParams3 = this.profileImageview.getLayoutParams();
        Double.isNaN(weight);
        int i = (int) (weight / 1.3d);
        layoutParams3.width = i;
        ViewGroup.LayoutParams layoutParams4 = this.profileImageview.getLayoutParams();
        Double.isNaN(d);
        int i2 = (int) (d / 1.3d);
        layoutParams4.height = i2;
        this.profileImageviewBg.getLayoutParams().width = i;
        this.profileImageviewBg.getLayoutParams().height = i2;
        ViewGroup.LayoutParams layoutParams5 = this.textmainlayout.getLayoutParams();
        Double.isNaN(weight);
        layoutParams5.width = (int) (weight / 1.5d);
        ViewGroup.LayoutParams layoutParams6 = this.textmainlayout.getLayoutParams();
        Double.isNaN(weight);
        layoutParams6.height = (int) (weight / 3.0d);
        RelativeLayout relativeLayout = this.textmainlayout;
        Double.isNaN(weight);
        relativeLayout.setX((float) ((int) (weight / 4.0d)));
    }

    private void setData(Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setText(DegineOneViewHolder.TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID));
        this.quoetsTextview.setTextColor(Color.parseColor(this.TextColor));
        if (this.ImagePath.equalsIgnoreCase("")) {
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        } else if (!str.equalsIgnoreCase("")) {
            Glide.with(activity).load(Integer.valueOf(Integer.parseInt(this.ImagePath))).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        } else {
            Glide.with(activity).load(Uri.fromFile(new File(this.ImagePath)).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        }
    }

    private void getFramedetialdata(String str) {
//        if (!str.equalsIgnoreCase("")) {
//            QuotesFrameDetails quotesFrameDetails = ArrayListUtility.autherQuotesdetails.get(5);
//            this.Quotes = quotesFrameDetails.getQuotes();
//            this.QuotesFrameID = quotesFrameDetails.getQuotesFrameID();
//            this.ImagePath = quotesFrameDetails.getImagePath();
//            this.FontName = quotesFrameDetails.getFontName();
//            this.TextColor = quotesFrameDetails.getTextColor();
//            return;
//        }
        QuotesFrameDetails quotesFrameDetails2 = MyApplication.getInstance().getQuotesframdetails().get(5);
        this.Quotes = quotesFrameDetails2.getQuotes();
        this.QuotesFrameID = quotesFrameDetails2.getQuotesFrameID();
        this.ImagePath = quotesFrameDetails2.getImagePath();
        this.FontName = quotesFrameDetails2.getFontName();
        this.TextColor = quotesFrameDetails2.getTextColor();
    }
}
