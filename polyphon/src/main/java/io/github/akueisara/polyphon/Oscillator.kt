package io.github.akueisara.polyphon

class Oscillator: RenderableAudio {
    init {
        System.loadLibrary("polyphon")
    }

    external override fun setToneOn(isOn: Boolean)
    external override fun setFrequency(frequency: Double)
    external override fun setAmplitude(amplitude: Float)
}