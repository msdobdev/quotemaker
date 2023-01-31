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
import com.jcminarro.roundkornerlayout.RoundKornerRelativeLayout;
import com.ms.dob.textonphoto.model.LoadFont;
import com.ms.dob.textonphoto.model.QuotesFrameDetails;
import com.ms.dob.textonphoto.model.SetTextColorFontSize;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;
import jp.wasabeef.blurry.Blurry;

public class EditDegineFourIDAndSize {
    ImageView bgblureimageview;
    View degineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    ImageView profile_image_outline;
    RoundKornerRelativeLayout profilelayout;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textCirclelayout;

    public void GetDegineFourID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d4);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_4);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_4);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_4);
        this.textCirclelayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_4);
        this.bgblureimageview = (ImageView) this.degineView.findViewById(R.id.blurimageview_4);
        this.profilelayout = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout_3);
        layoutsize(activity);
        setData(activity);
        if (i == 3) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroudimageview(this.bgblureimageview);
        SetTextColorFontSize.getInstance().setBackgroundLayout((RelativeLayout) null);
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
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(3);
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
