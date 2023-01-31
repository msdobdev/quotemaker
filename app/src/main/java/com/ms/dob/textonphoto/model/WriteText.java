package com.ms.dob.textonphoto.model;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ms.dob.textonphoto.adapter.FontAdapter;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.R;
import com.ms.dob.textonphoto.utility.ImageSizeUtility;
import java.util.ArrayList;

public class WriteText {
    public static TextView[] dynamicTextView = new TextView[100];
    static Activity mactivity;
    public static ArrayList<TextView> textViewArrayList = new ArrayList<>();
    public static Typeface typeface;
    public static TextView weitetextview;

    public static void addtext(Activity activity, MultiTouchListener.OnClicklisner onClicklisner, String str) {
        AssetManager assets = activity.getAssets();
        typeface = Typeface.createFromAsset(assets, "font/" + FontAdapter.fontname[1]);
        mactivity = activity;
        int size = textViewArrayList.size();
        dynamicTextView[size] = new TextView(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        SetText(dynamicTextView[size], str);
        SetTextColor(dynamicTextView[size], "#ffffff");
        SetTextSize(dynamicTextView[size], ImageSizeUtility.convertPixelsToDp(50.0f, mactivity));
        SetTextFont(dynamicTextView[size], typeface);
        dynamicTextView[size].setPadding(30, 10, 10, 10);
        dynamicTextView[size].setLayoutParams(layoutParams);
        TextView textView = dynamicTextView[size];
        textView.setTag("" + size);
        dynamicTextView[size].setGravity(17);
        ((FrameLayout) activity.findViewById(R.id.imagetextadview)).addView(dynamicTextView[size]);
        textViewArrayList.add(dynamicTextView[size]);
        dynamicTextView[size].setOnTouchListener(new MultiTouchListener(onClicklisner, (MultiTouchListener.OnClicklisnerImage) null));
        weitetextview = dynamicTextView[size];
//        EditingLayoutId.Set_Delete_EditText(-1);
    }

    public static void SetText(TextView textView, String str) {
        textView.setText(str);
    }

    public static String GetText(TextView textView) {
        return textView.getText().toString();
    }

    public static void SetTextColor(TextView textView, String str) {
        textView.setTextColor(Color.parseColor(str));
    }

    public static ColorStateList GetTextColor(TextView textView) {
        return textView.getTextColors();
    }

    public static void SetTextFont(TextView textView, Typeface typeface2) {
        textView.setTypeface(typeface2);
    }

    public static Typeface GetTextFont(TextView textView) {
        return textView.getTypeface();
    }

    public static void SetTextSize(TextView textView, float f) {
        textView.setTextSize(f);
    }
}
