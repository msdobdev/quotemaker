package com.ms.dob.textonphoto.viewholder;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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

public class DegineOneViewHolder extends RecyclerView.ViewHolder {
//    public static int frimwweight = 0;
    public static int layoutpercnt = 15;
    public String FontName = "";
    public String ImagePath = "";
    public String Quotes = "";
    public String QuotesFrameID = "";
    public String TextColor = "";
//    TextView authernameText;
    RelativeLayout bottomLayout;
    View lineView;
    LoadFont loadFont;
    RelativeLayout mainLayout;
    ImageView profileImageview;
    RoundKornerRelativeLayout profilelayout;
    RoundKornerRelativeLayout profilelayout2;
    TextView quoetsTextview;
    RelativeLayout uperLayout;
    public static String[] AuthernameColorcode = {"#324EFF", "#E24A34", "#00A012", "#9E3B11", "#5E759B", "#F97F87", "#3DF7CC", "#7738F4", "#7DA095", "#55359E"};

    public DegineOneViewHolder(View view, Activity activity, String str) {
        super(view);
        this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainlayout);
        this.uperLayout = (RelativeLayout) view.findViewById(R.id.layout_uper);
        this.bottomLayout = (RelativeLayout) view.findViewById(R.id.layout_bottom);
        this.profileImageview = (ImageView) view.findViewById(R.id.iv_profile);
        this.quoetsTextview = (TextView) view.findViewById(R.id.tv_quoets);
        this.lineView = view.findViewById(R.id.lineview);
        this.profilelayout = (RoundKornerRelativeLayout) view.findViewById(R.id.profilelayout);
        this.profilelayout2 = (RoundKornerRelativeLayout) view.findViewById(R.id.profilelayout2);
        layoutsize(activity);
        setData(activity, str);
    }

    private void layoutsize(Activity activity) {
        double weight = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * layoutpercnt));
        Double.isNaN(weight);
        Double.isNaN(weight);
        double d = (double) ((int) (weight - (weight / 10.0d)));
        this.profilelayout.setCornerRadius((float) weight, CornerType.ALL);
        int i = (int) weight;
        this.mainLayout.getLayoutParams().width = i;
        this.mainLayout.getLayoutParams().height = (int) d;
        this.uperLayout.getLayoutParams().width = i;
        ViewGroup.LayoutParams layoutParams = this.uperLayout.getLayoutParams();
        Double.isNaN(d);
        layoutParams.height = (int) (d / 2.0d);
        ViewGroup.LayoutParams layoutParams2 = this.profileImageview.getLayoutParams();
        Double.isNaN(weight);
        int i2 = (int) (weight / 1.6d);
        layoutParams2.width = i2;
        this.profileImageview.getLayoutParams().height = i2;
        this.profilelayout.getLayoutParams().width = i2;
        this.profilelayout.getLayoutParams().height = i2;
        ViewGroup.LayoutParams layoutParams3 = this.profilelayout2.getLayoutParams();
        Double.isNaN(weight);
        int i3 = (int) (weight / 1.63d);
        layoutParams3.width = i3;
        this.profilelayout2.getLayoutParams().height = i3;
    }

    private void setData(Activity activity, String str) {
        getFramedetialdata(str);
        this.loadFont = new LoadFont(this.FontName, activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setText(TextAnsTextcolor(this.Quotes, str, this.QuotesFrameID));
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
//            QuotesFrameDetails quotesFrameDetails = ArrayListUtility.autherQuotesdetails.get(0);
//            this.Quotes = quotesFrameDetails.getQuotes();
//            this.QuotesFrameID = quotesFrameDetails.getQuotesFrameID();
//            this.ImagePath = quotesFrameDetails.getImagePath();
//            this.FontName = quotesFrameDetails.getFontName();
//            this.TextColor = quotesFrameDetails.getTextColor();
//            return;
//        }
        QuotesFrameDetails quotesFrameDetails2 = MyApplication.getInstance().getQuotesframdetails().get(0);
        this.Quotes = quotesFrameDetails2.getQuotes();
        this.QuotesFrameID = quotesFrameDetails2.getQuotesFrameID();
        this.ImagePath = quotesFrameDetails2.getImagePath();
        this.FontName = quotesFrameDetails2.getFontName();
        this.TextColor = quotesFrameDetails2.getTextColor();
    }

    public static SpannableString TextAnsTextcolor(String str, String str2, String str3) {
        String str4;
        int parseColor = Color.parseColor(AuthernameColorcode[Integer.parseInt(str3)]);
        if (!str2.equalsIgnoreCase("")) {
            str4 = "“" + str + "”-" + str2 + "-";
        } else {
            str4 = "“" + str + "”";
        }
        SpannableString spannableString = new SpannableString(str4);
        spannableString.setSpan(new ForegroundColorSpan(parseColor), str.length() + 2, str4.length(), 0);
        return spannableString;
    }
}
