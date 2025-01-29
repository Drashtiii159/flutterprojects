package com.example.speakanddraw.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.speakanddraw.Ads.AdsExtensionKt;
import com.example.speakanddraw.Dialog.LoadingDialog;
import com.example.speakanddraw.ImagesLists.ListsKt;
import com.example.speakanddraw.ImagesLists.ModelListKt;
import com.example.speakanddraw.Interface.DrawServer;
import com.example.speakanddraw.PermiumActivity;
import speak.draw.ai.art.photo.image.generator.R;

import com.example.speakanddraw.ResultActivity;
import com.example.speakanddraw.RetrofitHelper;
import com.example.speakanddraw.adapter.AnimeAdapter;
import com.example.speakanddraw.adapter.RatioAdapter;
import com.example.speakanddraw.adapter.SelectModelAdapter;
import com.example.speakanddraw.model.Anime;
import com.example.speakanddraw.model.Artifact;
import com.example.speakanddraw.model.RatioViewModel;
import com.example.speakanddraw.model.SelectModel;
import com.example.speakanddraw.model.StableDiffResponse;
import com.example.speakanddraw.model.TextPrompts;
import com.example.speakanddraw.model.TextToImgRequest;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.permission.CheckMicPermissionsKt;
import com.example.speakanddraw.utlis.ExtensionKt;
import com.example.speakanddraw.utlis.SpeechRecognitionManager;
import com.example.speakanddraw.utlis.TinyDB;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import org.koin.android.ext.android.AndroidKoinScopeExtKt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import speak.draw.ai.art.photo.image.generator.databinding.FragmentHomeBinding;
import speak.draw.ai.art.photo.image.generator.databinding.RatioBottomSheetBinding;
import speak.draw.ai.art.photo.image.generator.databinding.RecordDialogBinding;
import speak.draw.ai.art.photo.image.generator.databinding.SeeallImagesBottomSheetBinding;
import speak.draw.ai.art.photo.image.generator.databinding.SelectModelBinding;
import speak.draw.ai.art.photo.image.generator.databinding.SettingsBottomSheetBinding;

/* compiled from: HomeFragment.kt */

/* loaded from: classes.dex */
public final class HomeFragment extends Fragment {
    public static final Companion Companion = new Companion(null);
    private static int cfGScale = 7;
    private static long seedValue;
    private final ActivityResultLauncher<String[]> permissionRequest;
    private final RatioViewModel ratioViewModel;
    private final TinyDB tinyDB;
    public FragmentHomeBinding binding;
    public RecordDialogBinding bindingH;
    public Dialog dialogH;
    public SharedPreferences sharedPrefs;
    private AnimeAdapter animeAdapter;
    private String combinedString;
    private LoadingDialog loadingDialog;
    private SpeechRecognitionManager speechRecognitionManager;
    private String userInput;
    private int w = 1024;
    private int h = 1024;
    private String negativePromptValue = "";
    private String r2;

