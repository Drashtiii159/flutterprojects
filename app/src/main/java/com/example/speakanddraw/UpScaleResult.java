package com.example.speakanddraw;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.permission.PermissionUtils;
import com.example.speakanddraw.permission.PermissionUtilsKt;
import com.example.speakanddraw.utlis.ExtensionKt;
import com.example.speakanddraw.utlis.SaveImages_1Kt;

import java.io.IOException;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.ActivityUpscaleResultBinding;

/* compiled from: UpScaleResult.kt */

/* loaded from: classes.dex */
public final class UpScaleResult extends AppCompatActivity {
    private final Lazy binding$delegate = LazyKt.lazy(new Function0<ActivityUpscaleResultBinding>() { // from class: com.example.speakanddraw.UpScaleResult$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */ {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityUpscaleResultBinding invoke() {
            ActivityUpscaleResultBinding inflate = ActivityUpscaleResultBinding.inflate(UpScaleResult.this.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }
    });
    private boolean isDownload;
    private int scaleHeight;
    private int scaleWidth;

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityUpscaleResultBinding getBinding() {
        return (ActivityUpscaleResultBinding) this.binding$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: com.example.speakanddraw.UpScaleResult$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                Constants.INSTANCE.setBitmapResult(null);
                UpScaleResult.this.finish();
            }
        });
        Log.e("TAG", "ResultScreen:" + Constants.INSTANCE.getBitmapResult() + " ");
        ActivityUpscaleResultBinding binding = getBinding();
        ImageView back = binding.back;
        Intrinsics.checkNotNullExpressionValue(back, "back");
        ExtensionKt.clickListener(back, new Function1<View, Unit>() { // from class: com.example.speakanddraw.UpScaleResult$onCreate$2$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Constants.INSTANCE.setBitmapResult(null);
                UpScaleResult.this.finish();
            }
        });
        binding.resultImage.setImageBitmap(Constants.INSTANCE.getBitmapResult());
        ImageView share = binding.share;
        Intrinsics.checkNotNullExpressionValue(share, "share");
        ExtensionKt.clickListener(share, new Function1<View, Unit>() { // from class: com.example.speakanddraw.UpScaleResult$onCreate$2$2
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                String[] permissions = PermissionUtilsKt.getPermissions();
                final UpScaleResult upScaleResult = UpScaleResult.this;
                PermissionUtils.INSTANCE.checkPermission(UpScaleResult.this, permissions, new PermissionUtils.OnPermissionListener() { // from class: com.example.speakanddraw.UpScaleResult$onCreate$2$2.1
                    @Override
                    // com.example.speakanddraw.permission.PermissionUtils.OnPermissionListener
                    public void onPermissionSuccess() {
                        ActivityUpscaleResultBinding binding2;
                        ActivityUpscaleResultBinding binding3;
                        ActivityUpscaleResultBinding binding4;
                        binding2 = UpScaleResult.this.getBinding();
                        ConstraintLayout constraintLayout = binding2.constraintCaptureView;
                        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.constraintCaptureView");
                        binding3 = UpScaleResult.this.getBinding();
                        int height = binding3.constraintCaptureView.getHeight();
                        binding4 = UpScaleResult.this.getBinding();
                        Uri saveBitmapToCacheAndGetUri = ExtensionKt.saveBitmapToCacheAndGetUri(UpScaleResult.this, ExtensionKt.takeScreenshotOfView(constraintLayout, height, binding4.constraintCaptureView.getWidth()));
                        if (saveBitmapToCacheAndGetUri != null) {
                            ExtensionKt.shareImage(UpScaleResult.this, saveBitmapToCacheAndGetUri);
                        }
                    }
                });
            }
        });
        LinearLayout download = binding.download;
        Intrinsics.checkNotNullExpressionValue(download, "download");
        ExtensionKt.clickListener(download, new Function1<View, Unit>() { // from class: com.example.speakanddraw.UpScaleResult$onCreate$2$3
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                boolean z;
                ActivityUpscaleResultBinding binding2;
                ActivityUpscaleResultBinding binding3;
                ActivityUpscaleResultBinding binding4;
                Intrinsics.checkNotNullParameter(it, "it");
                z = UpScaleResult.this.isDownload;
                if (!z) {
                    binding2 = UpScaleResult.this.getBinding();
                    ConstraintLayout constraintLayout = binding2.constraintCaptureView;
                    Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.constraintCaptureView");
                    UpScaleResult upScaleResult = UpScaleResult.this;
                    UpScaleResult upScaleResult2 = upScaleResult;
                    binding3 = upScaleResult.getBinding();
                    int height = binding3.constraintCaptureView.getHeight();
                    binding4 = UpScaleResult.this.getBinding();
                    try {
                        SaveImages_1Kt.saveImages1(constraintLayout, upScaleResult2, true, height, binding4.constraintCaptureView.getWidth());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    UpScaleResult.this.isDownload = true;
                    return;
                }
                ExtensionKt.showToast(UpScaleResult.this, "Photo Already saved");
            }
        });
    }
}
