package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Artifact.kt */

/* loaded from: classes.dex */
public final class Artifact {
    private final String base64;
    private final String finishReason;
    private final long seed;

    public Artifact(String base64, String finishReason, long j) {
        Intrinsics.checkNotNullParameter(base64, "base64");
        Intrinsics.checkNotNullParameter(finishReason, "finishReason");
        this.base64 = base64;
        this.finishReason = finishReason;
        this.seed = j;
    }

    public static /* synthetic */ Artifact copy$default(Artifact artifact, String str, String str2, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = artifact.base64;
        }
        if ((i & 2) != 0) {
            str2 = artifact.finishReason;
        }
        if ((i & 4) != 0) {
            j = artifact.seed;
        }
        return artifact.copy(str, str2, j);
    }

    public final String component1() {
        return this.base64;
    }

    public final String component2() {
        return this.finishReason;
    }

    public final long component3() {
        return this.seed;
    }

    public final Artifact copy(String base64, String finishReason, long j) {
        Intrinsics.checkNotNullParameter(base64, "base64");
        Intrinsics.checkNotNullParameter(finishReason, "finishReason");
        return new Artifact(base64, finishReason, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Artifact) {
            Artifact artifact = (Artifact) obj;
            return Intrinsics.areEqual(this.base64, artifact.base64) && Intrinsics.areEqual(this.finishReason, artifact.finishReason) && this.seed == artifact.seed;
        }
        return false;
    }

    public int hashCode() {
        return (((this.base64.hashCode() * 31) + this.finishReason.hashCode()) * 31) + Long.hashCode(this.seed);
    }

    public String toString() {
        String str = this.base64;
        String str2 = this.finishReason;
        return "Artifact(base64=" + str + ", finishReason=" + str2 + ", seed=" + this.seed + ")";
    }

    public final String getBase64() {
        return this.base64;
    }

    public final String getFinishReason() {
        return this.finishReason;
    }

    public final long getSeed() {
        return this.seed;
    }
}
