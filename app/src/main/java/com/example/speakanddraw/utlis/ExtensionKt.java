package com.example.speakanddraw.utlis;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.ByteStreamsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.DialogExitBinding;

/* compiled from: Extension.kt */

/* loaded from: classes.dex */
public final class ExtensionKt {
    public static final String fileName = "image";
    public static final String folderName = "Speak And Draw";
    private static long mLastClickTime = 0;
    private static boolean trythisBoolean = false;
    private static boolean trythisOnBack = false;
    private static String trythisValue = "";

    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshGallery$lambda$0(String str, Uri uri) {
    }

    public static final void hideSystemUI(Window window) {
        Intrinsics.checkNotNullParameter(window, "<this>");
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(window, window.getDecorView());
        Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(this, decorView)");
        insetsController.setSystemBarsBehavior(2);
        insetsController.hide(WindowInsetsCompat.Type.navigationBars());
    }

    public static final void showToast(Context context, String msg) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(msg, "msg");
        Toast.makeText(context, msg, 0).show();
    }

    public static final String getTrythisValue() {
        return trythisValue;
    }

    public static final void setTrythisValue(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        trythisValue = str;
    }

    public static final boolean getTrythisBoolean() {
        return trythisBoolean;
    }

    public static final void setTrythisBoolean(boolean z) {
        trythisBoolean = z;
    }

    public static final boolean getTrythisOnBack() {
        return trythisOnBack;
    }

    public static final void setTrythisOnBack(boolean z) {
        trythisOnBack = z;
    }

    public static final void shareImage(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(uri, "uri");
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("image/png");
        intent.putExtra("android.intent.extra.STREAM", uri);
        intent.putExtra("android.intent.extra.TEXT", "i created this amazing photo with simple text using this app Speak and Draw. you can also turn your imagination into reality using:  https://play.google.com/store/apps/details?id=" + context.getApplicationContext().getPackageName());
        context.startActivity(Intent.createChooser(intent, "Share"));
    }

    public static final void saveImages(View view, Context context, boolean z) {
        File file;
        File file2;
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Bitmap captureView = captureView(view);
        if (z) {
            String file3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            file = new File(file3 + File.separator + folderName);
        } else {
            file = new File(String.valueOf(context.getExternalFilesDir(null)), folderName);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (z) {
            file2 = new File(file, "images" + System.currentTimeMillis() + ".jpg");
        } else {
            file2 = new File(file, fileName);
        }
        if (z) {
            try {
                showToast(context, "Image saved to gallery");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (captureView != null) {
            captureView.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        }
        try {
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file4 = file2.toString();
        Intrinsics.checkNotNullExpressionValue(file4, "file.toString()");
        refreshGallery(context, file4);
    }

    public static final Bitmap captureView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        view.draw(canvas);
        return createBitmap;
    }

    public static final void refreshGallery(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        MediaScannerConnection.scanFile(context, new String[]{filePath}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.example.speakanddraw.utlis.ExtensionKt$$ExternalSyntheticLambda0
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String str, Uri uri) {
                ExtensionKt.refreshGallery$lambda$0(str, uri);
            }
        });
    }

    public static final Bitmap takeScreenshotOfView(View view, int i, int i2) {
        Intrinsics.checkNotNullParameter(view, "view");
        Bitmap bitmap = Bitmap.createBitmap(i2, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Drawable background = view.getBackground();
        if (background != null) {
            background.draw(canvas);
        } else {
            canvas.drawColor(-1);
        }
        view.draw(canvas);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    public static final Uri saveBitmapToCacheAndGetUri(Context context, Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        File file = new File(context.getCacheDir(), "generated_bitmap.jpg");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        try {
            fileOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (file.exists()) {
            return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        }
        return null;
    }

    public static final boolean isNetworkConnected(Context context) {
        NetworkCapabilities networkCapabilities;
        Intrinsics.checkNotNullParameter(context, "<this>");
        Object systemService = context.getSystemService("connectivity");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
        ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
        Network activeNetwork = connectivityManager.getActiveNetwork();
        if (activeNetwork == null || (networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)) == null) {
            return false;
        }
        return networkCapabilities.hasTransport(0) || networkCapabilities.hasTransport(1);
    }

    public static final /* synthetic */ <A extends Activity> void startNewActivity(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.reifiedOperationMarker(4, "A");
        context.startActivity(new Intent(context, Activity.class));
    }

    public static /* synthetic */ Uri toUri$default(Bitmap bitmap, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "image.png";
        }
        return toUri(bitmap, context, str);
    }

    public static final Uri toUri(Bitmap bitmap, Context context, String displayName) {
        OutputStream openOutputStream;
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        String str = Environment.DIRECTORY_PICTURES;
        Uri contentUri = MediaStore.Images.Media.getContentUri("external_primary");
        ContentValues contentValues = new ContentValues();
        contentValues.put("_display_name", displayName);
        contentValues.put("mime_type", "image/png");
        contentValues.put("relative_path", str);
        ContentResolver contentResolver = context.getContentResolver();
        Uri insert = contentResolver.insert(contentUri, contentValues);
        try {
            if (insert != null && (openOutputStream = contentResolver.openOutputStream(insert)) != null) {
                OutputStream outputStream = openOutputStream;

                    Boolean.valueOf(bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream));
                    CloseableKt.closeFinally(outputStream, null);

            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return insert;
    }

    public static final long getMLastClickTime() {
        return mLastClickTime;
    }

    public static final void setMLastClickTime(long j) {
        mLastClickTime = j;
    }

    public static final void clickListener(View view, final Function1<? super View, Unit> action) {
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(action, "action");
        view.setOnClickListener(new View.OnClickListener() { // from class: com.example.speakanddraw.utlis.ExtensionKt$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ExtensionKt.clickListener$lambda$4(action, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void clickListener$lambda$4(Function1 action, View it) {
        Intrinsics.checkNotNullParameter(action, "$action");
        if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        action.invoke(it);
    }

    public static final void showExitDialogFeedBack(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        Activity activity2 = activity;
        DialogExitBinding inflate = DialogExitBinding.inflate(LayoutInflater.from(activity2));
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.from(this))");
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(activity2);
        bottomSheetDialog.setContentView(inflate.getRoot());
        TextView yes = inflate.yes;
        Intrinsics.checkNotNullExpressionValue(yes, "yes");
        clickListener(yes, new Function1<View, Unit>() { // from class: com.example.speakanddraw.utlis.ExtensionKt$showExitDialogFeedBack$1$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
                activity.finishAffinity();
                ExtensionKt.setTrythisValue("");
                ExtensionKt.setTrythisOnBack(false);
            }
        });
        TextView no = inflate.no;
        Intrinsics.checkNotNullExpressionValue(no, "no");
        clickListener(no, new Function1<View, Unit>() { // from class: com.example.speakanddraw.utlis.ExtensionKt$showExitDialogFeedBack$1$2
            /* JADX INFO: Access modifiers changed from: package-private */ {
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke  reason: avoid collision after fix types in other method */
            public final void invoke2(View it) {
                Intrinsics.checkNotNullParameter(it, "it");
               bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }

    public static final Uri getUriForFile(Context context, File file) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(file, "file");
        return FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
    }

    public static final Uri getUri(Fragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "<this>");
        String file = fragment.requireContext().getCacheDir().toString();
        Uri parse = Uri.parse(file + File.separator + "cached_image.jpg");
        Intrinsics.checkNotNullExpressionValue(parse, "parse(\n        requireCo\u2026 \"cached_image.jpg\"\n    )");
        return parse;
    }

//    public static final String getRealPathFromUri(Context context, Uri uri) {
//        Context applicationContext;
//        Cursor query;
//        Cursor cursor;
//        Intrinsics.checkNotNullParameter(context, "<this>");
//        Intrinsics.checkNotNullParameter(uri, "uri");
//        ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
//        if (Intrinsics.areEqual("content", uri.getScheme()) && (query = contentResolver.query(uri, new String[]{"_display_name"}, null, null, null)) != null) {
//            Cursor cursor2 = query;
//            try {
//                if (cursor2.moveToFirst()) {
//                    String str = applicationContext.getCacheDir().getAbsolutePath() + File.separator + cursor.getString(cursor.getColumnIndexOrThrow("_display_name"));
//                    if (copyUriToFile(context, uri, str)) {
//                        CloseableKt.closeFinally(cursor2, null);
//                        return str;
//                    }
//                }
//                Unit unit = Unit.INSTANCE;
//                CloseableKt.closeFinally(cursor2, null);
//            } catch (Throwable th) {
//                try {
//                    throw th;
//                } catch (Throwable th2) {
//                    CloseableKt.closeFinally(cursor2, th);
//                    throw th2;
//                }
//            }
//        }
//        String path = uri.getPath();
//        return path == null ? "" : path;
//    }

//    public static final boolean copyUriToFile(Context context, Uri uri, String filePath) {
//        Intrinsics.checkNotNullParameter(context, "<this>");
//        Intrinsics.checkNotNullParameter(uri, "uri");
//        Intrinsics.checkNotNullParameter(filePath, "filePath");
//        try {
//            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
//            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
//            if (openInputStream != null) {
//                InputStream inputStream = openInputStream;
//                FileOutputStream fileOutputStream2 = fileOutputStream;
//                long copyTo$default = ByteStreamsKt.copyTo$default(inputStream, fileOutputStream2, 0, 2, null);
//                CloseableKt.closeFinally(fileOutputStream2, null);
//                Long.valueOf(copyTo$default);
//                CloseableKt.closeFinally(inputStream, null);
//            }
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
