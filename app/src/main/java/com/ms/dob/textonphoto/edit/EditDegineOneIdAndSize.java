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
import com.ms.dob.textonphoto.utility.ScreenDimension;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;

import java.io.File;

public class EditDegineOneIdAndSize {
    public static double Degine_H = 0.0d;
    public static double Degine_W = 0.0d;
    public static int Editlayoutpercentmarzine = 7;
    public RelativeLayout bottomLayout;
    View degineView;
    View lineView;
    LoadFont loadFont;
    public RelativeLayout mainLayout;
    ImageView profileImageview;
    RoundKornerRelativeLayout profilelayout;
    RoundKornerRelativeLayout profilelayout2;
    TextView quoetsTextview;
    QuotesFrameDetails quotesFrameDetails;
    public RelativeLayout uperLayout;

    public void GetDegine_OneId(Activity activity, int i) {
        this.degineView = activity.findViewById(R.id.d1);
        this.mainLayout = (RelativeLayout) this.degineView.findViewById(R.id.mainlayout);
        this.uperLayout = (RelativeLayout) this.degineView.findViewById(R.id.layout_uper);
        this.bottomLayout = (RelativeLayout) this.degineView.findViewById(R.id.layout_bottom);
        this.profileImageview = (ImageView) this.degineView.findViewById(R.id.iv_profile);
        this.quoetsTextview = (TextView) this.degineView.findViewById(R.id.tv_quoets);
        this.lineView = this.degineView.findViewById(R.id.lineview);
        this.profilelayout = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout);
        this.profilelayout2 = (RoundKornerRelativeLayout) this.degineView.findViewById(R.id.profilelayout2);
        if (i == 0) {
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
        Degine_W = (double) (ScreenDimension.getWeight(activity) - ((ScreenDimension.getWeight(activity) / 100) * Editlayoutpercentmarzine));
        Degine_H = (double) ((int) (Degine_W - (Degine_W / 28.0d)));
        this.profilelayout.setCornerRadius((float) Degine_W, CornerType.ALL);
        this.mainLayout.getLayoutParams().width = (int) Degine_W;
        this.mainLayout.getLayoutParams().height = (int) Degine_H;
        this.uperLayout.getLayoutParams().width = (int) Degine_W;
        this.uperLayout.getLayoutParams().height = (int) (Degine_H / 2.0d);
        this.profileImageview.getLayoutParams().width = (int) (Degine_W / 1.4d);
        this.profileImageview.getLayoutParams().height = (int) (Degine_W / 1.4d);
        this.profilelayout.getLayoutParams().width = (int) (Degine_W / 1.4d);
        this.profilelayout.getLayoutParams().height = (int) (Degine_W / 1.4d);
        this.profilelayout2.getLayoutParams().width = (int) (Degine_W / 1.43d);
        this.profilelayout2.getLayoutParams().height = (int) (Degine_W / 1.43d);
    }

    private void setData(Activity activity) {
        this.quotesFrameDetails = MyApplication.getInstance().getQuotesframdetails().get(0);
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
