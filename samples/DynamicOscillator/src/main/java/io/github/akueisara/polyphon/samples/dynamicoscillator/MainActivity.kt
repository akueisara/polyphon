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
import io.github.akueisara.polyphon.ui.OutputView

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

@Composable
private fun DynamicOscillatorApp() {
    var isStart by remember { mutableStateOf(false) }
    var frequency by remember { mutableStateOf(440f) }
    var amplitude by remember { mutableStateOf(0.2f) }
    var rampDuration by remember { mutableStateOf(1.0f) }

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
                        AudioEngine.output?.setFrequency(it.toDouble(), rampDuration)
                    }
                )

                ParameterSlider(
                    text = "Amplitude",
                    parameter = amplitude,
                    valueRange = 0f..1f,
                    onParameterChange = {
                        amplitude = it
                        AudioEngine.output?.setAmplitude(it, rampDuration)
                    }
                )

                ParameterSlider(
                    text = "Ramp Duration",
                    parameter = rampDuration,
                    valueRange = 0f..10f,
                    onParameterChange = {
                        rampDuration = it
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

@Preview
@Composable
private fun OutputViewPreview() {
    OutputView(
        modifier = Modifier.height(250.dp)
            .fillMaxWidth()
            .padding(12.dp),
        audioBuffer = mutableListOf(
            0.005756,
            0.011494,
            0.017193,
            0.022835,
            0.028402,
            0.033874,
            0.039234,
            0.044464,
            0.049546,
            0.054464,
            0.059201,
            0.063742,
            0.068072,
            0.072176,
            0.076041,
            0.079653,
            0.083001,
            0.086074,
            0.088862,
            0.091355,
            0.093544,
            0.095424,
            0.096987,
            0.098229,
            0.099144,
            0.099731,
            0.099988,
            0.099912,
            0.099506,
            0.098769,
            0.097705,
            0.096316,
            0.094609,
            0.092587,
            0.090259,
            0.087631,
            0.084712,
            0.081513,
            0.078043,
            0.074314,
            0.070339,
            0.066131,
            0.061704,
            0.057071,
            0.052250,
            0.047255,
            0.042104,
            0.036813,
            0.031399,
            0.025882,
            0.020279,
            0.014608,
            0.008890,
            0.003141,
            -0.002618,
            -0.008368,
            -0.014090,
            -0.019766,
            -0.025376,
            -0.030902,
            -0.036325,
            -0.041628,
            -0.046793,
            -0.051803,
            -0.056641,
            -0.061291,
            -0.065737,
            -0.069966,
            -0.073963,
            -0.077714,
            -0.081208,
            -0.084433,
            -0.087377,
            -0.090032,
            -0.092388,
            -0.094438,
            -0.096174,
            -0.097592,
            -0.098686,
            -0.099452,
            -0.099889,
            -0.099995,
            -0.099768,
            -0.099211,
            -0.098326,
            -0.097113,
            -0.095579,
            -0.093728,
            -0.091566,
            -0.089101,
            -0.086340,
            -0.083292,
            -0.079969,
            -0.076380,
            -0.072538,
            -0.068455,
            -0.064145,
            -0.059623,
            -0.054902,
            -0.050000,
            -0.044932,
            -0.039715,
            -0.034366,
            -0.028903,
            -0.023345,
            -0.017709,
            -0.012014,
            -0.006279,
            -0.000524,
            0.005233,
            0.010973,
            0.016677,
            0.022325,
            0.027899,
            0.033381,
            0.038751,
            0.043994,
            0.049090,
            0.054024,
            0.058778,
            0.063338,
            0.067687,
            0.071813,
            0.075699,
            0.079335,
            0.082708,
            0.085806,
            0.088620,
            0.091140,
            0.093358,
            0.095266,
            0.096858,
            0.098129,
            0.099075,
            0.099692,
            0.099978,
            0.099933,
            0.099556,
            0.098849,
            0.097815,
            0.096456,
            0.094777,
            0.092784,
            0.090483,
            0.087882,
            0.084989,
            0.081815,
            0.078369,
            0.074664,
            0.070711,
            0.066523,
            0.062115,
            0.057501,
            0.052696,
            0.047716,
            0.042578,
            0.037299,
            0.031896,
            0.026388,
            0.020791,
            0.015126,
            0.009411,
            0.003665,
            -0.002094,
            -0.007846,
            -0.013571,
            -0.019252,
            -0.024869,
            -0.030403,
            -0.035837,
            -0.041151,
            -0.046329,
            -0.051354,
            -0.056208,
            -0.060876,
            -0.065342,
            -0.069591,
            -0.073609,
            -0.077384,
            -0.080901,
            -0.084151,
            -0.087121,
            -0.089803,
            -0.092186,
            -0.094264,
            -0.096029,
            -0.097476,
            -0.098600,
            -0.099396,
            -0.099863,
            -0.099999,
            -0.099803,
            -0.064145,
            -0.059623,
            -0.054902,
            -0.050000,
            -0.044932,
            -0.039715,
            -0.034366,
            -0.028903,
            -0.023345,
            -0.017709,
            -0.012014,
            -0.006279,
            -0.000524,
            0.005233,
            0.010973,
            0.016677,
            0.022325,
            0.027899,
            0.033381,
            0.038751,
            0.043994,
            0.049090,
            0.054024,
            0.058778,
            0.063338,
            0.067687,
            0.071813,
            0.075699,
            0.079335,
            0.082708,
            0.085806,
            0.088620,
            0.091140,
            0.093358,
            0.095266,
            0.096858,
            0.098129,
            0.099075,
            0.099692,
            0.099978,
            0.099933,
            0.099556,
            0.098849,
            0.097815,
            0.096456,
            0.094777,
            0.092784,
            0.090483,
            0.087882,
            0.084989,
            0.081815,
            0.078369,
            0.074664,
            0.070711,
            0.066523,
            0.062115,
            0.057501,
            0.052696,
            0.047716,
            0.042578,
            0.037299,
            0.031896,
            0.026388,
            0.020791,
            0.015126,
            0.009411,
            0.003665,
            -0.002094,
            -0.007846,
            -0.013571,
            -0.019252,
            -0.024869,
            -0.030403,
            -0.035837,
            -0.041151,
            -0.046329,
            -0.051354,
            -0.056208,
            -0.060876,
            -0.065342,
            -0.069591,
            -0.073609,
            -0.077384,
            -0.080901,
            -0.084151,
            -0.087121,
            -0.089803,
            -0.092186,
            -0.094264,
            -0.096029,
            -0.097476,
            -0.098600,
            -0.099396,
            -0.099863,
            -0.099999,
            -0.099803
        )
    )
}
