package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RatioData.kt */

/* loaded from: classes.dex */
public final class RatioData {
    private int rHeight;
    private int rWidth;
    private int ratioImage;
    private String ratioSize;

    public RatioData(int i, String ratioSize, int i2, int i3) {
        Intrinsics.checkNotNullParameter(ratioSize, "ratioSize");
        this.ratioImage = i;
        this.ratioSize = ratioSize;
        this.rWidth = i2;
        this.rHeight = i3;
    }

    public static /* synthetic */ RatioData copy$default(RatioData ratioData, int i, String str, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i = ratioData.ratioImage;
        }
        if ((i4 & 2) != 0) {
            str = ratioData.ratioSize;
        }
        if ((i4 & 4) != 0) {
            i2 = ratioData.rWidth;
        }
        if ((i4 & 8) != 0) {
            i3 = ratioData.rHeight;
        }
        return ratioData.copy(i, str, i2, i3);
    }

    public final int component1() {
        return this.ratioImage;
    }

    public final String component2() {
        return this.ratioSize;
    }

    public final int component3() {
        return this.rWidth;
    }

    public final int component4() {
        return this.rHeight;
    }

    public final RatioData copy(int i, String ratioSize, int i2, int i3) {
        Intrinsics.checkNotNullParameter(ratioSize, "ratioSize");
        return new RatioData(i, ratioSize, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RatioData) {
            RatioData ratioData = (RatioData) obj;
            return this.ratioImage == ratioData.ratioImage && Intrinsics.areEqual(this.ratioSize, ratioData.ratioSize) && this.rWidth == ratioData.rWidth && this.rHeight == ratioData.rHeight;
        }
        return false;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.ratioImage) * 31) + this.ratioSize.hashCode()) * 31) + Integer.hashCode(this.rWidth)) * 31) + Integer.hashCode(this.rHeight);
    }

    public String toString() {
        int i = this.ratioImage;
        String str = this.ratioSize;
        int i2 = this.rWidth;
        return "RatioData(ratioImage=" + i + ", ratioSize=" + str + ", rWidth=" + i2 + ", rHeight=" + this.rHeight + ")";
    }

    public final int getRHeight() {
        return this.rHeight;
    }

    public final void setRHeight(int i) {
        this.rHeight = i;
    }

    public final int getRWidth() {
        return this.rWidth;
    }

    public final void setRWidth(int i) {
        this.rWidth = i;
    }

    public final int getRatioImage() {
        return this.ratioImage;
    }

    public final void setRatioImage(int i) {
        this.ratioImage = i;
    }

    public final String getRatioSize() {
        return this.ratioSize;
    }

    public final void setRatioSize(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.ratioSize = str;
    }
}
