package com.ms.dob.textonphoto.fragment;

import android.app.Activity;
import android.net.Uri;
//import android.support.v7.widget.helper.ItemTouchHelper;
import androidx.recyclerview.widget.ItemTouchHelper;
import android.widget.FrameLayout;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.ms.dob.textonphoto.multitouch.MultiTouchListener;
import com.ms.dob.textonphoto.MyApplication;
import com.ms.dob.textonphoto.R;
//
import java.util.ArrayList;

public class AddImage {
    public static ImageView[] dynamicImageview = new ImageView[100];
    public static ArrayList<ImageView> imageViewsArrylist = new ArrayList<>();
    public static Activity mactivity;
    public static ImageView selectedimageview;

    public static void addImage(Activity activity, MultiTouchListener.OnClicklisnerImage onClicklisnerImage, int i) {
        mactivity = activity;
        int size = imageViewsArrylist.size();
        dynamicImageview[size] = new ImageView(activity);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        dynamicImageview[size].setLayoutParams(layoutParams);
        dynamicImageview[size].getLayoutParams().height = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        dynamicImageview[size].getLayoutParams().width = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
        ((FrameLayout) activity.findViewById(R.id.imagetextadview)).addView(dynamicImageview[size]);
        Glide.with(activity).load(Uri.parse(MyApplication.getInstance().getAddEmojiarrylist().get(i))).asBitmap().centerCrop().into(dynamicImageview[size]);
        ImageView imageView = dynamicImageview[size];
        imageView.setTag("" + size);
        dynamicImageview[size].setOnTouchListener(new MultiTouchListener((MultiTouchListener.OnClicklisner) null, onClicklisnerImage));
        imageViewsArrylist.add(dynamicImageview[size]);
        selectedimageview = dynamicImageview[size];
    }
}
