package io.github.krxwallo.common

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate

@Composable
fun Curve(
    modifier: Modifier = Modifier
) {
    Canvas(modifier) {
        if (corners == 2) drawKochCurve(start.x, start.y, end.x, end.y, depth)
        else {
            //drawKochCurve(start.x, start.y, end.x, end.y, depth)
            fun rotated(start: Offset, end: Offset, maxLines: Int, currentLine: Int = 0) {
                drawKochCurve(start.x, start.y, end.x, end.y, depth)
                if (currentLine == maxLines) {
                    // max recursion depth; draw curve
                    //drawKochCurve(start.x, start.y, end.x, end.y, depth)
                }
                else {
                    rotate(300f/*360f - (180f / corners)*/, start) {
                        rotated(Offset(2*start.x-end.x, 2*start.y-end.y), start, maxLines, currentLine + 1)
                    }
                }
            }
            rotated(start, end, corners)
        }
    }
}

fun DrawScope.drawKochCurve(x1: Float, y1: Float, x2: Float, y2: Float, maxDepth: Int, currentDepth: Int = 0) {
    if (currentDepth == maxDepth) {
        // max recursion depth; draw line
        drawLine(Color.Black, Offset(x1, y1), Offset(x2, y2))
    }
    else {
        // draw it recursively
        drawKochCurve(x1, y1, x1 + (x2 - x1) / 3, y1 + (y2 - y1) / 3, maxDepth, currentDepth + 1)
        drawKochCurve(x1 + (x2 - x1) / 3, y1 + (y2 - y1) / 3, x1 + (x2 - x1) / 2 + (y2 - y1) / 3, y1 + (y2 - y1) / 2 - (x2 - x1) / 3, maxDepth, currentDepth + 1)
        drawKochCurve(x1 + (x2 - x1) / 2 + (y2 - y1) / 3, y1 + (y2 - y1) / 2 - (x2 - x1) / 3, x1 + 2 * (x2 - x1) / 3, y1 + 2 * (y2 - y1) / 3, maxDepth, currentDepth + 1)
        drawKochCurve(x1 + 2 * (x2 - x1) / 3, y1 + 2 * (y2 - y1) / 3, x2, y2, maxDepth, currentDepth + 1)
    }
}