package com.example.speakanddraw.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.speakanddraw.Dialog.PromptDialog;
import com.example.speakanddraw.ImagesLists.ListsKt;
import com.example.speakanddraw.MainActivity;
import com.example.speakanddraw.adapter.InspirationAdapter;
import com.example.speakanddraw.model.Anime;
import com.example.speakanddraw.utlis.ExtensionKt;

import java.util.ArrayList;
import java.util.List;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.FragmentInspireBinding;

/* compiled from: InspireFragment.kt */

/* loaded from: classes.dex */
public final class InspireFragment extends Fragment {
    public FragmentInspireBinding binding;
    public ArrayList<Anime> filter;
    private InspirationAdapter animeAdapter;
    private NavController navController;

    public final FragmentInspireBinding getBinding() {
        FragmentInspireBinding fragmentInspireBinding = this.binding;
        if (fragmentInspireBinding != null) {
            return fragmentInspireBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(FragmentInspireBinding fragmentInspireBinding) {
        Intrinsics.checkNotNullParameter(fragmentInspireBinding, "<set-?>");
        this.binding = fragmentInspireBinding;
    }

    public final ArrayList<Anime> getFilter() {
        ArrayList<Anime> arrayList = this.filter;
        if (arrayList != null) {
            return arrayList;
        }
        Intrinsics.throwUninitializedPropertyAccessException("filter");
        return null;
    }

    public final void setFilter(ArrayList<Anime> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.filter = arrayList;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentInspireBinding inflate = FragmentInspireBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        RecyclerView recyclerView = getBinding().recyclerInspiration;
        Intrinsics.checkNotNullExpressionValue(recyclerView, "binding.recyclerInspiration");
        setUpRcv(ListsKt.addFaces(), recyclerView);
        ConstraintLayout root = getBinding().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        return root;
    }

    private final void setUpRcv(List<Anime> list, RecyclerView recyclerView) {
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        InspirationAdapter inspirationAdapter = new InspirationAdapter(requireContext, list, new Function1<Anime, Unit>() { // from class: com.example.speakanddraw.Fragment.InspireFragment$setUpRcv$adapter$1
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Anime anime) {
                invoke2(anime);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(final Anime current) {
                Intrinsics.checkNotNullParameter(current, "current");
                FragmentActivity requireActivity = InspireFragment.this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
                final InspireFragment inspireFragment = InspireFragment.this;
                new PromptDialog(requireActivity, current, new Function1<String, Unit>() { // from class: com.example.speakanddraw.Fragment.InspireFragment$setUpRcv$adapter$1$promptDialog$1
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */ {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(String str) {
                        invoke2(str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke  reason: avoid collision after fix types in other method */
                    public final void invoke2(String it) {
                        Intrinsics.checkNotNullParameter(it, "it");
                        InspireFragment.this.startActivity(new Intent(InspireFragment.this.requireContext(), MainActivity.class));
                        ExtensionKt.setTrythisValue(it);
                        ExtensionKt.setTrythisBoolean(true);
                        Log.e("showText", "onBindViewHolder: " + current.getNumber());
                    }
                }).show();
            }
        });
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        recyclerView.setAdapter(inspirationAdapter);
    }
}
