package tech.moonboots.audiokit

object AudioKit {

    init {
        System.loadLibrary("audiokit")
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun startEngine()
    external fun setToneOn(isOn: Boolean)
}