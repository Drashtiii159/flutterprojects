package com.example.speakanddraw.utlis;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TinyDB.kt */

/* loaded from: classes.dex */
public final class TinyDB {
    public static final Companion Companion = new Companion(null);
    private final SharedPreferences preferences;
    private String defaultAppImageDataDir;
    private String savedImagePath;

    public TinyDB(Context context) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Intrinsics.checkNotNullExpressionValue(defaultSharedPreferences, "getDefaultSharedPreferences(\n        appContext)");
        this.preferences = defaultSharedPreferences;
        this.savedImagePath = "";
    }

    public static /* synthetic */ int getInt$default(TinyDB tinyDB, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        return tinyDB.getInt(str, i);
    }

    public static /* synthetic */ String getString$default(TinyDB tinyDB, String str, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        return tinyDB.getString(str, str2);
    }

    public final String getSavedImagePath() {
        return this.savedImagePath;
    }

    public final Bitmap getImage(String str) {
        try {
            return BitmapFactory.decodeFile(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final String putImage(String str, String str2, Bitmap bitmap) {
        if (str == null || str2 == null || bitmap == null) {
            return null;
        }
        this.defaultAppImageDataDir = str;
        String str3 = setupFullPath(str2);
        if (!Intrinsics.areEqual(str3, "")) {
            this.savedImagePath = str3;
            saveBitmap(str3, bitmap);
        }
        return str3;
    }

    public final boolean putImageWithFullPath(String str, Bitmap bitmap) {
        return (str == null || bitmap == null || !saveBitmap(str, bitmap)) ? false : true;
    }

    private final String setupFullPath(String str) {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        String str2 = this.defaultAppImageDataDir;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultAppImageDataDir");
            str2 = null;
        }
        File file = new File(externalStorageDirectory, str2);
        Companion companion = Companion;
        if (companion.isExternalStorageReadable() && companion.isExternalStorageWritable() && !file.exists() && !file.mkdirs()) {
            Log.e("ERROR", "Failed to setup folder");
            return "";
        }
        return file.getPath() + "/" + str;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0061 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean saveBitmap(String str, Bitmap bitmap) {
        boolean z;
        boolean z2 = false;
        boolean z3 = false;
        if (str == null || bitmap == null) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.delete()) {
            try {
                z = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                z = false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        z3 = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream2);
                        try {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                            z2 = true;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            z2 = false;
                        }
                    } catch (Exception e3) {
          
                        fileOutputStream = fileOutputStream2;
     
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                                z2 = true;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                z2 = false;
                                z3 = false;
                                return !z && z3 && z2;
                            }
                            z3 = false;
                            if (z) {
                            }
                        }
                        z2 = false;
                        z3 = false;
                        if (z) {
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.flush();
                                fileOutputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
      
                }
            } catch (Exception e6) {
    
            }
            return !z && z3 && z2;
        }
        return false;
    }

    public final int getInt(String str, int i) {
        return this.preferences.getInt(str, i);
    }

    public final ArrayList<Integer> getListInt(String str) {
        String[] myList = TextUtils.split(this.preferences.getString(str, ""), "\u201a\u2017\u201a");
        Intrinsics.checkNotNullExpressionValue(myList, "myList");
        ArrayList arrayList = new ArrayList(CollectionsKt.listOf(Arrays.copyOf(myList, myList.length)));
        ArrayList<Integer> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String item = (String) it.next();
            Intrinsics.checkNotNullExpressionValue(item, "item");
            arrayList2.add(Integer.valueOf(Integer.parseInt(item)));
        }
        return arrayList2;
    }

    public final long getLong(String str, long j) {
        return this.preferences.getLong(str, j);
    }

    public final float getFloat(String str) {
        return this.preferences.getFloat(str, 0.0f);
    }

    public final double getDouble(String str, double d) {
        String string$default = getString$default(this, str, null, 2, null);
        try {
            Intrinsics.checkNotNull(string$default);
            return Double.parseDouble(string$default);
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    public final ArrayList<Double> getListDouble(String str) {
        String[] myList = TextUtils.split(this.preferences.getString(str, ""), "\u201a\u2017\u201a");
        Intrinsics.checkNotNullExpressionValue(myList, "myList");
        ArrayList arrayList = new ArrayList(CollectionsKt.listOf(Arrays.copyOf(myList, myList.length)));
        ArrayList<Double> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String item = (String) it.next();
            Intrinsics.checkNotNullExpressionValue(item, "item");
            arrayList2.add(Double.valueOf(Double.parseDouble(item)));
        }
        return arrayList2;
    }

    public final ArrayList<Long> getListLong(String str) {
        String[] myList = TextUtils.split(this.preferences.getString(str, ""), "\u201a\u2017\u201a");
        Intrinsics.checkNotNullExpressionValue(myList, "myList");
        ArrayList arrayList = new ArrayList(CollectionsKt.listOf(Arrays.copyOf(myList, myList.length)));
        ArrayList<Long> arrayList2 = new ArrayList<>();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String item = (String) it.next();
            Intrinsics.checkNotNullExpressionValue(item, "item");
            arrayList2.add(Long.valueOf(Long.parseLong(item)));
        }
        return arrayList2;
    }

    public final String getString(String str, String str2) {
        return this.preferences.getString(str, str2);
    }

    public final ArrayList<String> getListString(String str) {
        String[] split = TextUtils.split(this.preferences.getString(str, ""), "\u201a\u2017\u201a");
        Intrinsics.checkNotNullExpressionValue(split, "split(preferences.getString(key, \"\"), \"\u201a\u2017\u201a\")");
        return new ArrayList<>(CollectionsKt.listOf(Arrays.copyOf(split, split.length)));
    }

    public final boolean getBoolean(String str) {
        return this.preferences.getBoolean(str, false);
    }

    public final ArrayList<Boolean> getListBoolean(String str) {
        ArrayList<String> listString = getListString(str);
        ArrayList<Boolean> arrayList = new ArrayList<>();
        Iterator<String> it = listString.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.hashCode() == 3569038 && next.equals("true")) {
                arrayList.add(true);
            } else {
                arrayList.add(false);
            }
        }
        return arrayList;
    }

    public final void putInt(String str, int i) {
        checkForNullKey(str);
        this.preferences.edit().putInt(str, i).apply();
    }

    public final void putListInt(String str, ArrayList<Integer> intList) {
        Intrinsics.checkNotNullParameter(intList, "intList");
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("\u201a\u2017\u201a", (Integer[]) intList.toArray(new Integer[0]))).apply();
    }

    public final void putLong(String str, long j) {
        checkForNullKey(str);
        this.preferences.edit().putLong(str, j).apply();
    }

    public final void putListLong(String str, ArrayList<Long> longList) {
        Intrinsics.checkNotNullParameter(longList, "longList");
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("\u201a\u2017\u201a", (Long[]) longList.toArray(new Long[0]))).apply();
    }

    public final void putFloat(String str, float f) {
        checkForNullKey(str);
        this.preferences.edit().putFloat(str, f).apply();
    }

    public final void putDouble(String str, double d) {
        checkForNullKey(str);
        putString(str, String.valueOf(d));
    }

    public final void putListDouble(String str, ArrayList<Double> doubleList) {
        Intrinsics.checkNotNullParameter(doubleList, "doubleList");
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("\u201a\u2017\u201a", (Double[]) doubleList.toArray(new Double[0]))).apply();
    }

    public final void putString(String str, String str2) {
        checkForNullKey(str);
        checkForNullValue(str2);
        this.preferences.edit().putString(str, str2).apply();
    }

    private final void putListString(String str, ArrayList<String> arrayList) {
        checkForNullKey(str);
        this.preferences.edit().putString(str, TextUtils.join("\u201a\u2017\u201a", (String[]) arrayList.toArray(new String[0]))).apply();
    }

    public final void putBoolean(String str, boolean z) {
        checkForNullKey(str);
        this.preferences.edit().putBoolean(str, z).apply();
    }

    public final void putListBoolean(String str, ArrayList<Boolean> boolList) {
        Intrinsics.checkNotNullParameter(boolList, "boolList");
        checkForNullKey(str);
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<Boolean> it = boolList.iterator();
        while (it.hasNext()) {
            Boolean item = it.next();
            Intrinsics.checkNotNullExpressionValue(item, "item");
            if (item.booleanValue()) {
                arrayList.add("true");
            } else {
                arrayList.add("false");
            }
        }
        putListString(str, arrayList);
    }

    public final void remove(String str) {
        this.preferences.edit().remove(str).apply();
    }

    public final boolean deleteImage(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        return new File(path).delete();
    }

    public final void clear() {
        this.preferences.edit().clear().apply();
    }

    public final Map<String, ?> getAll() {
        Map<String, ?> all = this.preferences.getAll();
        Intrinsics.checkNotNullExpressionValue(all, "preferences.all");
        return all;
    }

    public final void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public final void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.preferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    private final void checkForNullKey(String str) {
        str.getClass();
    }

    private final void checkForNullValue(String str) {
        str.getClass();
    }

    /* compiled from: TinyDB.kt */
    
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isExternalStorageWritable() {
            return Intrinsics.areEqual("mounted", Environment.getExternalStorageState());
        }

        public final boolean isExternalStorageReadable() {
            String externalStorageState = Environment.getExternalStorageState();
            return Intrinsics.areEqual("mounted", externalStorageState) || Intrinsics.areEqual("mounted_ro", externalStorageState);
        }
    }
}
