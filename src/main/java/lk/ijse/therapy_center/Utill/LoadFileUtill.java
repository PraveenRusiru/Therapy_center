package lk.ijse.therapy_center.Utill;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class LoadFileUtill {
    public  void loadFXML(Pane pane, String url){
        try {
            pane.getChildren().clear();
            pane.getChildren().add(FXMLLoader.load(getClass().getResource(url)));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
