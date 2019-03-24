package seg.major.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import seg.major.App;
import seg.major.model.UpdateAppointmentModel;
import seg.major.structure.Appointment;
import seg.major.structure.AppointmentEntry;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class UpdateAppointmentController implements Initializable, ControllerInterface {


    private PrimaryController primaryController;

    private Map<String, Object> data = new HashMap<>();
    private boolean isInitialState;

    /** ---------- FXML ---------- */

    @FXML
    public CheckBox completed;
    @FXML
    public CheckBox notReceived;
    @FXML
    public CheckBox underReview;
    @FXML
    public DatePicker appDueDate;
    @FXML
    public Text textInfo;
    @FXML
    public Button updateBtn;
    @FXML
    public Button cancelBtn;
    @FXML
    public RadioButton oneMonthRadioButton;
    @FXML
    public RadioButton threeMonthsRadioButton;
    @FXML
    public RadioButton twoWeeksRadioButton;
    /** ---------- Inherited / Implemented ---------- */
    /**
     * Allow javafx to initalise the controller with the view
     */
    public void initialize(URL url, ResourceBundle rb) {
        isInitialState = true;
        setExclusionProperty();
    }
    private void setExclusionProperty() {
        appDueDate.setOnAction(new EventHandler() {
            public void handle(Event t) {
                LocalDate date = appDueDate.getValue();
                isInitialState = false;
                if(!date.equals(null)&&isInitialState==false) {
                    oneMonthRadioButton.setDisable(true);
                    twoWeeksRadioButton.setDisable(true);
                    threeMonthsRadioButton.setDisable(true);
                }
            }
        });
        twoWeeksRadioButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                appDueDate.setDisable(true);
                }
        });
        oneMonthRadioButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                appDueDate.setDisable(true);
            }
        });
        threeMonthsRadioButton.setOnAction(new EventHandler() {
            public void handle(Event t) {
                appDueDate.setDisable(true);
            }
        });
        completed.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                underReview.setSelected(false);
                notReceived.setSelected(false);
            }
        });
        underReview.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                completed.setSelected(false);
                notReceived.setSelected(false);
            }
        });
        notReceived.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                underReview.setSelected(false);
                completed.setSelected(false);
            }
        });
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
        resetView();
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
        else if(underReview.isSelected()) {
            newStatus = "Under Review";
            newStatusInt = 2;
        }
        else if(notReceived.isSelected()){
            newStatus = "Not received yet";
            newStatusInt = 3;
        }
        else {
            //do nothing
        }

        Appointment appointment = new Appointment(appointmentEntry.getAppointmentId(), newStatusInt, appDueDate.getValue(),appointmentEntry.getPatientId());

        data.remove("appointmentEntry");

        appointmentEntry.setComplete(newStatus);
        appointmentEntry.setDueDate(appDueDate.getValue());

        UpdateAppointmentModel.updateAppointment(appointment);

        primaryController.sendTo(App.schema, "appointmentEntry", appointmentEntry);
        primaryController.setPane(App.schema);

        resetView();
    }

    public void cancelButtonClicked(ActionEvent event) {
        data.remove("appointmentEntry");
        primaryController.setPane(App.schema);
        resetView();
    }

    private void resetView(){
        oneMonthRadioButton.setDisable(false);
        oneMonthRadioButton.setSelected(false);
        threeMonthsRadioButton.setDisable(false);
        threeMonthsRadioButton.setSelected(false);
        twoWeeksRadioButton.setDisable(false);
        twoWeeksRadioButton.setSelected(false);
        appDueDate.setDisable(false);
        completed.setSelected(false);
        underReview.setSelected(false);
        notReceived.setSelected(false);
        isInitialState = true;

    }

    public void resetButtonClicked(ActionEvent actionEvent) {
        resetView();
        setChosenAppointmentEntry();
    }

    /** ---------- Inherited / Implemented ---------- */
}
