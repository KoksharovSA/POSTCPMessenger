package ru.konsist.models;

import java.util.List;

public class Reservation {
    private String reservationCode;
    private String roomNumber;
    List<String> residents;
    String CL;
    String arrDate;
    String depDate;

    public String getArrDate() {
        return arrDate;
    }

    public void setArrDate(String arrDate) {
        this.arrDate = arrDate;
    }

    public String getDepDate() {
        return depDate;
    }

    public void setDepDate(String depDate) {
        this.depDate = depDate;
    }

    public Reservation(String reservationCode, String roomNumber, List<String> residents, String CL, String arrDate, String depDate) {
        this.reservationCode = reservationCode;
        this.roomNumber = roomNumber;
        this.residents = residents;
        this.CL = CL;
        this.arrDate = arrDate;
        this.depDate = depDate;
    }

    public Reservation() {
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public void setReservationCode(String reservationCode) {
        this.reservationCode = reservationCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<String> getResidents() {
        return residents;
    }

    public void setResidents(List<String> residents) {
        this.residents = residents;
    }

    public String getCL() {
        return CL;
    }

    public void setCL(String CL) {
        this.CL = CL;
    }


}
