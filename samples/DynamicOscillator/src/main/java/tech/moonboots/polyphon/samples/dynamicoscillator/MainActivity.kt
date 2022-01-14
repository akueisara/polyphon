package tech.moonboots.polyphon.samples.dynamicoscillator

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import tech.moonboots.audiokit.AudioKit
import tech.moonboots.polyphon.samples.dynamicoscillator.ui.theme.DynamicOscillatorComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AudioKit.startEngine()
        setContent {
            DynamicOscillatorApp()
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DynamicOscillatorApp() {
    DynamicOscillatorComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> AudioKit.setToneOn(true)
                        MotionEvent.ACTION_UP -> AudioKit.setToneOn(false)
                        else -> false
                    }
                    true
                },
            color = MaterialTheme.colors.background
        ) {
            Text(
                text = "Dynamic Oscillator",
                style = MaterialTheme.typography.h4,
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tap to make a sound",
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    DynamicOscillatorApp()
}