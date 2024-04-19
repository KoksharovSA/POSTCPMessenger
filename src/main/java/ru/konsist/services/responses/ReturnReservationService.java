package ru.konsist.services.responses;

import ru.konsist.models.Reservation;

import java.util.Arrays;
import java.util.UUID;

public class ReturnReservationService implements IReturnReservation{
    @Override
    public Reservation returnReservation(String roomNumber) {
        Reservation reservation = new Reservation();
        reservation.setReservationCode(UUID.randomUUID().toString());
        reservation.setRoomNumber(roomNumber);
        reservation.setCL("Y");
        reservation.setArrDate("10-SEP-23");
        reservation.setDepDate("31-SEP-23");
        reservation.setResidents(Arrays.asList("Ceberyabova, Alyena Yuryevna",
                "Chuvilev, Artem Alexandrovitch",
                "Freize, Artur Vladimirovich"));
        return reservation;
    }
}
