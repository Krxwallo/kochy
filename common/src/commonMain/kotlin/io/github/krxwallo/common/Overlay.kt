package io.github.krxwallo.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

expect val overlaySize: Float

@Composable
fun EditOverlay(
    modifier: Modifier = Modifier
) {
    Canvas(modifier
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                var found = false
                for (pos in listOf(change.position, change.previousPosition)) {
                    if (pos.x in start.x.dragRange() && pos.y in start.y.dragRange()) {
                        start += dragAmount
                        found = true
                        break
                    } else if (pos.x in end.x.dragRange() && pos.y in end.y.dragRange()) {
                        end += dragAmount
                        found = true
                        break
                    }
                }
                if (!found) {
                    start += dragAmount
                    end += dragAmount
                }
            }
        }
    ) {
        drawCircle(Color.Black, overlaySize, start)
        drawCircle(Color.Red, overlaySize, end)
    }
}

private fun Float.dragRange() = (this - (overlaySize + 10f))..(this + (overlaySize + 10f))