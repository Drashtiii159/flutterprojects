package com.example.speakanddraw.permission;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: checkMicPermissions.kt */

/* loaded from: classes.dex */
public final class CheckMicPermissionsKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void micPermissionsDenied$lambda$1(DialogInterface dialogInterface, int i) {
    }

    public static final void checkMicPermissions(Context context, Function1<? super Boolean, Unit> isGrantedPermission) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(isGrantedPermission, "isGrantedPermission");
        if (ContextCompat.checkSelfPermission(context, "android.permission.RECORD_AUDIO") == 0) {
            isGrantedPermission.invoke(true);
        } else {
            isGrantedPermission.invoke(false);
        }
    }

    public static final void micPermissionsDenied(final Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        new MaterialAlertDialogBuilder(context).setTitle((CharSequence) "Permission Denied").setMessage((CharSequence) "Without this permission the App is unable get voice input from MIC. Are you want deny this permission?").setNegativeButton((CharSequence) "OPEN SETTINGS", new DialogInterface.OnClickListener() { // from class: com.example.speakanddraw.permission.CheckMicPermissionsKt$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CheckMicPermissionsKt.micPermissionsDenied$lambda$0(context, dialogInterface, i);
            }
        }).setPositiveButton((CharSequence) "I'M SURE", new DialogInterface.OnClickListener() { // from class: com.example.speakanddraw.permission.CheckMicPermissionsKt$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                CheckMicPermissionsKt.micPermissionsDenied$lambda$1(dialogInterface, i);
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void micPermissionsDenied$lambda$0(Context this_micPermissionsDenied, DialogInterface dialogInterface, int i) {
        Intrinsics.checkNotNullParameter(this_micPermissionsDenied, "$this_micPermissionsDenied");
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", this_micPermissionsDenied.getPackageName(), null));
        this_micPermissionsDenied.startActivity(intent);
    }
}
