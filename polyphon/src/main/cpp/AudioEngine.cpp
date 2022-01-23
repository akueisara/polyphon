#include "AudioEngine.h"

void AudioEngine::start() {
    oboe::AudioStreamBuilder builder;
    oboe::Result result = builder.setDataCallback(this)
            ->setPerformanceMode(oboe::PerformanceMode::LowLatency)
            ->setSharingMode(oboe::SharingMode::Exclusive)
            ->setChannelCount(1)
            ->openStream(mStream);

    if (result != oboe::Result::OK) {
        LOGE("Error opening stream: %s", oboe::convertToText(result));
        // Handle error
    }

    oscillator.setSampleRate(mStream->getSampleRate());

    LOGD("Stream opened: AudioAPI = %d, channelCount = %d, deviceID = %d",
         mStream->getAudioApi(),
         mStream->getChannelCount(),
         mStream->getDeviceId());

    result = mStream->start();
    if (result != oboe::Result::OK) {
        LOGE("Error starting playback stream. Error: %s", oboe::convertToText(result));
        mStream->close();
    }
}

oboe::Result AudioEngine::stop() {
    oboe::Result result = oboe::Result::OK;
    if (mStream) {
        result = mStream->stop();
        mStream->close();
        mStream.reset();
    }
    return result;
}

void AudioEngine::setToneOn(bool isOn) {
    oscillator.setWaveOn(isOn);
}

oboe::DataCallbackResult AudioEngine::onAudioReady(
        oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames) {
    oscillator.renderAudio(static_cast<float *>(audioData), numFrames);
    return oboe::DataCallbackResult::Continue;
}