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

public class DegineFiveViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    QuotesFrameDetails autherquotesDetails;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    RelativeLayout textCirclelayout;
    public DegineFiveViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_5);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_5);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_5);
        this.textCirclelayout = (RelativeLayout) view.findViewById(R.id.textcircellayout_5);
        layoutsize(activity);
        setData(activity, str);
    }

    private void layoutsize(Activity activity) {
        double weight = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * DegineOneViewHolder.layoutpercnt));
        Double.isNaN(weight);
        Double.isNaN(weight);
        this.mainLayout.getLayoutParams().width = (int) weight;
        int i = (int) ((double) ((int) (weight - (weight / 10.0d))));
        this.mainLayout.getLayoutParams().height = i;
        ViewGroup.LayoutParams layoutParams = this.profileImageview.getLayoutParams();
        Double.isNaN(weight);
        int i2 = (int) (weight / 1.7d);
        layoutParams.width = i2;
        this.profileImageview.getLayoutParams().height = i;
        this.textCirclelayout.getLayoutParams().width = i2;
        this.textCirclelayout.getLayoutParams().height = i2;
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
//            this.autherquotesDetails = ArrayListUtility.autherQuotesdetails.get(4);
//            this.Quotes = this.autherquotesDetails.getQuotes();
//            this.QuotesFrameID = this.autherquotesDetails.getQuotesFrameID();
//            this.ImagePath = this.autherquotesDetails.getImagePath();
//            this.FontName = this.autherquotesDetails.getFontName();
//            this.TextColor = this.autherquotesDetails.getTextColor();
//            return;
//        }
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(4);
        this.Quotes = this.quotesFrameDetails.getQuotes();
        this.QuotesFrameID = this.quotesFrameDetails.getQuotesFrameID();
        this.ImagePath = this.quotesFrameDetails.getImagePath();
        this.FontName = this.quotesFrameDetails.getFontName();
        this.TextColor = this.quotesFrameDetails.getTextColor();
    }
}
