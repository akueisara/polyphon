#include <jni.h>
#include <string>
#include <oboe/Oboe.h>
#include "AudioEngine.h"

static AudioEngine audioEngine;

extern "C" {
   JNIEXPORT void JNICALL
   Java_io_github_akueisara_audiokit_AudioEngine_startEngine(
            JNIEnv *env,
            jobject /*unused*/) {
        audioEngine.start();
    }

    JNIEXPORT void JNICALL
    Java_io_github_akueisara_audiokit_AudioEngine_stopEngine(
            JNIEnv *env,
            jobject /*unused*/) {
        audioEngine.stop();
    }

    JNIEXPORT void JNICALL
    Java_io_github_akueisara_audiokit_Oscillator_setToneOn(
            JNIEnv *env,
            jobject /*unused*/,
            jboolean is_on) {
        audioEngine.setToneOn(is_on);
    }
}