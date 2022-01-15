package tech.moonboots.polyphon.samples.dynamicoscillator

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tech.moonboots.audiokit.AudioEngine
import tech.moonboots.polyphon.samples.dynamicoscillator.ui.theme.DynamicOscillatorComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DynamicOscillatorApp()
        }
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
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            isStart = !isStart
                            if(isStart) {
                                AudioEngine.start()
                            } else {
                                AudioEngine.stop()
                            }
                        },
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInteropFilter {
                            when (it.action) {
                                MotionEvent.ACTION_DOWN -> AudioEngine.setToneOn(true)
                                MotionEvent.ACTION_UP -> AudioEngine.setToneOn(false)
                                else -> false
                            }
                            true
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text("Tap to make a sound")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    DynamicOscillatorApp()
}