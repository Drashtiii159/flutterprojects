package com.example.speakanddraw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.speakanddraw.Dialog.LoadingDialog;
import com.example.speakanddraw.api_upscale.ApiUpScale;
import com.example.speakanddraw.api_upscale.UriToFileKt;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.utlis.ExtensionKt;

import java.io.File;
import java.io.IOException;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import speak.draw.ai.art.photo.image.generator.R;
import speak.draw.ai.art.photo.image.generator.databinding.ActivityUpScaleBinding;

/* compiled from: UpScaleActivity.kt */

/* loaded from: classes.dex */
public final class UpScaleActivity extends AppCompatActivity {
    private final ActivityResultLauncher<String> openCamera;
    private final Lazy binding$delegate = LazyKt.lazy(new Function0<ActivityUpScaleBinding>() { // from class: com.example.speakanddraw.UpScaleActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */ {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityUpScaleBinding invoke() {
            ActivityUpScaleBinding inflate = ActivityUpScaleBinding.inflate(UpScaleActivity.this.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }
    });
    private Uri galleryUri;
    private LoadingDialog loadingDialog;
    private int upscale_Value = 2;

    public UpScaleActivity() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda5
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                UpScaleActivity.openCamera$lambda$0(UpScaleActivity.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul\u2026\n            }\n\n        }");
        this.openCamera = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openCamera$lambda$0(UpScaleActivity this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            this$0.galleryUri = uri;
            this$0.getBinding().image.setImageURI(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$lambda$1(UpScaleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openCamera.launch("image/*");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$lambda$3(UpScaleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 2;
        ActivityUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background11);
        binding.fourX.setBackgroundResource(R.drawable.card_background);
        binding.sixX.setBackgroundResource(R.drawable.card_background);
        binding.picOne.setImageResource(R.drawable.selected_circle);
        binding.picTwo.setImageResource(R.drawable.unselected_circle);
        binding.picThree.setImageResource(R.drawable.unselected_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$lambda$5(UpScaleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 4;
        ActivityUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background);
        binding.fourX.setBackgroundResource(R.drawable.card_background11);
        binding.sixX.setBackgroundResource(R.drawable.card_background);
        binding.picOne.setImageResource(R.drawable.unselected_circle);
        binding.picTwo.setImageResource(R.drawable.selected_circle);
        binding.picThree.setImageResource(R.drawable.unselected_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$lambda$7(UpScaleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 6;
        ActivityUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background);
        binding.fourX.setBackgroundResource(R.drawable.card_background);
        binding.sixX.setBackgroundResource(R.drawable.card_background11);
        binding.picOne.setImageResource(R.drawable.unselected_circle);
        binding.picTwo.setImageResource(R.drawable.unselected_circle);
        binding.picThree.setImageResource(R.drawable.selected_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$10$lambda$9(UpScaleActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Uri uri = this$0.galleryUri;
        File uriToFile = uri != null ? UriToFileKt.uriToFile(this$0, uri) : null;
        boolean z = false;
        if (uriToFile != null && uriToFile.exists()) {
            z = true;
        }
        if (z) {
            LoadingDialog loadingDialog = this$0.loadingDialog;
            if (loadingDialog != null) {
                loadingDialog.show();
            }
            this$0.upscale(uriToFile);
            return;
        }
        ExtensionKt.showToast(this$0, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityUpScaleBinding getBinding() {
        return (ActivityUpScaleBinding) this.binding$delegate.getValue();
    }

    public final LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public final void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        getBinding().image.setImageBitmap(Constants.INSTANCE.getBitmapResult());
        Bitmap bitmapResult = Constants.INSTANCE.getBitmapResult();
        this.galleryUri = bitmapResult != null ? ExtensionKt.toUri(bitmapResult, this, "my_image.png") : null;
        this.loadingDialog = new LoadingDialog(this);
        ActivityUpScaleBinding binding = getBinding();
        binding.uploadLayout.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleActivity.onCreate$lambda$10$lambda$1(UpScaleActivity.this, view);
            }
        });
        binding.twoX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleActivity.onCreate$lambda$10$lambda$3(UpScaleActivity.this, view);
            }
        });
        binding.fourX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleActivity.onCreate$lambda$10$lambda$5(UpScaleActivity.this, view);
            }
        });
        binding.sixX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleActivity.onCreate$lambda$10$lambda$7(UpScaleActivity.this, view);
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.UpScaleActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleActivity.onCreate$lambda$10$lambda$9(UpScaleActivity.this, view);
            }
        });
    }

    private final void upscale(File file) {
        try {
            ApiUpScale.INSTANCE.getImageEnhanceService().processImage(this.upscale_Value, MultipartBody.Part.Companion.createFormData("file", file.getName(), RequestBody.Companion.create(MediaType.Companion.parse("image/*"), file))).enqueue(new Callback<ResponseBody>() { // from class: com.example.speakanddraw.UpScaleActivity$upscale$1
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    ActivityUpScaleBinding binding;
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(response, "response");
                    if (response.isSuccessful()) {
                        ResponseBody body = response.body();
                        byte[] bytes = new byte[0];
                        try {
                            bytes = body != null ? body.bytes() : null;
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        if (bytes != null) {
                            Log.e("onResponse", "onResponse: if condition ");
                            LoadingDialog loadingDialog = UpScaleActivity.this.getLoadingDialog();
                            if (loadingDialog != null) {
                                loadingDialog.dismiss();
                            }
                            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            binding = UpScaleActivity.this.getBinding();
                            binding.image.setImageBitmap(decodeByteArray);
                            Constants.INSTANCE.setBitmapResult(decodeByteArray);
                            return;
                        }
                        return;
                    }
                    String message = response.message();
                    Log.e("onResponse", "onResponse: else condition ");
                    Log.e("apiResponse", "onResponse: else: " + response.code());
                    Log.e("apiResponse", "onResponse: else: " + response.errorBody());
                    Log.e("apiResponse", "onResponse: else: " + message);
                    LoadingDialog loadingDialog2 = UpScaleActivity.this.getLoadingDialog();
                    if (loadingDialog2 != null) {
                        loadingDialog2.dismiss();
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    Log.e("onResponse", "onResponse: fail condition ");
                    LoadingDialog loadingDialog = UpScaleActivity.this.getLoadingDialog();
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }
}
