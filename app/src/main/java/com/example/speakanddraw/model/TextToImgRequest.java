package com.example.speakanddraw.model;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TextToImgRequest.kt */

/* loaded from: classes.dex */
public final class TextToImgRequest {
    private final Integer cfg_scale;
    private final String clip_guidance_preset;
    private final Integer height;
    private final String sampler;
    private final Integer samples;
    private final Long seed;
    private final Integer steps;
    private final String style_preset;
    private final List<TextPrompts> text_prompts;
    private final Integer width;

    public TextToImgRequest(List<TextPrompts> text_prompts, Integer num, Integer num2, Integer num3, String str, String str2, Integer num4, Long l, Integer num5, String str3) {
        Intrinsics.checkNotNullParameter(text_prompts, "text_prompts");
        this.text_prompts = text_prompts;
        this.width = num;
        this.height = num2;
        this.cfg_scale = num3;
        this.clip_guidance_preset = str;
        this.sampler = str2;
        this.samples = num4;
        this.seed = l;
        this.steps = num5;
        this.style_preset = str3;
    }

    public /* synthetic */ TextToImgRequest(List list, Integer num, Integer num2, Integer num3, String str, String str2, Integer num4, Long l, Integer num5, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : str, (i & 32) != 0 ? null : str2, (i & 64) != 0 ? null : num4, (i & 128) != 0 ? 0L : l, (i & 256) != 0 ? null : num5, (i & 512) == 0 ? str3 : null);
    }

    public final List<TextPrompts> component1() {
        return this.text_prompts;
    }

    public final String component10() {
        return this.style_preset;
    }

    public final Integer component2() {
        return this.width;
    }

    public final Integer component3() {
        return this.height;
    }

    public final Integer component4() {
        return this.cfg_scale;
    }

    public final String component5() {
        return this.clip_guidance_preset;
    }

    public final String component6() {
        return this.sampler;
    }

    public final Integer component7() {
        return this.samples;
    }

    public final Long component8() {
        return this.seed;
    }

    public final Integer component9() {
        return this.steps;
    }

    public final TextToImgRequest copy(List<TextPrompts> text_prompts, Integer num, Integer num2, Integer num3, String str, String str2, Integer num4, Long l, Integer num5, String str3) {
        Intrinsics.checkNotNullParameter(text_prompts, "text_prompts");
        return new TextToImgRequest(text_prompts, num, num2, num3, str, str2, num4, l, num5, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TextToImgRequest) {
            TextToImgRequest textToImgRequest = (TextToImgRequest) obj;
            return Intrinsics.areEqual(this.text_prompts, textToImgRequest.text_prompts) && Intrinsics.areEqual(this.width, textToImgRequest.width) && Intrinsics.areEqual(this.height, textToImgRequest.height) && Intrinsics.areEqual(this.cfg_scale, textToImgRequest.cfg_scale) && Intrinsics.areEqual(this.clip_guidance_preset, textToImgRequest.clip_guidance_preset) && Intrinsics.areEqual(this.sampler, textToImgRequest.sampler) && Intrinsics.areEqual(this.samples, textToImgRequest.samples) && Intrinsics.areEqual(this.seed, textToImgRequest.seed) && Intrinsics.areEqual(this.steps, textToImgRequest.steps) && Intrinsics.areEqual(this.style_preset, textToImgRequest.style_preset);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.text_prompts.hashCode() * 31;
        Integer num = this.width;
        int hashCode2 = (hashCode + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.height;
        int hashCode3 = (hashCode2 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.cfg_scale;
        int hashCode4 = (hashCode3 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str = this.clip_guidance_preset;
        int hashCode5 = (hashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.sampler;
        int hashCode6 = (hashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Integer num4 = this.samples;
        int hashCode7 = (hashCode6 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Long l = this.seed;
        int hashCode8 = (hashCode7 + (l == null ? 0 : l.hashCode())) * 31;
        Integer num5 = this.steps;
        int hashCode9 = (hashCode8 + (num5 == null ? 0 : num5.hashCode())) * 31;
        String str3 = this.style_preset;
        return hashCode9 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        List<TextPrompts> list = this.text_prompts;
        Integer num = this.width;
        Integer num2 = this.height;
        Integer num3 = this.cfg_scale;
        String str = this.clip_guidance_preset;
        String str2 = this.sampler;
        Integer num4 = this.samples;
        Long l = this.seed;
        Integer num5 = this.steps;
        return "TextToImgRequest(text_prompts=" + list + ", width=" + num + ", height=" + num2 + ", cfg_scale=" + num3 + ", clip_guidance_preset=" + str + ", sampler=" + str2 + ", samples=" + num4 + ", seed=" + l + ", steps=" + num5 + ", style_preset=" + this.style_preset + ")";
    }

    public final List<TextPrompts> getText_prompts() {
        return this.text_prompts;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final Integer getCfg_scale() {
        return this.cfg_scale;
    }

    public final String getClip_guidance_preset() {
        return this.clip_guidance_preset;
    }

    public final String getSampler() {
        return this.sampler;
    }

    public final Integer getSamples() {
        return this.samples;
    }

    public final Long getSeed() {
        return this.seed;
    }

    public final Integer getSteps() {
        return this.steps;
    }

    public final String getStyle_preset() {
        return this.style_preset;
    }
}
