import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import io.github.krxwallo.common.App


fun main() = application {
    Window(title = "Kochy", onCloseRequest = ::exitApplication) {
        App()
    }
}
