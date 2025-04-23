package lk.ijse.therapy_center.Controller.Receptionist;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import lk.ijse.therapy_center.Utill.LoadFileUtill;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptionistBaseController implements Initializable {

LoadFileUtill loadFileUtill = new LoadFileUtill();
    @FXML
    private ImageView patientTab;

    @FXML
    private Pane paymentTab;


    @FXML
    private ImageView appointmentTab;

    @FXML
    private ImageView therapyProgrammeTab;

    @FXML
    private VBox vBox;


    @FXML
    void naviagteToAppointmentTab(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Reception/Appointment/AppointmentCrud.fxml");
    }

    @FXML
    void navigateToPatientTab(MouseEvent event) {

    }

    @FXML
    void navigateTopaymentTab(MouseEvent event) {
        loadFileUtill.loadFXML(vBox,"/View/Reception/Payment/Payment.fxml");
    }

    @FXML
    void navigateTotherapyProgrammeTab(MouseEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadFileUtill.loadFXML(vBox,"/View/Reception/Patient/PatientCrud.fxml");
    }
}
