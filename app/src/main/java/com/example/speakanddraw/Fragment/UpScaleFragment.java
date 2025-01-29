package com.example.speakanddraw.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.speakanddraw.Dialog.LoadingDialog;
import speak.draw.ai.art.photo.image.generator.R;
import com.example.speakanddraw.UpScaleResult;
import com.example.speakanddraw.api_upscale.ApiUpScale;
import com.example.speakanddraw.api_upscale.UriToFileKt;
import com.example.speakanddraw.object.CompressFile;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.utlis.ExtensionKt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import speak.draw.ai.art.photo.image.generator.databinding.FragmentUpScaleBinding;

/* compiled from: UpScaleFragment.kt */

/* loaded from: classes.dex */
public final class UpScaleFragment extends Fragment {
    private final ActivityResultLauncher<String> openCamera;
    public FragmentUpScaleBinding binding;
    private Uri galleryUri;
    private LoadingDialog loadingDialog;
    private int upscale_Value = 2;

    public UpScaleFragment() {
        ActivityResultLauncher<String> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$$ExternalSyntheticLambda0
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                UpScaleFragment.openCamera$lambda$0(UpScaleFragment.this, (Uri) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul\u2026\n            }\n\n        }");
        this.openCamera = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void openCamera$lambda$0(UpScaleFragment this$0, Uri uri) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri != null) {
            this$0.galleryUri = uri;
            this$0.getBinding().image.setImageURI(uri);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$8$lambda$1(UpScaleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.openCamera.launch("image/*");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$8$lambda$3(UpScaleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 2;
        FragmentUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background11);
        binding.fourX.setBackgroundResource(R.drawable.card_background);
        binding.sixX.setBackgroundResource(R.drawable.card_background);
        binding.picOne.setImageResource(R.drawable.selected_circle);
        binding.picTwo.setImageResource(R.drawable.unselected_circle);
        binding.picThree.setImageResource(R.drawable.unselected_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$8$lambda$5(UpScaleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 4;
        FragmentUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background);
        binding.fourX.setBackgroundResource(R.drawable.card_background11);
        binding.sixX.setBackgroundResource(R.drawable.card_background);
        binding.picOne.setImageResource(R.drawable.unselected_circle);
        binding.picTwo.setImageResource(R.drawable.selected_circle);
        binding.picThree.setImageResource(R.drawable.unselected_circle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreateView$lambda$8$lambda$7(UpScaleFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.upscale_Value = 6;
        FragmentUpScaleBinding binding = this$0.getBinding();
        binding.twoX.setBackgroundResource(R.drawable.card_background);
        binding.fourX.setBackgroundResource(R.drawable.card_background);
        binding.sixX.setBackgroundResource(R.drawable.card_background11);
        binding.picOne.setImageResource(R.drawable.unselected_circle);
        binding.picTwo.setImageResource(R.drawable.unselected_circle);
        binding.picThree.setImageResource(R.drawable.selected_circle);
    }

    public final FragmentUpScaleBinding getBinding() {
        FragmentUpScaleBinding fragmentUpScaleBinding = this.binding;
        if (fragmentUpScaleBinding != null) {
            return fragmentUpScaleBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(FragmentUpScaleBinding fragmentUpScaleBinding) {
        Intrinsics.checkNotNullParameter(fragmentUpScaleBinding, "<set-?>");
        this.binding = fragmentUpScaleBinding;
    }

    public final LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public final void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Uri uri;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentUpScaleBinding inflate = FragmentUpScaleBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.loadingDialog = new LoadingDialog(requireActivity);
        if (Constants.INSTANCE.getBitmapResult() != null) {
            getBinding().image.setImageBitmap(Constants.INSTANCE.getBitmapResult());
            Bitmap bitmapResult = Constants.INSTANCE.getBitmapResult();
            if (bitmapResult != null) {
                Context requireContext = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
                uri = ExtensionKt.toUri(bitmapResult, requireContext, "my_image.png");
            } else {
                uri = null;
            }
            this.galleryUri = uri;
        }
        FragmentUpScaleBinding binding = getBinding();
        binding.uploadLayout.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleFragment.onCreateView$lambda$8$lambda$1(UpScaleFragment.this, view);
            }
        });
        binding.twoX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleFragment.onCreateView$lambda$8$lambda$3(UpScaleFragment.this, view);
            }
        });
        binding.fourX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleFragment.onCreateView$lambda$8$lambda$5(UpScaleFragment.this, view);
            }
        });
        binding.sixX.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpScaleFragment.onCreateView$lambda$8$lambda$7(UpScaleFragment.this, view);
            }
        });
        ConstraintLayout send = binding.send;
        Intrinsics.checkNotNullExpressionValue(send, "send");
        ExtensionKt.clickListener(send, new Function1<View, Unit>() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$onCreateView$1$5
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Uri uri2;
                Uri uri3;
                Intrinsics.checkNotNullParameter(it, "it");
                uri2 = UpScaleFragment.this.galleryUri;
                Log.e("uri", "onCreateView: uri " + uri2);
                Context requireContext2 = UpScaleFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                if (ExtensionKt.isNetworkConnected(requireContext2)) {
                    uri3 = UpScaleFragment.this.galleryUri;
                    if (uri3 == null) {
                        FragmentActivity requireActivity2 = UpScaleFragment.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity()");
                        ExtensionKt.showToast(requireActivity2, "Please select an image");
                        return;
                    }
                    LoadingDialog loadingDialog = UpScaleFragment.this.getLoadingDialog();
                    if (loadingDialog != null) {
                        loadingDialog.show();
                    }
                    UpScaleFragment.this.sendResponse();
                    return;
                }
                Context requireContext3 = UpScaleFragment.this.requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
                ExtensionKt.showToast(requireContext3, "please connect to Internet");
            }
        });
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void sendResponse() {
        File file;
        Uri uri = this.galleryUri;
        File file2 = null;
        if (uri != null) {
            Context requireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
            file = UriToFileKt.uriToFile(requireContext, uri);
        } else {
            file = null;
        }
        int i = this.upscale_Value;
        if (i == 6 || i == 4) {
            Uri uri2 = this.galleryUri;
            if (uri2 != null) {
                CompressFile compressFile = CompressFile.INSTANCE;
                Context requireContext2 = requireContext();
                Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                file2 = compressFile.getCompressedFile(requireContext2, uri2);
            }
            Log.e("file", "onCreateView: " + file2);
            file = file2;
        }
        boolean z = false;
        if (file != null && file.exists()) {
            z = true;
        }
        if (z) {
            upscale(file);
            return;
        }
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        ExtensionKt.showToast(requireActivity, "Please select an image");
    }

    private final void upscale(File file) {
        try {
            ApiUpScale.INSTANCE.getImageEnhanceService().processImage(this.upscale_Value, MultipartBody.Part.Companion.createFormData("file", file.getName(), RequestBody.Companion.create(MediaType.Companion.parse("image/*"), file))).enqueue(new Callback<ResponseBody>() { // from class: com.example.speakanddraw.Fragment.UpScaleFragment$upscale$1
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
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
                            LoadingDialog loadingDialog = UpScaleFragment.this.getLoadingDialog();
                            if (loadingDialog != null) {
                                loadingDialog.dismiss();
                            }
                            Constants.INSTANCE.setBitmapResult(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
                            FragmentActivity requireActivity = UpScaleFragment.this.requireActivity();
                            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                            FragmentActivity fragmentActivity = requireActivity;
                            fragmentActivity.startActivity(new Intent(fragmentActivity, UpScaleResult.class));
                            return;
                        }
                        return;
                    }
                    LoadingDialog loadingDialog2 = UpScaleFragment.this.getLoadingDialog();
                    if (loadingDialog2 != null) {
                        loadingDialog2.dismiss();
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Intrinsics.checkNotNullParameter(call, "call");
                    Intrinsics.checkNotNullParameter(t, "t");
                    Log.e("onResponse", "onResponse: fail condition ");
                    LoadingDialog loadingDialog = UpScaleFragment.this.getLoadingDialog();
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void saveUriToCache(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        InputStream openInputStream = null;
        try {
            openInputStream = requireContext().getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Context context = getContext();
        File cacheDir = context != null ? context.getCacheDir() : null;
        if (openInputStream != null) {
            try {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(cacheDir, "cached_image.jpg"));
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = openInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } finally {
                try {
                    openInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
