package ru.konsist.services.responses;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;

public class ResponseApprovalOfPaymentReceipt implements IResponse{

    @Override
    public String respond(String clientRequest) throws UnsupportedEncodingException {
        String hexStr = "0130303030303030303346697363616c20202020202020202020021c323320464f5f38375f444f4e451c436865636b2064657461696c207265636569766564033130433704";
        byte[] responseBytes = HexFormat.of().parseHex(hexStr);
        String responseString = new String(responseBytes, "UTF-8");
        String[] clientRequestArray = clientRequest.split("" + (char) 28 + "");
        String[] responseStringArray = responseString.split("" + (char) 28 + "");

        //Ответ что чек об операции получен
        //Меняем WorkStation Number
        responseString = responseString.replace(responseString.substring(1, 10), clientRequest.substring(1, 10));

        //Меняем номер сообщения для ответа
        responseString = responseString.replace(responseStringArray[1].split(" ")[0], clientRequestArray[1].split(" ")[0]);

        return responseString;
    }
}
