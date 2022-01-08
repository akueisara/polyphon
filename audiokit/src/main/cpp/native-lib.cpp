#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_tech_moonboots_audiokit_AudioKit_helloFromCplusplusString(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}