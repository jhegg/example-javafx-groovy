package com.jhegg.github.notifier

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import javafx.beans.property.SimpleStringProperty
import javafx.beans.property.StringProperty
import javafx.concurrent.Service
import javafx.concurrent.Task

class GithubService extends Service<String> {
    private StringProperty url = new SimpleStringProperty('https://api.github.com/users/jhegg/received_events/public')
    CenterLayoutController layoutController

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                new URL(url.get()).getText([
                        'User-Agent':'groovy',
                        'Accept':'application/vnd.github.v3.text-match+json',
                        'Authorization':App.token,
                        ])
            }
        }
    }

    @Override
    protected void succeeded() {
        super.succeeded()

        def result = new JsonSlurper().parseText(value)
        def events = result.collect {
            new GithubEvent(id: it.id, type: it.type, login: it.actor.login, created_at: it.created_at, json: JsonOutput.toJson(it))
        }

        layoutController.observableList.setAll(events)
    }

    @Override
    protected void failed() {
        super.failed()
        layoutController.textArea.setText('Failed retrieving results')
    }

    void setController(CenterLayoutController controller) {
        this.layoutController = controller
    }
}
