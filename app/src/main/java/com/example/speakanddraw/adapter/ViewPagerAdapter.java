package com.example.speakanddraw.adapter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ViewPagerAdaptor.kt */

/* loaded from: classes.dex */
public final class ViewPagerAdapter extends FragmentStateAdapter {
    private final List<Fragment> fragments;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewPagerAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.fragments = new ArrayList();
    }

    public final void addFragment(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragments.add(fragment);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.fragments.size();
    }

    @Override // androidx.viewpager2.adapter.FragmentStateAdapter
    public Fragment createFragment(int i) {
        return this.fragments.get(i);
    }
}
