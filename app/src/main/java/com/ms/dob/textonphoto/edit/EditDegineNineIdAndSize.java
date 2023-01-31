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
//
import java.io.File;

public class EditDegineNineIdAndSize {
    View degineView;
    public RelativeLayout imagemainlayout;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout textmainlayout;

    public void GetDegineNine(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d9);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout_9);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile_9);
        this.imagemainlayout = (RelativeLayout) this.degineView.findViewById(R.id.profilelayout_9);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets_9);
        this.textmainlayout = (RelativeLayout) this.degineView.findViewById(R.id.textcircellayout_9);
        layoutsize(activity);
        setData(activity);
        if (i == 8) {
            this.degineView.setVisibility(0);
        } else {
            this.degineView.setVisibility(8);
        }
        this.profileImageview.setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, (MultiTouchListener.OnClicklisnerImage) null));
        SetTextColorFontSize.getInstance().setTextView(this.quoetsTextview);
        SetTextColorFontSize.getInstance().setProfileimageview(this.profileImageview);
        SetTextColorFontSize.getInstance().setBackgroudimageview((ImageView) null);
        SetTextColorFontSize.getInstance().setBackgroundLayout((RelativeLayout) null);
    }

    private void layoutsize(Activity activity) {
        double d = EditDegineOneIdAndSize.Degine_W;
        this.mainLayout.getLayoutParams().width = (int) d;
        this.mainLayout.getLayoutParams().height = (int) ((double) ((int) (d / 1.4d)));
        this.textmainlayout.getLayoutParams().width = (int) (d / 2.5d);
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(8);
        this.loadFont = new LoadFont(this.quotesFrameDetails.getFontName(), activity);
        this.quoetsTextview.setTypeface(this.loadFont.getCustamfount());
        this.quoetsTextview.setTextColor(Color.parseColor(this.quotesFrameDetails.getTextColor()));
        this.quoetsTextview.setText(this.quotesFrameDetails.getQuotes());
        if (!this.quotesFrameDetails.getImagePath().equalsIgnoreCase("")) {
            Glide.with(activity).load(Uri.fromFile(new File(this.quotesFrameDetails.getImagePath())).toString()).asBitmap().centerCrop().placeholder((int) R.drawable.ic_loader).into(this.profileImageview);
        } else {
            Glide.with(activity).load(Integer.valueOf(R.drawable.ic_no_image_found)).centerCrop().into(this.profileImageview);
        }
    }
}
