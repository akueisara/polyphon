package tech.moonboots.audiokit

class Oscillator: RenderableAudio {
    init {
        System.loadLibrary("audiokit")
    }

    external override fun setToneOn(isOn: Boolean)
}