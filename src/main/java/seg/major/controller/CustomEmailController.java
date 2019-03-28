package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seg.major.App;
import seg.major.controller.util.EmailSender;
import seg.major.model.CustomEmailModel;
import seg.major.model.EditNotificationEmailModel;
import seg.major.model.util.Email;
import seg.major.structure.Contact;

import java.net.URL;
import java.util.*;
/**
 * AddPatientController acts as the controller for the custom_email.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class CustomEmailController implements Initializable, ControllerInterface {

    public TextField sendToTextBox;
    public TextField subjectTextBox;
    public Button backButton;
    public Button sendButton;
    public TextArea emailBody;

    private PrimaryController primaryController;


    private Map<String, Object> data = new HashMap<>();

    private List<Contact> contacts;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.contacts = new ArrayList<>();
        this.sendToTextBox.setEditable(false);
    }

    private void fillSendToTextBox() {
        if(this.contacts.size() == 0){
            this.sendToTextBox.appendText("No contacts found");
        }else if(this.contacts.size() == 1){
            this.sendToTextBox.appendText(this.contacts.get(0).getEmail());
        }else{
            for(int i = 0; i < this.contacts.size() - 1; i ++ ){
                this.sendToTextBox.appendText(this.contacts.get(i).getEmail() + ";  ");
            }
            this.sendToTextBox.appendText(this.contacts.get(this.contacts.size()-1).getEmail());
        }


    }

    @Override
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    @Override
    public void setData(Map<String, Object> toInject) {

    }

    @Override
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        for(var value : (ArrayList<Contact>)toAddVal){
            System.out.println(value.getEmail());
            this.contacts.add(value);
        }
    }

    @Override
    public void update() {
        this.sendToTextBox.setText("");
        this.subjectTextBox.setText("");
        fillSendToTextBox();
    }

    public void backButtonClicked(ActionEvent event) {

        this.contacts.clear();
        this.primaryController.setPane(App.notifyList);
    }

    public void sendButtonClicked(ActionEvent event) {
        String subject = this.subjectTextBox.getText();
        String content = this.emailBody.getText();

        EmailSender emailSender = new EmailSender(this.contacts, subject, content);
        emailSender.start();
        primaryController.setPane(App.notifyList);
    }
}
