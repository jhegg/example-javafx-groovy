package com.jhegg.github.notifier

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.Stage

class App extends Application {
    static String urlWithPlaceholder = "https://api.github.com/users/%s/received_events"
    static String username = "jhegg"
    static String token = GString.EMPTY

    protected Stage primaryStage
    protected CenterLayoutController centerLayoutController

    static void main(String[] args) {
        def cli = new CliBuilder()
        cli.with {
            h longOpt: 'help', 'Show usage information'
            t longOpt: 'token', args: 1, 'GitHub OAuth token (Optional)'
            u longOpt: 'user', args: 1, 'GitHub username to be queried (default: jhegg)'
        }
        def options = cli.parse(args)
        if (options.h) {
            cli.usage()
            return
        }
        if (options.t) {
            token = options.t
        }
        if (options.u) {
            username = options.u
        }

        launch(App.class, args)
    }

    void exitApp() {
        Platform.exit()
    }

    @Override
    void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage
        primaryStage.title = "Hello World"
        primaryStage.scene = getScene()
        primaryStage.show()
    }

    @Override
    void stop() throws Exception {
        super.stop()
        exitApp()
    }

    private def getScene() {
        def rootLayout = getRootLayout()
        rootLayout.setCenter(getCenterLayout())
        new Scene(rootLayout)
    }

    private BorderPane getRootLayout() {
        def loader = new FXMLLoader(getClass().getClassLoader().getResource('RootLayout.fxml') as URL)
        BorderPane pane = loader.load()
        RootLayoutController controller = loader.getController()
        controller.setApp(this)
        return pane
    }

    private Pane getCenterLayout() {
        def loader = new FXMLLoader(getClass().getClassLoader().getResource('CenterLayout.fxml') as URL)
        Pane pane = loader.load()
        centerLayoutController = loader.getController()
        return pane
    }
}