package com.example.speakanddraw;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.example.speakanddraw.utlis.ExtensionKt;
import com.example.speakanddraw.utlis.TinyDB;

import org.koin.android.ext.android.AndroidKoinScopeExtKt;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import speak.draw.ai.art.photo.image.generator.R;
import speak.draw.ai.art.photo.image.generator.databinding.ActivityPermiumBinding;


public final class PermiumActivity extends AppCompatActivity {
    private final TinyDB tinyBD = (TinyDB) AndroidKoinScopeExtKt.getKoinScope(this).get(Reflection.getOrCreateKotlinClass(TinyDB.class), null, null);
    private ActivityPermiumBinding binding;
    private int counter;
    private int subscriptiontype = 3;

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(PermiumActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityPermiumBinding activityPermiumBinding = this$0.binding;
        ImageView imageView = activityPermiumBinding != null ? activityPermiumBinding.closeBtn : null;
        if (imageView == null) {
            return;
        }
        imageView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$1(PermiumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$2(PermiumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkWeekly();
        this$0.subscriptiontype = 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$3(PermiumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checkMonthly();
        this$0.subscriptiontype = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$4(PermiumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.checklifeTime();
        this$0.subscriptiontype = 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$6$lambda$5(PermiumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.counter + 1;
        this$0.counter = i;
        Log.e("checkContinue", "onCreate: " + i);
    }

    public final int getCounter() {
        return this.counter;
    }

    public final void setCounter(int i) {
        this.counter = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityPermiumBinding inflate = ActivityPermiumBinding.inflate(getLayoutInflater());
        this.binding = inflate;
        setContentView(inflate != null ? inflate.getRoot() : null);
        if (Build.VERSION.SDK_INT >= 26) {
            getWindow().setFlags(512, 512);
        }
        Window window = getWindow();
        Intrinsics.checkNotNullExpressionValue(window, "window");
        ExtensionKt.hideSystemUI(window);
        Looper myLooper = Looper.myLooper();
        Intrinsics.checkNotNull(myLooper);
        new Handler(myLooper).postDelayed(new Runnable() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                PermiumActivity.onCreate$lambda$0(PermiumActivity.this);
            }
        }, 3000L);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: com.example.speakanddraw.PermiumActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                PermiumActivity.this.finish();
            }
        });
        ActivityPermiumBinding activityPermiumBinding = this.binding;
        if (activityPermiumBinding != null) {
            activityPermiumBinding.closeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermiumActivity.onCreate$lambda$6$lambda$1(PermiumActivity.this, view);
                }
            });
            activityPermiumBinding.weeklyBox.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermiumActivity.onCreate$lambda$6$lambda$2(PermiumActivity.this, view);
                }
            });
            activityPermiumBinding.monthlyBox.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermiumActivity.onCreate$lambda$6$lambda$3(PermiumActivity.this, view);
                }
            });
            activityPermiumBinding.lifeTimeBox.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermiumActivity.onCreate$lambda$6$lambda$4(PermiumActivity.this, view);
                }
            });
            activityPermiumBinding.buy.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.PermiumActivity$$ExternalSyntheticLambda5
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    PermiumActivity.onCreate$lambda$6$lambda$5(PermiumActivity.this, view);
                }
            });
        }
    }

    private final void checkWeekly() {
        PermiumActivity permiumActivity = this;
        int color = ContextCompat.getColor(permiumActivity, R.color.white);
        int color2 = ContextCompat.getColor(permiumActivity, R.color.whitegrey);
        ActivityPermiumBinding activityPermiumBinding = this.binding;
        if (activityPermiumBinding != null) {
            activityPermiumBinding.lifeTimeText.setTextColor(color2);
            activityPermiumBinding.weeklyText.setTextColor(color);
            activityPermiumBinding.monthlyText.setTextColor(color2);
            activityPermiumBinding.lifeTimeResultText.setTextColor(color2);
            activityPermiumBinding.weeklyResultText.setTextColor(color);
            activityPermiumBinding.monthlyResultText.setTextColor(color2);
            activityPermiumBinding.weeklyBox.setBackgroundResource(R.drawable.bg_e8_btn1);
            activityPermiumBinding.monthlyBox.setBackgroundResource(R.drawable.box_background);
            activityPermiumBinding.lifeTimeBox.setBackgroundResource(R.drawable.box_background);
        }
    }

    private final void checkMonthly() {
        PermiumActivity permiumActivity = this;
        int color = ContextCompat.getColor(permiumActivity, R.color.white);
        int color2 = ContextCompat.getColor(permiumActivity, R.color.whitegrey);
        ActivityPermiumBinding activityPermiumBinding = this.binding;
        if (activityPermiumBinding != null) {
            activityPermiumBinding.lifeTimeText.setTextColor(color2);
            activityPermiumBinding.weeklyText.setTextColor(color2);
            activityPermiumBinding.monthlyText.setTextColor(color);
            activityPermiumBinding.lifeTimeResultText.setTextColor(color2);
            activityPermiumBinding.weeklyResultText.setTextColor(color2);
            activityPermiumBinding.monthlyResultText.setTextColor(color);
            activityPermiumBinding.weeklyBox.setBackgroundResource(R.drawable.box_background);
            activityPermiumBinding.monthlyBox.setBackgroundResource(R.drawable.bg_e8_btn1);
            activityPermiumBinding.lifeTimeBox.setBackgroundResource(R.drawable.box_background);
        }
    }

    private final void checklifeTime() {
        PermiumActivity permiumActivity = this;
        int color = ContextCompat.getColor(permiumActivity, R.color.white);
        int color2 = ContextCompat.getColor(permiumActivity, R.color.whitegrey);
        ActivityPermiumBinding activityPermiumBinding = this.binding;
        if (activityPermiumBinding != null) {
            activityPermiumBinding.lifeTimeText.setTextColor(color);
            activityPermiumBinding.weeklyText.setTextColor(color2);
            activityPermiumBinding.monthlyText.setTextColor(color2);
            activityPermiumBinding.lifeTimeResultText.setTextColor(color);
            activityPermiumBinding.weeklyResultText.setTextColor(color2);
            activityPermiumBinding.monthlyResultText.setTextColor(color2);
            activityPermiumBinding.weeklyBox.setBackgroundResource(R.drawable.box_background);
            activityPermiumBinding.monthlyBox.setBackgroundResource(R.drawable.box_background);
            activityPermiumBinding.lifeTimeBox.setBackgroundResource(R.drawable.bg_e8_btn1);
        }
    }

    private final void goToHome() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(67108864);
        intent.addFlags(268435456);
        startActivity(intent);
    }
}
