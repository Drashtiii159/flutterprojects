package com.example.speakanddraw.permission;

import android.os.Build;

import androidx.constraintlayout.widget.ConstraintLayout;

import kotlin.Metadata;

/* compiled from: PermissionUtils.kt */

/* loaded from: classes.dex */
public final class PermissionUtilsKt {
    private static final String[] permissions;

    static {
        String[] strArr;
        if (Build.VERSION.SDK_INT >= 33) {
            strArr = new String[]{"android.permission.READ_MEDIA_IMAGES"};
        } else {
            strArr = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
        }
        permissions = strArr;
    }

    public static final String[] getPermissions() {
        return permissions;
    }
}
