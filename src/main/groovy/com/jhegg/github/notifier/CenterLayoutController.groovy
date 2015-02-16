package com.jhegg.github.notifier

import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TextArea

class CenterLayoutController {
    @FXML
    private ListView<GithubEvent> listView

    @FXML
    TextArea textArea;

    def observableList = FXCollections.<GithubEvent>observableArrayList()

    @SuppressWarnings("GroovyUnusedDeclaration")
    @FXML
    private void initialize() {
        listView.setItems(observableList)
        textArea.setEditable(false)
        listView.getSelectionModel().selectedItemProperty().addListener(
                {observableValue, oldValue, newValue ->
                    displayTextArea(newValue)} as ChangeListener)

        GithubService githubService = new GithubService()
        githubService.setController(this)
        githubService.start()
    }

    private void displayTextArea(GithubEvent event) {
        textArea.setText(event.json)
    }
}
