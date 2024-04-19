package ru.konsist.services.tcpConnect;

import ru.konsist.services.responses.SocketResponseService;

import java.net.*;
import java.io.*;

public class ServerService extends Thread {
    private ServerSocket serverSocket;

    public ServerService(int port, String ipAddress) throws IOException {
        serverSocket = new ServerSocket(port, 50, InetAddress.getByName(ipAddress));
//        serverSocket.setSoTimeout(100000);
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("Ожидание клиента на порт " + serverSocket.getLocalPort() + "...");

                //Запускаем ожидание подключения к серверу
                Socket socketSomething = serverSocket.accept();
//                socketSomething.setSoTimeout(60000);

                System.out.println("К серверу подключился : " + socketSomething.getRemoteSocketAddress());

                SocketResponseService responseService = new SocketResponseService(socketSomething);
                responseService.setDaemon(true);
                responseService.start();
            } catch (IOException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
