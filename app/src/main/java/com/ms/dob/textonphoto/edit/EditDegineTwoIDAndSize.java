package com.ms.dob.textonphoto.edit;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.jcminarro.roundkornerlayout.CornerType;
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;

public class EditDegineTwoIDAndSize {
    View degineView;
    View lineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    RoundKornerRelativeLayout profilelayout;
    View profilelayout2;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;

    public void GetDegineTwoID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d2);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_2);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_2);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_2);
        this.lineView = this.degineView.findViewById(R.id.lineview_2);
        this.profilelayout = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout_2);
        this.profilelayout2 = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout2);
        if (i == 1) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        layoutsize(activity);
        setData(activity);
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroundLayout(this.mainLayout);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        double d2 = EditDegineOneIdAndSize.Degine_H;
        this.mainLayout.getLayoutParams().width = (int) d;
        this.mainLayout.getLayoutParams().height = (int) d2;
        this.profilelayout.setCornerRadius((float) d, CornerType.ALL);
        int i = (int) (d / 1.4d);
        this.profileImageview.getLayoutParams().width = i;
        this.profileImageview.getLayoutParams().height = (int) (d2 / 1.4d);
        this.profilelayout.getLayoutParams().width = i;
        this.profilelayout.getLayoutParams().height = i;
        int i2 = (int) (d / 1.43d);
        this.profilelayout2.getLayoutParams().width = i2;
        this.profilelayout2.getLayoutParams().height = i2;
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(1);
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
