package com.ai.app.di;

import android.app.Application;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.koin.android.ext.koin.KoinExtKt;
import org.koin.core.KoinApplication;
import org.koin.core.context.DefaultContextExtKt;
import org.koin.core.logger.Level;

public final class InitKoin {
    private Application context;

    public InitKoin(Application context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public final Application getContext() {
        return this.context;
    }

    public final void setContext(Application application) {
        Intrinsics.checkNotNullParameter(application, "<set-?>");
        this.context = application;
    }

    public final void load() {
        DefaultContextExtKt.startKoin(new Function1<KoinApplication, Unit>() {
            @Override
            public Unit invoke(KoinApplication koinApplication) {
                Intrinsics.checkNotNullParameter(koinApplication, "$this$startKoin");
                KoinExtKt.androidContext(koinApplication, InitKoin.this.getContext());
                // Pass a proper LogLevel to androidLogger method, e.g., LogLevel.DEBUG
                KoinExtKt.androidLogger(koinApplication, Level.ERROR);
                DefaultContextExtKt.loadKoinModules(CollectionsKt.listOf(ModulesKt.getDiModule()));
                return Unit.INSTANCE;
            }
        });
    }

}
