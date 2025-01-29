package com.example.speakanddraw.utlis;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SaveImages_1.kt */

/* loaded from: classes.dex */
public final class SaveImages_1Kt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void refreshGallery1$lambda$3(String str, Uri uri) {
    }

    public static final void saveImages1(View view, Context context, boolean z, int i, int i2) throws IOException {
        File file;
        File file2;
        Intrinsics.checkNotNullParameter(view, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Bitmap captureViewWithIncreasedResolution = captureViewWithIncreasedResolution(view, z, i, i2);
        if (z) {
            String file3 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            file = new File(file3 + File.separator + ExtensionKt.folderName);
        } else {
            file = new File(context.getCacheDir(), ExtensionKt.folderName);
        }
        if (!file.exists()) {
            file.mkdirs();
        }
        if (z) {
            file2 = new File(file, "images" + System.currentTimeMillis() + ".jpg");
        } else {
            file2 = new File(file, ExtensionKt.fileName);
        }
        if (z) {
            try {
                ExtensionKt.showToast(context, "Image saved to gallery");
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        FileOutputStream fileOutputStream = null;

            fileOutputStream = new FileOutputStream(file2);

        if (captureViewWithIncreasedResolution != null) {
            captureViewWithIncreasedResolution.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
        }
                    fileOutputStream.flush();

        try {
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String file4 = file2.toString();
        Intrinsics.checkNotNullExpressionValue(file4, "file.toString()");
        refreshGallery1(context, file4);
    }

    public static final Bitmap captureViewWithIncreasedResolution(View view, boolean z, int i, int i2) {
        Intrinsics.checkNotNullParameter(view, "view");
        Integer valueOf = Integer.valueOf(i2);
        Integer valueOf2 = Integer.valueOf(i);
        Bitmap createBitmap = Bitmap.createBitmap(valueOf.intValue(), valueOf2.intValue(), Bitmap.Config.ARGB_8888);
        if (createBitmap != null) {
            createBitmap.eraseColor(0);
        }
        Canvas canvas = createBitmap != null ? new Canvas(createBitmap) : null;
        if (canvas != null) {
            canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        }
        Float valueOf3 = Float.valueOf(valueOf.intValue() / view.getWidth());
        Float valueOf4 = Float.valueOf(valueOf2.intValue() / view.getHeight());
        if (canvas != null) {
            canvas.scale(valueOf3.floatValue(), valueOf4.floatValue());
        }
        view.draw(canvas);
        return createBitmap;
    }

    public static final void refreshGallery1(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        MediaScannerConnection.scanFile(context, new String[]{filePath}, null, new MediaScannerConnection.OnScanCompletedListener() { // from class: com.example.speakanddraw.utlis.SaveImages_1Kt$$ExternalSyntheticLambda0
            @Override // android.media.MediaScannerConnection.OnScanCompletedListener
            public final void onScanCompleted(String str, Uri uri) {
                SaveImages_1Kt.refreshGallery1$lambda$3(str, uri);
            }
        });
    }
}
