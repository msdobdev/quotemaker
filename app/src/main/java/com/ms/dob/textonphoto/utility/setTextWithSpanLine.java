package com.ms.dob.textonphoto.utility;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.LineBackgroundSpan;
import android.widget.TextView;

public class setTextWithSpanLine {
    public static void setTextWithSpan(TextView textView, int icolor, SpannableString spannableString, float f) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(spannableString);
        spannableStringBuilder.setSpan(new LineBackgroundSpan() {

            @Override
            public void drawBackground(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, int i8) {
                RectF rect= new RectF();
                rect.set(f,f,f,f);
                int paddingSize = 12;
                int round = Math.round(paint.measureText(charSequence, i6, i7));
                int color = paint.getColor();
                if (i8 == 0) {
                    rect.set((float) (i - (paddingSize / 2)), (float) (i3 - (paddingSize / 4)), (float) (i + round + (paddingSize / 2)), ((float) i3) + textView.getTextSize() + ((float) paddingSize));
                } else {
                    rect.set((float) (i - (paddingSize / 2)), (float) (i3 - (paddingSize / 4)), (float) (i + round + (paddingSize / 2)), ((float) i3) + textView.getTextSize() + ((float) paddingSize));
                }
                paint.setColor(icolor);
                canvas.drawRoundRect(rect, 0, 0, paint);
                paint.setColor(color);
            }
        },0, spannableString.length(), 33);
        textView.setShadowLayer((float) 12, 0.0f, 0.0f, 0);
        textView.setLineSpacing(0.0f, f);
        textView.setText(spannableStringBuilder, TextView.BufferType.NORMAL);
    }
}
