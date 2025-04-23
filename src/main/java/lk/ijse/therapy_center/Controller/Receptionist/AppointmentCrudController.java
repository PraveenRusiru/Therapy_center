package lk.ijse.therapy_center.Controller.Receptionist;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.BO.Impl.AppointmentBOImpl;
import lk.ijse.therapy_center.BO.Impl.PatientBOImpl;
import lk.ijse.therapy_center.BO.Impl.TherapyProgrammeBoImpl;
import lk.ijse.therapy_center.BO.Impl.TherapySessionBOImpl;
import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.DTO.TM.AppointmentTM;
import lk.ijse.therapy_center.DTO.TM.PatientTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AppointmentCrudController implements Initializable {
    AppointmentBOImpl appointmentBOImpl=(AppointmentBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.APPOINTMENT);
    PatientBOImpl patientBOImpl=(PatientBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.PATIENT);
    TherapySessionBOImpl therapySessionBOImpl=(TherapySessionBOImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPY_SESSION);
    List<PatientDTO> allPatient=new ArrayList<>();
    List<TherapySessionDTO> allTherapySession=new ArrayList<>();
    String patientId="";


    @FXML
    private Label appointmentId;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<AppointmentTM, String> colStatus;

    @FXML
    private TableColumn<AppointmentTM, String> colAppointmentId;

    @FXML
    private TableColumn<AppointmentTM, String> colEndTime;

    @FXML
    private TableColumn<AppointmentTM, String> colPatientId;

    @FXML
    private TableColumn<AppointmentTM, String> colSessionId;

    @FXML
    private TableColumn<AppointmentTM, String> colStartTime;


    @FXML
    private ComboBox<String> comboboxPatientId;


    @FXML
    private ComboBox<String> comboboxSession;


    @FXML
    private Label patientName;


    @FXML
    private TableView<AppointmentTM> tblCustomer;

    @FXML
    private TextField txtEndTime;

    @FXML
    private TextField txtStartTime;

    @FXML
    private VBox vBox;

    public AppointmentCrudController() throws Exception {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted= appointmentBOImpl.deleteAppointment(appointmentId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted...!").show();
                refreshTable();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete therapist...!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws Exception {
        String patientId=comboboxPatientId.getSelectionModel().getSelectedItem();
        String sessionId=comboboxSession.getSelectionModel().getSelectedItem();
        String appointment=appointmentId.getText();
        String startTime=txtStartTime.getText();
        String endTime=txtEndTime.getText();
        boolean isStartTime= ValidateUtill.isValidTime(startTime);
        boolean isEndTime=ValidateUtill.isValidTime(endTime);
        if(isStartTime&&isEndTime){
            AppointmentDTO appointmentDTO=new AppointmentDTO();
            appointmentDTO.setId(appointment);
            appointmentDTO.setStartTime(startTime);
            appointmentDTO.setEndTime(endTime);
            appointmentDTO.setSession_id(sessionId);
            appointmentDTO.setPatient_id(patientId);
            appointmentDTO.setStatus("Unpaid");
            boolean isSaved=false;
            try{
                isSaved= appointmentBOImpl.saveAppointment(appointmentDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment saved...!").show();
                refreshTable();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save appointment...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Make sure every item selected  ! ...").show();
        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String patientId=comboboxPatientId.getSelectionModel().getSelectedItem();
        String sessionId=comboboxSession.getSelectionModel().getSelectedItem();
        String appointment=appointmentId.getText();
        String startTime=txtStartTime.getText();
        String endTime=txtEndTime.getText();
        boolean isStartTime= ValidateUtill.isValidTime(startTime);
        boolean isEndTime=ValidateUtill.isValidTime(endTime);
        if(isStartTime&&isEndTime){
            AppointmentDTO appointmentDTO=new AppointmentDTO();
            appointmentDTO.setId(appointment);
            appointmentDTO.setStartTime(startTime);
            appointmentDTO.setEndTime(endTime);
            appointmentDTO.setSession_id(sessionId);
            appointmentDTO.setPatient_id(patientId);
            appointmentDTO.setStatus("Unpaid");
            boolean isSaved=false;
            try{
                isSaved= appointmentBOImpl.updateAppointment(appointmentDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Appointment saved...!").show();
                refreshTable();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save appointment...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Make sure every item selected  ! ...").show();
        }

    }

    @FXML
    void selectingPatient(ActionEvent event) throws Exception {
            patientId=comboboxPatientId.getSelectionModel().getSelectedItem();
        PatientDTO result = allPatient.stream()
                .filter(dto -> dto.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        patientName.setText("\t\t\tTherapist :"+result.getName());
        System.out.println("Therapist id :"+patientId);
//        refreshTable();
    }

    @FXML
    void selectingSession(ActionEvent event) {
        String sessionId=comboboxSession.getSelectionModel().getSelectedItem();
        TherapySessionDTO result = allTherapySession.stream()
                .filter(dto -> dto.getId().equals(sessionId))
                .findFirst()
                .orElse(null);

        txtStartTime.setText(result.getStartTime());
        txtEndTime.setText(result.getEndTime());
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AppointmentTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            appointmentId.setText(selectedItem.getId());
            comboboxSession.setValue(selectedItem.getSession_id());
            comboboxPatientId.setValue(selectedItem.getPatient_id());
            txtEndTime.setText(selectedItem.getEndTime());
            txtStartTime.setText(selectedItem.getStartTime());
            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colAppointmentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("session_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
//            String nextCustomerID = customerModel.getNextCustomerID();
            System.out.println(appointmentBOImpl.getNextAppointmentId());
            appointmentId.setText(appointmentBOImpl.getNextAppointmentId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try {
             allPatient = patientBOImpl.getAllPatient();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<String> patientList = FXCollections.observableArrayList();
        for (PatientDTO patientDTO : allPatient) {
            System.out.println("Patient ID "+patientDTO.getId());
            patientList.add(patientDTO.getId());
        }
        comboboxPatientId.setItems(patientList);
        try {
            allTherapySession=therapySessionBOImpl.getAllTherapistForProgramme();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        ObservableList<String> therapySessionList = FXCollections.observableArrayList();
        for (TherapySessionDTO therapySessionDTO : allTherapySession) {
            System.out.println("Therapy Session ID "+therapySessionDTO.getId());
            therapySessionList.add(therapySessionDTO.getId());
        }
        comboboxSession.setItems(therapySessionList);


    }
    private void refreshPage() throws Exception {
        refreshTable();

        String nextCustomerID = appointmentBOImpl.getNextAppointmentId();
        appointmentId.setText(nextCustomerID);

        txtStartTime.setText("");
        txtEndTime.setText("");

        patientName.setText("");
        comboboxSession.getSelectionModel().clearSelection();
        comboboxPatientId.getSelectionModel().clearSelection();

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable() throws Exception {
        List<AppointmentDTO> appointmentDTOS = appointmentBOImpl.getAllAppointment();
        ObservableList<AppointmentTM> appointmentTMS = FXCollections.observableArrayList();

        for (AppointmentDTO appointmentDTO : appointmentDTOS) {
            AppointmentTM appointmentTM = new AppointmentTM();
            appointmentTM.setId(appointmentDTO.getId());
            appointmentTM.setEndTime(appointmentDTO.getEndTime());
            appointmentTM.setStartTime(appointmentDTO.getStartTime());
            appointmentTM.setPatient_id(appointmentDTO.getPatient_id());
            appointmentTM.setSession_id(appointmentDTO.getSession_id());
            appointmentTM.setStatus(appointmentDTO.getStatus());
            appointmentTMS.add(appointmentTM);
        }
        tblCustomer.setItems(appointmentTMS);
    }
}
