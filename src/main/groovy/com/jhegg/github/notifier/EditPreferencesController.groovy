package com.jhegg.github.notifier

import javafx.fxml.FXML
import javafx.scene.control.TextField
import javafx.stage.Stage

class EditPreferencesController {
    @FXML
    private TextField token
    @FXML
    private TextField userName

    Stage dialogStage

    boolean wasOkClicked

    @FXML
    void clickedOk() {
        App.username = userName.getText()
        App.token = token.getText()
        wasOkClicked = true
        dialogStage.close()
    }

    @FXML
    void clickedCancel() {
        dialogStage.close()
    }

    void setDisplayedPreferences(String token, String userName) {
        this.token.setText(token)
        this.userName.setText(userName)
    }
}
