package com.jhegg.github.notifier

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.Stage

class App extends Application {
    static void main(String[] args) {
        launch(App.class, args)
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        primaryStage.title = "Hello World"
        primaryStage.scene = getScene()
        primaryStage.show()
    }

    def getScene() {
        def rootLayout = getRootLayout()
        rootLayout.setCenter(getCenterLayout())
        new Scene(rootLayout)
    }

    BorderPane getRootLayout() {
        new FXMLLoader().load(getClass().getClassLoader().getResourceAsStream('RootLayout.fxml') as InputStream)
    }

    Pane getCenterLayout() {
        new FXMLLoader().load(getClass().getClassLoader().getResourceAsStream('CenterLayout.fxml') as InputStream)
    }
}