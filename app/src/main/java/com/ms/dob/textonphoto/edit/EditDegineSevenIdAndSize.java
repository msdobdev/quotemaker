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
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.setTextWithSpanLine;

import java.io.File;

public class EditDegineSevenIdAndSize {
    View degineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textmainlayout;

    public void GetDegineSevenID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d7);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_7);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_7);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_7);
        this.textmainlayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_7);
        layoutsize(activity);
        setData(activity);
        if (i == 6) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroudimageview((ImageView) null);
        SetTextColorFontSize.getInstance().setBackgroundLayout(this.mainLayout);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        double d2 = EditDegineOneIdAndSize.Degine_H;
        this.mainLayout.getLayoutParams().width = (int) d;
        this.mainLayout.getLayoutParams().height = (int) d2;
        this.profileImageview.getLayoutParams().height = (int) (d2 / 1.7d);
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(6);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        setTextWithSpanLine.setTextWithSpan(this.quoetsTextview, -1, new SpannableString(this.quotesFrameDetails.getQuotes()), 1.5f);
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        } else {
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        }
    }
}
