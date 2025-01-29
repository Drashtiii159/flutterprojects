package com.example.speakanddraw.application;

import android.app.Application;


import com.ai.app.di.InitKoin;

import kotlin.Metadata;

/* compiled from: MyApp.kt */

/* loaded from: classes.dex */
public final class MyApp extends Application {
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        new InitKoin(this).load();
    }
}
