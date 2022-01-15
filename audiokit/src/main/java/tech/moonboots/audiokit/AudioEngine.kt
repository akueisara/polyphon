package tech.moonboots.audiokit

object AudioEngine {

    init {
        System.loadLibrary("audiokit")
    }

    fun start() {
        startEngine()
    }

    fun stop() {
        stopEngine()
    }

    private external fun startEngine()
    private external fun stopEngine()
    external fun setToneOn(isOn: Boolean)
}