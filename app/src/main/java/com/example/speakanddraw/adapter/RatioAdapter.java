package com.example.speakanddraw.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakanddraw.model.Anime;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.R;

/* compiled from: RatioAdapter.kt */

/* loaded from: classes.dex */
public final class RatioAdapter extends RecyclerView.Adapter<RatioAdapter.ViewHolder> {
    private final Context context;
    private final List<Anime> num;
    public SharedPreferences sharedPrefs;
    private Function1<? super Integer, Unit> onCopyItemCLick;
    private int selectedPosition;

    public RatioAdapter(Context context, List<Anime> num) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(num, "num");
        this.context = context;
        this.num = num;
        this.selectedPosition = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(View view) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$2(RatioAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<? super Integer, Unit> function1 = this$0.onCopyItemCLick;
        if (function1 != null) {
            function1.invoke(Integer.valueOf(i));
        }
        this$0.selectedPosition = i;
        this$0.notifyDataSetChanged();
        SharedPreferences.Editor edit = this$0.getSharedPrefs().edit();
        edit.putInt("position", this$0.selectedPosition);
        edit.apply();
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<Anime> getNum() {
        return this.num;
    }

    public final Function1<Integer, Unit> getOnCopyItemCLick() {
        return (Function1<Integer, Unit>) this.onCopyItemCLick;
    }

    public final void setOnCopyItemCLick(Function1<? super Integer, Unit> function1) {
        this.onCopyItemCLick = function1;
    }

    public final int getSelectedPosition() {
        return this.selectedPosition;
    }

    public final void setSelectedPosition(int i) {
        this.selectedPosition = i;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ratio_item, parent, false);
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
        Anime anime = this.num.get(i);
        holder.getNumbers().setText(anime.getNumber());
        holder.getImage().setImageResource(anime.getImage());
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("speak_and_Draw", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "holder.itemView.context.\u2026ODE_PRIVATE\n            )");
        setSharedPrefs(sharedPreferences);
        this.selectedPosition = getSharedPrefs().getInt("position", this.selectedPosition);
        int color = ContextCompat.getColor(this.context, R.color.white);
        int color2 = ContextCompat.getColor(this.context, R.color.purple);
        if (this.selectedPosition == i) {
            holder.getImageBg().setBackgroundResource(R.drawable.card_selected);
            holder.getNumbers().setTextColor(color);
            ImageViewCompat.setImageTintList(holder.getImage(), ColorStateList.valueOf(color));
        } else {
            holder.getImageBg().setBackgroundResource(R.drawable.card_background11);
            holder.getNumbers().setTextColor(color2);
            ImageViewCompat.setImageTintList(holder.getImage(), ColorStateList.valueOf(color2));
        }
        holder.getImage().setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.RatioAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RatioAdapter.onBindViewHolder$lambda$0(view);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.RatioAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RatioAdapter.onBindViewHolder$lambda$2(RatioAdapter.this, i, view);
            }
        });
    }

    /* compiled from: RatioAdapter.kt */
    
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final ConstraintLayout imageBg;
        private final TextView numbers;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.num);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.num)");
            this.numbers = (TextView) findViewById;
            View findViewById2 = itemView.findViewById(R.id.image);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.image)");
            this.image = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.cvStyle);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.cvStyle)");
            this.imageBg = (ConstraintLayout) findViewById3;
        }

        public final TextView getNumbers() {
            return this.numbers;
        }

        public final ImageView getImage() {
            return this.image;
        }

        public final ConstraintLayout getImageBg() {
            return this.imageBg;
        }
    }
}
