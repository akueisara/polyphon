#include <atomic>
#include <stdint.h>
#include "IRenderableAudio.h"

constexpr float kDefaultAmplitude = 0.1;
constexpr double kDefaultFrequency = 440.0;
constexpr int32_t kDefaultSampleRate = 48000;
constexpr double kPi = M_PI;
constexpr double kTwoPi = kPi * 2;

class Oscillator: public IRenderableAudio {
public:
    void setWaveOn(bool isWaveOn);
    void setSampleRate(int32_t sampleRate);
    void setFrequency(double frequency);
    void setAmplitude(float amplitude);
    void renderAudio(float *audioData, int32_t numFrames) override;

private:
    std::atomic<bool> isWaveOn_{false};
    double phase_ = 0.0;
    float amplitude_ = kDefaultAmplitude;
    double phaseIncrement_ = 0.0;
    double frequency_ = kDefaultFrequency;
    int32_t sampleRate_ = kDefaultSampleRate;

    void updatePhaseIncrement();
};