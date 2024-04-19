package ru.konsist.services.tcpConnect;

import java.net.*;
import java.io.*;
import java.util.HexFormat;

public class ClientService implements IClient{
    private String lastMessage = "<LinkStart Date=\"131123\" Time=\"112600\"/>";
    private String serverName;
    int port;
    private Socket client;

    public ClientService(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }
    public void clientConnect() throws IOException {
        System.out.println("Подключение к " + serverName + " на порт " + port);
        this.client = new Socket(this.serverName, this.port);
        System.out.println("Подкючились к " + client.getRemoteSocketAddress());
    }

    public void clientDisconnect() throws IOException {
        this.client.close();
    }

    public void sendMessage(String message){
        try {
            if (!message.equals("!")){
                this.lastMessage = message;
            }

            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

//            out.writeUTF("Привет из " + client.getLocalSocketAddress());
//            out.writeBytes("0130303030303030393946697363616c20202020202020202020021c303120464f5f38375f5245535f4c4953541c526f6f6d2020204e616d652020202020202020202020202020202020202020202020202020204172726976616c2f44657061727475726520202020205061796d656e7420202020202020202020201c311c31343030363538201c393030302020204261626165762c20456c7368616e20416468616d204f676c7920202020202032382d5345502d32322032322d4445432d3233202020434c202020592020202020201c2020033330333804");

            String hexPatternResponseString = "0130303030303030303346697363616c20202020202020202020021c3231204946435f4348475f5053541c3531321c3135033041373804";
            byte[] responseBytes;
            responseBytes = HexFormat.of().parseHex(hexPatternResponseString);
            lastMessage = new String(responseBytes, "UTF-8");

            out.writeUTF(lastMessage);
            System.out.println("Отправили сообщение : " + lastMessage);
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            String mes = in.readUTF();
            if (!mes.equals(" ")){
                System.out.println("Сервер ответил : " + mes);
            }
        } catch (IOException e) {
            System.out.println(e);;
        }
    }
}
