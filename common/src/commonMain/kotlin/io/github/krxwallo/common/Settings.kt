package io.github.krxwallo.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

var depth by mutableStateOf(4)
var corners by mutableStateOf(3)
var editing by mutableStateOf(true)
var start by mutableStateOf(Offset(30f, 30f))
var end by mutableStateOf(Offset(30f, 1000f))
var rotationMultiplier by mutableStateOf(-1f)

@Composable
fun Settings() {
    Column(Modifier.background(Color(0, 255, 255, 50)).padding(5.dp)) {
        // Depth
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Depth: $depth", Modifier.width(100.dp))
            Spacer(Modifier.width(10.dp))
            var sliderValue by remember { mutableStateOf(depth.toFloat()) }
            if (sliderValue.roundToInt() != depth) sliderValue = depth.toFloat()
            Slider(sliderValue, {
                sliderValue = it
                depth = sliderValue.roundToInt()
            }, valueRange = 0f..7f)
        }
        // Corners
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Corners: ${corners.takeIf { it != 1 } ?: "-"}", Modifier.width(100.dp))
            Spacer(Modifier.width(10.dp))
            var sliderValue by remember { mutableStateOf(corners.toFloat()) }
            if (sliderValue.roundToInt() != corners) sliderValue = corners.toFloat()
            Slider(sliderValue, {
                sliderValue = it
                corners = sliderValue.roundToInt()
            }, valueRange = 1f..10f)
        }

        Row {
            // Invert Rotation
            Button(modifier = Modifier.weight(1f), onClick = {
                rotationMultiplier *= -1f
            }) {
                Text("Invert Rotation")
            }
            Spacer(Modifier.width(5.dp))
            // Moving
            Button(modifier = Modifier.weight(1f), onClick = {
                editing = !editing
            }) {
                Text(if (editing) "Save" else "Move")
            }
        }
    }
}