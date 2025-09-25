package Server.net;

import General.net.Request;
import General.net.Response;
import Server.commandManager.CommandManager;
import Server.commandManager.commands.Show;

import java.io.*;
import java.net.*;

public class Serv extends Thread {

    // Порт сервера
    private int port = 50457;
    // Сокет для получения и отправки пакетов
    DatagramSocket socket;
    // Флаг для проверки, работает ли сервер
    private boolean isRunning;
    // Буфер для получения данных
    private static byte[] INbuffer = new byte[4192];

    // Конструктор сервера
    public Serv() {
        try {
            // Создаем сокет на определенном порту
            socket = new DatagramSocket(port);
            // Устанавливаем поток демоном
            setDaemon(true);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    // Метод для запуска сервера
    public void run() {
        isRunning = true;
        var commandManager = new CommandManager();
        // Создаем пакет для получения данных
        DatagramPacket packet = new DatagramPacket(INbuffer, INbuffer.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        InetAddress address = packet.getAddress();
        Response response = new Response(commandManager.getInfo());

        //отправляем коллекцию для валидации
        // Создаем поток для записи объекта ответа в байты
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        try {
            byteArrayOutputStream.flush();
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            socket.setReuseAddress(true);
            // Подключаемся к клиенту
            socket.connect(new InetSocketAddress(address, packet.getPort()));

            // Записываем объект ответа в поток
            objectOutputStream.flush();
            objectOutputStream.writeObject(response);
            byte[] OUTBuffer = byteArrayOutputStream.toByteArray();

            // Создаем и отправляем пакет с ответом клиенту
            DatagramPacket OUTpacket = new DatagramPacket(OUTBuffer, OUTBuffer.length, address, packet.getPort());
            System.out.println("отправляю коллекцию для валидации");
            socket.send(OUTpacket);
            socket.disconnect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        try {
            while (isRunning) {
                // Создаем пакет для получения данных
                packet = new DatagramPacket(INbuffer, INbuffer.length);
                socket.receive(packet);
                address = packet.getAddress();

                // Читаем объект запроса из байтов
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(INbuffer);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                Object obj = objectInputStream.readObject();


                // Проверяем тип полученного объекта
                if (!(obj instanceof Request)) {
                    System.out.println("Request type is incorrect");
                    continue;
                }
                Request request = (Request) obj;
                // Отправляем ответ клиенту
                sendResponse(address, request, packet.getPort());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Метод для отправки ответа клиенту
    public void sendResponse(InetAddress address, Request request, int sendPort) throws IOException {
        System.out.println(request.toString());


        String argumentToResponse = "Hello client";
        try {
            argumentToResponse = commandManager.executeCommand(request.getData());
        } catch (NullPointerException e) {e.printStackTrace();}

        Response response = new Response(argumentToResponse);
        System.out.println(new Show().execute());

        // Создаем поток для записи объекта ответа в байты
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.flush();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

        socket.setReuseAddress(true);

        // Подключаемся к клиенту
        socket.connect(new InetSocketAddress(address, sendPort));

        // Записываем объект ответа в поток
        objectOutputStream.flush();
        objectOutputStream.writeObject(response);
        byte[] OUTBuffer = byteArrayOutputStream.toByteArray();

        // Создаем и отправляем пакет с ответом клиенту
        DatagramPacket OUTpacket = new DatagramPacket(OUTBuffer, OUTBuffer.length, address, sendPort);
        socket.send(OUTpacket);
        socket.disconnect();
    }

    public static CommandManager commandManager = new CommandManager();
}
