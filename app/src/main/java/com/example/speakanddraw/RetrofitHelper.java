package com.example.speakanddraw;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;


/* compiled from: RetrofitHelper.kt */

/* loaded from: classes.dex */
public final class RetrofitHelper {
    public static final RetrofitHelper INSTANCE = new RetrofitHelper();
    private static final String BASE_URL = "Bearer sk-qp5NDwJAedP9R1yKbFajeuC5CEODzmj60nN5700bALBrMLIZ";
    private static final String STB_AI_BASE_URL = "https://api.stability.ai/";
    private static final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() { // from class: speak.draw.ai.art.photo.image.generator.RetrofitHelper$$ExternalSyntheticLambda0
        @Override // okhttp3.Interceptor
        public final Response intercept(Interceptor.Chain chain) {
            Response client$lambda$0;
            client$lambda$0 = RetrofitHelper.client$lambda$0(chain);
            return client$lambda$0;
        }
    }).connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();

    private RetrofitHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response client$lambda$0(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request build = chain.request().newBuilder().addHeader("Authorization", BASE_URL).build();
        int size = build.headers().size();
        for (int i = 0; i < size; i++) {
            String name = build.headers().name(i);
            System.out.println((Object) ("DALLE_HEADER :" + name + ": " + build.headers().value(i)));
        }
        try {
            return chain.proceed(build);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final String getBASE_URL() {
        return BASE_URL;
    }

    public final String getSTB_AI_BASE_URL() {
        return STB_AI_BASE_URL;
    }

    public final Retrofit getStabilityAIInstance() {
        Retrofit build = new Retrofit.Builder().baseUrl(STB_AI_BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client).build();
        Intrinsics.checkNotNullExpressionValue(build, "Builder()\n            .b\u2026ent)\n            .build()");
        return build;
    }
}
