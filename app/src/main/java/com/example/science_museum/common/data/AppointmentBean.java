package com.example.science_museum.common.data;

public class AppointmentBean {
    private long appointmentId;
    private long uid;
    private String telephoneNumber;
    private String idNumber;
    private String bookerName;
    private String appointmentDate;

    // Getters
    public long getAppointmentId() {
        return appointmentId;
    }

    public long getUid() {
        return uid;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getBookerName() {
        return bookerName;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    // Setters
    public void setAppointmentId(long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setBookerName(String bookerName) {
        this.bookerName = bookerName;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

}

