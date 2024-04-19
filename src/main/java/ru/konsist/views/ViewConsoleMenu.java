package ru.konsist.views;

import ru.konsist.services.tcpConnect.ClientService;
import ru.konsist.services.tcpConnect.IClient;
import ru.konsist.services.tcpConnect.ServerService;

import java.io.IOException;
import java.util.Scanner;

public class ViewConsoleMenu implements IViewMenu{
    public ViewConsoleMenu() {
    }
    @Override
    public void viewRun() throws IOException {
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.print("Введите номер приложения (1 - Server, 2 - Client, 3 - выход): ");
            Integer num = scanner.nextInt();
            if (num != 3) {
                if (num == 1) {
                    System.out.print("Введите IP адрес и порт сервера через двоеточие: ");
                    String[] socket = scanner.next().split(":");
                    Thread server = new ServerService(Integer.parseInt(socket[1]), socket[0]);
                    server.setDaemon(true);
                    server.setName("Server");
                    server.run();
                } else if (num == 2) {
                    System.out.print("Введите IP адрес и порт сервера через двоеточие: ");
                    String[] socket = scanner.next().split(":");
                    IClient client = new ClientService(socket[0], Integer.parseInt(socket[1]));
                    client.clientConnect();
                    boolean more = true;
                    while (more){
                        System.out.print("Введите сообщение или знак '!', для выхода - 'q': ");
                        String message = scanner.next();
                        if (message.equals("q")){
                            client.sendMessage(message);
                            more = false;
                            client.clientDisconnect();
                        } else {
                            client.sendMessage(message);
                        }
                    }
                } else {
                    System.out.println("Неверный ввод!");
                }
            } else {
                run = false;
            }
        }
    }
}
