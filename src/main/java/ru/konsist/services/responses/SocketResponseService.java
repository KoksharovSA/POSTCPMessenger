package ru.konsist.services.responses;

import ru.konsist.services.supports.SupportService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class SocketResponseService extends Thread {
    private Socket socketSomething;

    public SocketResponseService(Socket socket) {
        this.socketSomething = socket;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //Создаём поток на чтение
                DataInputStream in = new DataInputStream(socketSomething.getInputStream());

                while (true) {

                    //Читаем поток до закрывающего символа, десятичный код символа = 4 (Управляющий символ: Завершение передачи)
                    String clientRequest = "";
                    byte readByte;
                    List<Byte> listBytes = new ArrayList<>();
                    while ((readByte = in.readByte()) != 4) {
                        listBytes.add(readByte);
                        if (readByte == 4) {
                            return;
                        }
                    }
                    listBytes.add((byte) 4);
                    for (byte item : listBytes) {
                        clientRequest += (char) item;
                    }

                    System.out.println("Клиент " + socketSomething.getRemoteSocketAddress() + " : " + clientRequest);
                    SupportService.getInstance().bytesToBytesAndCharsTable(listBytes);

                    //Генерируем ответ
                    String responseString = new GeneratorResponseService().generateResponse(clientRequest);
                    byte[] responseBytes = responseString.getBytes(Charset.defaultCharset());
                    SupportService.getInstance().bytesToBytesAndCharsTable(responseBytes);
                    System.out.println("Север ответил: " + new String(responseBytes, "UTF-8"));

                    //Отправляем ответ клиенту
                    //Создаём поток на отправку
                    DataOutputStream out = new DataOutputStream(socketSomething.getOutputStream());
                    out.write(responseBytes);
                }
            } catch (SocketTimeoutException t) {
                System.out.println("Время сокета " + socketSomething.getRemoteSocketAddress() + " истекло.");
            } catch (IOException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
