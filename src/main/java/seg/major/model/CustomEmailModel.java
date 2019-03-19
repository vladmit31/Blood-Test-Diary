package seg.major.model;

import seg.major.model.util.Email;
import seg.major.structure.Contact;

public class CustomEmailModel {

    public void sendEmail(Contact sendTo, String subject, String content){
        Email email = new Email("smtp.gmail.com", "25", "iudortures@gmail.com", "horiapavel69", sendTo.getEmail(), subject, content);
        email.sendEmail();
    }

}
