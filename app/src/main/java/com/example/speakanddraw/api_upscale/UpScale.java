package com.example.speakanddraw.api_upscale;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/* compiled from: UpScale.kt */

/* loaded from: classes.dex */
public interface UpScale {
    @POST("upscale_image/")
    @Multipart
    Call<ResponseBody> processImage(@Query("upscale_factor") int i, @Part MultipartBody.Part part);
}
