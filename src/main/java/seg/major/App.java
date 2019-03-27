/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package seg.major;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import seg.major.controller.PrimaryController;
import seg.major.model.ReminderSender;
import seg.major.model.database.PatientDAO;
import seg.major.model.util.Props;
import seg.major.structure.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App extends Application {

    // Name of the view that is shown on first loading the application
    public static final String login = "login";
    public static final String addPatient = "add_patient";
    public static final String updatePatient = "update_patient";
    public static final String updateAppointment = "update_appointment";
    public static final String contacts = "contacts";
    public static final String patients = "patients";
    public static final String schema = "schema";
    public static final String notifyList = "notifyList";
    public static final String propertiesLocation = "props.properties";
    public static final String customEmail = "custom_email";
    public static final String editDefaultEmail = "edit_default_email";
    public static final String customLabNotification = "custom_lab_notification";
    public static final String changePassword = "change_password";
    public static final String customReminder = "custom_reminder";
    public static final String addUser = "add_user";
    public static Props props = new Props(App.class.getClassLoader().getResource(propertiesLocation));


    public static void main(String[] args) {
        System.out.println("App Started!");
        props = new Props(App.class.getClassLoader().getResource(propertiesLocation));
        launch(args);
    }

    private void resetLastNotif(){
        for(Patient patient : PatientDAO.getAll()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse("2000-10-10", formatter);

            patient.setLastTimeNotified(date);

            PatientDAO.update(patient);
        }
    }

    /**
     * Start the GUI and load panes
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        resetLastNotif();
        ReminderSender.sendRemainders();

        PrimaryController primaryController = new PrimaryController(primaryStage);
        primaryController.addViews(
                new String[] { login, notifyList, addPatient, customEmail, patients, schema, contacts,
                                            updatePatient, updateAppointment, customReminder, addUser, editDefaultEmail, customLabNotification, changePassword });

        primaryController.setPane(App.login);

        Group root = new Group();
        Scene scene = new Scene(root);
        root.getChildren().addAll(primaryController);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("images/logo.png"));

        primaryStage.setTitle("Aeon: Blood Test Diary");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
