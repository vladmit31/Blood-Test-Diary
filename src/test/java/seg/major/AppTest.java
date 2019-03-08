/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package seg.major;

import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import seg.major.controller.PrimaryController;
import seg.major.model.util.Props;

import static org.junit.Assert.*;

public class AppTest extends ApplicationTest {

    // Name of the view that is shown on first loading the application
    public static String login = "login";
    public static String addPatient = "add_patient";
    public static String patients = "patients";
    public static String schema = "schema";
    public static String propertiesLocation = "props.properties";
    public static Props props;

    @Override
    public void start(Stage primaryStage) throws Exception {
        PrimaryController primaryController = new PrimaryController();
        Group root = new Group();
        Scene scene = new Scene(root);

        primaryController.addViews(new String[] { login, addPatient, patients, schema });
        primaryController.setPane(App.login);
        root.getChildren().addAll(primaryController);
        primaryStage.setScene(scene);
        primaryStage.minWidthProperty().bind(primaryController.minWidthProperty());
        primaryStage.minHeightProperty().bind(primaryController.minHeightProperty());
        primaryStage.show();

    }
}
