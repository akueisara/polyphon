package tech.moonboots.audiokit

object AudioEngine {

    var output: RenderableAudio? = null

    init {
        System.loadLibrary("audiokit")
    }

    fun start() {
        assert(output != null) { "output must be set before calling start()" }
        startEngine()
    }

    fun stop() {
        stopEngine()
    }

    private external fun startEngine()
    private external fun stopEngine()
}