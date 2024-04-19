package ru.konsist.services.responses;

import ru.konsist.models.Reservation;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;

public class ResponseClientsForPayments implements IResponse{
    @Override
    public String respond(String clientRequest) throws UnsupportedEncodingException {
        String hexPatternResponseString = "0130303030303030313246697363616c20202020202020202020021c3833204946435f4753545f53454c1c3431321c4946435f4348475f5053541c311c3431322f5475726368696e736b69792c20416e61746f6c033135463204";
        byte[] responseBytes = HexFormat.of().parseHex(hexPatternResponseString);
        String responseString = new String(responseBytes, "UTF-8");
        String[] clientRequestArray = clientRequest.split("" + (char) 28 + "");
        String[] responseStringArray = responseString.split("" + (char) 28 + "");
        String room = clientRequestArray[2];
        Reservation reservation = new ReturnReservationService().returnReservation(room);

        //Ответ кто проживает в номере и их имя и фамилия
        //Меняем WorkStation Number
        responseString = responseString.replace(responseString.substring(1, 10), clientRequest.substring(1, 10));

        //Меняем номер сообщения для ответа
        responseString = responseString.replace(responseStringArray[1].split(" ")[0], clientRequestArray[1].split(" ")[0]);

        //Меняем проживающих в номере
        int p = 28;
        String residents = reservation.getResidents().size() + "" + (char) p + "";
        for (int i = 0; i < reservation.getResidents().size(); i++) {
            residents += room + "/" + reservation.getResidents().get(i).substring(0, 18) + (char) p;
        }
        residents = residents.substring(0, residents.length() - 2);
        responseString = responseString.replace("1" + (char) p + responseStringArray[5].split("" + (char) 3 + "")[0], residents);

        //Меняем номер комнаты
        responseString = responseString.replace(responseStringArray[2], reservation.getRoomNumber());
        return responseString;
    }
}
