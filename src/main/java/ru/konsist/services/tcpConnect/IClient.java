package ru.konsist.services.tcpConnect;

import java.io.IOException;

public interface IClient {
    void clientConnect() throws IOException;
    void clientDisconnect() throws IOException;
    void sendMessage(String message);
}
