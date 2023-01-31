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
import com.jcminarro.roundkornerlayout.CornerType;
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;

public class DegineTwoViewHolder extends RecyclerView.ViewHolder {
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
    QuotesFrameDetails autherquotesDetails;
    View lineView;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    RoundKornerRelativeLayout profilelayout;
    RoundKornerRelativeLayout profilelayout2;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;

    public DegineTwoViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout_2);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile_2);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets_2);
        this.lineView = view.findViewById(R.id.lineview_2);
        this.profilelayout = (RoundKornerRelativeLayout) view.findViewById(R.id.profilelayout_2);
        this.profilelayout2 = (RoundKornerRelativeLayout) view.findViewById(R.id.profilelayout2);
        layoutsize(activity);
        setData(activity, str);
    }

    private void layoutsize(Activity activity) {
        double weight = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * DegineOneViewHolder.layoutpercnt));
        Double.isNaN(weight);
        Double.isNaN(weight);
        this.profilelayout.setCornerRadius((float) weight, CornerType.ALL);
        this.mainLayout.getLayoutParams().width = (int) weight;
        this.mainLayout.getLayoutParams().height = (int) ((double) ((int) (weight - (weight / 10.0d))));
        ViewGroup.LayoutParams layoutParams = this.profileImageview.getLayoutParams();
        Double.isNaN(weight);
        int i = (int) (weight / 1.6d);
        layoutParams.width = i;
        this.profileImageview.getLayoutParams().height = i;
        this.profilelayout.getLayoutParams().width = i;
        this.profilelayout.getLayoutParams().height = i;
        ViewGroup.LayoutParams layoutParams2 = this.profilelayout2.getLayoutParams();
        Double.isNaN(weight);
        int i2 = (int) (weight / 1.63d);
        layoutParams2.width = i2;
        this.profilelayout2.getLayoutParams().height = i2;
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
//            this.autherquotesDetails = ArrayListUtility.autherQuotesdetails.get(1);
//            this.Quotes = this.autherquotesDetails.getQuotes();
//            this.QuotesFrameID = this.autherquotesDetails.getQuotesFrameID();
//            this.ImagePath = this.autherquotesDetails.getImagePath();
//            this.FontName = this.autherquotesDetails.getFontName();
//            this.TextColor = this.autherquotesDetails.getTextColor();
//            return;
//        }
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(1);
        this.Quotes = this.quotesFrameDetails.getQuotes();
        this.QuotesFrameID = this.quotesFrameDetails.getQuotesFrameID();
        this.ImagePath = this.quotesFrameDetails.getImagePath();
        this.FontName = this.quotesFrameDetails.getFontName();
        this.TextColor = this.quotesFrameDetails.getTextColor();
    }
}
