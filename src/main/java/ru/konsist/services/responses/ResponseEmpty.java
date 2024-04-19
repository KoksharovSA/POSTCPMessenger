package ru.konsist.services.responses;

import java.io.UnsupportedEncodingException;
import java.util.HexFormat;

public class ResponseEmpty implements IResponse{
    @Override
    public String respond(String clientRequest) throws UnsupportedEncodingException {
        String hexStr = "0120202020202020203020202020202020202020202020202020020304";
        byte[] responseBytes = HexFormat.of().parseHex(hexStr);
        String responseString = new String(responseBytes, "UTF-8");
        return responseString;
    }
}
