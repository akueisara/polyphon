package tech.moonboots.polyphon.samples.dynamicoscillator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import tech.moonboots.audiokit.AudioKit
import tech.moonboots.polyphon.samples.dynamicoscillator.ui.theme.DynamicOscillatorComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicOscillatorApp()
        }
    }
}

@Composable
private fun DynamicOscillatorApp() {
    DynamicOscillatorComposeTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            Text(text = AudioKit.helloFromCplusplusString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DynamicOscillatorApp()
}