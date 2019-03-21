package seg.major.model;

import javafx.scene.control.Alert;
import seg.major.model.util.Email;
import seg.major.structure.Contact;

public class CustomEmailModel extends Thread{

    private Contact sendTo;
    private String subject;
    private String content;

    public CustomEmailModel (Contact sendTo, String subject, String content) {
        this.sendTo = sendTo;
        this.subject = subject;
        this.content = content;
    }

    private void sendEmail(){
        Email email = new Email("smtp.gmail.com", "25", "iudortures@gmail.com", "horiapavel69", this.sendTo.getEmail(), this.subject, this.content);
        email.sendEmail();
    }

    @Override
    public void run() {
        sendEmail();
    }
}
