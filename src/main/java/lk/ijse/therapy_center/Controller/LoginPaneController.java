package lk.ijse.therapy_center.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import lk.ijse.therapy_center.BO.BOFactory;
import lk.ijse.therapy_center.BO.UserBO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginPaneController implements Initializable {

    UserBO userBO=(UserBO) BOFactory.getInstance().getBOType(BOFactory.BOType.USER);
    boolean isPasswordField=false;
    String password="";
    @FXML
    private Pane LoginPane;

    @FXML
    private Label forgetPasswordLbl;

    @FXML
    private ImageView hideImage;

    @FXML
    private JFXButton loginBtn;

    @FXML
    private Circle hideImageCircle;

    @FXML
    private Circle showImageCircle;

    @FXML
    private JFXPasswordField passwordTxt;

    @FXML
    private ImageView showImage;

    @FXML
    private TextField showTxt;

    @FXML
    private Line showTxtLine;

    @FXML
    private JFXTextField userNameTxt;

    public LoginPaneController() throws Exception {
    }

    @FXML
    void hidePassword(MouseEvent event) {
        isPasswordField=false;
        password=showTxt.getText();
        showTxt.setVisible(false);
        passwordTxt.setVisible(true);
        hideImage.setVisible(true);
        hideImageCircle.setVisible(true);
        showImage.setVisible(false);
        showTxt.setVisible(false);
        showImageCircle.setVisible(false);
        passwordTxt.setText(password);
    }

    @FXML
    void navigateForgetPasswordPane(MouseEvent event) {

    }

    @FXML
    void navigateToDashboard(ActionEvent event) throws SQLException {
        if(!isPasswordField){
            password=passwordTxt.getText();
        }else{
            password=showTxt.getText();
        }
        System.out.println("password "+ password);
        if(userNameTxt.getText().equals("admin")){
            if(userBO.isUserValide("U001",password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Admin Login Successful !!");

                try{
                    Parent load= FXMLLoader.load(getClass().getResource("/View/Admins/TherapyCrud/TherapyHero.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Admin Dashboard");
                    Scene scene = new Scene(load);
                    stage.setScene(scene);
                    stage.show();
                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    currentStage.close();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Username or Password is incorrect").show();
            }
        }else if(userNameTxt.getText().equals("receptionist")){
            if(userBO.isUserValide("U002",password)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Receptionist Login Successful !!");

                try{
                    Parent load= FXMLLoader.load(getClass().getResource("/View/Reception/Receptionbase.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Receptionist Dashboard");
                    Scene scene = new Scene(load);
                    stage.setScene(scene);
                    stage.show();
                    Stage currentStage = (Stage) loginBtn.getScene().getWindow();
                    currentStage.close();
                }catch(Exception e){
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Username or Password is incorrect").show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR,"Enter a valid username and password !").show();
        }

//        boolean isValidate= userBO.isUserValide();
    }

    @FXML
    void navigateToNextTxt(ActionEvent event) {

    }

    @FXML
    void passwordTxtKeyPressed(KeyEvent event) {

    }

    @FXML
    void showPassword(MouseEvent event) {
        isPasswordField=true;
        password=passwordTxt.getText();
        passwordTxt.setVisible(false);
        hideImage.setVisible(false);
        hideImageCircle.setVisible(false);
        showImage.setVisible(true);
        showTxtLine.setVisible(true);
        showTxt.setVisible(true);
        showImageCircle.setVisible(true);
        showTxt.setText(password);
    }

    @FXML
    void showTxtKeyPressed(KeyEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showImage.setVisible(false);
        showTxt.setVisible(false);
        showImageCircle.setVisible(false);
        showTxtLine.setVisible(false);
    }
}
