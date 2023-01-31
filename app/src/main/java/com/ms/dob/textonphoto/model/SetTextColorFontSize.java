package com.ms.dob.textonphoto.model;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SetTextColorFontSize {
    public static SetTextColorFontSize setTextColorFontSize;
    String TextColor;
    int TextSize;
    TextView TextView;
    ImageView backgroudimageview;
    RelativeLayout backgroundLayout;
    ImageView profileimageview;

    public static SetTextColorFontSize getInstance() {
        if (setTextColorFontSize == null) {
            setTextColorFontSize = new SetTextColorFontSize();
        }
        return setTextColorFontSize;
    }

    public void setTextView(TextView textView) {
        this.TextView = textView;
    }

    public void setTextColor(String str) {
        this.TextColor = str;
    }

    public void setTextSize(int i) {
        this.TextSize = i;
    }

    public TextView getTextView() {
        return this.TextView;
    }

    public String getTextColor() {
        return this.TextColor;
    }

    public int getTextSize() {
        return this.TextSize;
    }

    public ImageView getProfileimageview() {
        return this.profileimageview;
    }

    public ImageView getBackgroudimageview() {
        return this.backgroudimageview;
    }

    public void setProfileimageview(ImageView imageView) {
        this.profileimageview = imageView;
    }

    public void setBackgroudimageview(ImageView imageView) {
        this.backgroudimageview = imageView;
    }

    public RelativeLayout getBackgroundLayout() {
        return this.backgroundLayout;
    }

    public void setBackgroundLayout(RelativeLayout relativeLayout) {
        this.backgroundLayout = relativeLayout;
    }
}
