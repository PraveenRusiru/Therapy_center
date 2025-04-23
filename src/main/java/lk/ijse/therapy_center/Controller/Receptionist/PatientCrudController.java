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
import lk.ijse.therapy_center.BO.Impl.PatientBOImpl;
import lk.ijse.therapy_center.DAO.DAOFactory;
import lk.ijse.therapy_center.DTO.PatientDTO;
import lk.ijse.therapy_center.DTO.TM.PatientTM;
import lk.ijse.therapy_center.DTO.TM.TherapistTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.Utill.ChangeUtill;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PatientCrudController implements Initializable {
    PatientBOImpl patientBOImpl=(PatientBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.PATIENT);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<PatientTM, String> colEmail;

    @FXML
    private TableColumn<PatientTM, String> colMedicalHistory;

    @FXML
    private TableColumn<PatientTM, String> colName;

    @FXML
    private TableColumn<PatientTM, String> colPatientId;

    @FXML
    private TableColumn<PatientTM, String> colPhone;

    @FXML
    private TableColumn<PatientTM, String> colRegistrationDate;

    @FXML
    private Label lblPatientId;

    @FXML
    private TextArea medicalHistoryTxt;

    @FXML
    private TableView<PatientTM> tblCustomer;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private VBox vBox;

    public PatientCrudController() throws Exception {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String customerId = lblPatientId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = patientBOImpl.deletePatient(customerId);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Patient deleted...!").show();
                refreshPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to delete patient...!").show();
            }
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) throws Exception {
        refreshPage();
    }

    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws Exception {
        String id = lblPatientId.getText();
        String name = txtName.getText();
        String medicalHistory = medicalHistoryTxt.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        boolean isValidEmail = ValidateUtill.isValidEmail(email);
        boolean isValidPhone = ValidateUtill.isValidPhone(phone);
        ChangeUtill.changeColours(txtEmail,isValidEmail);
        ChangeUtill.changeColours(txtPhone,isValidPhone);
        if(isValidEmail && isValidPhone) {
            PatientDTO patientDTO=new PatientDTO(id,name,phone,email,medicalHistory, Date.valueOf(LocalDate.now()));
            boolean isSaved = patientBOImpl.savePatient(patientDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient saved...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save patient...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with email field or phone field ...").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String id = lblPatientId.getText();
        String name = txtName.getText();
        String medicalHistory = medicalHistoryTxt.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        boolean isValidEmail = ValidateUtill.isValidEmail(email);
        boolean isValidPhone = ValidateUtill.isValidPhone(phone);
        ChangeUtill.changeColours(txtEmail,isValidEmail);
        ChangeUtill.changeColours(txtPhone,isValidPhone);
        if(isValidEmail && isValidPhone) {
            PatientDTO patientDTO=new PatientDTO(id,name,phone,email,medicalHistory, Date.valueOf(LocalDate.now()));
            boolean isSaved = patientBOImpl.updatePatient(patientDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Patient Updated...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to update patient...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with email field or phone field ...").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        PatientTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblPatientId.setText(selectedItem.getId());
            txtName.setText(selectedItem.getName());
            medicalHistoryTxt.setText(selectedItem.getMedical_history());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getContact_number());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colPatientId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colMedicalHistory.setCellValueFactory(new PropertyValueFactory<>("medical_history"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("contact_number"));
        colRegistrationDate.setCellValueFactory(new PropertyValueFactory<>("registration_date"));

        try {
            refreshPage();
//            String nextCustomerID = customerModel.getNextCustomerID();
            System.out.println(patientBOImpl.getNextPatientId());
            lblPatientId.setText(patientBOImpl.getNextPatientId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }
    private void refreshPage() throws Exception {
        refreshTable();

        String nextCustomerID = patientBOImpl.getNextPatientId();
        lblPatientId.setText(nextCustomerID);

        txtName.setText("");
        medicalHistoryTxt.setText("");
        txtEmail.setText("");
        txtPhone.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable() throws Exception {
        List<PatientDTO> patientDTOS = patientBOImpl.getAllPatient();
        ObservableList<PatientTM> patientTMS = FXCollections.observableArrayList();

        for (PatientDTO patientDTO : patientDTOS) {
            PatientTM patientTM = new PatientTM();
            patientTM.setId(patientDTO.getId());
            patientTM.setEmail(patientDTO.getEmail());
            patientTM.setName(patientDTO.getName());
            patientTM.setContact_number(patientDTO.getContact_number());
            patientTM.setMedical_history(patientDTO.getMedical_history());
            patientTM.setRegistration_date(patientDTO.getRegistration_date());
            patientTMS.add(patientTM);
        }
        tblCustomer.setItems(patientTMS);
    }
}
