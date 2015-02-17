package com.jhegg.github.notifier

import groovy.json.JsonOutput
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

    private GithubService githubService

    @SuppressWarnings("GroovyUnusedDeclaration")
    @FXML
    private void initialize() {
        listView.setItems(observableList)
        textArea.setEditable(false)
        listView.getSelectionModel().selectedItemProperty().addListener(
                {observableValue, oldValue, newValue ->
                    displayTextArea(newValue)} as ChangeListener)

        githubService = new GithubService()
        githubService.setController(this)
        githubService.start()
    }

    private void displayTextArea(GithubEvent event) {
        if (event != null )
            textArea.setText(JsonOutput.prettyPrint(event.json))
    }

    void updateEvents(List<GithubEvent> githubEvents) {
        observableList.setAll(githubEvents)
        listView.selectionModel.selectFirst()
    }

    void refreshDisplay() {
        githubService.restart() // todo This is not an ideal usage for proper error handling
    }
}
