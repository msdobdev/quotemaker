package com.ms.dob.textonphoto.edit;

import android.app.Activity;
import android.graphics.BitmapFactory;
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
import jp.wasabeef.blurry.Blurry;

public class EditDegineThreeIDAndSize {
    ImageView bgblureimageview;
    View degineView;
    View lineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    RoundKornerRelativeLayout profilelayout;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;

    public void GetDegineThreeID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d3);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_3);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_3);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_3);
        this.lineView = this.degineView.findViewById(R.id.lineview_3);
        this.bgblureimageview = (ImageView) this.degineView.findViewById(R.id.blurimageview);
        this.profilelayout = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout_3);
        if (i == 2) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        layoutsize(activity);
        setData(activity);
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroudimageview(this.bgblureimageview);
        SetTextColorFontSize.getInstance().setBackgroundLayout((RelativeLayout) null);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        double d2 = EditDegineOneIdAndSize.Degine_H;
        this.profilelayout.setCornerRadius((float) d, CornerType.ALL);
        this.mainLayout.getLayoutParams().width = (int) d;
        this.mainLayout.getLayoutParams().height = (int) d2;
        this.profileImageview.getLayoutParams().width = (int) (d / 1.4d);
        this.profileImageview.getLayoutParams().height = (int) (d2 / 1.4d);
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(2);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setText(this.quotesFrameDetails.getQuotes());
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
            Blurry.with(activity).radius(25).async().animate().from(BitmapFactory.decodeFile(this.quotesFrameDetails.getImagePath())).into(this.bgblureimageview);
            return;
        }
        Blurry.with(activity).radius(25).async().animate().from(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image_found)).into(this.bgblureimageview);
        Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
    }
}
