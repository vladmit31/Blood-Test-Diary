package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import seg.major.App;
import seg.major.controller.util.EmailSender;
import seg.major.structure.Contact;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * AddPatientController acts as the controller for the custom_email.fxml file
 * @author Team Pacane
 * @version 1.0
 */
public class CustomEmailController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;
    private Map<String, Object> data = new HashMap<>();
    private List<Contact> contacts;

    @FXML
    public TextField sendToTextBox;
    @FXML
    public TextField subjectTextBox;
    @FXML
    public Button backButton;
    @FXML
    public Button sendButton;
    @FXML
    public TextArea emailBody;

    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL location, ResourceBundle resources) {
        this.contacts = new ArrayList<>();
        this.sendToTextBox.setEditable(false);
    }

    /**
     * Populate the sendToBox with the email addresses of the contacts
     */
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

    /**
     * Set the primaryController
     *
     * @param primaryController the PrimaryController to set
     */
    public void setScreenParent(PrimaryController primaryController) {
        this.primaryController = primaryController;
    }

    /**
     * Set the data
     *
     * @param data the data to set
     */
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    /**
     * Add data to the given fx-item and update the scene
     *
     * @param toAddKey the key of the data
     * @param toAddVal the value of the data
     */
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        ArrayList<Contact> = (ArrayList<Contact>)toAddVal;
        for(Contact value : toAddVal){
            this.contacts.add(value);
        }
    }

    /**
     * Update the scene with changes from the data HashMap
     */
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
