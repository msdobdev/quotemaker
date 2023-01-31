package com.ms.dob.textonphoto.model;

import android.graphics.Color;
import android.widget.TextView;
import java.util.ArrayList;

public class ShadowDetails {
    public static ArrayList<ShadowDetails> shadowdetails = new ArrayList<>();
    public int colorbarPossition = 0;
    public int shadowRadious = 1;
    public int shadowX = 1;
    public int shadowY = 1;
    public int shadowcolor = Color.parseColor("#ffffff");
    TextView textView;

    public void SetShadowOnText(TextView textView2, int i, int i2, int i3, int i4, int i5) {
        if (textView2 != null) {
            additeamonlist(textView2, i, i2, i3, i4, i5);
            ShadowDetails shadowDetails = shadowdetails.get(getIteampo(textView2));
            textView2.setShadowLayer((float) shadowDetails.getShadowRadious(), (float) shadowDetails.getShadowX(), (float) shadowDetails.getShadowY(), shadowDetails.getShadowcolor());
        }
    }

    public TextView getTextView() {
        return this.textView;
    }

    public void setTextView(TextView textView2) {
        this.textView = textView2;
    }

    public int getShadowcolor() {
        return this.shadowcolor;
    }

    public int getShadowX() {
        return this.shadowX;
    }

    public int getShadowY() {
        return this.shadowY;
    }

    public int getShadowRadious() {
        return this.shadowRadious;
    }

    public void setShadowcolor(int i) {
        this.shadowcolor = i;
    }

    public void setShadowX(int i) {
        this.shadowX = i;
    }

    public void setShadowY(int i) {
        this.shadowY = i;
    }

    public void setShadowRadious(int i) {
        this.shadowRadious = i;
    }

    public int getColorbarPossition() {
        return this.colorbarPossition;
    }

    public void setColorbarPossition(int i) {
        this.colorbarPossition = i;
    }

    public void additeamonlist(TextView textView2, int i, int i2, int i3, int i4, int i5) {
        ShadowDetails shadowDetails = new ShadowDetails();
        shadowDetails.setShadowcolor(i);
        shadowDetails.setShadowX(i2);
        shadowDetails.setShadowY(i3);
        shadowDetails.setShadowRadious(i4);
        shadowDetails.setTextView(textView2);
        shadowDetails.setColorbarPossition(i5);
        shadowdetails.add(shadowDetails);
    }

    public void UpdateShadowiteam(ShadowDetails shadowDetails, TextView textView2, int i, int i2, int i3, int i4, int i5, int i6) {
        shadowDetails.setShadowcolor(i);
        shadowDetails.setShadowX(i2);
        shadowDetails.setShadowY(i3);
        shadowDetails.setShadowRadious(i4);
        shadowDetails.setTextView(textView2);
        shadowDetails.setColorbarPossition(i6);
        shadowdetails.set(i5, shadowDetails);
    }

    public static int getIteampo(TextView textView2) {
        for (int i = 0; i < shadowdetails.size(); i++) {
            if (shadowdetails.get(i).getTextView() == textView2) {
                return i;
            }
        }
        return -1;
    }
}
