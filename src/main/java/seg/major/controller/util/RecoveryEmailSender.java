package seg.major.controller.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import seg.major.model.util.Email;
/**
 * Sends a password recovery e-mail to the user
 * requesting the password change.
 * @author Team Pacane
 * @version 1.0
 */
public class RecoveryEmailSender extends Thread {

    private String sendTo;
    private String subject;
    private String body;

    public RecoveryEmailSender(String sendTo, String subject, String body) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.body = body;
    }

    public void sendEmail(){
        Email email = new Email("smtp.gmail.com", "25", "iudortures@gmail.com", "horiapavel69", sendTo, subject, body);
        email.sendEmail();
    }

    public void run(){
        sendEmail();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText("Your email has been sent to: \n" + sendTo);
                alert.showAndWait();
            }
        });
    }
}
