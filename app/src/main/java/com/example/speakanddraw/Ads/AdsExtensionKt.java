package com.example.speakanddraw.Ads;

import android.content.Context;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.speakanddraw.utlis.TinyDB;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;


public final class AdsExtensionKt {
    public static final boolean isAlreadyPurchased(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return new TinyDB(context.getApplicationContext()).getBoolean("KEY_PURCHASE");
    }

    public static final void Invisible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(4);
    }

    public static final void visible(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(0);
    }

    public static final void Gone(View view) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        view.setVisibility(8);
    }
}
