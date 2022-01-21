#include "Oscillator.h"
#include <math.h>

void Oscillator::setWaveOn(bool isWaveOn) {
    isWaveOn_.store(isWaveOn);
}

void Oscillator::setSampleRate(int32_t sampleRate) {
    sampleRate_ = sampleRate;
    updatePhaseIncrement();
}

void Oscillator::setFrequency(double frequency) {
    frequency_ = frequency;
    updatePhaseIncrement();
}

void Oscillator::setAmplitude(float amplitude) {
    amplitude_ = amplitude;
}

void Oscillator::renderAudio(float *audioData, int32_t numFrames) {
    if (!isWaveOn_.load()) phase_ = 0;

    for (int i = 0; i < numFrames; i++) {

        if (isWaveOn_.load()) {

            // Calculates the next sample value for the sine wave.
            audioData[i] = (float) (sin(phase_) * amplitude_);

            // Increments the phase, handling wrap around.
            phase_ += phaseIncrement_;
            if (phase_ > kTwoPi) phase_ -= kTwoPi;

        } else {
            // Outputs silence by setting sample value to zero.
            audioData[i] = 0;
        }
    }
}

void Oscillator::updatePhaseIncrement() {
    phaseIncrement_ = (kTwoPi * frequency_) / (double) sampleRate_;
}