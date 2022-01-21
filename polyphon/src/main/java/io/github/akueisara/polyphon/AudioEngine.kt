package io.github.akueisara.polyphon

object AudioEngine {

    var output: RenderableAudio? = null

    init {
        System.loadLibrary("polyphon")
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