package com.example.speakanddraw.object;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CompressFile.kt */

/* loaded from: classes.dex */
public final class CompressFile {
    public static final CompressFile INSTANCE = new CompressFile();

    private CompressFile() {
    }

    public final File getCompressedFile(Context context, Uri uri) {
        int calculateInSampleSize;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        try {
            InputStream openInputStream = context.getContentResolver().openInputStream(uri);
            if (openInputStream != null) {
                InputStream inputStream = openInputStream;
                InputStream inputStream2 = inputStream;
                BitmapFactory.Options options = new BitmapFactory.Options();
                inputStream2.available();
                BitmapFactory.decodeStream(inputStream2, null, options);
                options.inJustDecodeBounds = true;
                int i = options.outWidth;
                int i2 = options.outHeight;
                if (i2 > 2000) {
                    calculateInSampleSize = INSTANCE.calculateInSampleSize(options, 1024, 1024);
                } else {
                    calculateInSampleSize = INSTANCE.calculateInSampleSize(options, i, i2);
                }
                options.inSampleSize = calculateInSampleSize;
                options.inJustDecodeBounds = false;
                InputStream openInputStream2 = context.getContentResolver().openInputStream(uri);
                if (openInputStream2 == null) {
                    CloseableKt.closeFinally(inputStream, null);
                } else {
                    InputStream inputStream3 = openInputStream2;
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(inputStream3, null, options);
                        File saveCompressedImageToFile = decodeStream != null ? INSTANCE.saveCompressedImageToFile(decodeStream, context, "bgRemover") : null;
                        CloseableKt.closeFinally(inputStream3, null);
                        CloseableKt.closeFinally(inputStream, null);
                        return saveCompressedImageToFile;
                    } finally {
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public final int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public final File saveCompressedImageToFile(Bitmap bitmap, Context context, String folderName) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(folderName, "folderName");
        File file = new File(context.getCacheDir(), folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, "temp.jpeg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
            fileOutputStream.close();
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final File getCompressedOriginalFile(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        ContentResolver contentResolver = context.getContentResolver();
        InputStream openInputStream = null;
        try {
            openInputStream = contentResolver.openInputStream(uri);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        if (openInputStream != null) {
            try {
                openInputStream.available();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        BitmapFactory.decodeStream(openInputStream, null, options);
        options.inJustDecodeBounds = true;
        int i = options.outWidth;
        int i2 = options.outHeight;
        if (i2 > 1000 || i > 1000) {
            options.inSampleSize = calculateInOriginalSize(options, 1024, 1024);
        } else {
            options.inSampleSize = calculateInSampleSize(options, i, i2);
        }
        options.inJustDecodeBounds = false;
        if (openInputStream != null) {
            try {
                openInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Bitmap decodeStream = null;
        try {
            decodeStream = BitmapFactory.decodeStream(contentResolver.openInputStream(uri), null, options);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (decodeStream != null) {
            return INSTANCE.saveCompressedOriginalImageToFile(decodeStream, context, "bgRemover1");
        }
        return null;
    }

    public final int calculateInOriginalSize(BitmapFactory.Options options, int i, int i2) {
        Intrinsics.checkNotNullParameter(options, "options");
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 >= i2 && i7 / i5 >= i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public final File saveCompressedOriginalImageToFile(Bitmap bitmap, Context context, String folderName) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(folderName, "folderName");
        File file = new File(context.getCacheDir(), folderName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, "temp.jpeg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
            fileOutputStream.close();
            return file2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
