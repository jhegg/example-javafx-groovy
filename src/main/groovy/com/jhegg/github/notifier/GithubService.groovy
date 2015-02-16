package com.jhegg.github.notifier

import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.concurrent.Service
import javafx.concurrent.Task

class GithubService extends Service<String> {
    private StringProperty url = new SimpleStringProperty()
    CenterLayoutController layoutController

    void setUrl(String value) {
        url.set(value)
    }

    String getUrl() {
        url.get()
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                new URL(getUrl()).getText()
            }
        }
    }

    @Override
    protected void succeeded() {
        super.succeeded()
        layoutController.observableList.add('Success')
        layoutController.textArea.setText(getValue())
    }

    @Override
    protected void failed() {
        super.failed()
        layoutController.observableList.add('Failed')
    }

    void setController(CenterLayoutController controller) {
        this.layoutController = controller
    }
}
