package com.example.speakanddraw.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.example.speakanddraw.model.Anime;

import java.util.List;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.R;

/* compiled from: AnimeAdapter.kt */

/* loaded from: classes.dex */
public final class AnimeAdapter extends RecyclerView.Adapter<AnimeAdapter.ViewHolder> {
    private final Context context;
    private final List<Anime> num;
    public SharedPreferences sharedPrefs;
    private Function2<? super Anime, ? super Integer, Unit> onCopyItemCLick;
    private int selectedPosition;

    public AnimeAdapter(Context context, List<Anime> num) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(num, "num");
        this.context = context;
        this.num = num;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$1(AnimeAdapter this$0, int i, Anime current, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        this$0.selectedPosition = i;
        this$0.notifyDataSetChanged();
        Function2<? super Anime, ? super Integer, Unit> function2 = this$0.onCopyItemCLick;
        if (function2 != null) {
            function2.invoke(current, Integer.valueOf(i));
        }
        SharedPreferences.Editor edit = this$0.getSharedPrefs().edit();
        edit.putInt("position", this$0.selectedPosition);
        edit.apply();
    }

    public final Function2<Anime, Integer, Unit> getOnCopyItemCLick() {
        return (Function2<Anime, Integer, Unit>) this.onCopyItemCLick;
    }

    public final void setOnCopyItemCLick(Function2<? super Anime, ? super Integer, Unit> function2) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.num_item, parent, false);
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
        final Anime anime = this.num.get(i);
        holder.getNumbers().setText(anime.getNumber());
        holder.getImage().setImageResource(anime.getImage());
        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("speak_and_Draw", 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "holder.itemView.context.\u2026ODE_PRIVATE\n            )");
        setSharedPrefs(sharedPreferences);
        int i2 = getSharedPrefs().getInt("position", this.selectedPosition);
        this.selectedPosition = i2;
        if (i2 == i) {
//            AdsExtensionKt.visible(holder.getSelectedStyle());
        } else {
//            AdsExtensionKt.Gone(holder.getSelectedStyle());
        }
        holder.getImage().setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.AnimeAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AnimeAdapter.onBindViewHolder$lambda$1(AnimeAdapter.this, i, anime, view);
            }
        });
    }

    /* compiled from: AnimeAdapter.kt */
    
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView numbers;
        private final ImageView permium;
        private final ImageView selectedStyle;

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
            View findViewById3 = itemView.findViewById(R.id.selectedStyle);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.selectedStyle)");
            this.selectedStyle = (ImageView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.permium);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.permium)");
            this.permium = (ImageView) findViewById4;
        }

        public final TextView getNumbers() {
            return this.numbers;
        }

        public final ImageView getImage() {
            return this.image;
        }

        public final ImageView getSelectedStyle() {
            return this.selectedStyle;
        }

        public final ImageView getPermium() {
            return this.permium;
        }
    }
}
