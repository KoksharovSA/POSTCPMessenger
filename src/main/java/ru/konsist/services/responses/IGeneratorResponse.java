package ru.konsist.services.responses;

import java.io.UnsupportedEncodingException;

public interface IGeneratorResponse {
    String generateResponse(String clientRequest) throws UnsupportedEncodingException;
}
