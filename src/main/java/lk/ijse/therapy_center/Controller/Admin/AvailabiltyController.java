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
import lk.ijse.therapy_center.BO.Impl.AvailabiltyBOImpl;
import lk.ijse.therapy_center.BO.Impl.TherapistBoImpl;
import lk.ijse.therapy_center.DTO.AvailabiltyDTO;
import lk.ijse.therapy_center.DTO.TM.AvailabiltyTM;
import lk.ijse.therapy_center.DTO.TM.TherapistTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;
import lk.ijse.therapy_center.Utill.ChangeUtill;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.net.URL;
//import java.sql.Time;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AvailabiltyController implements Initializable {
    TherapistBoImpl therapistBoImpl= (TherapistBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPIST);
    List<TherapistDTO> therapistDTOS=new ArrayList<>();
    AvailabiltyBOImpl availabiltyBOImpl=(AvailabiltyBOImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.AVAILABILITY);
    String searchId=" ";
    int rowId=-1;
    @FXML
    private Button btnDelete;

    @FXML
    private Label TherapistId;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCheckReset;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<AvailabiltyTM, String> colDay;

    @FXML
    private TableColumn<AvailabiltyTM, String> colEnd;

    @FXML
    private TableColumn<AvailabiltyTM, String> colStart;

    @FXML
    private TableColumn<AvailabiltyTM, String> cold;

    @FXML
    private TableColumn<AvailabiltyTM, String> colTherapistId;


    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckEndTime;

    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckStart;

    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckTherapistId;

    @FXML
    private ComboBox<String> comboboxTherapistId;

    @FXML
    private ComboBox<String> comboboxDay;

    @FXML
    private ComboBox<String> cbEndHH;

    @FXML
    private ComboBox<String> cbEndMM;

    @FXML
    private ComboBox<String> cbStartHH;

    @FXML
    private ComboBox<String> cbStartMM;

    @FXML
    private ComboBox<String> cbCheckEndHH;

    @FXML
    private ComboBox<String> cbCheckEndMM;

    @FXML
    private ComboBox<String> cbCheckStartHH;

    @FXML
    private ComboBox<String> cbCheckStartMM;

    @FXML
    private ComboBox<String> comboboxCheckDay;
    @FXML
    private TableView<AvailabiltyTM> tblCustomer;

    @FXML
    private TableView<AvailabiltyTM> tblAvailability;


    @FXML
    private Button btnSearch;

    @FXML
    private VBox vBox;

    public AvailabiltyController() throws Exception {
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
//        String customerId = lblCustomerId.getText();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapist ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

//            boolean isDeleted = therapistBOImpl.deleteTherapist(customerId);
            boolean isDeleted= availabiltyBOImpl.deleteAvaialbility(String.valueOf(rowId));
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Therapist deleted...!").show();
                refreshTable(searchId);
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
    void btnCheckResetOnAction(ActionEvent event) {
            tblAvailability.getItems().clear();
            btnSearch.setVisible(true);
            btnCheckReset.setVisible(false);
    }
    @FXML
    void btnSaveCustomerOnAction(ActionEvent event) throws Exception {
        String therapist_id=comboboxTherapistId.getSelectionModel().getSelectedItem();
        String day=comboboxDay.getSelectionModel().getSelectedItem();
        String startTime= cbStartHH.getSelectionModel().getSelectedItem()+":"+cbStartMM.getSelectionModel().getSelectedItem();
        String endTime= cbEndHH.getSelectionModel().getSelectedItem()+":"+cbEndMM.getSelectionModel().getSelectedItem();
        System.out.println("Day :"+day);
        System.out.println("StartTime :"+startTime);
        System.out.println("EndTime :"+endTime);
        boolean isStartTime=ValidateUtill.isValidTime(startTime);
        boolean isEndTime=ValidateUtill.isValidTime(endTime);

        if(isStartTime && isEndTime && day!=null && therapist_id!=null  ) {
            AvailabiltyDTO availabiltyDTO=new AvailabiltyDTO();
            availabiltyDTO.setTherapist_id(therapist_id);
            availabiltyDTO.setAvailableDays(day);
            availabiltyDTO.setStartTime(startTime);
            availabiltyDTO.setEndTime(endTime);
            boolean isSaved=false;
            try {
                 isSaved = availabiltyBOImpl.saveAvaialbility(availabiltyDTO);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Availability saved...!").show();
                refreshTable(searchId);
                refreshPage();
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to save availability...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Make sure every item selected  ! ...").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String therapist_id=comboboxTherapistId.getSelectionModel().getSelectedItem();
        String day=comboboxDay.getSelectionModel().getSelectedItem();
        String startTime= cbStartHH.getSelectionModel().getSelectedItem()+":"+cbStartMM.getSelectionModel().getSelectedItem();
        String endTime= cbEndHH.getSelectionModel().getSelectedItem()+":"+cbEndMM.getSelectionModel().getSelectedItem();

        boolean isStartTime=ValidateUtill.isValidTime(startTime);
        boolean isEndTime=ValidateUtill.isValidTime(endTime);

        System.out.println("StartTime :"+startTime);
        System.out.println("EndTime :"+endTime);
        if(isStartTime && isEndTime && day!=null && therapist_id!=null) {
            AvailabiltyDTO availabiltyDTO=new AvailabiltyDTO();
            availabiltyDTO.setTherapist_id(therapist_id);
            availabiltyDTO.setAvailableDays(day);
            availabiltyDTO.setStartTime(startTime);
            availabiltyDTO.setEndTime(endTime);
            availabiltyDTO.setId(rowId);
            boolean isSaved = availabiltyBOImpl.updateAvaialbility(availabiltyDTO);
            if(isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Availability updated...!").show();
                refreshTable(searchId);
                refreshPage();
                rowId=-1;
            }else{
                new Alert(Alert.AlertType.ERROR, "Fail to update availability...!").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Make sure every item selected ! ...").show();
        }
    }

    @FXML
    void onClickTable(MouseEvent event) {
        AvailabiltyTM selectedItem=tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            comboboxTherapistId.setValue(selectedItem.getTherapist_id());
            comboboxDay.setValue(selectedItem.getAvailableDays());

            String[] startTime=selectedItem.getStartTime().split(":");
            cbStartHH.setValue(startTime[0]);
            cbStartMM.setValue(startTime[1]);
            String[] endTime=selectedItem.getEndTime().split(":");
            cbEndHH.setValue(endTime[0]);
            cbEndMM.setValue(endTime[1]);

            rowId=selectedItem.getId();

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) throws Exception {
        String day=comboboxCheckDay.getSelectionModel().getSelectedItem();
        String startTime= cbCheckStartHH.getSelectionModel().getSelectedItem()+":"+cbCheckStartMM.getSelectionModel().getSelectedItem();
        String endTime= cbCheckEndHH.getSelectionModel().getSelectedItem()+":"+cbCheckEndMM.getSelectionModel().getSelectedItem();
        btnCheckReset.setVisible(true);
        btnSearch.setVisible(false);

        AvailabiltyDTO availabiltyDTO=new AvailabiltyDTO();
        availabiltyDTO.setAvailableDays(day);
        availabiltyDTO.setStartTime(startTime);
        availabiltyDTO.setEndTime(endTime);

        List<AvailabiltyDTO> availabiltyDTOS = availabiltyBOImpl.checkAvailability(availabiltyDTO);
        ObservableList<AvailabiltyTM> availabilableTMS = FXCollections.observableArrayList();
        for (AvailabiltyDTO availabiltyDTO1 : availabiltyDTOS) {
            AvailabiltyTM availabiltyTM = new AvailabiltyTM();
            availabiltyTM.setTherapist_id(availabiltyDTO1.getTherapist_id());
            availabiltyTM.setStartTime(availabiltyDTO1.getStartTime());
            availabiltyTM.setEndTime(availabiltyDTO1.getEndTime());
            availabilableTMS.add(availabiltyTM);

        }
        tblAvailability.setItems(availabilableTMS);
    }

    @FXML
    void selectingTherapist(ActionEvent event) throws Exception {
         searchId = comboboxTherapistId.getSelectionModel().getSelectedItem();
        TherapistDTO result = therapistDTOS.stream()
                .filter(dto -> dto.getTherapist_id().equals(searchId))
                .findFirst()
                .orElse(null);

        TherapistId.setText("\t\t\tTherapist :"+result.getName());
        System.out.println("Therapist id :"+searchId);
        refreshTable(searchId);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("availableDays"));
        colStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        cold.setCellValueFactory(new PropertyValueFactory<>("id"));

        colCheckTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        colCheckStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colCheckEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        ObservableList<String> therapistList= FXCollections.observableArrayList();
        try {
             therapistDTOS = therapistBoImpl.getAllTherapist();
            for (TherapistDTO dto:therapistDTOS){
                therapistList.add(dto.getTherapist_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

            comboboxTherapistId.setItems(therapistList);

        try {
            refreshPage();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ObservableList<String> dayList= FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        comboboxDay.setItems(dayList);
        comboboxCheckDay.setItems(dayList);


        ObservableList<String> hours= FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","00");
        cbCheckEndHH.setItems(hours);
        cbCheckStartHH.setItems(hours);
        cbStartHH.setItems(hours);
        cbEndHH.setItems(hours);
        ObservableList<String> minutes= FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");
        cbEndMM.setItems(minutes);
        cbStartMM.setItems(minutes);
        cbCheckEndMM.setItems(minutes);
        cbCheckStartMM.setItems(minutes);
        btnCheckReset.setVisible(false);
    }
    private void refreshPage() throws Exception {

//        comboboxTherapistId.getSelectionModel().clearSelection();
        comboboxDay.getSelectionModel().clearSelection();
        cbStartMM.getSelectionModel().clearSelection();
        cbEndMM.getSelectionModel().clearSelection();
        cbStartHH.getSelectionModel().clearSelection();
        cbEndHH.getSelectionModel().clearSelection();

        btnSave.setDisable(false);

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable(String searchId) throws Exception {
        List<AvailabiltyDTO> availabiltyDTOS =availabiltyBOImpl.getAllAvaialbilitiesById(searchId);

        ObservableList<AvailabiltyTM> availabilableTMS = FXCollections.observableArrayList();
        for (AvailabiltyDTO availabiltyDTO : availabiltyDTOS) {
            AvailabiltyTM availabiltyTM = new AvailabiltyTM();
            availabiltyTM.setId(availabiltyDTO.getId());
            availabiltyTM.setTherapist_id(availabiltyDTO.getTherapist_id());
            availabiltyTM.setAvailableDays(availabiltyDTO.getAvailableDays());
            availabiltyTM.setStartTime(availabiltyDTO.getStartTime());
            availabiltyTM.setEndTime(availabiltyDTO.getEndTime());
            availabilableTMS.add(availabiltyTM);
        }
        tblCustomer.setItems(availabilableTMS);
    }
}
