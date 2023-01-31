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

public class EditDegineSixIDAndSize {
    View degineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    ImageView profileImageviewBg;
    public RelativeLayout profilelayout;
    public RelativeLayout profilelayout_move;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textbglayout;
    public RelativeLayout textmainlayout;

    public void GetDegineSixID(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d6);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_6);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_6);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_6);
        this.textmainlayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_6);
        this.profilelayout = (RelativeLayout) this.degineView.findViewById(R.id.profilelayout_6);
        this.profileImageviewBg = (ImageView) this.degineView.findViewById(R.id.profilebg);
        this.textbglayout = (RelativeLayout) this.degineView.findViewById(R.id.textlayoutbg);
        this.profilelayout_move = (RelativeLayout) this.degineView.findViewById(R.id.profilelayout_move);
        layoutsize(activity);
        setData(activity);
        if (i == 5) {
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
        this.profilelayout.getLayoutParams().width = (int) (d / 1.1d);
        this.profilelayout.getLayoutParams().height = (int) (d2 / 1.1d);
        int i = (int) (d / 1.3d);
        this.profileImageview.getLayoutParams().width = i;
        int i2 = (int) (d2 / 1.3d);
        this.profileImageview.getLayoutParams().height = i2;
        this.profileImageviewBg.getLayoutParams().width = i;
        this.profileImageviewBg.getLayoutParams().height = i2;
        this.profilelayout_move.getLayoutParams().width = i;
        this.profilelayout_move.getLayoutParams().height = i2;
        this.textmainlayout.getLayoutParams().width = (int) (d / 1.8d);
        int i3 = (int) (d / 3.0d);
        this.textmainlayout.getLayoutParams().height = i3;
        this.textmainlayout.setX((float) i3);
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(5);
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
