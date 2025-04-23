package lk.ijse.therapy_center.BO;

import lk.ijse.therapy_center.BO.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}
    public static BOFactory getInstance() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }
    public enum BOType {
        USER,THERAPY_SESSION,THERAPY_PROGRAMME,AVAILABILITY,THERAPIST_FOR_PROGRAMME,THERAPIST,PAYMENT,PATIENT,APPOINTMENT;
    }
    public  SuperBO getBOType(BOType type) throws Exception {
        switch (type) {
            case USER-> {
                return new UserBoImpl();
            }
            case THERAPIST -> {
                return new TherapistBoImpl();
            }
            case THERAPY_PROGRAMME -> {
                return new TherapyProgrammeBoImpl();
            }
            case AVAILABILITY -> {
                return new AvailabiltyBOImpl();
            }
            case THERAPIST_FOR_PROGRAMME -> {
                return new TherapySessionBOImpl();
            }
            case PATIENT -> {
                return new PatientBOImpl();
            }case APPOINTMENT -> {
                return new AppointmentBOImpl();
            }
            case THERAPY_SESSION -> {
                return new TherapySessionBOImpl();
            }
            case PAYMENT -> {
                return new PaymentBOImpl();
            }
        }
        return null;
    }
}
