package io.github.akueisara.polyphon.ui

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import java.util.Collections.max
import java.util.Collections.min


@Composable
fun OutputView(
    modifier: Modifier = Modifier,
    audioBuffer: List<Double>
) {
    if (audioBuffer.isEmpty()) return

    Canvas(modifier = modifier) {
        val totalBufferSize = audioBuffer.size
        val distanceBetweenPoints =  size.width / totalBufferSize.toFloat()
        val height = size.height

        var currentX = 0f
        val maxAudioValue = max(audioBuffer)
        val maxMinDiff = maxAudioValue - min(audioBuffer)
        audioBuffer.forEachIndexed { index, value ->
            if (index < totalBufferSize - 1) {
                drawLine(
                    start = Offset(
                        currentX,
                        calculateYCoordinate(maxMinDiff, maxAudioValue, value, height)
                    ),
                    end = Offset(
                        currentX + distanceBetweenPoints,
                        calculateYCoordinate(maxMinDiff, maxAudioValue, audioBuffer[index + 1], height)
                    ),
                    color = Color.Black,
                    strokeWidth = Stroke.DefaultMiter
                )
            }
            currentX += distanceBetweenPoints
        }
    }
}

/**
 * Calculates the Y pixel coordinate for a given audio value.
 * @param maxMinDiff The difference between the highest and lowest audio values.
 * @param highestValue The highest audio value in the buffer.
 * @param currentValue The audio value to calculate the Y coordinate for.
 * @param canvasHeight The height of the canvas.
 * @return The Y pixel coordinate for the given audio value.
 *
 **/
private fun calculateYCoordinate(
    maxMinDiff: Double,
    highestValue: Double,
    currentValue: Double,
    canvasHeight: Float
): Float {
    val difference = (highestValue - currentValue).toFloat()
    val relativeSize = canvasHeight / maxMinDiff.toFloat()
    return difference * relativeSize
}