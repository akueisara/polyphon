#ifndef POLYPHON_AUDIOENGINE_H
#define POLYPHON_AUDIOENGINE_H

#include <oboe/Oboe.h>
#include <logging.h>
#include "Oscillator.h"


class AudioEngine: public oboe::AudioStreamCallback {
public:
    void start();
    oboe::Result stop();
    void setToneOn(bool isOn);

    oboe::DataCallbackResult
    onAudioReady(oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames);

private:
    std::shared_ptr<oboe::AudioStream> stream;
    Oscillator oscillator;
};

#endif //POLYPHON_AUDIOENGINE_H