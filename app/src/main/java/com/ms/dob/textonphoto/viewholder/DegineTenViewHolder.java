package com.ms.dob.textonphoto.viewholder;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
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

public class DegineTenViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    RelativeLayout imagemainlayout;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    RelativeLayout textmainlayout;

    public DegineTenViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_10);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_10);
        this.imagemainlayout = (RelativeLayout) view.findViewById(R.id.profilelayout_10);
        this.quoetsTextview = (TextView) view.findViewById(R.id.quoetstextview_10);
        this.textmainlayout = (RelativeLayout) view.findViewById(R.id.textcircellayout_10);
        layoutsize(activity);
        setData(activity, str);
    }

    private void layoutsize(Activity activity) {
        double weight = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * DegineOneViewHolder.layoutpercnt));
        Double.isNaN(weight);
        double d = (double) ((int) (weight * 1.5d));
        this.mainLayout.getLayoutParams().width = (int) weight;
        this.mainLayout.getLayoutParams().height = (int) d;
        TextView textView = this.quoetsTextview;
        Double.isNaN(d);
        textView.setY((float) ((int) (d / 1.5d)));
    }

    private void setData(Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setTextColor(Color.parseColor(this.TextColor));
        setTextWithSpanLine.setTextWithSpan(this.quoetsTextview, Color.parseColor("#E6ffffff"), DegineOneViewHolder.TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID), 1.5f);
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
//            QuotesFrameDetails quotesFrameDetails = ArrayListUtility.autherQuotesdetails.get(9);
//            this.Quotes = quotesFrameDetails.getQuotes();
//            this.QuotesFrameID = quotesFrameDetails.getQuotesFrameID();
//            this.ImagePath = quotesFrameDetails.getImagePath();
//            this.FontName = quotesFrameDetails.getFontName();
//            this.TextColor = quotesFrameDetails.getTextColor();
//            return;
//        }
        QuotesFrameDetails quotesFrameDetails2 = MyApplication.getInstance().getQuotesframdetails().get(9);
        this.Quotes = quotesFrameDetails2.getQuotes();
        this.QuotesFrameID = quotesFrameDetails2.getQuotesFrameID();
        this.ImagePath = quotesFrameDetails2.getImagePath();
        this.FontName = quotesFrameDetails2.getFontName();
        this.TextColor = quotesFrameDetails2.getTextColor();
    }
}
