package com.ms.dob.textonphoto.ratiolayout;

public enum FixedAttribute {
    HEIGHT(0),
    WIDTH(1);
    

    /* renamed from: id */
    final int f236id;

    private FixedAttribute(int i) {
        this.f236id = i;
    }

    public static FixedAttribute fromId(int i) {
        for (FixedAttribute fixedAttribute : values()) {
            if (fixedAttribute.f236id == i) {
                return fixedAttribute;
            }
        }
        throw new IllegalArgumentException();
    }
}
