package com.jhegg.github.notifier

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
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
        new Scene(getRootLayout())
    }

    Parent getRootLayout() {
        def loader = new FXMLLoader()
        loader.load(getClass().getClassLoader().getResourceAsStream('RootLayout.fxml') as InputStream)
    }
}