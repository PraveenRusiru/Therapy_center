package lk.ijse.therapy_center.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lk.ijse.therapy_center.Utill.LoadFileUtill;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginHeroController implements Initializable {


    @FXML
    private AnchorPane loginPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LoadFileUtill loadFileUtill = new LoadFileUtill();
        loadFileUtill.loadFXML(loginPane,"/View/LoginPane.fxml");
    }

}
