package com.example.speakanddraw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.speakanddraw.model.Anime;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.R;

/* compiled from: InspirationAdapter.kt */

/* loaded from: classes.dex */
public final class InspirationAdapter extends RecyclerView.Adapter<InspirationAdapter.ViewHolder> {
    private final Context context;
    private final List<Anime> num;
    private final Function1<Anime, Unit> onItemClick;
    private Function1<? super Anime, Unit> onCopyItemCLick;
    private int selectedPosition;

    /* JADX WARN: Multi-variable type inference failed */
    public InspirationAdapter(Context context, List<Anime> num, Function1<? super Anime, Unit> onItemClick) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(num, "num");
        Intrinsics.checkNotNullParameter(onItemClick, "onItemClick");
        this.context = context;
        this.num = num;
        this.onItemClick = (Function1<Anime, Unit>) onItemClick;
        this.selectedPosition = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onBindViewHolder$lambda$0(InspirationAdapter this$0, Anime current, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(current, "$current");
        this$0.onItemClick.invoke(current);
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<Anime> getNum() {
        return this.num;
    }

    public final Function1<Anime, Unit> getOnItemClick() {
        return this.onItemClick;
    }

    public final Function1<Anime, Unit> getOnCopyItemCLick() {
        return (Function1<Anime, Unit>) this.onCopyItemCLick;
    }

    public final void setOnCopyItemCLick(Function1<? super Anime, Unit> function1) {
        this.onCopyItemCLick = function1;
    }

    public final int getSelectedPosition() {
        return this.selectedPosition;
    }

    public final void setSelectedPosition(int i) {
        this.selectedPosition = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.inspire_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.num.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Anime anime = this.num.get(i);
        holder.getNumbers().setText(anime.getStyleId());
        holder.getImage().setImageResource(anime.getImage());
        holder.getImage().setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.adapter.InspirationAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InspirationAdapter.onBindViewHolder$lambda$0(InspirationAdapter.this, anime, view);
            }
        });
    }

    /* compiled from: InspirationAdapter.kt */
    
    /* loaded from: classes.dex */
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
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
        }

        public final TextView getNumbers() {
            return this.numbers;
        }

        public final ImageView getImage() {
            return this.image;
        }
    }
}
