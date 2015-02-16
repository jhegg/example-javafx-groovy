package com.jhegg.github.notifier

import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import javafx.scene.control.TextArea

class CenterLayoutController {
    @FXML
    private ListView<String> listView

    @FXML
    private TextArea textArea;

    def observableList = FXCollections.<String>observableArrayList()

    @SuppressWarnings("GroovyUnusedDeclaration")
    @FXML
    private void initialize() {
        observableList.add('test1')
        observableList.add('test2')
        listView.setItems(observableList)

        textArea.setEditable(false)

        listView.getSelectionModel().selectedItemProperty().addListener(
                {observableValue, oldValue, newValue ->
                    displayTextArea(newValue)} as ChangeListener)
    }

    private void displayTextArea(String text) {
        textArea.setText(text)
    }
}