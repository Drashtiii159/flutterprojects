package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextPrompts.kt */

/* loaded from: classes.dex */
public final class TextPrompts {
    private final String text;
    private final double weight;

    public TextPrompts(String text, double d) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.text = text;
        this.weight = d;
    }

    public static /* synthetic */ TextPrompts copy$default(TextPrompts textPrompts, String str, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            str = textPrompts.text;
        }
        if ((i & 2) != 0) {
            d = textPrompts.weight;
        }
        return textPrompts.copy(str, d);
    }

    public final String component1() {
        return this.text;
    }

    public final double component2() {
        return this.weight;
    }

    public final TextPrompts copy(String text, double d) {
        Intrinsics.checkNotNullParameter(text, "text");
        return new TextPrompts(text, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TextPrompts) {
            TextPrompts textPrompts = (TextPrompts) obj;
            return Intrinsics.areEqual(this.text, textPrompts.text) && Double.compare(this.weight, textPrompts.weight) == 0;
        }
        return false;
    }

    public int hashCode() {
        return (this.text.hashCode() * 31) + Double.hashCode(this.weight);
    }

    public String toString() {
        String str = this.text;
        return "TextPrompts(text=" + str + ", weight=" + this.weight + ")";
    }

    public final String getText() {
        return this.text;
    }

    public final double getWeight() {
        return this.weight;
    }
}
