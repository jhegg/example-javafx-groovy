package com.jhegg.github.notifier

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.Stage

class App extends Application {
    static String token = GString.EMPTY

    static void main(String[] args) {
        def cli = new CliBuilder()
        cli.with {
            h longOpt: 'help', 'Show usage information'
            t longOpt: 'token', 'GitHub OAuth token'
        }
        def options = cli.parse(args)
        if (options.h) {
            cli.usage()
            return
        }
        if (options.t) {
            token = options.t
        }

        launch(App.class, args)
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        primaryStage.title = "Hello World"
        primaryStage.scene = getScene()
        primaryStage.show()
    }

    @Override
    void stop() throws Exception {
        super.stop()
        exitApp()
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

    static void exitApp() {
        Platform.exit()
    }
}