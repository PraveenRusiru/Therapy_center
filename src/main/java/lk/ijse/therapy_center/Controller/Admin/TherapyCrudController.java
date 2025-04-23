package lk.ijse.therapy_center.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.BO.Impl.TherapyProgrammeBoImpl;
import lk.ijse.therapy_center.BO.TherapistBO;
import lk.ijse.therapy_center.DTO.TM.TherapistTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;
import lk.ijse.therapy_center.Utill.ChangeUtill;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyCrudController implements Initializable {
    TherapistBO therapistBOImpl= (TherapistBO) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPIST);
    TherapyProgrammeBoImpl therapyProgrammeBoImpl=(TherapyProgrammeBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPY_PROGRAMME);
    public TherapyCrudController() throws Exception {}

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<TherapistTM, String> colEmail;

    @FXML
    private TableColumn<TherapistTM, String> colName;

    @FXML
    private TableColumn<TherapistTM, String> colPhone;

    @FXML
    private TableColumn<TherapistTM, String> colSpecilisation;

    @FXML
    private TableColumn<TherapistTM, String> colTherapistId;

    @FXML
    private Label lblCustomerId;

    @FXML
    private TableView<TherapistTM> tblCustomer;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

    @FXML
    private TextField txtSpecilisation;

    @FXML
    private ComboBox<String> comboboxprogramme;

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String customerId = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapist ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = therapistBOImpl.deleteTherapist(customerId);

            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted...!").show();
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
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String specilisation = txtSpecilisation.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        boolean isValidEmail = ValidateUtill.isValidEmail(email);
        boolean isValidPhone = ValidateUtill.isValidPhone(phone);
        ChangeUtill.changeColours(txtEmail,isValidEmail);
        ChangeUtill.changeColours(txtPhone,isValidPhone);
        if(isValidEmail && isValidPhone) {
            TherapistDTO therapistDTO = new TherapistDTO();
            therapistDTO.setTherapist_id(id);
            therapistDTO.setName(name);
            therapistDTO.setEmail(email);
            therapistDTO.setPhone(phone);
            therapistDTO.setSpecilization(specilisation);

            boolean isSaved = therapistBOImpl.saveTherapist(therapistDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist saved...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save therapist...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with email field or phone field ...").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String id = lblCustomerId.getText();
        String name = txtName.getText();
        String specilisation = txtSpecilisation.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();

        boolean isValidEmail = ValidateUtill.isValidEmail(email);
        boolean isValidPhone = ValidateUtill.isValidPhone(phone);
        ChangeUtill.changeColours(txtEmail,isValidEmail);
        ChangeUtill.changeColours(txtPhone,isValidPhone);
        if(isValidEmail && isValidPhone){
            TherapistDTO therapistDTO = new TherapistDTO();
            therapistDTO.setTherapist_id(id);
            therapistDTO.setName(name);
            therapistDTO.setEmail(email);
            therapistDTO.setPhone(phone);
            therapistDTO.setSpecilization(specilisation);

            boolean isSaved = therapistBOImpl.updateTherapist(therapistDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist updated...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save therapist...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with email field or phone field ...").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        TherapistTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblCustomerId.setText(selectedItem.getTherapist_id());
            txtName.setText(selectedItem.getName());
            txtSpecilisation.setText(selectedItem.getSpecilization());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colSpecilisation.setCellValueFactory(new PropertyValueFactory<>("specilization"));

        ObservableList<String> programmeList= FXCollections.observableArrayList();
        try {
            List<Therapy_ProgrammeDTO> programmeDTOS = therapyProgrammeBoImpl.getAllProgramme();
            for (Therapy_ProgrammeDTO dto:programmeDTOS){
                programmeList.add(dto.getProgramme_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


        comboboxprogramme.setItems(programmeList);
        try {
            refreshPage();
//            String nextCustomerID = customerModel.getNextCustomerID();
            System.out.println(therapistBOImpl.getNextTherapistId());
            lblCustomerId.setText(therapistBOImpl.getNextTherapistId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void refreshPage() throws Exception {
        refreshTable();

        String nextCustomerID = therapistBOImpl.getNextTherapistId();
        lblCustomerId.setText(nextCustomerID);
        comboboxprogramme.getSelectionModel().clearSelection();
        txtName.setText("");
        txtSpecilisation.setText("");
        txtEmail.setText("");
        txtPhone.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable() throws Exception {
        List<TherapistDTO> TherapistDTOS = therapistBOImpl.getAllTherapist();
        ObservableList<TherapistTM> therapistTMS = FXCollections.observableArrayList();

        for (TherapistDTO therapistDTO : TherapistDTOS) {
            TherapistTM therapistTM = new TherapistTM();
            therapistTM.setTherapist_id(therapistDTO.getTherapist_id());
            therapistTM.setName(therapistDTO.getName());
            therapistTM.setEmail(therapistDTO.getEmail());
            therapistTM.setPhone(therapistDTO.getPhone());
            therapistTM.setSpecilization(therapistDTO.getSpecilization());
            therapistTMS.add(therapistTM);
        }
        tblCustomer.setItems(therapistTMS);
    }
}
