#ifndef AUDIOKIT_AUDIOENGINE_H
#define AUDIOKIT_AUDIOENGINE_H

#include <oboe/Oboe.h>
#include <logging.h>
#include "Oscillator.h"


class AudioEngine: public oboe::AudioStreamCallback {
public:
    void start();
    void setToneOn(bool isOn);

    oboe::DataCallbackResult
    onAudioReady(oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames);

private:
    Oscillator oscillator;
};

#endif //AUDIOKIT_AUDIOENGINE_H