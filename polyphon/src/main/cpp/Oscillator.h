#ifndef OSCILLATOR_H
#define OSCILLATOR_H

#include <cstdint>
#include <atomic>
#include <math.h>
#include "IRenderableAudio.h"

constexpr double kDefaultFrequency = 440.0;
constexpr int32_t kDefaultSampleRate = 48000;
constexpr double kPi = M_PI;
constexpr double kTwoPi = kPi * 2;

class Oscillator : public IRenderableAudio {

public:

    ~Oscillator() = default;

    void setWaveOn(bool isWaveOn);
    void setSampleRate(int32_t sampleRate);
    void setFrequency(double frequency);
    void setAmplitude(float amplitude);

    enum SignalType {
        Sine = 0,
        Square = 1,
        Triangle = 2,
        Sawtooth = 3
    };

    void setSignalType(int signalType);

    // From IRenderableAudio
    void renderAudio(float *audioData, int32_t numFrames) override;

private:
    std::atomic<bool> mIsWaveOn{false};
    float mPhase = 0.0;
    std::atomic<float> mAmplitude{0.1};
    SignalType mSignalType = SignalType::Sine;
    std::atomic<double> mPhaseIncrement{0.0};
    double mFrequency = kDefaultFrequency;
    int32_t mSampleRate = kDefaultSampleRate;

    void updatePhaseIncrement() {
        mPhaseIncrement.store((kTwoPi * mFrequency) / static_cast<double>(mSampleRate));
    };
};

#endif //OSCILLATOR_H