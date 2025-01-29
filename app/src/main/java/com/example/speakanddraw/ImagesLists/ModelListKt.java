package com.example.speakanddraw.ImagesLists;

import androidx.constraintlayout.widget.ConstraintLayout;

import speak.draw.ai.art.photo.image.generator.R;
import com.example.speakanddraw.model.SelectModel;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* compiled from: ModelList.kt */

/* loaded from: classes.dex */
public final class ModelListKt {
    public static final ArrayList<SelectModel> modelList() {
        return CollectionsKt.arrayListOf(new SelectModel("Realistic", R.raw.model_4, "v1/generation/stable-diffusion-xl-1024-v1-0/text-to-image"), new SelectModel("Signature", R.raw.model_3, "v1/generation/stable-diffusion-xl-1024-v0-9/text-to-image"), new SelectModel("Animated", R.raw.model_1, "v1/generation/stable-diffusion-xl-beta-v2-2-2/text-to-image"), new SelectModel("Desire V1", R.raw.model_2, "v1/generation/stable-diffusion-512-v2-1/text-to-image"), new SelectModel("Magic V2", R.raw.model_5, "v1/generation/stable-diffusion-768-v2-1/text-to-image"), new SelectModel("Magic V1", R.raw.model_6, "v1/generation/stable-diffusion-768-v2-0/text-to-image"));
    }
}
