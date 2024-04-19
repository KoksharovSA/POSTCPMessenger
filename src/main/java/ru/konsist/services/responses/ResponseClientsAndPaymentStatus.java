package ru.konsist.services.responses;

import ru.konsist.models.Reservation;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;

public class ResponseClientsAndPaymentStatus implements IResponse{
    @Override
    public String respond(String clientRequest) throws UnsupportedEncodingException {
        String hexPatternResponseString = "0130303030303030393946697363616c20202020202020202020021c303120464f5f38375f5245535f4c4953541c526f6f6d2020204e616d652020202020202020202020202020202020202020202020202020204172726976616c2f44657061727475726520202020205061796d656e7420202020202020202020201c311c31343030363538201c393030302020204261626165762c20456c7368616e20416468616d204f676c7920202020202032382d5345502d32322032322d4445432d3233202020434c202020592020202020201c2020033330333804";
        byte[] responseBytes = HexFormat.of().parseHex(hexPatternResponseString);
        String responseString = new String(responseBytes, "UTF-8");

        String[] clientRequestArray = clientRequest.split("" + (char) 28 + "");
        String[] responseStringArray = responseString.split("" + (char) 28 + "");

        String room = clientRequestArray[2].split("" + (char)3 + "")[0];
        Reservation reservation = new ReturnReservationService().returnReservation(room);
        StringBuilder responseSB = new StringBuilder();
        responseSB.append(reservation.getRoomNumber());
        for (int i = 0; i < 7 - reservation.getRoomNumber().length(); i++) {
            responseSB.append(" ");
        }
        if (reservation.getResidents().get(0).length() > 30){
            responseSB.append(reservation.getResidents().get(0).substring(0,30));
            responseSB.append(" ");
        } else {
            responseSB.append(reservation.getResidents().get(0));
            for (int i = 0; i < 31 - reservation.getResidents().get(0).length(); i++) {
                responseSB.append(" ");
            }
        }
        responseSB.append(reservation.getArrDate() + " " + reservation.getDepDate());
        responseSB.append("   CL   " + reservation.getCL() + "      ");

        //Меняем WorkStation Number
        responseString = responseString.replace(responseString.substring(1, 10), clientRequest.substring(1, 10));

        //Меняем номер сообщения для ответа
        responseString = responseString.replace(responseStringArray[1].split(" ")[0], clientRequestArray[1].split(" ")[0]);

        //Меняем номер комнаты
        responseString = responseString.replace(responseStringArray[5].split("" + (char)3 + "")[0], responseSB.toString());
        return responseString;
    }
}
