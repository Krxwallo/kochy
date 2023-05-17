package io.github.krxwallo.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun App() {
    Column {
        Settings()
        Spacer(Modifier.height(10.dp))
        Box {
            Curve(Modifier.fillMaxSize())
            if (editing) EditOverlay(Modifier.fillMaxSize())
        }
    }
}