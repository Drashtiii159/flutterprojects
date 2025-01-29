package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.speakanddraw.utlis.ExtensionKt;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Anime.kt */

/* loaded from: classes.dex */
public final class Anime {
    private final int image;
    private final String number;
    private final String styleId;

    public Anime(String number, int i, String str) {
        Intrinsics.checkNotNullParameter(number, "number");
        this.number = number;
        this.image = i;
        this.styleId = str;
    }

    public /* synthetic */ Anime(String str, int i, String str2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, (i2 & 4) != 0 ? null : str2);
    }

    public static /* synthetic */ Anime copy$default(Anime anime, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = anime.number;
        }
        if ((i2 & 2) != 0) {
            i = anime.image;
        }
        if ((i2 & 4) != 0) {
            str2 = anime.styleId;
        }
        return anime.copy(str, i, str2);
    }

    public final String component1() {
        return this.number;
    }

    public final int component2() {
        return this.image;
    }

    public final String component3() {
        return this.styleId;
    }

    public final Anime copy(String number, int i, String str) {
        Intrinsics.checkNotNullParameter(number, "number");
        return new Anime(number, i, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Anime) {
            Anime anime = (Anime) obj;
            return Intrinsics.areEqual(this.number, anime.number) && this.image == anime.image && Intrinsics.areEqual(this.styleId, anime.styleId);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = ((this.number.hashCode() * 31) + Integer.hashCode(this.image)) * 31;
        String str = this.styleId;
        return hashCode + (str == null ? 0 : str.hashCode());
    }

    public String toString() {
        String str = this.number;
        int i = this.image;
        return "Anime(number=" + str + ", image=" + i + ", styleId=" + this.styleId + ")";
    }

    public final int getImage() {
        return this.image;
    }

    public final String getNumber() {
        return this.number;
    }

    public final String getStyleId() {
        return this.styleId;
    }
}
