package ru.konsist.services.responses;

import java.io.UnsupportedEncodingException;

public interface IResponse {
    String respond(String clientRequest) throws UnsupportedEncodingException;
}
