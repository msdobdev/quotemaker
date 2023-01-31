package com.ms.dob.textonphoto.edit;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.text.SpannableString;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.setTextWithSpanLine;

import java.io.File;

public class EditDegineTenIdAndSize {
    View degineView;
    public RelativeLayout imagemainlayout;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textmainlayout;

    public void GetDegineTen(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d10);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_10);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_10);
        this.imagemainlayout = (RelativeLayout) this.degineView.findViewById(R.id.profilelayout_10);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.quoetstextview_10);
        this.textmainlayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_10);
        layoutsize(activity);
        setData(activity);
        if (i == 9) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        this.quoetsTextview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroudimageview((ImageView) null);
        SetTextColorFontSize.getInstance().setBackgroundLayout((RelativeLayout) null);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        double weight = (double) ((ScreenDimension.getWeight(activity) / 100) * 20);
        Double.isNaN(weight);
        double d2 = d - weight;
        double d3 = (double) ((int) (EditDegineOneIdAndSize.Degine_H * 1.5d));
        this.mainLayout.getLayoutParams().width = (int) d2;
        this.mainLayout.getLayoutParams().height = (int) d3;
        this.quoetsTextview.setY((float) ((int) (EditDegineOneIdAndSize.Degine_H / 1.8d)));
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(9);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        setTextWithSpanLine.setTextWithSpan(this.quoetsTextview, Color.parseColor("#E6ffffff"), new SpannableString(this.quotesFrameDetails.getQuotes()), 1.5f);
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        } else {
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        }
    }
}
