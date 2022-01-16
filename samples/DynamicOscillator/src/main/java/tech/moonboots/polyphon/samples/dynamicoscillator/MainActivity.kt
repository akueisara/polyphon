package tech.moonboots.polyphon.samples.dynamicoscillator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.moonboots.audiokit.AudioEngine
import tech.moonboots.audiokit.Oscillator
import tech.moonboots.polyphon.samples.dynamicoscillator.ui.theme.DynamicOscillatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicOscillatorApp()
        }
        AudioEngine.output = Oscillator()
        AudioEngine.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        AudioEngine.stop()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun DynamicOscillatorApp() {
    var isStart by remember { mutableStateOf(false) }

    DynamicOscillatorComposeTheme {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(
                        style = MaterialTheme.typography.h6,
                        text = "Dynamic Oscillator"
                    )
                }
            }
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = if (isStart) "Stop" else "Start",
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable {
                            isStart = !isStart
                            AudioEngine.output?.setToneOn(isStart)
                        },
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
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