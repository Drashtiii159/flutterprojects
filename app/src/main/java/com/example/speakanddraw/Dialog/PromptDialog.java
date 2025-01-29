package com.example.speakanddraw.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;


import com.example.speakanddraw.model.Anime;
import com.google.android.material.R;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.PromptDialogBinding;

/* compiled from: PromptDialog.kt */

/* loaded from: classes.dex */
public final class PromptDialog extends Dialog {
    private final Anime anime;
    private PromptDialogBinding binding;
    private Function1<? super String, Unit> tryButtonClick;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromptDialog(Activity context, Anime anime, Function1<? super String, Unit> tryButtonClick) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(anime, "anime");
        Intrinsics.checkNotNullParameter(tryButtonClick, "tryButtonClick");
        this.anime = anime;
        this.tryButtonClick = tryButtonClick;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$1(PromptDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String styleId = this$0.anime.getStyleId();
        if (styleId != null) {
            this$0.tryButtonClick.invoke(styleId);
        }
        this$0.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(PromptDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public final Anime getAnime() {
        return this.anime;
    }

    public final Function1<String, Unit> getTryButtonClick() {
        return (Function1<String, Unit>) this.tryButtonClick;
    }

    public final void setTryButtonClick(Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "<set-?>");
        this.tryButtonClick = function1;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        FrameLayout root;
        super.onCreate(bundle);
        getContext().setTheme(R.style.ThemeOverlay_Material3_Dialog_Alert);
        PromptDialogBinding inflate = PromptDialogBinding.inflate(LayoutInflater.from(getContext()));
        this.binding = inflate;
        if (inflate != null) {
            inflate.Try.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Dialog.PromptDialog$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PromptDialog.onCreate$lambda$3$lambda$1(PromptDialog.this, view);
                }
            });
            inflate.closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Dialog.PromptDialog$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PromptDialog.onCreate$lambda$3$lambda$2(PromptDialog.this, view);
                }
            });
            inflate.image.setImageResource(this.anime.getImage());
            inflate.currentTxt.setText(this.anime.getStyleId());
        }
        PromptDialogBinding promptDialogBinding = this.binding;
        if (promptDialogBinding != null && (root = promptDialogBinding.getRoot()) != null) {
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
}
