package com.example.speakanddraw.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

import speak.draw.ai.art.photo.image.generator.R;

import com.example.speakanddraw.Ads.AdsExtensionKt;
import com.example.speakanddraw.model.SelectModel;
import com.example.speakanddraw.utlis.ExtensionKt;

import java.util.List;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SelectModelAdapter.kt */

/* loaded from: classes.dex */
public final class SelectModelAdapter extends RecyclerView.Adapter<SelectModelAdapter.ViewHolder> {
    private final Context context;
    private final List<SelectModel> num;
    public SharedPreferences sharedPrefs;
    private Function2<? super SelectModel, ? super Integer, Unit> onCopyItemCLick;
    private int selectedPosition;

    public SelectModelAdapter(Context context, List<SelectModel> num) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(num, "num");
        this.context = context;
        this.num = num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(SelectModelAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ExtensionKt.showToast(this$0.context, "Recommended");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(SelectModelAdapter this$0, SelectModel current, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        Function2<? super SelectModel, ? super Integer, Unit> function2 = this$0.onCopyItemCLick;
        if (function2 != null) {
            function2.invoke(current, Integer.valueOf(i));
        }
        this$0.selectedPosition = i;
        this$0.notifyDataSetChanged();
        SharedPreferences.Editor edit = this$0.getSharedPrefs().edit();
        edit.putInt("position", this$0.selectedPosition);
        edit.apply();
    }

    public final Function2<SelectModel, Integer, Unit> getOnCopyItemCLick() {
        return (Function2<SelectModel, Integer, Unit>) this.onCopyItemCLick;
    }

    public final void setOnCopyItemCLick(Function2<? super SelectModel, ? super Integer, Unit> function2) {
        this.onCopyItemCLick = function2;
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.model_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.num.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final SelectModel selectModel = this.num.get(i);
        holder.getNumbers().setText(selectModel.getName());
        bindAnimation(holder, selectModel.getAnimation());
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("speak_and_Draw", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "holder.itemView.context.\u2026ODE_PRIVATE\n            )");
        setSharedPrefs(sharedPreferences);
        int i2 = getSharedPrefs().getInt("position", this.selectedPosition);
        this.selectedPosition = i2;
        if (i2 == i) {
            AdsExtensionKt.visible(holder.getSelectedStyle());
            holder.getNumbers().setTextColor(ContextCompat.getColor(this.context, R.color.purple));
        } else {
            AdsExtensionKt.Gone(holder.getPermium());
            AdsExtensionKt.Gone(holder.getSelectedStyle());
            holder.getNumbers().setTextColor(ContextCompat.getColor(this.context, R.color.black));
        }
        if (holder.getAdapterPosition() == 1) {
            AdsExtensionKt.visible(holder.getRecommend());
        } else {
            AdsExtensionKt.Gone(holder.getRecommend());
        }
        holder.getRecommend().setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.SelectModelAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectModelAdapter.onBindViewHolder$lambda$0(SelectModelAdapter.this, view);
            }
        });
        holder.getAnimation().setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.SelectModelAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SelectModelAdapter.onBindViewHolder$lambda$2(SelectModelAdapter.this, selectModel, i, view);
            }
        });
    }

    private final void bindAnimation(ViewHolder viewHolder, int i) {
        viewHolder.getAnimation().setAnimation(i);
        viewHolder.getAnimation().setRepeatCount(-1);
        viewHolder.getAnimation().playAnimation();
        viewHolder.getAnimation().setSpeed(0.8f);
    }

    /* compiled from: SelectModelAdapter.kt */
    
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final LottieAnimationView animation;
        private final TextView numbers;
        private final ImageView permium;
        private final ImageView recommend;
        private final ImageView selectedStyle;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.num);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.num)");
            this.numbers = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.animation);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.animation)");
            this.animation = (LottieAnimationView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.permium);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.permium)");
            this.permium = (ImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.recommend);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.recommend)");
            this.recommend = (ImageView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.selectStyle);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.selectStyle)");
            this.selectedStyle = (ImageView) findViewById5;
        }

        public final TextView getNumbers() {
            return this.numbers;
        }

        public final LottieAnimationView getAnimation() {
            return this.animation;
        }

        public final ImageView getPermium() {
            return this.permium;
        }

        public final ImageView getRecommend() {
            return this.recommend;
        }

        public final ImageView getSelectedStyle() {
            return this.selectedStyle;
        }
    }
}
