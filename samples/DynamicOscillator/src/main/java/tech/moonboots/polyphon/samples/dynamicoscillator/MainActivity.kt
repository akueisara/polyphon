package tech.moonboots.polyphon.samples.dynamicoscillator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import tech.moonboots.audiokit.AudioKit
import tech.moonboots.polyphon.samples.dynamicoscillator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        binding.sampleText.text = AudioKit().stringFromJNI()
    }
}