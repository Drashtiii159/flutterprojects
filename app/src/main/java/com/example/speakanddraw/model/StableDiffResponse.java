package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: StableDiffResponse.kt */

/* loaded from: classes.dex */
public final class StableDiffResponse {
    private final List<Artifact> artifacts;

    public StableDiffResponse(List<Artifact> artifacts) {
        Intrinsics.checkNotNullParameter(artifacts, "artifacts");
        this.artifacts = artifacts;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StableDiffResponse copy$default(StableDiffResponse stableDiffResponse, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = stableDiffResponse.artifacts;
        }
        return stableDiffResponse.copy(list);
    }

    public final List<Artifact> component1() {
        return this.artifacts;
    }

    public final StableDiffResponse copy(List<Artifact> artifacts) {
        Intrinsics.checkNotNullParameter(artifacts, "artifacts");
        return new StableDiffResponse(artifacts);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StableDiffResponse) && Intrinsics.areEqual(this.artifacts, ((StableDiffResponse) obj).artifacts);
    }

    public int hashCode() {
        return this.artifacts.hashCode();
    }

    public String toString() {
        return "StableDiffResponse(artifacts=" + this.artifacts + ")";
    }

    public final List<Artifact> getArtifacts() {
        return this.artifacts;
    }
}
