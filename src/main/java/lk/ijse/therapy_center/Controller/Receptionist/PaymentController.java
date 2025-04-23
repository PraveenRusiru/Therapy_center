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
import lk.ijse.therapy_center.BO.Impl.PaymentBOImpl;
import lk.ijse.therapy_center.BO.Impl.TherapySessionBOImpl;
import lk.ijse.therapy_center.DTO.AppointmentDTO;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.DTO.PaymentDTO;
import lk.ijse.therapy_center.DTO.TM.AppointmentTM;
import lk.ijse.therapy_center.DTO.TM.PaymentTM;
import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lk.ijse.therapy_center.Entity.TherapySession;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PaymentController implements Initializable {
    PaymentBOImpl paymentBOImpl=(PaymentBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.PAYMENT);
    PatientBOImpl patientBOImpl=(PatientBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.PATIENT);
    TherapySessionBOImpl therapySessionBOImpl=(TherapySessionBOImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPY_SESSION);
    AppointmentBOImpl appointmentBOImpl=(AppointmentBOImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.APPOINTMENT);
    List<PatientDTO> allPatient=new ArrayList<>();
    List<TherapySessionDTO> allTherapySession=new ArrayList<>();
    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PaymentTM, BigDecimal> colAmount;

    @FXML
    private TableColumn<PaymentTM, Date> colDate;

    @FXML
    private TableColumn<PaymentTM, String> colPatientId;

    @FXML
    private TableColumn<PaymentTM, String> colPaymentId;

    @FXML
    private TableColumn<PaymentTM, String> colSessionId;

    @FXML
    private TableColumn<PaymentTM, String> colStatus;

    @FXML
    private ComboBox<String> comboboxPatientId;

    @FXML
    private ComboBox<String> comboboxSession;

    @FXML
    private ComboBox<String> comboboxStatus;

    @FXML
    private DatePicker datePicker;

    @FXML
    private Label paymentId;


    @FXML
    private Label appointmentId;

    @FXML
    private Label patientName;

    @FXML
    private TableView<PaymentTM> tblCustomer;

    @FXML
    private TextField txtAmount;

    @FXML
    private VBox vBox;

    public PaymentController() throws Exception {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this appointment ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted= paymentBOImpl.deletePayment(paymentId.getText());
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
        String payment=paymentId.getText();
        BigDecimal amount=BigDecimal.valueOf(Double.parseDouble(txtAmount.getText()));
        Date date= Date.valueOf(datePicker.getValue());
        String status=comboboxStatus.getSelectionModel().getSelectedItem();

        boolean isAmouuntValid= ValidateUtill.isPriceValid(String.valueOf(amount));

        if(isAmouuntValid){
            PaymentDTO paymentDTO=new PaymentDTO();
            paymentDTO.setAmount(amount);
            paymentDTO.setDate(date);
            paymentDTO.setStatus(status);
            paymentDTO.setSession_id(sessionId);
            paymentDTO.setPatient_id(patientId);
            paymentDTO.setId(payment);
            boolean isSaved=false;
            try{
                isSaved= paymentBOImpl.savePayments(paymentDTO);
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
        String payment=paymentId.getText();
        BigDecimal amount=BigDecimal.valueOf(Double.parseDouble(txtAmount.getText()));
        Date date= Date.valueOf(datePicker.getValue());
        String status=comboboxStatus.getSelectionModel().getSelectedItem();

        boolean isAmouuntValid= ValidateUtill.isPriceValid(String.valueOf(amount));

        if(isAmouuntValid){
            PaymentDTO paymentDTO=new PaymentDTO();
            paymentDTO.setAmount(amount);
            paymentDTO.setDate(date);
            paymentDTO.setStatus(status);
            paymentDTO.setSession_id(sessionId);
            paymentDTO.setPatient_id(patientId);
            paymentDTO.setId(payment);
            boolean isSaved=false;
            try{
                isSaved= paymentBOImpl.updatePayment(paymentDTO);
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
    void datePicked(ActionEvent event) {

    }

    @FXML
    void onClickTable(MouseEvent event) {
        PaymentTM selectedItem=(PaymentTM) tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            paymentId.setText(selectedItem.getId());
            comboboxSession.setValue(selectedItem.getSession_id());
            comboboxPatientId.setValue(selectedItem.getPatient_id());
            txtAmount.setText(selectedItem.getAmount().toString());
            comboboxStatus.setValue(selectedItem.getStatus());
            datePicker.setValue(selectedItem.getDate().toLocalDate());


            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }

    @FXML
    void selectingPatient(ActionEvent event) throws Exception {
      String  patientId=comboboxPatientId.getSelectionModel().getSelectedItem();
        PatientDTO result = allPatient.stream()
                .filter(dto -> dto.getId().equals(patientId))
                .findFirst()
                .orElse(null);

        patientName.setText("\t\t\tTherapist :"+result.getName());
        System.out.println("Therapist id :"+patientId);

        List<TherapySession> allByCustomers = appointmentBOImpl.findAllByCustomer(patientId);
        ObservableList<String> sessionList = FXCollections.observableArrayList();
        for (TherapySession sessionId : allByCustomers) {

            sessionList.add(sessionId.getId());
        }
        comboboxSession.setItems(sessionList);
    }

    @FXML
    void selectingSession(ActionEvent event) {

    }

    @FXML
    void selectingStatus(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("patient_id"));
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colSessionId.setCellValueFactory(new PropertyValueFactory<>("session_id"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            refreshPage();
//            String nextCustomerID = customerModel.getNextCustomerID();
            System.out.println(paymentBOImpl.getNextPaymentId());
            paymentId.setText(paymentBOImpl.getNextPaymentId());
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

        ObservableList<String> status = FXCollections.observableArrayList("Unpaid","Paid","Advanced");
        comboboxStatus.setItems(status);

    }
    private void refreshPage() throws Exception {
        refreshTable();

        String nextCustomerID = paymentBOImpl.getNextPaymentId();
        paymentId.setText(nextCustomerID);

        txtAmount.setText("");


        patientName.setText("");
        comboboxSession.getSelectionModel().clearSelection();
        comboboxPatientId.getSelectionModel().clearSelection();
        comboboxStatus.getSelectionModel().clearSelection();

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable() throws Exception {
        List<PaymentDTO> paymentDTOS = paymentBOImpl.getAllPayment();
        ObservableList<PaymentTM> paymentTMS = FXCollections.observableArrayList();

        for (PaymentDTO paymentDTO : paymentDTOS) {
            PaymentTM paymentTM = new PaymentTM();

            paymentTM.setId(paymentDTO.getId());
            paymentTM.setDate(paymentDTO.getDate());
            paymentTM.setStatus(paymentDTO.getStatus());
            paymentTM.setAmount(paymentDTO.getAmount());
            paymentTM.setSession_id(paymentDTO.getSession_id());
            paymentTM.setPatient_id(paymentDTO.getPatient_id());
            paymentTMS.add(paymentTM);

        }
        tblCustomer.setItems(paymentTMS);
    }
}
