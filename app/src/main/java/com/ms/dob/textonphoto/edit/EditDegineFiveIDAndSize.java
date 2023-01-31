package com.ms.dob.textonphoto.edit;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
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

import java.io.File;

public class EditDegineFiveIDAndSize {
    View degineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textCirclelayout;

    public void GetDegineFiveID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d5);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_5);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_5);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_5);
        this.textCirclelayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_5);
        layoutsize(activity);
        setData(activity);
        if (i == 4) {
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
        int i = (int) d2;
        this.mainLayout.getLayoutParams().height = i;
        this.profileImageview.getLayoutParams().width = (int) (d / 1.7d);
        this.profileImageview.getLayoutParams().height = i;
        int i2 = (int) (d / 1.8d);
        this.textCirclelayout.getLayoutParams().width = i2;
        this.textCirclelayout.getLayoutParams().height = i2;
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(4);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setText(this.quotesFrameDetails.getQuotes());
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        } else {
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        }
    }
}
