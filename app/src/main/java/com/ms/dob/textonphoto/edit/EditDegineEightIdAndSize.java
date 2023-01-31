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
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;
import jp.wasabeef.blurry.Blurry;

public class EditDegineEightIdAndSize {
    View degineView;
    public RelativeLayout imagemainlayout;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview_blur;
    ImageView profileImageview_uper;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textmainlayout;

    public void GetDegineEight(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d8);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_8);
        this.profileImageview_uper = (ImageView) this.degineView.findViewById(R.id.iv_profile_8_uper);
        this.profileImageview_blur = (ImageView) this.degineView.findViewById(R.id.iv_profile_8_blur);
        this.imagemainlayout = (RelativeLayout) this.degineView.findViewById(R.id.profilelayout_8);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_8);
        this.textmainlayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_8);
        layoutsize(activity);
        setData(activity);
        if (i == 7) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        this.profileImageview_uper.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview_uper);
        SetTextColorFontSize.getInstance().setBackgroudimageview(this.profileImageview_blur);
        SetTextColorFontSize.getInstance().setBackgroundLayout(this.mainLayout);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        double d2 = EditDegineOneIdAndSize.Degine_H;
        this.mainLayout.getLayoutParams().width = (int) d;
        this.mainLayout.getLayoutParams().height = (int) d2;
        int i = (int) (d2 / 1.7d);
        this.profileImageview_blur.getLayoutParams().height = i;
        this.imagemainlayout.getLayoutParams().height = i;
        this.profileImageview_uper.getLayoutParams().height = i;
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(7);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        this.quoetsTextview.setText(this.quotesFrameDetails.getQuotes());
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview_uper);
            Blurry.with(activity).radius(5).async().from(BitmapFactory.decodeFile(this.quotesFrameDetails.getImagePath())).into(this.profileImageview_blur);
            return;
        }
        Blurry.with(activity).radius(5).async().from(BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_no_image_found)).into(this.profileImageview_blur);
        Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview_uper);
    }
}