    public HomeFragment() {
        HomeFragment homeFragment = this;
        this.tinyDB = (TinyDB) AndroidKoinScopeExtKt.getKoinScope(homeFragment).get(Reflection.getOrCreateKotlinClass(TinyDB.class), null, null);
        this.ratioViewModel = (RatioViewModel) AndroidKoinScopeExtKt.getKoinScope(homeFragment).get(Reflection.getOrCreateKotlinClass(RatioViewModel.class), null, null);
        ActivityResultLauncher<String[]> registerForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda6
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                HomeFragment.permissionRequest$lambda$0(HomeFragment.this, (Map) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(registerForActivityResult, "registerForActivityResul\u2026sDenied()\n        }\n    }");
        this.permissionRequest = registerForActivityResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$7(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void permissionRequest$lambda$0(HomeFragment this$0, Map map) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(map.get("android.permission.RECORD_AUDIO"), (Object) true)) {
            return;
        }
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        CheckMicPermissionsKt.micPermissionsDenied(requireActivity);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$2(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.ratioBottomSheet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$3(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.settingBottomSheet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$4(HomeFragment this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.showKeyboard(requireActivity, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$5(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.seeAllImages();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$11$lambda$6(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.selectModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean clickListener$lambda$11$lambda$8(final HomeFragment this$0, View view, final MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        CheckMicPermissionsKt.checkMicPermissions(requireActivity, new Function1<Boolean, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$clickListener$1$7$1
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
    public final void clickListener$lambda$11$lambda$9(final HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity requireActivity = this$0.requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        CheckMicPermissionsKt.checkMicPermissions(requireActivity, new Function1<Boolean, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$clickListener$1$8$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z) {
                if (z) {
               HomeFragment.this.showRecordingDialog();
                } else {
                    HomeFragment.this.requestPermissions();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static  void clickListener$lambda$11$lambda$10(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context requireContext = this$0.requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        if (!ExtensionKt.isNetworkConnected(requireContext)) {
            Context requireContext2 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            ExtensionKt.showToast(requireContext2, "please connect to Internet");
            return;
        }
        String obj = StringsKt.trim((CharSequence) this$0.getBinding().editText.getText().toString()).toString();
        this$0.userInput = obj;
        Intrinsics.checkNotNull(obj);
        if (obj.length() == 0) {
            Context requireContext3 = this$0.requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext3, "requireContext()");
            ExtensionKt.showToast(requireContext3, "please enter your prompt");
            return;
        }
        LoadingDialog loadingDialog = this$0.loadingDialog;
        if (loadingDialog != null) {
            loadingDialog.show();
        }
        this$0.sendResonce();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void selectModel$lambda$13(HomeFragment this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Constants.INSTANCE.setModel_End_Point(String.valueOf(TinyDB.getString$default(this$0.tinyDB, "ModelEndPoint", null, 2, null)));
        this$0.w = TinyDB.getInt$default(this$0.tinyDB, "Width", 0, 2, null);
        this$0.w = TinyDB.getInt$default(this$0.tinyDB, "Height", 0, 2, null);
        int ratioController = Constants.INSTANCE.getRatioController();
        if (ratioController == 1) {
            this$0.ratioViewModel.getRatioList().postValue(this$0.ratioViewModel.getRatios());
        } else if (ratioController == 2) {
            this$0.ratioViewModel.getRatioList().postValue(this$0.ratioViewModel.getRatios2());
        } else if (ratioController != 3) {
        } else {
            this$0.ratioViewModel.getRatioList().postValue(this$0.ratioViewModel.getRatios1());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean showRecordingDialog$lambda$17$lambda$14(HomeFragment this$0, View view, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SpeechRecognitionManager speechRecognitionManager = null;
        if (motionEvent.getAction() == 1) {
            SpeechRecognitionManager speechRecognitionManager2 = this$0.speechRecognitionManager;
            if (speechRecognitionManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("speechRecognitionManager");
                speechRecognitionManager2 = null;
            }
            speechRecognitionManager2.stopListening();
        }
        if (motionEvent.getAction() == 0) {
            SpeechRecognitionManager speechRecognitionManager3 = this$0.speechRecognitionManager;
            if (speechRecognitionManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("speechRecognitionManager");
            } else {
                speechRecognitionManager = speechRecognitionManager3;
            }
            speechRecognitionManager.startListening();
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRecordingDialog$lambda$17$lambda$15(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.combinedString = this$0.getBindingH().textHold.getText().toString();
        this$0.getBinding().editText.setText(this$0.combinedString);
        LottieAnimationView lottieAnimationView = this$0.getBinding().lottie;
        Intrinsics.checkNotNullExpressionValue(lottieAnimationView, "binding.lottie");
        AdsExtensionKt.Gone(lottieAnimationView);
        this$0.getDialogH().dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showRecordingDialog$lambda$17$lambda$16(HomeFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getBindingH().textHold.setText(this$0.getString(R.string.tap_and_hold));
        this$0.getBindingH().cross.setVisibility(View.INVISIBLE);
        this$0.getBindingH().tick.setVisibility(View.INVISIBLE);
    }

    static /* synthetic */ void generateImageFromText$default(HomeFragment homeFragment, List list, String str, int i, long j, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        homeFragment.generateImageFromText(list, str, i, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ratioBottomSheet$lambda$18(BottomSheetDialog rcDialog, View view) {
        Intrinsics.checkNotNullParameter(rcDialog, "$rcDialog");
        rcDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void settingBottomSheet$lambda$23(SettingsBottomSheetBinding bindingH, HomeFragment this$0, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(bindingH, "$bindingH");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String obj = bindingH.seedValue.getText().toString();
        seedValue = obj == null || obj.length() == 0 ? this$0.convertStringToInt(bindingH.seedValue.getText().toString()) : 0L;
        this$0.negativePromptValue = bindingH.negativePrompt.getText().toString();
        String obj2 = bindingH.seedValue.getText().toString();
        if (!(obj2.length() > 0)) {
            obj2 = null;
        }
        if (obj2 == null) {
            obj2 = "0";
        }
        int i = cfGScale;
        String obj3 = bindingH.negativePrompt.getText().toString();
        SharedPreferences.Editor edit = this$0.getSharedPrefs().edit();
        edit.putInt("cfg_scale", i);
        edit.putString("seeding_value", obj2);
        edit.putString("negative_prompt_value", obj3);
        edit.apply();
    }

    public final FragmentHomeBinding getBinding() {
        FragmentHomeBinding fragmentHomeBinding = this.binding;
        if (fragmentHomeBinding != null) {
            return fragmentHomeBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(FragmentHomeBinding fragmentHomeBinding) {
        Intrinsics.checkNotNullParameter(fragmentHomeBinding, "<set-?>");
        this.binding = fragmentHomeBinding;
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

    public final TinyDB getTinyDB() {
        return this.tinyDB;
    }

    public final String getNegativePromptValue() {
        return this.negativePromptValue;
    }

    public final void setNegativePromptValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.negativePromptValue = str;
    }

    public final SharedPreferences getSharedPrefs() {
        SharedPreferences sharedPreferences = this.sharedPrefs;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        Intrinsics.throwUninitializedPropertyAccessException("sharedPrefs");
        return null;
    }

    public final void setSharedPrefs(SharedPreferences sharedPreferences) {
        Intrinsics.checkNotNullParameter(sharedPreferences, "<set-?>");
        this.sharedPrefs = sharedPreferences;
    }

    public final LoadingDialog getLoadingDialog() {
        return this.loadingDialog;
    }

    public final void setLoadingDialog(LoadingDialog loadingDialog) {
        this.loadingDialog = loadingDialog;
    }

    public final Dialog getDialogH() {
        Dialog dialog = this.dialogH;
        if (dialog != null) {
            return dialog;
        }
        Intrinsics.throwUninitializedPropertyAccessException("dialogH");
        return null;
    }

    public final void setDialogH(Dialog dialog) {
        Intrinsics.checkNotNullParameter(dialog, "<set-?>");
        this.dialogH = dialog;
    }

    public final RecordDialogBinding getBindingH() {
        RecordDialogBinding recordDialogBinding = this.bindingH;
        if (recordDialogBinding != null) {
            return recordDialogBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("bindingH");
        return null;
    }

    public final void setBindingH(RecordDialogBinding recordDialogBinding) {
        Intrinsics.checkNotNullParameter(recordDialogBinding, "<set-?>");
        this.bindingH = recordDialogBinding;
    }

    public final String getUserInput() {
        return this.userInput;
    }

    public final void setUserInput(String str) {
        this.userInput = str;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume: called");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentHomeBinding inflate = FragmentHomeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.loadingDialog = new LoadingDialog(requireActivity);
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("Speak_and_Draw", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "requireContext().getShar\u2026xt.MODE_PRIVATE\n        )");
        setSharedPrefs(sharedPreferences);
        if (ExtensionKt.getTrythisBoolean()) {
            getBinding().editText.setText(ExtensionKt.getTrythisValue());
            LottieAnimationView lottieAnimationView = getBinding().lottie;
            Intrinsics.checkNotNullExpressionValue(lottieAnimationView, "binding.lottie");
            AdsExtensionKt.Gone(lottieAnimationView);
            ImageView imageView = getBinding().micImage;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.micImage");
            AdsExtensionKt.Gone(imageView);
            ImageView imageView2 = getBinding().keyboard;
            Intrinsics.checkNotNullExpressionValue(imageView2, "binding.keyboard");
            AdsExtensionKt.Gone(imageView2);
        }
        if (ExtensionKt.getTrythisOnBack()) {
            getBinding().editText.setText("");
            LottieAnimationView lottieAnimationView2 = getBinding().lottie;
            Intrinsics.checkNotNullExpressionValue(lottieAnimationView2, "binding.lottie");
            AdsExtensionKt.visible(lottieAnimationView2);
            ImageView imageView3 = getBinding().micImage;
            Intrinsics.checkNotNullExpressionValue(imageView3, "binding.micImage");
            AdsExtensionKt.visible(imageView3);
            ImageView imageView4 = getBinding().keyboard;
            Intrinsics.checkNotNullExpressionValue(imageView4, "binding.keyboard");
            AdsExtensionKt.visible(imageView4);
            ExtensionKt.setTrythisOnBack(false);
        }
        final FragmentHomeBinding binding = getBinding();
        binding.editText.addTextChangedListener(new TextWatcher() { // from class: com.example.speakanddraw.Fragment.HomeFragment$onCreateView$1$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                LottieAnimationView lottie = binding.lottie;
                Intrinsics.checkNotNullExpressionValue(lottie, "lottie");
                AdsExtensionKt.visible(lottie);
                ImageView micImage = binding.micImage;
                Intrinsics.checkNotNullExpressionValue(micImage, "micImage");
                AdsExtensionKt.visible(micImage);
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Intrinsics.checkNotNull(charSequence);
                if (charSequence.length() == 0) {
                    Log.e("count", "onTextChanged: " + i3);
                    LottieAnimationView lottie = binding.lottie;
                    Intrinsics.checkNotNullExpressionValue(lottie, "lottie");
                    AdsExtensionKt.visible(lottie);
                    ImageView micImage = binding.micImage;
                    Intrinsics.checkNotNullExpressionValue(micImage, "micImage");
                    AdsExtensionKt.visible(micImage);
                    ImageView keyboard = binding.keyboard;
                    Intrinsics.checkNotNullExpressionValue(keyboard, "keyboard");
                    AdsExtensionKt.visible(keyboard);
                    return;
                }
                LottieAnimationView lottie2 = binding.lottie;
                Intrinsics.checkNotNullExpressionValue(lottie2, "lottie");
                AdsExtensionKt.Gone(lottie2);
                ImageView micImage2 = binding.micImage;
                Intrinsics.checkNotNullExpressionValue(micImage2, "micImage");
                AdsExtensionKt.Gone(micImage2);
                ImageView keyboard2 = binding.keyboard;
                Intrinsics.checkNotNullExpressionValue(keyboard2, "keyboard");
                AdsExtensionKt.Gone(keyboard2);
            }
        });
        clickListener();
        styleRcv();
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    private final void styleRcv() {
        getBinding().styleRecycler.setLayoutManager(new GridLayoutManager(requireContext(), 2, RecyclerView.HORIZONTAL, false));
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        this.animeAdapter = new AnimeAdapter(requireContext, ListsKt.addFilter1());
        getBinding().styleRecycler.setAdapter(this.animeAdapter);
        AnimeAdapter animeAdapter = this.animeAdapter;
        if (animeAdapter == null) {
            return;
        }
        animeAdapter.setOnCopyItemCLick(new Function2<Anime, Integer, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$styleRcv$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Anime anime, Integer num) {
                invoke(anime, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Anime pos, int i) {
                Intrinsics.checkNotNullParameter(pos, "pos");
                HomeFragment.this.getTinyDB().putString("StyleId", String.valueOf(pos.getStyleId()));
                Log.d("styleRcv", "styleRcv:   " + i + " ");
            }
        });
    }

    private final void clickListener() {
        FragmentHomeBinding binding = getBinding();
        binding.ratio.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$2(HomeFragment.this, view);
            }
        });
        binding.settings.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$3(HomeFragment.this, view);
            }
        });
        binding.keyboard.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$4(HomeFragment.this, view);
            }
        });
        binding.moreLayout.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$5(HomeFragment.this, view);
            }
        });
        binding.model.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$6(HomeFragment.this, view);
            }
        });
        binding.dots.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$7(view);
            }
        });
        binding.mic.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean clickListener$lambda$11$lambda$8;
                clickListener$lambda$11$lambda$8 = HomeFragment.clickListener$lambda$11$lambda$8(HomeFragment.this, view, motionEvent);
                return clickListener$lambda$11$lambda$8;
            }
        });
        binding.lottie.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
          clickListener$lambda$11$lambda$9(HomeFragment.this, view);
            }
        });
        binding.send.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.clickListener$lambda$11$lambda$10(HomeFragment.this, view);
            }
        });
    }

    private final void showKeyboard(Activity activity, View view) {
        Object systemService = activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        ((InputMethodManager) systemService).toggleSoftInput(2, 0);
    }

    private final void selectModel() {
        SelectModelBinding inflate = SelectModelBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context))");
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(inflate.getRoot());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        SelectModelAdapter selectModelAdapter = new SelectModelAdapter(requireContext, ModelListKt.modelList());
        RecyclerView recyclerView = inflate.reyclerView;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 2, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(selectModelAdapter);
        selectModelAdapter.setOnCopyItemCLick(new Function2<SelectModel, Integer, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$selectModel$1$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(SelectModel selectModel, Integer num) {
                invoke(selectModel, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(SelectModel item, int i) {
                Intrinsics.checkNotNullParameter(item, "item");
                HomeFragment.this.getTinyDB().putString("ModelEndPoint", String.valueOf(item.getEndPoint()));
                HomeFragment.this.getTinyDB().putString("ModelName", item.getName());
                if (i == 0) {
                    HomeFragment.this.getTinyDB().putInt("RatioValues", 1);
                    HomeFragment.this.getTinyDB().putInt("Width", 1024);
                    HomeFragment.this.getTinyDB().putInt("Height", 1024);
                } else if (i == 1) {
                    HomeFragment.this.getTinyDB().putInt("RatioValues", 1);
                    HomeFragment.this.getTinyDB().putInt("Width", 1024);
                    HomeFragment.this.getTinyDB().putInt("Height", 1024);
                    Log.e("TAG", "selectModel:0,1 ");
                } else if (i == 4 || i == 5) {
                    HomeFragment.this.getTinyDB().putInt("RatioValues", 2);
                    HomeFragment.this.getTinyDB().putInt("Width", 768);
                    HomeFragment.this.getTinyDB().putInt("Height", 768);
                    Log.e("TAG", "selectModel:4,5 ");
                } else {
                    HomeFragment.this.getTinyDB().putInt("RatioValues", 3);
                    HomeFragment.this.getTinyDB().putInt("Width", 512);
                    HomeFragment.this.getTinyDB().putInt("Height", 512);
                    Log.e("TAG", "selectModel:else ");
                }
            }
        });
        bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda5
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                HomeFragment.selectModel$lambda$13(HomeFragment.this, dialogInterface);
            }
        });
        bottomSheetDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showRecordingDialog() {
        RecordDialogBinding inflate = RecordDialogBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context))");
        setBindingH(inflate);
        setDialogH(new BottomSheetDialog(requireContext()));
        getDialogH().setContentView(getBindingH().getRoot());
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        this.speechRecognitionManager = new SpeechRecognitionManager(requireActivity, getBindingH());
        LottieAnimationView lottieAnimationView = getBindingH().lottie;
        Intrinsics.checkNotNullExpressionValue(lottieAnimationView, "bindingH.lottie");
        lottieAnimationView.setAnimation(R.raw.loading);
        lottieAnimationView.setRepeatCount(-1);
        lottieAnimationView.playAnimation();
        lottieAnimationView.setSpeed(0.5f);
        getBindingH().cross.setVisibility(View.INVISIBLE);
        getBindingH().tick.setVisibility(View.INVISIBLE);
        RecordDialogBinding bindingH = getBindingH();
        bindingH.recordMic.setOnTouchListener(new View.OnTouchListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                boolean showRecordingDialog$lambda$17$lambda$14;
                showRecordingDialog$lambda$17$lambda$14 = HomeFragment.showRecordingDialog$lambda$17$lambda$14(HomeFragment.this, view, motionEvent);
                return showRecordingDialog$lambda$17$lambda$14;
            }
        });
        bindingH.tick.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.showRecordingDialog$lambda$17$lambda$15(HomeFragment.this, view);
            }
        });
        bindingH.cross.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.showRecordingDialog$lambda$17$lambda$16(HomeFragment.this, view);
            }
        });
        getDialogH().show();
    }

    private final void sendResonce() {
        Constants constants = Constants.INSTANCE;
        String str = this.userInput;
        Intrinsics.checkNotNull(str, "null cannot be cast to non-null type kotlin.String");
        constants.setUserText(str);
        Constants.INSTANCE.setStyleId(String.valueOf(TinyDB.getString$default(this.tinyDB, "StyleId", null, 2, null)));
        String str2 = this.userInput + " " + Constants.INSTANCE.getStyleId();
        Log.d("styleRcv", "finalPrompt:    " + str2 + " ");
        generateImageFromText(CollectionsKt.mutableListOf(new TextPrompts(str2, 0.7d)), null, cfGScale, seedValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void requestPermissions() {
        this.permissionRequest.launch(new String[]{"android.permission.RECORD_AUDIO"});
    }

    public final ArrayList<Anime> Items() {
        return CollectionsKt.arrayListOf(new Anime("1:1", R.drawable.r1, null, 4, null), new Anime("9:7", R.drawable.r2, null, 4, null), new Anime("19:13", R.drawable.r3, null, 4, null), new Anime("16:9", R.drawable.r4, null, 4, null), new Anime("12:5", R.drawable.r5, null, 4, null), new Anime("5:12", R.drawable.r6, null, 4, null), new Anime("9:16", R.drawable.r1, null, 4, null), new Anime("13:19", R.drawable.r2, null, 4, null), new Anime("7:9", R.drawable.r5, null, 4, null));
    }

    private final void generateImageFromText(List<TextPrompts> list, String str, int i, long j) {
        TextToImgRequest textToImgRequest = new TextToImgRequest(list, Integer.valueOf(this.w), Integer.valueOf(this.h), Integer.valueOf(i), "FAST_GREEN", "K_DPMPP_2S_ANCESTRAL", null, Long.valueOf(j), 50, str, 64, null);
        DrawServer retrofit = (DrawServer) RetrofitHelper.INSTANCE.getStabilityAIInstance().create(DrawServer.class);
        Log.d("DALLE", "API called Called");
        Log.d("TAG", "generateImageFromText: " + Constants.INSTANCE.getModel_End_Point());
        Intrinsics.checkNotNullExpressionValue(retrofit, "retrofit");
        DrawServer.DefaultImpls.textToImageRequest(retrofit, Constants.INSTANCE.getModel_End_Point(), null, textToImgRequest, 2, null).enqueue(new Callback<StableDiffResponse>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$generateImageFromText$1
            @Override // retrofit2.Callback
            public void onResponse(Call<StableDiffResponse> call, Response<StableDiffResponse> response) {
                List<Artifact> artifacts;
                Artifact artifact;
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                Log.d("DALLE", "Inside Response " + response.body());
                if (response.isSuccessful()) {
                    Log.e("onResponse", "isSuccessful: ");
                    LoadingDialog loadingDialog = HomeFragment.this.getLoadingDialog();
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
                    Constants.INSTANCE.setBitmapResult(BitmapFactory.decodeByteArray(decode, 0, decode.length));
                    HomeFragment.this.startActivity(new Intent(HomeFragment.this.requireContext(), ResultActivity.class));
                    return;
                }
                ResponseBody errorBody = response.errorBody();
                try {
                    Log.d("DALLE", "on Response isNotSuccess\n " + (errorBody != null ? errorBody.string() : null));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                LoadingDialog loadingDialog2 = HomeFragment.this.getLoadingDialog();
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
                FragmentActivity requireActivity = HomeFragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionKt.showToast(requireActivity, "Prompt not valid, or connectivity issue try again with different prompt");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<StableDiffResponse> call, Throwable t) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(t, "t");
                LoadingDialog loadingDialog = HomeFragment.this.getLoadingDialog();
                if (loadingDialog != null) {
                    loadingDialog.dismiss();
                }
                if (StringsKt.contains((CharSequence) String.valueOf(t.getMessage()), (CharSequence) "Unable to resolve host", false)) {
                    return;
                }
                LoadingDialog loadingDialog2 = HomeFragment.this.getLoadingDialog();
                if (loadingDialog2 != null) {
                    loadingDialog2.dismiss();
                }
                FragmentActivity requireActivity = HomeFragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                ExtensionKt.showToast(requireActivity, "Prompt not valid, or connectivity issue try again with different prompt");
            }
        });
    }

    private final void ratioBottomSheet() {
        RatioBottomSheetBinding inflate = RatioBottomSheetBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context))");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(inflate.getRoot());
        ArrayList<Anime> Items = Items();
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        RatioAdapter ratioAdapter = new RatioAdapter(requireContext, Items);
        inflate.tick.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HomeFragment.ratioBottomSheet$lambda$18(bottomSheetDialog, view);
            }
        });
        RecyclerView recyclerView = inflate.reyclerView;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), RecyclerView.HORIZONTAL, false));
        recyclerView.setAdapter(ratioAdapter);
        ratioAdapter.setOnCopyItemCLick(new Function1<Integer, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$ratioBottomSheet$2$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Integer num) {
                invoke(num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(int i) {
                switch (i) {
                    case 0:
                        HomeFragment.this.setW(1024);
                        HomeFragment.this.setH(1024);
                        return;
                    case 1:
                        HomeFragment.this.setW(1152);
                        HomeFragment.this.setH(896);
                        return;
                    case 2:
                        HomeFragment.this.setW(1216);
                        HomeFragment.this.setH(832);
                        return;
                    case 3:
                        HomeFragment.this.setW(1344);
                        HomeFragment.this.setH(768);
                        return;
                    case 4:
                        HomeFragment.this.setW(1536);
                        HomeFragment.this.setH(640);
                        return;
                    case 5:
                        HomeFragment.this.setW(640);
                        HomeFragment.this.setH(1536);
                        return;
                    case 6:
                        HomeFragment.this.setW(768);
                        HomeFragment.this.setH(1344);
                        return;
                    case 7:
                        HomeFragment.this.setW(832);
                        HomeFragment.this.setH(1216);
                        return;
                    case 8:
                        HomeFragment.this.setW(896);
                        HomeFragment.this.setH(1152);
                        return;
                    default:
                        return;
                }
            }
        });
        bottomSheetDialog.show();
    }

    private final void seeAllImages() {
        SeeallImagesBottomSheetBinding inflate = SeeallImagesBottomSheetBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context))");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(inflate.getRoot());
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        AnimeAdapter animeAdapter = new AnimeAdapter(requireContext, ListsKt.addFilter1());
        RecyclerView recyclerView = inflate.reyclerView;
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(animeAdapter);
        animeAdapter.setOnCopyItemCLick(new Function2<Anime, Integer, Unit>() { // from class: com.example.speakanddraw.Fragment.HomeFragment$seeAllImages$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */ {
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Anime anime, Integer num) {
                invoke(anime, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Anime it, int i) {
                AnimeAdapter animeAdapter2;
                AnimeAdapter animeAdapter3;
                Intrinsics.checkNotNullParameter(it, "it");
                if (i == 1 || i == 3 || i == 5 || i == 21 || i == 24 || i == 30 || i == 32) {
                    Context requireContext2 = HomeFragment.this.requireContext();
                    Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
                    if (!AdsExtensionKt.isAlreadyPurchased(requireContext2)) {
                        FragmentActivity requireActivity = HomeFragment.this.requireActivity();
                        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                        FragmentActivity fragmentActivity = requireActivity;
                        fragmentActivity.startActivity(new Intent(fragmentActivity, PermiumActivity.class));
                    } else {
                        HomeFragment.this.getTinyDB().putString("StyleId", String.valueOf(it.getStyleId()));
                        animeAdapter2 = HomeFragment.this.animeAdapter;
                        if (animeAdapter2 != null) {
                            animeAdapter2.notifyDataSetChanged();
                        }
                    }
                } else {
                    HomeFragment.this.getTinyDB().putString("StyleId", String.valueOf(it.getStyleId()));
                    animeAdapter3 = HomeFragment.this.animeAdapter;
                    if (animeAdapter3 != null) {
                        animeAdapter3.notifyDataSetChanged();
                    }
                }
                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    private final void settingBottomSheet() {
        final SettingsBottomSheetBinding inflate = SettingsBottomSheetBinding.inflate(LayoutInflater.from(getContext()));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(context))");
        setDialogH(new BottomSheetDialog(requireContext()));
        getDialogH().setContentView(inflate.getRoot());
        inflate.slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$settingBottomSheet$1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                HomeFragment.Companion companion = HomeFragment.Companion;
                Intrinsics.checkNotNull(seekBar);
                companion.setCfGScale(seekBar.getProgress());
                HomeFragment.this.saveSeekBarProgress(HomeFragment.Companion.getCfGScale());
            }
        });
        getDialogH().setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.example.speakanddraw.Fragment.HomeFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                HomeFragment.settingBottomSheet$lambda$23(inflate, HomeFragment.this, dialogInterface);
            }
        });
        inflate.slider.setProgress(getSharedPrefs().getInt("cfg_scale", 7));
        inflate.seedValue.setText(getSharedPrefs().getString("seeding_value", "0"));
        inflate.negativePrompt.setText(getSharedPrefs().getString("negative_prompt_value", ""));
        getDialogH().show();
    }

    public final long convertStringToInt(String str) {
        Intrinsics.checkNotNullParameter(str, "str");
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            System.out.println((Object) ("Error: " + e.getMessage()));
            return 0L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void saveSeekBarProgress(int i) {
        SharedPreferences.Editor edit = requireContext().getSharedPreferences("MyPrefSeekBar", 0).edit();
        edit.putInt("seekBarProgress", i);
        edit.apply();
    }

    /* compiled from: HomeFragment.kt */
    
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final long getSeedValue() {
            return HomeFragment.seedValue;
        }

        public final void setSeedValue(long j) {
            HomeFragment.seedValue = j;
        }

        public final int getCfGScale() {
            return HomeFragment.cfGScale;
        }

        public final void setCfGScale(int i) {
            HomeFragment.cfGScale = i;
        }
    }
}
