package seg.major.model;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import seg.major.model.util.Email;
import seg.major.structure.Contact;
/**
 * Model class for CustomEmailController class.
 * Provides communication between controller and DAOs if needed.
 * @author Team Pacane
 * @version 1.0
 */
public class CustomEmailModel{

    private Contact sendTo;
    private String subject;
    private String content;

    public CustomEmailModel (Contact sendTo, String subject, String content) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.content = content;
    }

    public void sendEmail(){
        Email email = new Email("smtp.gmail.com", "25", "iudortures@gmail.com", "horiapavel69", this.sendTo.getEmail(), this.subject, this.content);
        email.sendEmail();
    }

}
