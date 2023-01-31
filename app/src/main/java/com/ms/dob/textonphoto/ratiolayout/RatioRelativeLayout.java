package com.ms.dob.textonphoto.ratiolayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.ms.dob.textonphoto.R;

public class RatioRelativeLayout extends RelativeLayout implements RatioBase {
    private FixedAttribute fixedAttribute = FixedAttribute.WIDTH;
    private float horizontalRatio = 1.0f;
    private float verticalRatio = 1.0f;

    public RatioRelativeLayout(Context context) {
        super(context);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public RatioRelativeLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    @TargetApi(21)
    public RatioRelativeLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(attributeSet);
    }

    private void init(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.RatioRelativeLayout);
        this.fixedAttribute = FixedAttribute.fromId(obtainStyledAttributes.getInt(R.styleable.RatioRelativeLayout_fixed_attribute, 0));
        this.horizontalRatio = obtainStyledAttributes.getFloat(R.styleable.RatioRelativeLayout_horizontal_ratio, 1.0f);
        this.verticalRatio = obtainStyledAttributes.getFloat(R.styleable.RatioRelativeLayout_vertical_ratio, 1.0f);
        obtainStyledAttributes.recycle();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(i2), 1073741824);
        if (this.fixedAttribute == FixedAttribute.WIDTH) {
            super.onMeasure(i, MeasureSpec.makeMeasureSpec((int) (((float) size) * (this.verticalRatio / this.horizontalRatio)), 1073741824));
        } else {
            super.onMeasure(MeasureSpec.makeMeasureSpec((int) (((float) makeMeasureSpec) * (this.horizontalRatio / this.verticalRatio)), 1073741824), i2);
        }
    }

    public void setRatio(float f, float f2, boolean z) {
        setRatio(this.fixedAttribute, f, f2, true);
    }

    public void setRatio(FixedAttribute fixedAttribute2, float f, float f2, boolean z) {
        this.fixedAttribute = fixedAttribute2;
        this.horizontalRatio = f;
        this.verticalRatio = f2;
        if (z) {
            invalidate();
            requestLayout();
        }
    }

    public float getHorizontalRatio() {
        return this.horizontalRatio;
    }

    public float getVerticalRatio() {
        return this.verticalRatio;
    }

    public FixedAttribute getFixedAttribute() {
        return this.fixedAttribute;
    }
}
