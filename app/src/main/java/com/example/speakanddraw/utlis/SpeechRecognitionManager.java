package com.example.speakanddraw.utlis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import androidx.constraintlayout.widget.ConstraintLayout;


import java.util.ArrayList;
import java.util.Locale;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import speak.draw.ai.art.photo.image.generator.databinding.RecordDialogBinding;

/* compiled from: SpeechRecognitionManager.kt */

/* loaded from: classes.dex */
public final class SpeechRecognitionManager {
    private final RecordDialogBinding bindingH;
    private final SpeechRecognizer speechRecognizer;
    private final Intent speechRecognizerIntent;

    public SpeechRecognitionManager(Context context, RecordDialogBinding bindingH) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(bindingH, "bindingH");
        this.bindingH = bindingH;
        SpeechRecognizer createSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        Intrinsics.checkNotNullExpressionValue(createSpeechRecognizer, "createSpeechRecognizer(context)");
        this.speechRecognizer = createSpeechRecognizer;
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
        this.speechRecognizerIntent = intent;
        createSpeechRecognizer.setRecognitionListener(new RecognitionListener() { // from class: com.example.speakanddraw.utlis.SpeechRecognitionManager.1
            @Override // android.speech.RecognitionListener
            public void onBufferReceived(byte[] bytes) {
                Intrinsics.checkNotNullParameter(bytes, "bytes");
            }

            @Override // android.speech.RecognitionListener
            public void onError(int i) {
            }

            @Override // android.speech.RecognitionListener
            public void onEvent(int i, Bundle bundle) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
            }

            @Override // android.speech.RecognitionListener
            public void onPartialResults(Bundle bundle) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
            }

            @Override // android.speech.RecognitionListener
            public void onReadyForSpeech(Bundle bundle) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
            }

            @Override // android.speech.RecognitionListener
            public void onRmsChanged(float f) {
            }

            @Override // android.speech.RecognitionListener
            public void onBeginningOfSpeech() {
                SpeechRecognitionManager.this.bindingH.textHold.setText("");
                SpeechRecognitionManager.this.bindingH.textHold.setText("Listening...");
            }

            @Override // android.speech.RecognitionListener
            public void onEndOfSpeech() {
                SpeechRecognitionManager.this.bindingH.textHold.setHint("");
            }

            @Override // android.speech.RecognitionListener
            public void onResults(Bundle bundle) {
                Intrinsics.checkNotNullParameter(bundle, "bundle");
                SpeechRecognitionManager.this.bindingH.tick.setVisibility(0);
                SpeechRecognitionManager.this.bindingH.cross.setVisibility(0);
                ArrayList<String> stringArrayList = bundle.getStringArrayList("results_recognition");
                SpeechRecognitionManager.this.bindingH.textHold.setText(stringArrayList != null ? stringArrayList.get(0) : null);
                Log.d("textHold", "onResults: " + stringArrayList);
            }
        });
    }

    public final void destroy() {
        this.speechRecognizer.destroy();
    }

    public final void startListening() {
        this.speechRecognizer.startListening(this.speechRecognizerIntent);
    }

    public final void stopListening() {
        this.speechRecognizer.stopListening();
    }
}
