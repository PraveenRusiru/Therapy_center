package lk.ijse.therapy_center;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.therapy_center.Config.ConfigurationFactory;
import lk.ijse.therapy_center.Entity.User;
import org.hibernate.Session;

import static javafx.application.Application.launch;

public class Main extends Application {
    public void start(Stage stage) throws Exception {
        stage.setTitle("Therapy Center");
        stage.setResizable(false);
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Reception/Receptionbase.fxml"))));
//        stage.show();
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/LoginHero.fxml"))));
//        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Admins/TherapyCrud/TherapyHero.fxml"))));
        stage.show();
    }
    public static void main(String[] args) {
//        Session session= ConfigurationFactory.getInstance().getSessionFactory();
//        User user=new User("U001","admin","1234","praveenrusiru752@gmail.com");
//        User user1=new User("U002","receptionist","12345678","praveenrusiru752@gmail.com");
//        session.beginTransaction();
//        session.save(user);
//        session.save(user1);
//        session.getTransaction().commit();
//        session.close();
        launch(args);

    }
}
