#include "Oscillator.h"

void Oscillator::setWaveOn(bool isWaveOn) {
    mIsWaveOn.store(isWaveOn);
}

void Oscillator::setSampleRate(int32_t sampleRate) {
    mSampleRate = sampleRate;
    mPhaseIncrement = 2.0f / sampleRate; // -1 to +1 is a range of 2
}

void Oscillator::setFrequency(double frequency, float rampDuration) {
    mTargetFrequency = frequency;
    mRampCount = rampDuration * mSampleRate;
}

void Oscillator::setAmplitude(float amplitude, float rampDuration) {
    mTargetAmplitude = amplitude;
    mRampCount = rampDuration * mSampleRate;
}

void Oscillator::setSignalType(int signalType) {
    mSignalType = (SignalType) signalType;
}

void Oscillator::renderAudio(float *audioData, int32_t numFrames) {
    if (mIsWaveOn) {
        for (int i = 0; i < numFrames; ++i) {
            if(mRampCount > 0) {
                mCurrentAmplitude = mCurrentAmplitude * (1.0f - 1.0f / mRampCount) + mTargetAmplitude * (1.0f / mRampCount);
                mCurrentFrequency = mCurrentFrequency * (1.0f - 1.0f / mRampCount) + mTargetFrequency * (1.0f / mRampCount);
                mRampCount--;
            }
            updatePhaseIncrement();
            switch (mSignalType) {
                case SignalType::Sine: {
                    audioData[i] = sinf(mPhase * kPi) * mCurrentAmplitude;
                    break;
                }
                case SignalType::Square: {
                    if (mPhase <= 0.0f) {
                        audioData[i] = -mCurrentAmplitude;
                    } else {
                        audioData[i] = mCurrentAmplitude;
                    }
                    break;
                }
                case SignalType::Triangle: {
                    float triangle = 2.0f * ((mPhase < 0.0f) ? (0.5f + mPhase) : (0.5f - mPhase));
                    audioData[i] = triangle * mCurrentAmplitude;
                    break;
                }
                case SignalType::Sawtooth: {
                    audioData[i] = mPhase * mCurrentAmplitude;
                    break;
                }
                default:
                    break;
            }
        }
    } else {
        memset(audioData, 0, sizeof(float) * numFrames);
    }
}