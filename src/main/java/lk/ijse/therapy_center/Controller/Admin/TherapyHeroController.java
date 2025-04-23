package lk.ijse.therapy_center.Controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.BO.TherapistBO;
import lk.ijse.therapy_center.DTO.TM.TherapistTM;
import lk.ijse.therapy_center.DTO.TherapistDTO;
import lk.ijse.therapy_center.Utill.ChangeUtill;
import lk.ijse.therapy_center.Utill.LoadFileUtill;
import lk.ijse.therapy_center.Utill.ValidateUtill;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TherapyHeroController implements Initializable {
    LoadFileUtill loadFileUtill = new LoadFileUtill();

    @FXML
    private VBox vBox;

    @FXML
    private ImageView availabiltyTAb;

    @FXML
    private ImageView therapistTab;

    @FXML
    private ImageView therapyProgrammeTab;


    @FXML
    private Pane programmesForTherapistTab;

    @FXML
    void naviagteToAvailabiltyTAb(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Admins/Availabilty/TherapyAvailabilty.fxml");
    }

    @FXML
    void navigateToTherapistTab(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Admins/TherapyCrud/TherapyCrud.fxml");
    }

    @FXML
    void navigateTotherapyProgrammeTab(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Admins/TherapyProgramme/TherapyProgramme.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFileUtill.loadFXML(vBox,"/View/Admins/TherapyCrud/TherapyCrud.fxml");
    }
    @FXML
    void navigateToprogrammesForTherapist(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Admins/TherapyProgramme/ProgrammesForTherapist.fxml");
    }
}
