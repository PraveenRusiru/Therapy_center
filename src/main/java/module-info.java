module lk.ijse.therapy_center {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires javafx.base;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.desktop;
    requires jbcrypt;
//    requires org.mindrot;

    opens lk.ijse.therapy_center.Utill ;
    opens lk.ijse.therapy_center.Config to jakarta.persistence;
    opens lk.ijse.therapy_center.Entity to org.hibernate.orm.core;
    opens lk.ijse.therapy_center.DTO.TM to javafx.base;
    opens lk.ijse.therapy_center to javafx.fxml;
    opens lk.ijse.therapy_center.Controller to javafx.fxml;
    opens lk.ijse.therapy_center.Controller.Admin to javafx.fxml;
    opens lk.ijse.therapy_center.Controller.Receptionist to javafx.fxml;
    exports lk.ijse.therapy_center;

}