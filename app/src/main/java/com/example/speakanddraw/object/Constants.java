package com.example.speakanddraw.object;

import android.graphics.Bitmap;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Constants.kt */

/* loaded from: classes.dex */
public final class Constants {
    public static final Constants INSTANCE = new Constants();
    private static Bitmap bitmapResult;
    private static int height;
    private static boolean isFromSplash;
    private static int ratioController;
    private static int width;
    private static String styleId = "";
    private static String userText = "";
    private static String model_End_Point = "v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image";
    private static String isFromrResult = "";

    private Constants() {
    }

    public final String getStyleId() {
        return styleId;
    }

    public final void setStyleId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        styleId = str;
    }

    public final String getUserText() {
        return userText;
    }

    public final void setUserText(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        userText = str;
    }

    public final int getHeight() {
        return height;
    }

    public final void setHeight(int i) {
        height = i;
    }

    public final int getWidth() {
        return width;
    }

    public final void setWidth(int i) {
        width = i;
    }

    public final int getRatioController() {
        return ratioController;
    }

    public final void setRatioController(int i) {
        ratioController = i;
    }

    public final String getModel_End_Point() {
        return model_End_Point;
    }

    public final void setModel_End_Point(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        model_End_Point = str;
    }

    public final boolean isFromSplash() {
        return isFromSplash;
    }

    public final void setFromSplash(boolean z) {
        isFromSplash = z;
    }

    public final Bitmap getBitmapResult() {
        return bitmapResult;
    }

    public final void setBitmapResult(Bitmap bitmap) {
        bitmapResult = bitmap;
    }

    public final String isFromrResult() {
        return isFromrResult;
    }

    public final void setFromrResult(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        isFromrResult = str;
    }
}
