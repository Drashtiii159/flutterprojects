package com.example.speakanddraw.api_upscale;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.OkHttpClient;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

/* compiled from: ApiUpScale.kt */

/* loaded from: classes.dex */
public final class ApiUpScale {
    public static final ApiUpScale INSTANCE = new ApiUpScale();
    private static final String base_url = "https://upscale-vzt2zxsi7q-uc.a.run.app/";
    private static final Gson gson;
    private static final Lazy imageEnhanceService$delegate;
    private static final OkHttpClient okHttpClient;
    private static final Retrofit retrofit;

    static {
        Gson create = new GsonBuilder().setLenient().create();
        Intrinsics.checkNotNullExpressionValue(create, "GsonBuilder()           \u2026nient()\n        .create()");
        gson = create;
        OkHttpClient build = new OkHttpClient.Builder().connectTimeout(160L, TimeUnit.SECONDS).readTimeout(160L, TimeUnit.SECONDS).writeTimeout(160L, TimeUnit.SECONDS).build();
        okHttpClient = build;
        Retrofit build2 = new Retrofit.Builder().baseUrl(base_url).client(build).addConverterFactory(GsonConverterFactory.create(create)).build();
        Intrinsics.checkNotNullExpressionValue(build2, "Builder()\n        .baseU\u2026e(gson))\n        .build()");
        retrofit = build2;
        imageEnhanceService$delegate = LazyKt.lazy(new Function0<UpScale>() { // from class: com.example.speakanddraw.api_upscale.ApiUpScale$imageEnhanceService$2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final UpScale invoke() {
                Retrofit retrofit3;
                retrofit3 = ApiUpScale.retrofit;
                return (UpScale) retrofit3.create(UpScale.class);
            }
        });
    }

    private ApiUpScale() {
    }

    public final UpScale getImageEnhanceService() {
        Object value = imageEnhanceService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-imageEnhanceService>(...)");
        return (UpScale) value;
    }
}
