package seg.major.controller.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import seg.major.model.util.Email;
import seg.major.structure.Contact;
/**
 * Sends e-mails to laboratories notifying them
 * that they should send their results to a specific contact.
 * @author Team Pacane
 * @version 1.0
 */
public class LaboratoryEmailSender extends Thread {

    private String sendTo;
    private String subject;
    private String body;

    public LaboratoryEmailSender(String sendTo, String subject, String body) {
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
