#include <jni.h>
#include <string>
#include <oboe/Oboe.h>
#include "AudioEngine.h"

static AudioEngine audioEngine;

extern "C" {

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_AudioEngine_startEngine(
        JNIEnv *env,
        jobject /*unused*/) {
    audioEngine.start();
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_AudioEngine_stopEngine(
        JNIEnv *env,
        jobject /*unused*/) {
    audioEngine.stop();
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_Oscillator_setToneOn(
        JNIEnv *env,
        jobject /*unused*/,
        jboolean is_on) {
    audioEngine.setToneOn(is_on);
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_Oscillator_setSampleRate(
        JNIEnv *env,
        jobject thiz,
        jint sample_rate) {
    audioEngine.oscillator.setSampleRate(sample_rate);
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_Oscillator_setFrequency(
        JNIEnv *env,
        jobject thiz,
        jdouble frequency,
        jfloat duration) {
    audioEngine.oscillator.setFrequency(frequency, duration);
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_Oscillator_setAmplitude(
        JNIEnv *env,
        jobject thiz,
        jfloat amplitude,
        jfloat duration) {
    audioEngine.oscillator.setAmplitude(amplitude, duration);
}

JNIEXPORT void JNICALL
Java_io_github_akueisara_polyphon_Oscillator_setSignalType(
        JNIEnv *env,
        jobject thiz,
        jint signal_type) {
    audioEngine.oscillator.setSignalType(signal_type);
}

}