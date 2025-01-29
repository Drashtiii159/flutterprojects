package com.example.speakanddraw.permission;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.Arrays;
import java.util.List;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PermissionUtils.kt */

/* loaded from: classes.dex */
public final class PermissionUtils {
    public static final PermissionUtils INSTANCE = new PermissionUtils();
    private static final String APP_NEED_PERMISSION = "This app needs permission to use this feature. You can grant them in app settings.";
    private static final String NEED_PERMISSION = "Need Permissions";
    private static final String SETTING_TEXT = "Settings";
    private static final String toastMessage1 = "Some error occurred. Please try again!";
    private static Activity context1 = null;

    private PermissionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkPermission$lambda$0(Activity context, DexterError dexterError) {
        Intrinsics.checkNotNullParameter(context, "$context");
        Log.d("TAG", "checkPermission: " + dexterError.name());
        Toast.makeText(context, toastMessage1, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showSettingsDialog$lambda$1(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        INSTANCE.openSettings();
    }

    public final void checkPermission(final Activity context, String[] permissionArray, final OnPermissionListener permissionListener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(permissionArray, "permissionArray");
        Intrinsics.checkNotNullParameter(permissionListener, "permissionListener");
        context1 = context;
        Dexter.withContext(context).withPermissions((String[]) Arrays.copyOf(permissionArray, permissionArray.length)).withListener(new MultiplePermissionsListener() { // from class: com.example.speakanddraw.permission.PermissionUtils$checkPermission$1
            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionsChecked(MultiplePermissionsReport report) {
                Intrinsics.checkNotNullParameter(report, "report");
                if (report.areAllPermissionsGranted()) {
                    permissionListener.onPermissionSuccess();
                }
                if (report.isAnyPermissionPermanentlyDenied()) {
                    PermissionUtils.INSTANCE.showSettingsDialog();
                }
            }

            @Override // com.karumi.dexter.listener.multi.MultiplePermissionsListener
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                if (permissionToken != null) {
                    permissionToken.continuePermissionRequest();
                }
            }
        }).withErrorListener(new PermissionRequestErrorListener() { // from class: com.example.speakanddraw.permission.PermissionUtils$$ExternalSyntheticLambda2
            @Override // com.karumi.dexter.listener.PermissionRequestErrorListener
            public final void onError(DexterError dexterError) {
                PermissionUtils.checkPermission$lambda$0(context, dexterError);
            }
        }).check();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showSettingsDialog() {
        Activity activity = context1;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context1");
            activity = null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(NEED_PERMISSION);
        builder.setMessage(APP_NEED_PERMISSION);
        builder.setPositiveButton(SETTING_TEXT, new DialogInterface.OnClickListener() { // from class: com.example.speakanddraw.permission.PermissionUtils$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                PermissionUtils.showSettingsDialog$lambda$1(dialogInterface, i);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() { // from class: com.example.speakanddraw.permission.PermissionUtils$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.show();
    }

    private final void openSettings() {
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Activity activity = context1;
        Activity activity2 = null;
        if (activity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context1");
            activity = null;
        }
        Uri fromParts = Uri.fromParts("package", activity.getPackageName(), null);
        Intrinsics.checkNotNullExpressionValue(fromParts, "fromParts(\"package\", context1.packageName, null)");
        intent.setData(fromParts);
        Activity activity3 = context1;
        if (activity3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("context1");
        } else {
            activity2 = activity3;
        }
        activity2.startActivity(intent);
    }

    /* compiled from: PermissionUtils.kt */
    
    /* loaded from: classes.dex */
    public interface OnPermissionListener {
        void onPermissionSuccess();
    }
}
