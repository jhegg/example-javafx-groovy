package com.jhegg.github.notifier

import javafx.fxml.FXML
import javafx.scene.control.Alert
import javafx.stage.StageStyle

class RootLayoutController {
    @FXML
    void exit() {
        App.exitApp()
    }

    @FXML
    void about() {
        Alert aboutBox = new Alert(Alert.AlertType.INFORMATION)
        aboutBox.setTitle("About")
        aboutBox.setHeaderText(null)
        aboutBox.setContentText("This example app illustrates using Groovy and JavaFX to interact with the GitHub API.")
        aboutBox.initStyle(StageStyle.UTILITY)
        aboutBox.showAndWait()
    }
}
