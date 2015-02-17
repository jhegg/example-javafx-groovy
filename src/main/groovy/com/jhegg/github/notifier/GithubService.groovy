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
                new URL(resolvedUrl).getText(getHeaders())
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
        layoutController.textArea.setText("Failed retrieving results from ${getResolvedUrl()} due to:\n ${getException()}")
    }

    String getResolvedUrl() {
        if (App.githubEnterpriseHostname) {
            getResolvedGithubEnterprisePrefix() + getResolvedUrlSuffix()
        } else {
            App.githubUrlPrefix + getResolvedUrlSuffix()
        }
    }

    private String getResolvedGithubEnterprisePrefix() {
        String.format(App.githubEnterpriseUrlPrefixWithPlaceholder, App.githubEnterpriseHostname)
    }

    private String getResolvedUrlSuffix() {
        String.format(App.githubUrlSuffixWithPlaceholder, App.userName)
    }

    void setController(CenterLayoutController controller) {
        this.layoutController = controller
    }

    private def getHeaders() {
        def headers = [
                'User-Agent': 'groovy',
                'Accept'    : 'application/vnd.github.v3.text-match+json',
        ]
        if (App.token) {
            headers << ['Authorization': App.token]
        }
        return headers
    }
}
