package org.verncreation.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.verncreation.project.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "nevPOS",
    ) {
        App()
    }
}