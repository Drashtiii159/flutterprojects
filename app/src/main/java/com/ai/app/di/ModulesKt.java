package com.ai.app.di;

import android.app.Application;
import android.content.Context;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.speakanddraw.model.RatioViewModel;
import com.example.speakanddraw.utlis.TinyDB;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.Kind;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.instance.SingleInstanceFactory;
import org.koin.core.module.Module;
import org.koin.core.parameter.ParametersHolder;
import org.koin.core.registry.ScopeRegistry;
import org.koin.core.scope.Scope;
import org.koin.dsl.ModuleDSLKt;


public final class ModulesKt {
    private static final Module diModule = ModuleDSLKt.module(false, new Function1<Module, Unit>() { // from class: com.ai.app.di.ModulesKt$diModule$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(Module module) {
            invoke2(module);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(Module module) {
            Intrinsics.checkNotNullParameter(module, "$this$module");
            SingleInstanceFactory<?> singleInstanceFactory = new SingleInstanceFactory<>(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(TinyDB.class), null, new Function2<Scope, ParametersHolder, TinyDB>() { // from class: com.ai.app.di.ModulesKt$diModule$1.1
                @Override // kotlin.jvm.functions.Function2
                public final TinyDB invoke(Scope single, ParametersHolder it) {
                    Intrinsics.checkNotNullParameter(single, "$this$single");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new TinyDB((Context) single.get(Reflection.getOrCreateKotlinClass(Context.class), null, null));
                }
            }, Kind.Singleton, CollectionsKt.emptyList()));
            module.indexPrimaryType(singleInstanceFactory);
            if (module.get_createdAtStart()) {
                module.prepareForCreationAtStart(singleInstanceFactory);
            }
            new Pair(module, singleInstanceFactory);
            FactoryInstanceFactory factoryInstanceFactory = new FactoryInstanceFactory(new BeanDefinition(ScopeRegistry.Companion.getRootScopeQualifier(), Reflection.getOrCreateKotlinClass(RatioViewModel.class), null, new Function2<Scope, ParametersHolder, RatioViewModel>() { // from class: com.ai.app.di.ModulesKt$diModule$1.2
                @Override // kotlin.jvm.functions.Function2
                public final RatioViewModel invoke(Scope viewModel, ParametersHolder it) {
                    Intrinsics.checkNotNullParameter(viewModel, "$this$viewModel");
                    Intrinsics.checkNotNullParameter(it, "it");
                    return new RatioViewModel((Application) viewModel.get(Reflection.getOrCreateKotlinClass(Application.class), null, null));
                }
            }, Kind.Factory, CollectionsKt.emptyList()));
            module.indexPrimaryType(factoryInstanceFactory);
            new Pair(module, factoryInstanceFactory);
        }
    });

    public static final Module getDiModule() {
        return diModule;
    }
}
