#include "AudioEngine.h"
#include "memory.h"

void AudioEngine::start() {
    oboe::AudioStreamBuilder builder;
    builder.setCallback(this);
    builder.setPerformanceMode(oboe::PerformanceMode::LowLatency);
    builder.setSharingMode(oboe::SharingMode::Exclusive);
    oboe::Result result = builder.openStream(stream);

    if (result != oboe::Result::OK) {
        LOGE("Error opening stream: %s", oboe::convertToText(result));
        // Handle error
    }

    auto setBufferSizeResult = stream->setBufferSizeInFrames(stream->getFramesPerBurst() * 2);
    if (setBufferSizeResult) {
        LOGI("New buffer size is %d frames", setBufferSizeResult.value());
    }

    oscillator.setSampleRate(stream->getSampleRate());

    result = stream->requestStart();
    if (result != oboe::Result::OK) {
        LOGE("Error starting playback stream. Error: %s", oboe::convertToText(result));
        stream->close();
    }
}

oboe::Result AudioEngine::stop() {
    oboe::Result result = oboe::Result::OK;
    if (stream) {
        result = stream->stop();
        stream->close();
        stream.reset();
    }
    return result;
}

void AudioEngine::setToneOn(bool isOn) {
    oscillator.setWaveOn(isOn);
}

oboe::DataCallbackResult AudioEngine::onAudioReady(
        oboe::AudioStream *oboeStream, void *audioData, int32_t numFrames) {
    oscillator.render(static_cast<float *>(audioData), numFrames);
    return oboe::DataCallbackResult::Continue;
}