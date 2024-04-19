package ru.konsist.services.responses;

import java.io.UnsupportedEncodingException;

public class GeneratorResponseService implements IGeneratorResponse{
    public String generateResponse(String clientRequest) throws UnsupportedEncodingException {
        boolean isMessageRequestNoPost = false;
        boolean isMessagePayment = false;

        //Определяем вид сообщения
        if (clientRequest.contains("FO_FI_RES_QUERY")) {
            isMessageRequestNoPost = true;
        } else if (clientRequest.contains("IFC_CHG_PST") || clientRequest.contains("FINAL_TENDER")) {
            isMessagePayment = true;
        }

        //Формирование нужного ответа
        String responseString = "";

        if (isMessageRequestNoPost) {
            //Запрос параметра NoPost у номера
            IResponse response = new ResponseClientsAndPaymentStatus();
            responseString = response.respond(clientRequest);
            Thread.currentThread().interrupt();
        } else if (isMessagePayment) {
            if (clientRequest.split("" + (char) 28 + "").length == 4) {
                //Запрс проживающих в номере для оплаты счёта
                responseString = new ResponseClientsForPayments().respond(clientRequest);
                isMessagePayment = false;
            } else if (clientRequest.split("" + (char) 28 + "").length == 38) {
                //Выбор гостя из предоставленных и запрос одобрения суммы покупки
                IResponse response = new ResponsePaymentApproval();
                responseString = response.respond(clientRequest);
            } else if (clientRequest.split("" + (char) 28 + "").length == 7) {
                //Отправка чека об операции
                IResponse response = new ResponseApprovalOfPaymentReceipt();
                responseString = response.respond(clientRequest);
                Thread.currentThread().interrupt();
            } else {
                //Пустой ответ проверки связи
                IResponse response = new ResponseEmpty();
                responseString = response.respond(clientRequest);
                Thread.currentThread().interrupt();
            }
        }
        return responseString;
    }
}
