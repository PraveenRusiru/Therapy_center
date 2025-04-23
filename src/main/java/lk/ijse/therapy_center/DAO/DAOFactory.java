package lk.ijse.therapy_center.DAO;

import lk.ijse.therapy_center.DAO.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {}
    public static DAOFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOType {
        USER,THERAPY_SESSION,THERAPY_PROGRAMME,AVAILABILITY,THERAPIST_FOR_PROGRAMME,THERAPIST,PAYMENT,PATIENT,APPOINTMENT;
    }
    public SuperDAO getSuperDAO(DAOType daoType) throws Exception {
        switch (daoType) {
            case USER-> {
                return new UserDaoImpl();
            }
            case THERAPIST -> {
                return new TherapistDaoImpl();
            }
            case THERAPY_PROGRAMME -> {
                return new TherapistProgrammeDaoImpl();
            }
            case AVAILABILITY -> {
                return new AvailablityDaoImpl();
            }
            case THERAPIST_FOR_PROGRAMME -> {
                return new TherapySessionDaoImpl();
            }
            case PATIENT -> {
                return new PatientDaoImpl();
            }
            case APPOINTMENT -> {
                return new AppointmentDaoImpl();
            }
            case THERAPY_SESSION -> {
                return new TherapySessionDaoImpl();
            }
            case PAYMENT -> {
                return new PaymentDaoImpl();
            }
        }
        return null;
    }
}
