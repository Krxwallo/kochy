package io.github.krxwallo.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput

@Composable
fun PointsOverlay(
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
                    }
                    else if (pos.x in end.x.dragRange() && pos.y in end.y.dragRange()) {
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
        drawCircle(Color.Black, 10f, start)
        drawCircle(Color.Red, 10f, end)
    }
}

private fun Float.dragRange() = (this - 30f)..(this + 30f)