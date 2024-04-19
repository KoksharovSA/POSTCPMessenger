package ru.konsist.services.responses;

import ru.konsist.models.Reservation;

public interface IReturnReservation {
    Reservation returnReservation(String roomNumber);
}
