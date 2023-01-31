package com.ms.dob.textonphoto.utility;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.ms.dob.textonphoto.utility.ScreenDimension;

public class ImageSizeUtility {
    public static float recyheight;
    public static float wright;

    public static void setCatergoryimageSize(Activity activity, RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
        float weight = ((((float) ScreenDimension.getWeight(activity)) / 100.0f) * 95.0f) / 3.0f;
        float height = ((((float) ScreenDimension.getHeight(activity)) / 100.0f) * 40.0f) / 2.0f;
        if (relativeLayout != null) {
            relativeLayout.getLayoutParams().height = (int) height;
            relativeLayout.getLayoutParams().width = (int) weight;
        }
        if (imageView != null) {
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            double d = (double) weight;
            Double.isNaN(d);
            int i = (int) (d / 2.2d);
            layoutParams.height = i;
            imageView.getLayoutParams().width = i;
            double d2 = (double) height;
            Double.isNaN(d2);
            imageView.setY((float) ((int) (d2 / 5.3d)));
        }
        double d3 = (double) height;
        Double.isNaN(d3);
        textView.setY((float) ((int) (d3 / 1.3d)));
    }
//
//    public static void setGalleryimageSize(ImageView imageView, Activity activity) {
//        int weight = ScreenDimension.getWeight(activity) / 3;
//        if (imageView != null) {
//            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//            double d = (double) weight;
//            Double.isNaN(d);
//            Double.isNaN(d);
//            layoutParams.height = (int) (d + (d / 2.5d));
//            imageView.getLayoutParams().width = weight;
//        }
//    }

//    public static void setStickerimageSize(ImageView imageView, Activity activity) {
//        double weight = (double) ScreenDimension.getWeight(activity);
//        Double.isNaN(weight);
//        int i = (int) (weight / 3.5d);
//        if (imageView != null) {
//            imageView.getLayoutParams().height = i;
//            imageView.getLayoutParams().width = i;
//        }
//    }

//    public static void Gapimagemarzine(ImageView imageView, Activity activity) {
//        int weight = ScreenDimension.getWeight(activity) / 3;
//        if (imageView != null) {
//            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//            double d = (double) weight;
//            Double.isNaN(d);
//            Double.isNaN(d);
//            layoutParams.height = ((int) (d + (d / 2.5d))) + 40;
//            imageView.getLayoutParams().width = weight + 40;
//        }
//    }

    public static float convertDpToPixel(float f, Activity activity) {
        return f * (((float) activity.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void setFountAdpterTextSize(RelativeLayout relativeLayout, Activity activity) {
        int weight = ScreenDimension.getWeight(activity) / 3;
        if (relativeLayout != null) {
            relativeLayout.getLayoutParams().height = weight / 3;
            relativeLayout.getLayoutParams().width = weight;
        }
    }

    public static float convertPixelsToDp(float f, Context context) {
        return f / (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f);
    }

    public static void setAddimagesize(ImageView imageView, Activity activity) {
        double weight = (double) ScreenDimension.getWeight(activity);
        Double.isNaN(weight);
        int i = (int) (weight / 4.5d);
        if (imageView != null) {
            imageView.getLayoutParams().height = i;
            imageView.getLayoutParams().width = i;
        }
    }

//    public static void MenuScreenImageeSize(ImageView imageView, Activity activity) {
//        double weight = (double) ((ScreenDimension.getWeight(activity) / 2) - ((ScreenDimension.getWeight(activity) / 100) * 5));
//        ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//        Double.isNaN(weight);
//        Double.isNaN(weight);
//        layoutParams.height = (int) (weight - (weight / 5.0d));
//        imageView.getLayoutParams().width = (int) weight;
//    }

//    public static void setAdsMenuScreeImageeSize(RelativeLayout relativeLayout, Activity activity, ImageView imageView, ImageView imageView2, TextView textView, ImageView imageView3) {
//        double weight = (double) (ScreenDimension.getWeight(activity) / 3);
//        double weight2 = (double) (ScreenDimension.getWeight(activity) / 100);
//        Double.isNaN(weight2);
//        Double.isNaN(weight);
//        double d = weight - (weight2 * 2.5d);
//        relativeLayout.getLayoutParams().height = (int) (1.4d * d);
//        int i = (int) d;
//        relativeLayout.getLayoutParams().width = i;
//        double d2 = d / 1.5d;
//        int i2 = (int) d2;
//        imageView.getLayoutParams().height = i2;
//        imageView.getLayoutParams().width = i2;
//        imageView.setY((float) ((((int) (1.5d * d)) / 2) - ((int) (d / 1.6d))));
//        int i3 = (int) (d / 2.0d);
//        imageView2.getLayoutParams().width = i3;
//        imageView2.getLayoutParams().height = i3 / 3;
//        imageView2.setY((float) ((int) ((d / 6.0d) + d2)));
//        textView.setY((float) ((int) (d2 + (d / 2.6d))));
//        textView.getLayoutParams().width = i;
//        imageView3.setX((float) ((int) (d / 1.25d)));
//    }

//    public static void setMenuScreeImageeSize(RelativeLayout relativeLayout, RecyclerView recyclerView, Activity activity) {
//        recyheight = (((float) ScreenDimension.getHeight(activity)) / 100.0f) * 40.0f;
//        wright = (((float) ScreenDimension.getWeight(activity)) / 100.0f) * 95.0f;
//        relativeLayout.getLayoutParams().height = (int) recyheight;
//        relativeLayout.getLayoutParams().width = (int) wright;
//        recyclerView.getLayoutParams().height = (int) recyheight;
//    }

//    public static void setQuotesCatergoryimageSize(Activity activity, RelativeLayout relativeLayout, ImageView imageView, TextView textView) {
//        float f = wright / 2.0f;
//        if (relativeLayout != null) {
//            int i = (int) f;
//            relativeLayout.getLayoutParams().height = i;
//            relativeLayout.getLayoutParams().width = i;
//        }
//        if (imageView != null) {
//            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
//            double d = (double) f;
//            Double.isNaN(d);
//            int i2 = (int) (d / 1.25d);
//            layoutParams.height = i2;
//            imageView.getLayoutParams().width = i2;
//        }
//    }
}
