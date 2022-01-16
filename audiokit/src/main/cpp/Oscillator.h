#include <atomic>
#include <stdint.h>
#include "IRenderableAudio.h"

class Oscillator: public IRenderableAudio {
public:
    void setWaveOn(bool isWaveOn);
    void setSampleRate(int32_t sampleRate);
    void renderAudio(float *audioData, int32_t numFrames) override;

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_ = 0.0;
    double phaseIncrement_ = 0.0;
};