package seg.major.controller.util;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import seg.major.model.CustomEmailModel;
import seg.major.structure.Contact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * Class sends e-mails and notifies the user that
 * the e-mail has been sent.
 * @author Team Pacane
 * @version 1.0
 */
public class EmailSender extends Thread {
    private final List<Contact> contacts;
    private final Map<Contact, String> contactsWithContent;
    private final String subject;
    private final String content;

    public EmailSender(List<Contact> contacts, String subject, String content){
        this.contacts = contacts;
        this.subject = subject;
        this.content = content;
        this.contactsWithContent = null;
    }

    public EmailSender(Map<Contact, String> contactsWithContent, String subject){
        this.contactsWithContent = contactsWithContent;
        this.subject = subject;

        this.contacts = null;
        this.content = "";

    }

    private void sendEmails(){
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

    private void sendEmailsWithContent(){
        Iterator it = contactsWithContent.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Contact contact = (Contact)pair.getKey();
            String generatedContent = (String)pair.getValue();
            (new CustomEmailModel(contact, this.subject, generatedContent))
                    .sendEmail();
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();

                sb.append("Your email has been sent to: \n");

                Iterator it = contactsWithContent.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry)it.next();
                    Contact contact = (Contact)pair.getKey();
                    String generatedContent = (String)pair.getValue();
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


    public void run(){
        if(this.contacts != null){
            sendEmails();
        }else if(this.contactsWithContent != null){
            sendEmailsWithContent();
        }
    }
}
