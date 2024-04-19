package ru.konsist.services.responses;

import ru.konsist.models.Reservation;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;

public class ResponsePaymentApproval implements IResponse{
    @Override
    public String respond(String clientRequest) throws UnsupportedEncodingException {
        byte[] responseBytes;
        String responseString;
        String[] clientRequestArray = clientRequest.split("" + (char) 28 + "");
        String room = clientRequestArray[2];
        String numberGuest = clientRequestArray[4];
        Reservation reservation = new ReturnReservationService().returnReservation(room);
        if (reservation.getCL().equals("N")) {
            //Выбор гостя из предоставленных и запрос одобрения суммы покупки
            //Если не разрешена постоплата (запрет начислений)
            String hexStr = "0130303030303030303346697363616c20202020202020202020021c3230204946435f4348475f5053541c4e1c441c4e6f204372656469742e20202020202020202020202020202020202020033046454404";
            responseBytes = HexFormat.of().parseHex(hexStr);
            responseString = new String(responseBytes, "UTF-8");
            String[] responseStringArray = responseString.split("" + (char) 28 + "");

            //Отвечаем No Credit
            //Меняем WorkStation Number
            responseString = responseString.replace(responseString.substring(1, 10), clientRequest.substring(1, 10));

            //Меняем номер сообщения для ответа
            responseString = responseString.replace(responseStringArray[1].split(" ")[0], clientRequestArray[1].split(" ")[0]);
            Thread.currentThread().interrupt();
        } else {
            //Если разрешена пост оплата
            //Выбор гостя из предоставленных и запрос одобрения суммы покупки
            String hexStr = "0130303030303030303346697363616c20202020202020202020021c3232204946435f4348475f5053541c4e1c501c3531322f486d656c65762020202020202020201c333830302e30301c31353338343135033131373904";
            responseBytes = HexFormat.of().parseHex(hexStr);
            responseString = new String(responseBytes, "UTF-8");
            String[] responseStringArray = responseString.split("" + (char) 28 + "");

            //Ответ что сумма согласованна
            //Меняем WorkStation Number
            responseString = responseString.replace(responseString.substring(1, 10), clientRequest.substring(1, 10));

            //Меняем номер сообщения для ответа
            responseString = responseString.replace(responseStringArray[1].split(" ")[0], clientRequestArray[1].split(" ")[0]);

            //Меняем гостя
            responseString = responseString.replace(responseStringArray[4], reservation.getResidents().get(Integer.parseInt(numberGuest.split("")[1])-1));

            //Меняем сумму оплаты
            responseString = responseString.replace(responseStringArray[5], clientRequestArray[6]);
        }
        return responseString;
    }
}
