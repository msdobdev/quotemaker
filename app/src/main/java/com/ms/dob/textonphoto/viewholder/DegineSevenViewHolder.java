package com.ms.dob.textonphoto.viewholder;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
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
import com.ms.dob.textonphoto.utility.setTextWithSpanLine;

import java.io.File;

public class DegineSevenViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    RelativeLayout textmainlayout;

    public DegineSevenViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_7);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_7);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_7);
        this.textmainlayout = (RelativeLayout) view.findViewById(R.id.textcircellayout_7);
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
        Double.isNaN(d);
        layoutParams.height = (int) (d / 1.7d);
    }

    private void setData(Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        SpannableString TextAnsTextcolor = DegineOneViewHolder.TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        setTextWithSpanLine.setTextWithSpan(this.quoetsTextview, -1, TextAnsTextcolor, 1.5f);
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
//            QuotesFrameDetails quotesFrameDetails = ArrayListUtility.autherQuotesdetails.get(6);
//            this.Quotes = quotesFrameDetails.getQuotes();
//            this.QuotesFrameID = quotesFrameDetails.getQuotesFrameID();
//            this.ImagePath = quotesFrameDetails.getImagePath();
//            this.FontName = quotesFrameDetails.getFontName();
//            this.TextColor = quotesFrameDetails.getTextColor();
//            return;
//        }
        QuotesFrameDetails quotesFrameDetails2 = MyApplication.getInstance().getQuotesframdetails().get(6);
        this.Quotes = quotesFrameDetails2.getQuotes();
        this.QuotesFrameID = quotesFrameDetails2.getQuotesFrameID();
        this.ImagePath = quotesFrameDetails2.getImagePath();
        this.FontName = quotesFrameDetails2.getFontName();
        this.TextColor = quotesFrameDetails2.getTextColor();
    }
}
