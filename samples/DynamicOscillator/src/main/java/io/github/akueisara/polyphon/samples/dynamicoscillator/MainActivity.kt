package io.github.akueisara.polyphon.samples.dynamicoscillator

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
import io.github.akueisara.polyphon.AudioEngine
import io.github.akueisara.polyphon.Oscillator
import io.github.akueisara.polyphon.samples.dynamicoscillator.ui.theme.DynamicOscillatorComposeTheme

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
    var frequency by remember { mutableStateOf(440f) }
    var amplitude by remember { mutableStateOf(0.2f) }

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
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                        .clickable {
                            isStart = !isStart
                            AudioEngine.output?.setToneOn(isStart)
                        },
                    text = if (isStart) "Stop" else "Start",
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .clickable {
                                AudioEngine.output?.setSignalType(Oscillator.SignalType.SINE)
                            }
                            .padding(16.dp),
                        text = "Sine",
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                AudioEngine.output?.setSignalType(Oscillator.SignalType.SQUARE)
                            }
                            .padding(16.dp),
                        text = "Square"
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                AudioEngine.output?.setSignalType(Oscillator.SignalType.TRIANGLE)
                            }
                            .padding(16.dp),
                        text = "Triangle"
                    )
                    Text(
                        modifier = Modifier
                            .clickable {
                                AudioEngine.output?.setSignalType(Oscillator.SignalType.SAWTOOTH)
                            }
                            .padding(16.dp),
                        text = "Sawtooth"
                    )
                }

                ParameterSlider(
                    text = "Frequency",
                    parameter = frequency,
                    valueRange = 220f..880f,
                    onParameterChange = {
                        frequency = it
                        AudioEngine.output?.setFrequency(frequency = it.toDouble())
                    }
                )

                ParameterSlider(
                    text = "Amplitude",
                    parameter = amplitude,
                    valueRange = 0.0f..1.0f,
                    onParameterChange = {
                        amplitude = it
                        AudioEngine.output?.setAmplitude(amplitude = it)
                    }
                )

            }
        }
    }
}

@Composable
private fun ParameterSlider(
    text: String,
    parameter: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onParameterChange: (Float) -> Unit
) {
    Row(horizontalArrangement = Arrangement.SpaceBetween) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = text
        )
        Box(modifier = Modifier.weight(1f))
        Text(
            text = "%.2f".format(parameter),
        )
    }

    Slider(
        value = parameter,
        valueRange = valueRange,
        onValueChange = {
            onParameterChange(it)
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    DynamicOscillatorApp()
}