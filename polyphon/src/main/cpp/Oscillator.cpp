#include "Oscillator.h"

void Oscillator::setWaveOn(bool isWaveOn) {
    mIsWaveOn.store(isWaveOn);
}

void Oscillator::setSampleRate(int32_t sampleRate) {
    mSampleRate = sampleRate;
    updatePhaseIncrement();
}

void Oscillator::setFrequency(double frequency) {
    mFrequency = frequency;
    updatePhaseIncrement();
}

void Oscillator::setAmplitude(float amplitude) {
    mAmplitude = amplitude;
}

void Oscillator::setSignalType(int signalType) {
    mSignalType = (SignalType) signalType;
}

void Oscillator::renderAudio(float *audioData, int32_t numFrames) {
    if (mIsWaveOn) {
        for (int i = 0; i < numFrames; ++i) {
            // TODO: Validate the audio data for different types of signals
            switch (mSignalType) {
                case SignalType::Sine: {
                    audioData[i] = sinf(mPhase) * mAmplitude;
                    break;
                }
                case SignalType::Square: {
                    if (mPhase <= kPi) {
                        audioData[i] = -mAmplitude;
                    } else {
                        audioData[i] = mAmplitude;
                    }
                    break;
                }
                case SignalType::Triangle: {
                    float triangle = 2.0f * ((mPhase < 0.0f) ? (0.5f + mPhase) : (0.5f - mPhase));
                    audioData[i] = triangle * mAmplitude;
                    break;
                }
                case SignalType::Sawtooth: {
                    audioData[i] = mPhase * mAmplitude;
                    break;
                }
                default:
                    break;
            }
            mPhase += mPhaseIncrement;
            if (mPhase > kTwoPi) mPhase -= kTwoPi;
        }
    } else {
        memset(audioData, 0, sizeof(float) * numFrames);
    }
}