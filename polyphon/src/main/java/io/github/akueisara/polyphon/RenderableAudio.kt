package io.github.akueisara.polyphon

interface RenderableAudio {
    fun setToneOn(isOn: Boolean)
    fun setFrequency(frequency: Double, duration: Float = 0.0f)
    fun setAmplitude(amplitude: Float, duration: Float = 0.0f)
    fun setSignalType(signalType: Oscillator.SignalType)
}