package com.example.speakanddraw.model;

import android.app.Application;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import speak.draw.ai.art.photo.image.generator.R;

import java.util.ArrayList;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RatioViewModel.kt */

/* loaded from: classes.dex */
public final class RatioViewModel extends ViewModel {
    private MutableLiveData<ArrayList<RatioData>> ratioList;

    public RatioViewModel(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.ratioList = new MutableLiveData<>();
    }

    public final MutableLiveData<ArrayList<RatioData>> getRatioList() {
        return this.ratioList;
    }

    public final void setRatioList(MutableLiveData<ArrayList<RatioData>> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.ratioList = mutableLiveData;
    }

    public final ArrayList<RatioData> getRatios() {
        return CollectionsKt.arrayListOf(new RatioData(R.drawable.r1, "1:1", 1024, 1024), new RatioData(R.drawable.r2, "9:7", 1152, 896), new RatioData(R.drawable.r3, "19:13", 1216, 832), new RatioData(R.drawable.r4, "16:9", 1344, 768), new RatioData(R.drawable.r5, "12:5", 1536, 640), new RatioData(R.drawable.r6, "5:12", 640, 1536), new RatioData(R.drawable.r2, "9:16", 768, 1344), new RatioData(R.drawable.r3, "13:19", 832, 1216), new RatioData(R.drawable.r4, "7:9", 896, 1152));
    }

    public final ArrayList<RatioData> getRatios1() {
        return CollectionsKt.arrayListOf(new RatioData(R.drawable.r1, "1:1", 512, 512), new RatioData(R.drawable.r2, "9:7", 512, 896), new RatioData(R.drawable.r3, "19:13", 896, 512), new RatioData(R.drawable.r4, "16:9", 512, 384), new RatioData(R.drawable.r5, "12:5", 768, 512), new RatioData(R.drawable.r6, "5:12", 384, 512), new RatioData(R.drawable.r2, "9:16", 640, 512), new RatioData(R.drawable.r1, "13:19", 512, 640), new RatioData(R.drawable.r3, "7:9", 512, 768));
    }

    public final ArrayList<RatioData> getRatios2() {
        return CollectionsKt.arrayListOf(new RatioData(R.drawable.r1, "1:1", 768, 768), new RatioData(R.drawable.r2, "3:2", 1152, 768), new RatioData(R.drawable.r3, "4:3", 1024, 768), new RatioData(R.drawable.r4, "16:9", 1365, 768), new RatioData(R.drawable.r5, "7:4", 1344, 768), new RatioData(R.drawable.r6, "5:4", 960, 768), new RatioData(R.drawable.r2, "9:16", 768, 1365), new RatioData(R.drawable.r1, "16:9", 1365, 768), new RatioData(R.drawable.r3, "3:4", 768, 1024));
    }
}
