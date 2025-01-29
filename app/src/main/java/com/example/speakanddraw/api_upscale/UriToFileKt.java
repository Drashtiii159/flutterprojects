package com.example.speakanddraw.api_upscale;

import android.content.Context;
import android.net.Uri;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

public final class UriToFileKt {
    public static final File uriToFile(Context context, Uri uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(uri, "uri");
        InputStream openInputStream = null;
        try {
            openInputStream = context.getContentResolver().openInputStream(uri);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        File createTempFile = FilesKt.createTempFile("temp", null, context.getCacheDir());
        if (openInputStream != null) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(createTempFile);
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = openInputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        openInputStream.close();
                        return createTempFile;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
