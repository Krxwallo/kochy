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

@Composable
fun Settings() {
    Column(Modifier.background(Color(0, 255, 255, 50)).padding(5.dp)) {
        // Depth
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Depth: $depth", Modifier.width(80.dp))
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
            Text("Corners: ${corners.takeIf { it != 2 } ?: "-"}", Modifier.width(80.dp))
            Spacer(Modifier.width(10.dp))
            var sliderValue by remember { mutableStateOf(corners.toFloat()) }
            if (sliderValue.roundToInt() != corners) sliderValue = corners.toFloat()
            Slider(sliderValue, {
                sliderValue = it
                corners = sliderValue.roundToInt()
            }, valueRange = 2f..10f)
        }
        // Start/End points
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                editing = !editing
            }) {
                Text(if (editing) "Save" else "Edit")
            }
        }
    }
}