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
var showPoints by mutableStateOf(true)
var start by mutableStateOf(Offset(30f, 30f))
var end by mutableStateOf(Offset(30f, 1000f))

@Composable
fun Settings() {
    Column(Modifier.background(Color(0, 255, 255, 50)).padding(5.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Depth: $depth")
            Spacer(Modifier.width(10.dp))
            var sliderValue by remember { mutableStateOf(depth.toFloat()) }
            if (sliderValue.roundToInt() != depth) sliderValue = depth.toFloat()
            Slider(sliderValue, {
                sliderValue = it
                depth = sliderValue.roundToInt()
            }, valueRange = 0f..7f)
        }
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                showPoints = !showPoints
            }) {
                Text((if (showPoints) "Hide" else "Show") + " Points")
            }
        }
    }
}