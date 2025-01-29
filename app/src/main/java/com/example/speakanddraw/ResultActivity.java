package com.example.speakanddraw;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakanddraw.Dialog.LoadingDialog;
import com.example.speakanddraw.Fragment.HomeFragment;
import com.example.speakanddraw.ImagesLists.ListsKt;
import com.example.speakanddraw.Interface.DrawServer;
import com.example.speakanddraw.adapter.AnimeAdapter;
import com.example.speakanddraw.model.Anime;
import com.example.speakanddraw.model.Artifact;
import com.example.speakanddraw.model.StableDiffResponse;
import com.example.speakanddraw.model.TextPrompts;
import com.example.speakanddraw.model.TextToImgRequest;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.permission.CheckMicPermissionsKt;
import com.example.speakanddraw.permission.PermissionUtils;
import com.example.speakanddraw.permission.PermissionUtilsKt;
import com.example.speakanddraw.utlis.ExtensionKt;
import com.example.speakanddraw.utlis.SpeechRecognitionManager;
import com.example.speakanddraw.utlis.TinyDB;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.koin.android.ext.android.AndroidKoinScopeExtKt;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import speak.draw.ai.art.photo.image.generator.databinding.ActivityResultBinding;
import speak.draw.ai.art.photo.image.generator.databinding.GenerateMoreDialogBinding;

/* compiled from: ResultActivity.kt */

/* loaded from: classes.dex */
public final class ResultActivity extends AppCompatActivity {
    private final ActivityResultLauncher<String[]> permissionRequest;
    private final Lazy binding$delegate = LazyKt.lazy(new Function0<ActivityResultBinding>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */ {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityResultBinding invoke() {
            ActivityResultBinding inflate = ActivityResultBinding.inflate(ResultActivity.this.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }
    });
    private final TinyDB tinyDB = (TinyDB) AndroidKoinScopeExtKt.getKoinScope(this).get(Reflection.getOrCreateKotlinClass(TinyDB.class), null, null);
    private boolean isDownload;
    private LoadingDialog loadingDialog;
    private SpeechRecognitionManager speechRecognitionManager;
    private int w = 1024;
    private int h = 1024;
    private String r2;

    public ResultActivity() {
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$$ExternalSyntheticLambda2
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                ResultActivity.permissionRequest$lambda$0(ResultActivity.this, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul\u2026sDenied()\n        }\n    }");
        this.permissionRequest = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void permissionRequest$lambda$0(ResultActivity this$0, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(map.get("android.permission.RECORD_AUDIO"), (Object) true)) {
            return;
        }
        CheckMicPermissionsKt.micPermissionsDenied(this$0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean generateMoreDialog$lambda$3(final ResultActivity this$0, View view, final MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CheckMicPermissionsKt.checkMicPermissions(this$0, new Function1<Boolean, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$generateMoreDialog$2$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                SpeechRecognitionManager speechRecognitionManager;
                SpeechRecognitionManager speechRecognitionManager2;
                if (!z) {
                    this$0.requestPermissions();
                    return;
                }
                SpeechRecognitionManager speechRecognitionManager3 = null;
                if (motionEvent.getAction() == 1) {
                    speechRecognitionManager2 = this$0.speechRecognitionManager;
                    if (speechRecognitionManager2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("speechRecognitionManager");
                        speechRecognitionManager2 = null;
                    }
                    speechRecognitionManager2.stopListening();
                }
                if (motionEvent.getAction() == 0) {
                    speechRecognitionManager = this$0.speechRecognitionManager;
                    if (speechRecognitionManager == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("speechRecognitionManager");
                    } else {
                        speechRecognitionManager3 = speechRecognitionManager;
                    }
                    speechRecognitionManager3.startListening();
                }
            }
        });
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void generateMoreDialog$lambda$4(ResultActivity this$0, BottomSheetDialog rcDialog, GenerateMoreDialogBinding rcBinding, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(rcDialog, "$rcDialog");
        Intrinsics.checkNotNullParameter(rcBinding, "$rcBinding");
        this$0.isDownload = false;
        rcDialog.dismiss();
        LoadingDialog loadingDialog = this$0.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.show();
        }
        String obj = rcBinding.editText.getText().toString();
        Constants.INSTANCE.setStyleId(String.valueOf(TinyDB.getString$default(this$0.tinyDB, "StyleId", null, 2, null)));
        Constants.INSTANCE.setUserText(obj);
        String str = obj + " " + Constants.INSTANCE.getStyleId();
        Log.d("TAG", "userInput:" + str + " ");
        this$0.generateImageFromText(CollectionsKt.mutableListOf(new TextPrompts(str, 0.7d)), null, HomeFragment.Companion.getCfGScale(), HomeFragment.Companion.getSeedValue());
    }

