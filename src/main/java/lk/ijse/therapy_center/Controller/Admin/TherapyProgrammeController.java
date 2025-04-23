package lk.ijse.therapy_center.Controller.Admin;

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
import lk.ijse.therapy_center.BO.Impl.TherapyProgrammeBoImpl;
import lk.ijse.therapy_center.DTO.TM.ProgrammeTM;
import lk.ijse.therapy_center.DTO.TM.TherapistTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;
import lk.ijse.therapy_center.Utill.ChangeUtill;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class TherapyProgrammeController implements Initializable {
   TherapyProgrammeBoImpl therapyProgrammeBoImpl=(TherapyProgrammeBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPY_PROGRAMME);

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnUpdate;


    @FXML
    private TableColumn<ProgrammeTM, String> colName;

    @FXML
    private TableColumn<ProgrammeTM, String> colDescription;

    @FXML
    private TableColumn<ProgrammeTM, String> colDuration;

    @FXML
    private TableColumn<ProgrammeTM, BigDecimal> colFee;

    @FXML
    private TableColumn<ProgrammeTM, String> colProgrammeId;

    @FXML
    private Label lblProgramId;

    @FXML
    private TableView<ProgrammeTM> tblCustomer;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtprogramme;

    @FXML
    private VBox vBox;

    public TherapyProgrammeController() throws Exception {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        String programId = lblProgramId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this programme ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

            boolean isDeleted = therapyProgrammeBoImpl.delete(programId);;

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
        String id = lblProgramId.getText();
        String name = txtprogramme.getText();
        String duration = txtDuration.getText();
        BigDecimal fee =BigDecimal.valueOf(Double.valueOf(txtFee.getText()));
        String description = txtDescription.getText();

        boolean isValidFee = ValidateUtill.isPriceValid(txtFee.getText());
        ChangeUtill.changeColours(txtFee,isValidFee);
        if(isValidFee) {
            Therapy_ProgrammeDTO programmeDTO=new Therapy_ProgrammeDTO();
            programmeDTO.setId(id);
            programmeDTO.setProgramme_name(name);
            programmeDTO.setProgramme_description(description);
            programmeDTO.setFee(fee);
            programmeDTO.setDuration(duration);
            boolean isSaved = therapyProgrammeBoImpl.saveProgramme(programmeDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy programme saved...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save therapy programme...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with fee ...").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String id = lblProgramId.getText();
        String name = txtprogramme.getText();
        String duration = txtDuration.getText();
        BigDecimal fee =BigDecimal.valueOf(Double.valueOf(txtFee.getText()));
        String description = txtDescription.getText();

        boolean isValidFee = ValidateUtill.isPriceValid(txtFee.getText());
        ChangeUtill.changeColours(txtFee,isValidFee);
        if(isValidFee) {
            Therapy_ProgrammeDTO programmeDTO=new Therapy_ProgrammeDTO();
            programmeDTO.setId(id);
            programmeDTO.setProgramme_name(name);
            programmeDTO.setProgramme_description(description);
            programmeDTO.setFee(fee);
            programmeDTO.setDuration(duration);
            boolean isUpdated = therapyProgrammeBoImpl.updateProgramme(programmeDTO);
            if(isUpdated) {
                new Alert(Alert.AlertType.INFORMATION, "Therapy programme updated...!").show();
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to update therapy programme...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Something wrong with fee ...").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        ProgrammeTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            lblProgramId.setText(selectedItem.getId());
            txtprogramme.setText(selectedItem.getProgramme_name());
                txtDuration.setText(selectedItem.getDuration().toString());
                txtFee.setText(selectedItem.getFee().toString());
                txtDescription.setText(selectedItem.getProgramme_description());
            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colProgrammeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("programme_name"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("programme_description"));

        try {
            refreshPage();
            String nextProgrammeId = therapyProgrammeBoImpl.getNextProgrammeId();
            System.out.println(nextProgrammeId);
            lblProgramId.setText(nextProgrammeId);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    private void refreshPage() throws Exception {
        refreshTable();

        String nextProgramID = therapyProgrammeBoImpl.getNextProgrammeId();
        lblProgramId.setText(nextProgramID);

        txtDescription.setText("");
        txtDuration.setText("");
        txtFee.setText("");
        txtprogramme.setText("");

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable() throws Exception {
        List<Therapy_ProgrammeDTO> programmeDTOS = therapyProgrammeBoImpl.getAllProgramme();
        ObservableList<ProgrammeTM> programmeTMS = FXCollections.observableArrayList();

        for (Therapy_ProgrammeDTO therapyProgrammeDTO : programmeDTOS) {
            ProgrammeTM programmeTM = new ProgrammeTM(
                    therapyProgrammeDTO.getId(),
                    therapyProgrammeDTO.getProgramme_name(),
                    therapyProgrammeDTO.getProgramme_description(),
                    therapyProgrammeDTO.getDuration(),
                    therapyProgrammeDTO.getFee()
            );
            programmeTMS.add(programmeTM);
        }
        tblCustomer.setItems(programmeTMS);
    }
}
