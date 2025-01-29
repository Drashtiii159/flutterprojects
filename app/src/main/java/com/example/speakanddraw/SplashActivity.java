package com.example.speakanddraw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.speakanddraw.Ads.AdsExtensionKt;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.utlis.TinyDB;

import org.koin.android.ext.android.AndroidKoinScopeExtKt;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import speak.draw.ai.art.photo.image.generator.databinding.ActivitySplashBinding;

/* compiled from: SplashActivity.kt */

/* loaded from: classes.dex */
public final class SplashActivity extends AppCompatActivity {
    private final Lazy binding$delegate = LazyKt.lazy(new Function0<ActivitySplashBinding>() { // from class: com.example.speakanddraw.SplashActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */ {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivitySplashBinding invoke() {
            ActivitySplashBinding inflate = ActivitySplashBinding.inflate(SplashActivity.this.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }
    });
    private final TinyDB tinyBD = (TinyDB) AndroidKoinScopeExtKt.getKoinScope(this).get(Reflection.getOrCreateKotlinClass(TinyDB.class), null, null);
    private boolean monthly;
    private boolean weekly;
    private boolean yearly;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(SplashActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySplashBinding binding = this$0.getBinding();
        CheckBox checkBox = binding.checkBox;
        Intrinsics.checkNotNullExpressionValue(checkBox, "checkBox");
        AdsExtensionKt.visible(checkBox);
        TextView privacyText = binding.privacyText;
        Intrinsics.checkNotNullExpressionValue(privacyText, "privacyText");
        AdsExtensionKt.visible(privacyText);
        TextView started = binding.started;
        Intrinsics.checkNotNullExpressionValue(started, "started");
        AdsExtensionKt.visible(started);
        TextView loading = binding.loading;
        Intrinsics.checkNotNullExpressionValue(loading, "loading");
        AdsExtensionKt.Gone(loading);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$3$lambda$2(ActivitySplashBinding this_run, SplashActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this_run.checkBox.isChecked()) {
            Constants.INSTANCE.setFromSplash(true);
            this$0.startActivity(new Intent(this$0, MainActivity.class).putExtra("isFromSplash", true));
            return;
        }
        Toast.makeText(this$0, "First agreed to our privacy policy", 0).show();
    }

    private final ActivitySplashBinding getBinding() {
        return (ActivitySplashBinding) this.binding$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        Looper myLooper = Looper.myLooper();
        Intrinsics.checkNotNull(myLooper);
        new Handler(myLooper).postDelayed(new Runnable() { // from class: com.example.speakanddraw.SplashActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                SplashActivity.onCreate$lambda$1(SplashActivity.this);
            }
        }, 2000L);
        final ActivitySplashBinding binding = getBinding();
        binding.started.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.SplashActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SplashActivity.onCreate$lambda$3$lambda$2(binding, SplashActivity.this, view);
            }
        });
    }
}
