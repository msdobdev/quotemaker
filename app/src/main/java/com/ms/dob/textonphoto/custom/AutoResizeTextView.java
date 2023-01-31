package com.ms.dob.textonphoto.custom;

import android.content.Context;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

public class AutoResizeTextView extends TextView {
    public static final float MIN_TEXT_SIZE = 20.0f;
    private static final String mEllipsis = "...";
    private boolean mAddEllipsis;
    private float mMaxTextSize;
    private float mMinTextSize;
    private boolean mNeedsResize;
    private float mSpacingAdd;
    private float mSpacingMult;
    private OnTextResizeListener mTextResizeListener;
    private float mTextSize;

    public interface OnTextResizeListener {
        void onTextResize(TextView textView, float f, float f2);
    }

    public AutoResizeTextView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AutoResizeTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mNeedsResize = false;
        this.mMaxTextSize = 0.0f;
        this.mMinTextSize = 20.0f;
        this.mSpacingMult = 1.0f;
        this.mSpacingAdd = 0.0f;
        this.mAddEllipsis = true;
        this.mTextSize = getTextSize();
    }

    /* access modifiers changed from: protected */
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        this.mNeedsResize = true;
        resetTextSize();
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        if (i != i3 || i2 != i4) {
            this.mNeedsResize = true;
        }
    }

    public void setOnResizeListener(OnTextResizeListener onTextResizeListener) {
        this.mTextResizeListener = onTextResizeListener;
    }

    public void setTextSize(float f) {
        super.setTextSize(f);
        this.mTextSize = getTextSize();
    }

    public void setTextSize(int i, float f) {
        super.setTextSize(i, f);
        this.mTextSize = getTextSize();
    }

    public void setLineSpacing(float f, float f2) {
        super.setLineSpacing(f, f2);
        this.mSpacingMult = f2;
        this.mSpacingAdd = f;
    }

    public void setMaxTextSize(float f) {
        this.mMaxTextSize = f;
        requestLayout();
        invalidate();
    }

    public float getMaxTextSize() {
        return this.mMaxTextSize;
    }

    public void setMinTextSize(float f) {
        this.mMinTextSize = f;
        requestLayout();
        invalidate();
    }

    public float getMinTextSize() {
        return this.mMinTextSize;
    }

    public void setAddEllipsis(boolean z) {
        this.mAddEllipsis = z;
    }

    public boolean getAddEllipsis() {
        return this.mAddEllipsis;
    }

    public void resetTextSize() {
        if (this.mTextSize > 0.0f) {
            super.setTextSize(0, this.mTextSize);
            this.mMaxTextSize = this.mTextSize;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z || this.mNeedsResize) {
            resizeText(((i3 - i) - getCompoundPaddingLeft()) - getCompoundPaddingRight(), ((i4 - i2) - getCompoundPaddingBottom()) - getCompoundPaddingTop());
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void resizeText() {
        resizeText((getWidth() - getPaddingLeft()) - getPaddingRight(), (getHeight() - getPaddingBottom()) - getPaddingTop());
    }

    public void resizeText(int i, int i2) {
        int i3 = i;
        int i4 = i2;
        CharSequence text = getText();
        if (text != null && text.length() != 0 && i4 > 0 && i3 > 0 && this.mTextSize != 0.0f) {
            if (getTransformationMethod() != null) {
                text = getTransformationMethod().getTransformation(text, this);
            }
            CharSequence charSequence = text;
            TextPaint paint = getPaint();
            float textSize = paint.getTextSize();
            float min = this.mMaxTextSize > 0.0f ? Math.min(this.mTextSize, this.mMaxTextSize) : this.mTextSize;
            int textHeight = getTextHeight(charSequence, paint, i3, min);
            float f = min;
            while (textHeight > i4 && f > this.mMinTextSize) {
                f = Math.max(f - 2.0f, this.mMinTextSize);
                textHeight = getTextHeight(charSequence, paint, i3, f);
            }
            if (this.mAddEllipsis && f == this.mMinTextSize && textHeight > i4) {
//                StaticLayout staticLayout = r1;
                StaticLayout staticLayout = new StaticLayout(charSequence, new TextPaint(paint), i, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, false);
                if (staticLayout.getLineCount() > 0) {
                    StaticLayout staticLayout3 = staticLayout;
                    int lineForVertical = staticLayout3.getLineForVertical(i4) - 1;
                    if (lineForVertical < 0) {
                        setText("");
                    } else {
                        int lineStart = staticLayout3.getLineStart(lineForVertical);
                        int lineEnd = staticLayout3.getLineEnd(lineForVertical);
                        float lineWidth = staticLayout3.getLineWidth(lineForVertical);
                        float measureText = paint.measureText(mEllipsis);
                        while (((float) i3) < lineWidth + measureText) {
                            lineEnd--;
                            lineWidth = paint.measureText(charSequence.subSequence(lineStart, lineEnd + 1).toString());
                        }
                        setText(charSequence.subSequence(0, lineEnd) + mEllipsis);
                    }
                }
            }
            setTextSize(0, f);
            setLineSpacing(this.mSpacingAdd, this.mSpacingMult);
            if (this.mTextResizeListener != null) {
                this.mTextResizeListener.onTextResize(this, textSize, f);
            }
            this.mNeedsResize = false;
        }
    }

    private int getTextHeight(CharSequence charSequence, TextPaint textPaint, int i, float f) {
        TextPaint textPaint2 = new TextPaint(textPaint);
        textPaint2.setTextSize(f);
        return new StaticLayout(charSequence, textPaint2, i, Layout.Alignment.ALIGN_NORMAL, this.mSpacingMult, this.mSpacingAdd, true).getHeight();
    }
}
