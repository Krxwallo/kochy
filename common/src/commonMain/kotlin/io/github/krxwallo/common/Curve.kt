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
        if (corners <= 1) drawKochCurve(start.x, start.y, end.x, end.y, depth)
        else {
            //drawKochCurve(start.x, start.y, end.x, end.y, depth)
            fun rotated(start: Offset, end: Offset, maxLines: Int, currentLine: Int = 0) {
                drawKochCurve(start.x, start.y, end.x, end.y, depth)
                if (currentLine == maxLines) {
                    // max recursion depth; draw curve
                    //drawKochCurve(start.x, start.y, end.x, end.y, depth)
                    return
                }
                rotate(rotationMultiplier * 360f / corners, start) {
                    rotated(Offset(2 * start.x - end.x, 2 * start.y - end.y), start, maxLines, currentLine + 1)
                }
            }
            rotated(start, end, corners-1)
        }
    }
}

fun DrawScope.drawKochCurve(x1: Float, y1: Float, x2: Float, y2: Float, maxDepth: Int, currentDepth: Int = 0) {
    if (currentDepth == maxDepth) {
        // max recursion depth; draw line
        drawLine(Color.Black, Offset(x1, y1), Offset(x2, y2))
    } else {
        // recursion depth not reached; split line
        val x3 = (2 * x1 + x2) / 3
        val y3 = (2 * y1 + y2) / 3
        val x4 = (x1 + 2 * x2) / 3
        val y4 = (y1 + 2 * y2) / 3
        val x5 = (x3 + x4) / 2 + (y4 - y3) * Math.sqrt(3.0) / 2
        val y5 = (y3 + y4) / 2 + (x3 - x4) * Math.sqrt(3.0) / 2
        drawKochCurve(x1, y1, x3, y3, maxDepth, currentDepth + 1)
        drawKochCurve(x3, y3, x5.toFloat(), y5.toFloat(), maxDepth, currentDepth + 1)
        drawKochCurve(x5.toFloat(), y5.toFloat(), x4, y4, maxDepth, currentDepth + 1)
        drawKochCurve(x4, y4, x2, y2, maxDepth, currentDepth + 1)
    }
}