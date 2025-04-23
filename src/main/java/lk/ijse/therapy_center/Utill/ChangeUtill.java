package lk.ijse.therapy_center.Utill;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ChangeUtill {
    public static void changeColours(TextField txt, boolean isValid) {
        txt.setStyle(txt.getStyle()+";-fx-border-color: #7367F0;");
        if(!isValid){
            txt.setStyle(txt.getStyle()+";-fx-border-color: red;");
        }
}
}
