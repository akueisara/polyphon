package io.github.akueisara.polyphon

class Oscillator : RenderableAudio {
    init {
        System.loadLibrary("polyphon")
    }

    enum class SignalType(val value: Int) {
        SINE(0),
        SQUARE(1),
        TRIANGLE(2),
        SAWTOOTH(3)
    }

    override fun setSignalType(signalType: SignalType) {
        setSignalType(signalType.value)
    }

    external override fun setToneOn(isOn: Boolean)
    external override fun setFrequency(frequency: Double)
    external override fun setAmplitude(amplitude: Float)
    private external fun setSignalType(signalType: Int)
}