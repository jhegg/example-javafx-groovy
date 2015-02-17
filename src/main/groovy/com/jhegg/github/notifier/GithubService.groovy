package com.jhegg.github.notifier

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import javafx.concurrent.Service
import javafx.concurrent.Task

class GithubService extends Service<String> {
    CenterLayoutController layoutController

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                // todo How about some actual error handling?
                new URL(resolvedUrl).getText([
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

        layoutController.updateEvents(events)
    }

    @Override
    protected void failed() {
        super.failed()
        layoutController.textArea.setText('Failed retrieving results')
    }

    String getResolvedUrl() {
        String.format(App.urlWithPlaceholder, App.username)
    }

    void setController(CenterLayoutController controller) {
        this.layoutController = controller
    }
}
