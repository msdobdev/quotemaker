package com.ms.dob.textonphoto.ratiolayout;

public interface RatioBase {
    FixedAttribute getFixedAttribute();

    float getHorizontalRatio();

    float getVerticalRatio();

    void setRatio(float f, float f2, boolean z);

    void setRatio(FixedAttribute fixedAttribute, float f, float f2, boolean z);
}
