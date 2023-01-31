package com.ms.dob.textonphoto.utility;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class GradientColorUtility {

    /* renamed from: c1 */
    public static String[] f239c1 = {"#E70557", "#FEC540", "#65A875", "#F8CDBD", "#55BDCA", "#E984BC", "#F7BA77", "#FF9B6F", "#29C0D5", "#1ADCC2", "#E96A73", "#2EC9DD", "#E95963", "#4D5361", "#ABDCFF", "#81FFEF", "#E2B0FF", "#FF96F9", "#FFA8A8", "#3C8CE7", "#FFF5C3", "#F05F57", "#FFD3A5"};

    /* renamed from: c2 */
    public static String[] f240c2 = {"#492DB6", "#C12CA2", "#29AD57", "#712F6F", "#BF7BC8", "#6A47AB", "#C22E86", "#6D339A", "#5C37A0", "#8993EC", "#173A7C", "#B752D2", "#933A8C", "#22252C", "#0396FF", "#F067B4", "#9F44D3", "#C32BAC", "#FCFF00", "#00EAFF", "#9452A5", "#360940", "#FD6585"};

    public static void changeGradientColor(Context context, int i, ImageView imageView, int i2) {
        float f = ImageSizeUtility.wright;
        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{Color.parseColor(f239c1[i]), Color.parseColor(f240c2[i])});
        if (i2 != 0) {
            gradientDrawable.setSize(i2, i2);
        }
        Glide.with(context).load("").fitCenter().placeholder((Drawable) gradientDrawable).into(imageView);
    }
}
