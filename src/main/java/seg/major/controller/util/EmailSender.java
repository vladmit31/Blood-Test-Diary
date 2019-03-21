package seg.major.controller.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import seg.major.model.CustomEmailModel;
import seg.major.structure.Contact;

import java.util.ArrayList;
import java.util.List;

public class EmailSender extends Thread {
    private final List<Contact> contacts;
    private final String subject;
    private final String content;

    public EmailSender(List<Contact> contacts, String subject, String content){
        this.contacts = contacts;
        this.subject = subject;
        this.content = content;
    }

    public void run(){
        for(Contact contact : this.contacts){
            (new CustomEmailModel(contact, subject, content))
                    .sendEmail();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();

                sb.append("Your email has been sent to: \n");

                for (Contact contact : contacts) {
                    sb.append(contact.getForename() + " " + contact.getSurname() + "\n");
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText(null);
                alert.setContentText(sb.toString());
                alert.showAndWait();
            }
        });
    }
}
