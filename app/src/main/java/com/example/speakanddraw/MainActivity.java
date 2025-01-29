package com.example.speakanddraw;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.BottomNavigationViewKt;

import com.example.speakanddraw.Ads.AdsExtensionKt;
import com.example.speakanddraw.object.Constants;
import com.example.speakanddraw.utlis.ExtensionKt;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.R;
import speak.draw.ai.art.photo.image.generator.databinding.ActivityMainBinding;

/* compiled from: MainActivity.kt */

/* loaded from: classes.dex */
public final class MainActivity extends AppCompatActivity implements NavController.OnDestinationChangedListener {
    private final Lazy binding$delegate = LazyKt.lazy(new Function0<ActivityMainBinding>() { // from class: com.example.speakanddraw.MainActivity$binding$2
        /* JADX INFO: Access modifiers changed from: package-private */ {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ActivityMainBinding invoke() {
            ActivityMainBinding inflate = ActivityMainBinding.inflate(MainActivity.this.getLayoutInflater());
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
            return inflate;
        }
    });
    private NavController navController;
    private NavHostFragment navHostFragment;

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean onCreate$lambda$2$lambda$1(MainActivity this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(menuItem, "menuItem");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.home) {
            NavController navController = this$0.navController;
            if (navController != null) {
                navController.popBackStack();
            }
            NavController navController2 = this$0.navController;
            if (navController2 != null) {
                navController2.navigate(R.id.homeFragment);
                return true;
            }
            return true;
        } else if (itemId == R.id.inspire) {
            NavController navController3 = this$0.navController;
            if (navController3 != null) {
                navController3.popBackStack();
            }
            NavController navController4 = this$0.navController;
            if (navController4 != null) {
                navController4.navigate(R.id.inspireFragment);
                return true;
            }
            return true;
        } else if (itemId == R.id.upscale) {
            NavController navController5 = this$0.navController;
            if (navController5 != null) {
                navController5.popBackStack();
            }
            NavController navController6 = this$0.navController;
            if (navController6 != null) {
                navController6.navigate(R.id.upScaleFragment);
                return true;
            }
            return true;
        } else {
            return false;
        }
    }

    private final ActivityMainBinding getBinding() {
        return (ActivityMainBinding) this.binding$delegate.getValue();
    }

    public final NavHostFragment getNavHostFragment() {
        return this.navHostFragment;
    }

    public final void setNavHostFragment(NavHostFragment navHostFragment) {
        this.navHostFragment = navHostFragment;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override
    // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        BottomNavigationView bottomNavigationView;
        super.onCreate(bundle);
        setContentView(getBinding().getRoot());
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavHostFragment navHostFragment = (NavHostFragment) findFragmentById;
        this.navHostFragment = navHostFragment;
        NavController navController = navHostFragment != null ? navHostFragment.getNavController() : null;
        this.navController = navController;
        if (navController != null) {
            navController.addOnDestinationChangedListener(this);
        }
        Window window = getWindow();
        if (window != null) {
            ExtensionKt.hideSystemUI(window);
        }
        String stringExtra = getIntent().getStringExtra("fragmentToDisplay");
        Constants.INSTANCE.setFromrResult(String.valueOf(stringExtra));
        if (stringExtra != null) {
            if (Intrinsics.areEqual(stringExtra, "Fragment1")) {
                ExtensionKt.setTrythisBoolean(false);
                NavController navController2 = this.navController;
                if (navController2 != null) {
                    navController2.popBackStack();
                }
                NavController navController3 = this.navController;
                if (navController3 != null) {
                    navController3.navigate(R.id.upScaleFragment);
                }
            }
            BottomNavigationView bottomNavigationView2 = getBinding().bottomNavigationView;
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView2, "binding.bottomNavigationView");
            AdsExtensionKt.Gone(bottomNavigationView2);
        }
        ActivityMainBinding binding = getBinding();
        NavController navController4 = this.navController;
        if (navController4 != null && (bottomNavigationView = getBinding().bottomNavigationView) != null) {
            Intrinsics.checkNotNullExpressionValue(bottomNavigationView, "bottomNavigationView");
            BottomNavigationViewKt.setupWithNavController(bottomNavigationView, navController4);
        }
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() { // from class: com.example.speakanddraw.MainActivity$$ExternalSyntheticLambda0
            @Override
            // com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
            public final boolean onNavigationItemSelected(MenuItem menuItem) {
                boolean onCreate$lambda$2$lambda$1;
                onCreate$lambda$2$lambda$1 = MainActivity.onCreate$lambda$2$lambda$1(MainActivity.this, menuItem);
                return onCreate$lambda$2$lambda$1;
            }
        });
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) { // from class: com.example.speakanddraw.MainActivity$onCreate$2
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // androidx.activity.OnBackPressedCallback
            public void handleOnBackPressed() {
                NavController navController5;
                NavController navController6;
                NavController navController7;
                NavController navController8;
                NavDestination currentDestination;
                NavDestination currentDestination2;
                NavDestination currentDestination3;
                navController5 = MainActivity.this.navController;
                boolean z = true;
                if (!((navController5 == null || (currentDestination3 = navController5.getCurrentDestination()) == null || currentDestination3.getId() != R.id.homeFragment) ? false : true)) {
                    navController6 = MainActivity.this.navController;
                    if (!((navController6 == null || (currentDestination2 = navController6.getCurrentDestination()) == null || currentDestination2.getId() != R.id.inspireFragment) ? false : true)) {
                        if (Intrinsics.areEqual(Constants.INSTANCE.isFromrResult(), "Fragment1")) {
                            navController7 = MainActivity.this.navController;
                            if (navController7 == null || (currentDestination = navController7.getCurrentDestination()) == null || currentDestination.getId() != R.id.upScaleFragment) {
                                z = false;
                            }
                            if (z) {
                                navController8 = MainActivity.this.navController;
                                if (navController8 != null) {
                                    navController8.navigateUp();
                                }
                                Constants.INSTANCE.setBitmapResult(null);
                                return;
                            }
                        }
                        ExtensionKt.showExitDialogFeedBack(MainActivity.this);
                        return;
                    }
                }
                ExtensionKt.showExitDialogFeedBack(MainActivity.this);
            }
        });
    }

    @Override // androidx.navigation.NavController.OnDestinationChangedListener
    public void onDestinationChanged(NavController controller, NavDestination destination, Bundle bundle) {
        Intrinsics.checkNotNullParameter(controller, "controller");
        Intrinsics.checkNotNullParameter(destination, "destination");
        ActivityMainBinding binding = getBinding();
        int id = destination.getId();
        boolean z = true;
        if (!(id == R.id.homeFragment || id == R.id.inspireFragment) && id != R.id.upScaleFragment) {
            z = false;
        }
        if (z) {
            binding.bottomNavigationView.setVisibility(View.VISIBLE);
        }
    }
}
