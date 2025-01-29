package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectModel.kt */

/* loaded from: classes.dex */
public final class SelectModel {
    private final int animation;
    private final String endPoint;
    private final String name;

    public SelectModel(String name, int i, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.name = name;
        this.animation = i;
        this.endPoint = str;
    }

    public /* synthetic */ SelectModel(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? null : str2);
    }

    public static /* synthetic */ SelectModel copy$default(SelectModel selectModel, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = selectModel.name;
        }
        if ((i2 & 2) != 0) {
            i = selectModel.animation;
        }
        if ((i2 & 4) != 0) {
            str2 = selectModel.endPoint;
        }
        return selectModel.copy(str, i, str2);
    }

    public final String component1() {
        return this.name;
    }

    public final int component2() {
        return this.animation;
    }

    public final String component3() {
        return this.endPoint;
    }

    public final SelectModel copy(String name, int i, String str) {
        Intrinsics.checkNotNullParameter(name, "name");
        return new SelectModel(name, i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SelectModel) {
            SelectModel selectModel = (SelectModel) obj;
            return Intrinsics.areEqual(this.name, selectModel.name) && this.animation == selectModel.animation && Intrinsics.areEqual(this.endPoint, selectModel.endPoint);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.name.hashCode() * 31) + Integer.hashCode(this.animation)) * 31;
        String str = this.endPoint;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.name;
        int i = this.animation;
        return "SelectModel(name=" + str + ", animation=" + i + ", endPoint=" + this.endPoint + ")";
    }

    public final int getAnimation() {
        return this.animation;
    }

    public final String getEndPoint() {
        return this.endPoint;
    }

    public final String getName() {
        return this.name;
    }
}
