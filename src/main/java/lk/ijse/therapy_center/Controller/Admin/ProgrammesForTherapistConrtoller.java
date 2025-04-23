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
import lk.ijse.therapy_center.BO.Impl.TherapySessionBOImpl;
import lk.ijse.therapy_center.BO.Impl.TherapyProgrammeBoImpl;
//import lk.ijse.therapy_center.BO.TherapistForProgrammeBO;
import lk.ijse.therapy_center.DTO.AvailabiltyDTO;
import lk.ijse.therapy_center.DTO.TM.AvailabiltyTM;
//import lk.ijse.therapy_center.DTO.TM.Therapist_for_programmeTM;
import lk.ijse.therapy_center.DTO.TM.TherapySessionTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
//import lk.ijse.therapy_center.DTO.Therapist_for_programmeDTO;
import lk.ijse.therapy_center.DTO.TherapySessionDTO;
import lk.ijse.therapy_center.DTO.Therapy_ProgrammeDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProgrammesForTherapistConrtoller implements Initializable {
    List<TherapistDTO> therapistDTOS=new ArrayList<>();
    AvailabiltyBOImpl availabiltyBOImpl=(AvailabiltyBOImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.AVAILABILITY);
    String searchId=" ";
    String rowId="-1";
    TherapistBoImpl therapistBoImpl= (TherapistBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPIST);
    TherapyProgrammeBoImpl therapyProgrammeBoImpl=(TherapyProgrammeBoImpl) BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPY_PROGRAMME);
    TherapySessionBOImpl therapySessionBOImpl =(TherapySessionBOImpl)BOFactory.getInstance().getBOType(BOFactory.BOType.THERAPIST_FOR_PROGRAMME);
    @FXML
    private Label TherapistId;

    @FXML
    private Button btnCheckReset;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private ComboBox<String> cbCheckEndHH;

    @FXML
    private ComboBox<String> cbCheckEndMM;

    @FXML
    private ComboBox<String> cbCheckStartHH;

    @FXML
    private ComboBox<String> cbCheckStartMM;

    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckEndTime;

    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckStart;

    @FXML
    private TableColumn<AvailabiltyTM, String> colCheckTherapistId;

    @FXML
    private TableColumn<TherapySessionTM, String> colDay;

    @FXML
    private TableColumn<TherapySessionTM, String> colProgramme;

    @FXML
    private TableColumn<TherapySessionTM, String> colTIme;

    @FXML
    private TableColumn<TherapySessionTM, String> colEndTIme;

    @FXML
    private TableColumn<TherapySessionTM, String> colTherapistId;

    @FXML
    private TableColumn<TherapySessionTM, Integer> colId;

    @FXML
    private ComboBox<String> comboboxCheckDay;

    @FXML
    private ComboBox<String> comboboxTherapistId;

    @FXML
    private ComboBox<String> comboboxprogramme;

    @FXML
    private TableView<AvailabiltyTM> tblAvailability;

    @FXML
    private TableView<TherapySessionTM> tblTherapistForProgramme;


    @FXML
    private ComboBox<String> comboxAvailability;

    @FXML
    private VBox vBox;


    @FXML
    private Label sessionId;

    public ProgrammesForTherapistConrtoller() throws Exception {
    }

    @FXML
    void btnCheckResetOnAction(ActionEvent event) {
        tblAvailability.getItems().clear();
        btnSearch.setVisible(true);
        btnCheckReset.setVisible(false);
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this therapist ?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.get() == ButtonType.YES) {

//            boolean isDeleted = therapistBOImpl.deleteTherapist(customerId);
            boolean isDeleted= therapySessionBOImpl.deleteTherapistForProgramme(String.valueOf(rowId));
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
    void btnSaveCustomerOnAction(ActionEvent event) throws Exception {
        String therapist_id=comboboxTherapistId.getValue();
        String therapy_programme_id=comboboxprogramme.getValue().split(" ")[0];
        System.out.println("therapy_programme_id "+comboboxprogramme.getValue().split(" ")[0]);
        System.out.println("Date "+comboxAvailability.getValue().split("\t")[0]);
        System.out.println("Start Time "+comboxAvailability.getValue().split("\t")[1]);
        String date= comboxAvailability.getValue().split("\t")[0];
        String startTime=comboxAvailability.getValue().split("\t")[1];
        String endTime=comboxAvailability.getValue().split("\t")[2];
        System.out.println("end time :"+endTime);

        TherapySessionDTO therapistForProgrammeDTO=new TherapySessionDTO();
        therapistForProgrammeDTO.setTherapist_id(therapist_id);
        therapistForProgrammeDTO.setTherapy_programme_id(therapy_programme_id);
        therapistForProgrammeDTO.setDate(date);
        therapistForProgrammeDTO.setStartTime(startTime);
        therapistForProgrammeDTO.setEndTime(endTime);
        therapistForProgrammeDTO.setId(sessionId.getText());
        boolean isSaved=false;
        boolean isDeleted=false;
        try {
            isSaved= therapySessionBOImpl.saveTherapistForProgramme(therapistForProgrammeDTO);
            AvailabiltyDTO availabiltyDTO = new AvailabiltyDTO();
            availabiltyDTO.setTherapist_id(therapist_id);
            availabiltyDTO.setStartTime(startTime);
            availabiltyDTO.setAvailableDays(date);
            int id=availabiltyBOImpl.getId(availabiltyDTO);
            isDeleted=availabiltyBOImpl.deleteAvaialbility(String.valueOf(id));
            System.out.println("is Saved :"+isSaved);
            System.out.println("id :"+id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(isSaved && isDeleted) {
            loadAvailabilities(searchId);
            new Alert(Alert.AlertType.INFORMATION, "Programmes for Therapist saved...!").show();
            refreshTable(therapist_id);
            refreshPage();
            rowId="-1";
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to save Programmes for Therapist...!").show();
        }

    }
    private void refreshPage() throws Exception {

//        comboboxTherapistId.getSelectionModel().clearSelection();
        comboboxprogramme.getSelectionModel().clearSelection();
        comboxAvailability.getSelectionModel().clearSelection();

        btnSave.setDisable(false);

        try {
            sessionId.setText(therapySessionBOImpl.getNextTherapistForProgrammeId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }

    private void refreshTable(String id) throws Exception {
           List<TherapySessionDTO> therapistForProgrammeDTOS= therapySessionBOImpl.getAllById(id);
        ObservableList<TherapySessionTM> therapistForProgrammeTMS=FXCollections.observableArrayList();
        for (TherapySessionDTO therapistForProgrammeDTO : therapistForProgrammeDTOS) {
            TherapySessionTM tm=new TherapySessionTM();
            tm.setTherapist_id(therapistForProgrammeDTO.getTherapist_id());
            tm.setDate(therapistForProgrammeDTO.getDate());
//            System.out.println(therapistForProgrammeDTO.getStartTime());
            tm.setStartTime(therapistForProgrammeDTO.getStartTime());
            System.out.println(tm.getStartTime());
            tm.setTherapy_programme_id(therapistForProgrammeDTO.getTherapy_programme_id());
            tm.setId(therapistForProgrammeDTO.getId());
            tm.setEndTime(therapistForProgrammeDTO.getEndTime());
            therapistForProgrammeTMS.add(tm);

        }
        tblTherapistForProgramme.setItems(therapistForProgrammeTMS);
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
    void btnUpdateOnAction(ActionEvent event) throws Exception {
        String therapist_id=comboboxTherapistId.getValue();
        String therapy_programme_id=comboboxprogramme.getValue().split(" ")[0];
        System.out.println("therapy_programme_id "+comboboxprogramme.getValue().split(" ")[0]);
        System.out.println("Date "+comboxAvailability.getValue().split("\t")[0]);
        System.out.println("Start Time "+comboxAvailability.getValue().split("\t")[1]);
        String date= comboxAvailability.getValue().split("\t")[0];
        String startTime=comboxAvailability.getValue().split("\t")[1];
        String endTime=comboxAvailability.getValue().split("\t")[2];

        System.out.println("end time :"+endTime);

        TherapySessionDTO therapistForProgrammeDTO=new TherapySessionDTO();
        therapistForProgrammeDTO.setTherapist_id(therapist_id);
        therapistForProgrammeDTO.setTherapy_programme_id(therapy_programme_id);
        therapistForProgrammeDTO.setDate(date);
        therapistForProgrammeDTO.setStartTime(startTime);
        therapistForProgrammeDTO.setEndTime(endTime);
        therapistForProgrammeDTO.setId(rowId);
        boolean isSaved=false;
        try {
            isSaved= therapySessionBOImpl.updateTherapistForProgramme(therapistForProgrammeDTO);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        if(isSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Programmes for Therapist updated...!").show();
            refreshTable(therapist_id);
            refreshPage();
            rowId="-1";
        }else{
            new Alert(Alert.AlertType.ERROR, "Fail to update Programmes for Therapist...!").show();
        }

    }

    @FXML
    void onClickTable(MouseEvent event) {
        TherapySessionTM selectedItem = tblTherapistForProgramme.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            comboboxTherapistId.setValue(selectedItem.getTherapist_id());
            comboxAvailability.setValue(selectedItem.getDate() +"\t"+selectedItem.getStartTime()+"\t"+selectedItem.getEndTime());

//            String[] startTime = selectedItem.getStartTime().split(":");
//            cbStartHH.setValue(startTime[0]);
//            cbStartMM.setValue(startTime[1]);
//            String[] endTime = selectedItem.getEndTime().split(":");
//            cbEndHH.setValue(endTime[0]);
//            cbEndMM.setValue(endTime[1]);

            rowId = selectedItem.getId();

            btnSave.setDisable(true);

            btnDelete.setDisable(false);
            btnUpdate.setDisable(false);
        }
    }
        @FXML
        void selectingTherapist (ActionEvent event) throws Exception {
            searchId = comboboxTherapistId.getSelectionModel().getSelectedItem();
            TherapistDTO result = therapistDTOS.stream()
                    .filter(dto -> dto.getTherapist_id().equals(searchId))
                    .findFirst()
                    .orElse(null);

            TherapistId.setText("\t\t\tTherapist :\t" + result.getName());
        System.out.println("Therapist id :"+searchId);
//            List<AvailabiltyDTO> availabiltyDTOS = availabiltyBOImpl.getAllAvaialbilitiesById(searchId);
//            ObservableList<String> availabaleList = FXCollections.observableArrayList();
//            for (AvailabiltyDTO availabiltyDTO1 : availabiltyDTOS) {
//                availabaleList.add(availabiltyDTO1.getAvailableDays() + "\t" + availabiltyDTO1.getStartTime() + "\t" + availabiltyDTO1.getEndTime());
//            }
//            comboxAvailability.setItems(availabaleList);
            loadAvailabilities(searchId);
            refreshTable(searchId);


    }

    public void loadAvailabilities(String id) throws Exception {
        List<AvailabiltyDTO> availabiltyDTOS = availabiltyBOImpl.getAllAvaialbilitiesById(id);
        ObservableList<String> availabaleList = FXCollections.observableArrayList();
        for (AvailabiltyDTO availabiltyDTO1 : availabiltyDTOS) {
            availabaleList.add(availabiltyDTO1.getAvailableDays() + "\t" + availabiltyDTO1.getStartTime() + "\t" + availabiltyDTO1.getEndTime());
        }
        comboxAvailability.setItems(availabaleList);
    }
//    @FXML
//    void selectAvailabilty(ActionEvent event) throws Exception {
//        String therapist_id=comboboxTherapistId.getValue();
////        String therapy_programme_id=comboboxprogramme.getValue().split(" ")[0];
////        System.out.println("therapy_programme_id "+comboboxprogramme.getValue().split(" ")[0]);
//        System.out.println("Date "+comboxAvailability.getValue().split("\t")[0]);
//        System.out.println("Start Time "+comboxAvailability.getValue().split("\t")[1]);
//        String date= comboxAvailability.getValue().split("\t")[0];
//        String startTime=comboxAvailability.getValue().split("\t")[1];
//        String endTime=comboxAvailability.getValue().split("\t")[2];
//        System.out.println("end time :"+endTime);
//
//        AvailabiltyDTO availabiltyDTO = new AvailabiltyDTO();
//        availabiltyDTO.setTherapist_id(therapist_id);
//        availabiltyDTO.setStartTime(startTime);
//        availabiltyDTO.setAvailableDays(date);
//        System.out.println("Id :"+availabiltyBOImpl.getId(availabiltyDTO));
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colCheckTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        colCheckStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colCheckEndTime.setCellValueFactory(new PropertyValueFactory<>("endTime"));

        colDay.setCellValueFactory(new PropertyValueFactory<>("date"));
        colProgramme.setCellValueFactory(new PropertyValueFactory<>("therapy_programme_id"));
        colTIme.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colTherapistId.setCellValueFactory(new PropertyValueFactory<>("therapist_id"));
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEndTIme.setCellValueFactory(new PropertyValueFactory<>("endTime"));

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

        ObservableList<String> dayList= FXCollections.observableArrayList("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
        comboboxCheckDay.setItems(dayList);
        try {
            sessionId.setText(therapySessionBOImpl.getNextTherapistForProgrammeId());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        ObservableList<String> hours= FXCollections.observableArrayList("01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","00");
        cbCheckEndHH.setItems(hours);
        cbCheckStartHH.setItems(hours);
        ObservableList<String> minutes= FXCollections.observableArrayList("00","05","10","15","20","25","30","35","40","45","50","55");
        cbCheckEndMM.setItems(minutes);
        cbCheckStartMM.setItems(minutes);
        btnCheckReset.setVisible(false);

        ObservableList<String> programmeList= FXCollections.observableArrayList();
        try {
            List<Therapy_ProgrammeDTO> programmeDTOS = therapyProgrammeBoImpl.getAllProgramme();
            for (Therapy_ProgrammeDTO dto:programmeDTOS){
                programmeList.add(dto.getId()+" "+dto.getProgramme_name());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        comboboxprogramme.setItems(programmeList);
    }
}