    static /* synthetic */ void generateImageFromText$default(ResultActivity resultActivity, List list, String str, int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        resultActivity.generateImageFromText(list, str, i, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ActivityResultBinding getBinding() {
        return (ActivityResultBinding) this.binding$delegate.getValue();
    }

    public final int getW() {
        return this.w;
    }

    public final void setW(int i) {
        this.w = i;
    }

    public final int getH() {
        return this.h;
    }

    public final void setH(int i) {
        this.h = i;
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
        getBinding().imageBackGround.setImageBitmap(Constants.INSTANCE.getBitmapResult());
        clickListener();
        this.loadingDialog = new LoadingDialog(this);
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$onCreate$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                Constants.INSTANCE.setBitmapResult(null);
                ExtensionKt.setTrythisBoolean(false);
                ResultActivity resultActivity = ResultActivity.this;
                resultActivity.startActivity(new Intent(resultActivity, MainActivity.class));
                ResultActivity.this.finish();
            }
        });
    }

    private final void clickListener() {
        ActivityResultBinding binding = getBinding();
        ConstraintLayout export = binding.export;
        Intrinsics.checkNotNullExpressionValue(export, "export");
        ExtensionKt.clickListener(export, new Function1<View, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$1
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
                final ResultActivity resultActivity = ResultActivity.this;
                PermissionUtils.INSTANCE.checkPermission(ResultActivity.this, permissions, new PermissionUtils.OnPermissionListener() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$1.1
                    @Override
                    // com.example.speakanddraw.permission.PermissionUtils.OnPermissionListener
                    public void onPermissionSuccess() {
                        boolean z;
                        ActivityResultBinding binding2;
                        z = ResultActivity.this.isDownload;
                        if (!z) {
                            binding2 = ResultActivity.this.getBinding();
                            ConstraintLayout constraintLayout = binding2.mainLayout;
                            Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.mainLayout");
                            ExtensionKt.saveImages(constraintLayout, ResultActivity.this, true);
                            return;
                        }
                        ExtensionKt.showToast(ResultActivity.this, "Photo Already saved");
                    }
                });
                ResultActivity.this.isDownload = true;
            }
        });
        ImageView share = binding.share;
        Intrinsics.checkNotNullExpressionValue(share, "share");
        ExtensionKt.clickListener(share, new Function1<View, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$2
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
                final ResultActivity resultActivity = ResultActivity.this;
                PermissionUtils.INSTANCE.checkPermission(ResultActivity.this, permissions, new PermissionUtils.OnPermissionListener() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$2.1
                    @Override
                    // com.example.speakanddraw.permission.PermissionUtils.OnPermissionListener
                    public void onPermissionSuccess() {
                        ActivityResultBinding binding2;
                        ActivityResultBinding binding3;
                        ActivityResultBinding binding4;
                        binding2 = ResultActivity.this.getBinding();
                        ConstraintLayout constraintLayout = binding2.mainLayout;
                        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.mainLayout");
                        binding3 = ResultActivity.this.getBinding();
                        int height = binding3.mainLayout.getHeight();
                        binding4 = ResultActivity.this.getBinding();
                        Uri saveBitmapToCacheAndGetUri = ExtensionKt.saveBitmapToCacheAndGetUri(ResultActivity.this, ExtensionKt.takeScreenshotOfView(constraintLayout, height, binding4.mainLayout.getWidth()));
                        if (saveBitmapToCacheAndGetUri != null) {
                            ExtensionKt.shareImage(ResultActivity.this, saveBitmapToCacheAndGetUri);
                        }
                    }
                });
            }
        });
        LinearLayout generateMore = binding.generateMore;
        Intrinsics.checkNotNullExpressionValue(generateMore, "generateMore");
        ExtensionKt.clickListener(generateMore, new Function1<View, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$3
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
                ResultActivity.this.generateMoreDialog();
            }
        });
        ImageView back = binding.back;
        Intrinsics.checkNotNullExpressionValue(back, "back");
        ExtensionKt.clickListener(back, new Function1<View, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$4
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
                ExtensionKt.setTrythisBoolean(false);
                ResultActivity resultActivity = ResultActivity.this;
                resultActivity.startActivity(new Intent(resultActivity, MainActivity.class));
                ResultActivity.this.finish();
            }
        });
        ImageView upscale = binding.upscale;
        Intrinsics.checkNotNullExpressionValue(upscale, "upscale");
        ExtensionKt.clickListener(upscale, new Function1<View, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$clickListener$1$5
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
                Constants.INSTANCE.getBitmapResult();
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                intent.putExtra("fragmentToDisplay", "Fragment1");
                ResultActivity.this.startActivity(intent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void generateMoreDialog() {
        ResultActivity resultActivity = this;
        final GenerateMoreDialogBinding inflate = GenerateMoreDialogBinding.inflate(LayoutInflater.from(resultActivity));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(this))");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(resultActivity);
        bottomSheetDialog.setContentView(inflate.getRoot());
        inflate.editText.setText(Constants.INSTANCE.getUserText());
        AnimeAdapter animeAdapter = new AnimeAdapter(resultActivity, ListsKt.addFilter1());
        RecyclerView recyclerView = inflate.reyclerView;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, 0, false));
        recyclerView.setAdapter(animeAdapter);
        animeAdapter.setOnCopyItemCLick(new Function2<Anime, Integer, Unit>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$generateMoreDialog$1$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Anime anime, Integer num) {
                invoke(anime, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Anime it, int i) {
                TinyDB tinyDB;
                Intrinsics.checkNotNullParameter(it, "it");
                tinyDB = ResultActivity.this.tinyDB;
                tinyDB.putString("StyleId", String.valueOf(it.getStyleId()));
            }
        });
        inflate.mic.setOnTouchListener(new View.OnTouchListener() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean generateMoreDialog$lambda$3;
                generateMoreDialog$lambda$3 = ResultActivity.generateMoreDialog$lambda$3(ResultActivity.this, view, motionEvent);
                return generateMoreDialog$lambda$3;
            }
        });
        inflate.send.setOnClickListener(new View.OnClickListener() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ResultActivity.generateMoreDialog$lambda$4(ResultActivity.this, bottomSheetDialog, inflate, view);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPermissions() {
        this.permissionRequest.launch(new String[]{"android.permission.RECORD_AUDIO"});
    }

    private final void generateImageFromText(List<TextPrompts> list, String str, int i, long j) {
        TextToImgRequest textToImgRequest = new TextToImgRequest(list, Integer.valueOf(this.w), Integer.valueOf(this.h), Integer.valueOf(i), "FAST_GREEN", "K_DPMPP_2S_ANCESTRAL", null, Long.valueOf(j), 50, str, 64, null);
        DrawServer retrofit = (DrawServer) RetrofitHelper.INSTANCE.getStabilityAIInstance().create(DrawServer.class);
        Log.d("DALLE", "API called Called");
        Intrinsics.checkNotNullExpressionValue(retrofit, "retrofit");
        DrawServer.DefaultImpls.textToImageRequest(retrofit, Constants.INSTANCE.getModel_End_Point(), null, textToImgRequest, 2, null).enqueue(new Callback<StableDiffResponse>() { // from class: speak.draw.ai.art.photo.image.generator.ResultActivity$generateImageFromText$1
            @Override // retrofit2.Callback
            public void onResponse(Call<StableDiffResponse> call, Response<StableDiffResponse> response) {
                ActivityResultBinding binding;
                List<Artifact> artifacts;
                Artifact artifact;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Log.d("DALLE", "Inside Response " + response.body());
                if (response.isSuccessful()) {
                    Log.e("onResponse", "isSuccessful: ");
                    LoadingDialog loadingDialog = ResultActivity.this.getLoadingDialog();
                    if (loadingDialog != null) {
                        loadingDialog.dismiss();
                    }
                    StableDiffResponse body = response.body();
                    Log.d("DALLE", "on Response Success\n " + (body != null ? body.getArtifacts() : null));
                    StableDiffResponse body2 = response.body();
                    if (body2 != null && (artifacts = body2.getArtifacts()) != null && (artifact = artifacts.get(0)) != null) {
                        r2 = artifact.getBase64();
                    }
                    String str2 = r2;
                    if (str2 == null || str2.length() == 0) {
                        return;
                    }
                    byte[] decode = Base64.decode(r2, 0);
                    byte[] bytes = r2.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                    Log.d("Response Size", "Size of response: " + bytes.length + " bytes");
                    Bitmap decodeByteArray = BitmapFactory.decodeByteArray(decode, 0, decode.length);
                    binding = ResultActivity.this.getBinding();
                    binding.imageBackGround.setImageBitmap(decodeByteArray);
                    Constants.INSTANCE.setBitmapResult(decodeByteArray);
                    return;
                }
                ResponseBody errorBody = response.errorBody();
                try {
                    Log.d("DALLE", "on Response isNotSuccess\n " + (errorBody != null ? errorBody.string() : null));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LoadingDialog loadingDialog2 = ResultActivity.this.getLoadingDialog();
                if (loadingDialog2 != null) {
                    loadingDialog2.dismiss();
                }
                String str3 = response.message().toString();
                ResponseBody errorBody2 = response.errorBody();
                try {
                    Log.e("onResponse", "else: " + (errorBody2 != null ? errorBody2.string() : null));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Log.e("onResponse", "else: " + str3);
                ExtensionKt.showToast(ResultActivity.this, "Prompt not valid, or connectivity issue try again with different prompt");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<StableDiffResponse> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                LoadingDialog loadingDialog = ResultActivity.this.getLoadingDialog();
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                if (StringsKt.contains((CharSequence) String.valueOf(t.getMessage()), (CharSequence) "Unable to resolve host",false)) {
                    return;
                }
                LoadingDialog loadingDialog2 = ResultActivity.this.getLoadingDialog();
                if (loadingDialog2 != null) {
                    loadingDialog2.dismiss();
                }
                ExtensionKt.showToast(ResultActivity.this, "Prompt not valid, or connectivity issue try again with different prompt");
            }
        });
    }
}
