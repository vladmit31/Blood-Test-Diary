package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.UpdateAppointmentModel;
import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable, ControllerInterface {

    private PrimaryController primaryController;

    private Map<String, Object> data = new HashMap<>();

    public CheckBox completed;
    public DatePicker appDueDate;
    public Text textInfo;
    public Button updateBtn;
    public Button cancelBtn;

    /** ---------- FXML ---------- */

    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {

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
     * @param toSet the data to set
     */
    public void setData(Map<String, Object> toSet) {
        this.data = toSet;
    }

    /**
     * Add data to the given fx-item and update the scene
     * 
     * @param toAddKey the key of the data
     * @param toAddVal the value of the data
     */
    public void addData(String toAddKey, Object toAddVal) {
        data.put(toAddKey, toAddVal);
        update();
    }

    /**
     * Update the scene with changes from the data HashMap
     */
    public void update() {
        setChosenAppointmentEntry();
    }

    private void setChosenAppointmentEntry() {
        AppointmentEntry appointmentEntry =(AppointmentEntry)this.data.get("appointmentEntry");

        textInfo.setText(appointmentEntry.getName());
        appDueDate.setValue(appointmentEntry.getDueDate());

        if(appointmentEntry.getComplete().equals("Incomplete")) {
            completed.setSelected(false);
        }else {
            completed.setSelected(true);
        }
    }

    public void completedCheckboxClicked(ActionEvent event) {

    }

    public void updateButtonClicked(ActionEvent event) {
        AppointmentEntry appointmentEntry =(AppointmentEntry)this.data.get("appointmentEntry");

        String newStatus = "Incomplete";
        int newStatusInt = 0;

        if(completed.isSelected()) {
            newStatus = "Complete";
            newStatusInt = 1;
        }

        Appointment appointment = new Appointment(appointmentEntry.getAppointmentId(), newStatusInt, appDueDate.getValue(),appointmentEntry.getPatientId());

        data.remove("appointmentEntry");

        appointmentEntry.setComplete(newStatus);
        appointmentEntry.setDueDate(appDueDate.getValue());

        UpdateAppointmentModel.updateAppointment(appointment);

        primaryController.sendTo(App.schema, "appointmentEntry", appointmentEntry);
        primaryController.setPane(App.schema);
    }

    public void cancelButtonClicked(ActionEvent event) {
        data.remove("appointmentEntry");
        primaryController.setPane(App.schema);
    }

    /** ---------- Inherited / Implemented ---------- */
}
