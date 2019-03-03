/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package seg.major;

import javafx.stage.Stage;
import seg.major.controller.LoginController;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;

public class App extends Application {

    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController loginController = new LoginController();
        BorderPane root = new BorderPane();
        root.setCenter(loginController.getPane());
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
