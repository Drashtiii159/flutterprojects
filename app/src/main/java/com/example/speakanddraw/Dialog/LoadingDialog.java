package com.example.speakanddraw.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.Window;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import speak.draw.ai.art.photo.image.generator.R;
//import com.example.speakanddraw.databinding.LoadingDialogBinding;

import java.util.Random;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.LoadingDialogBinding;

/* compiled from: LoadingDialog.kt */

/* loaded from: classes.dex */
public final class LoadingDialog extends Dialog {
    private final Handler handler;
    private final Random random;
    private final String[] texts;
    private LoadingDialogBinding binding;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoadingDialog(Activity context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.random = new Random();
        this.texts = new String[]{context.getString(R.string.working_on_your_prompt), context.getString(R.string.creating_your_ai_art), context.getString(R.string.anime_artistry), context.getString(R.string.crafting_unique_anime)};
        this.handler = new Handler(Looper.getMainLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void startUpdatingTextView$lambda$1(LoadingDialog this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setRandomText();
        this$0.startUpdatingTextView();
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        ConstraintLayout root;
        super.onCreate(bundle);
        setCancelable(false);
        getContext().setTheme(com.google.android.material.R.style.ThemeOverlay_Material3_Dialog_Alert);
        LoadingDialogBinding inflate = LoadingDialogBinding.inflate(LayoutInflater.from(getContext()));
        this.binding = inflate;
        LottieAnimationView lottieAnimationView = inflate != null ? inflate.lottie : null;
        if (lottieAnimationView != null) {
            lottieAnimationView.setAnimation(R.raw.loading);
        }
        if (lottieAnimationView != null) {
            lottieAnimationView.setRepeatCount(-1);
        }
        if (lottieAnimationView != null) {
            lottieAnimationView.playAnimation();
        }
        if (lottieAnimationView != null) {
            lottieAnimationView.setSpeed(0.5f);
        }
        startUpdatingTextView();
        LoadingDialogBinding loadingDialogBinding = this.binding;
        if (loadingDialogBinding != null && (root = loadingDialogBinding.getRoot()) != null) {
            setContentView(root);
        }
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-2, -2);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setBackgroundDrawable(new ColorDrawable(0));
        }
    }

    private final void startUpdatingTextView() {
        this.handler.postDelayed(new Runnable() { // from class: com.example.speakanddraw.Dialog.LoadingDialog$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                LoadingDialog.startUpdatingTextView$lambda$1(LoadingDialog.this);
            }
        }, 2000L);
    }

    private final void setRandomText() {
        int nextInt = this.random.nextInt(this.texts.length);
        LoadingDialogBinding loadingDialogBinding = this.binding;
        AppCompatTextView appCompatTextView = loadingDialogBinding != null ? loadingDialogBinding.textExitApp : null;
        if (appCompatTextView == null) {
            return;
        }
        appCompatTextView.setText(this.texts[nextInt]);
    }
}
