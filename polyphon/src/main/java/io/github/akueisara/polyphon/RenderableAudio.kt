package io.github.akueisara.polyphon

interface RenderableAudio {
    fun setToneOn(isOn: Boolean)
    fun setFrequency(frequency: Double)
    fun setAmplitude(amplitude: Float)
}