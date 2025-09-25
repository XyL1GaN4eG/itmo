package Client.main;


import Client.main.General.net.Request;
import Client.main.General.net.Response;

import java.io.*;
import java.net.*;

public class Client {
    // Порт клиента
    private int port;
    // Буфер для отправки и получения данных
    private byte[] buffer = new byte[4192];
    // Адрес сервера
    private InetAddress address;
    // Сокет для отправки и получения пакетов
    DatagramSocket socket;

    // Порт сервера, с которым будет установлено соединение
    final int serverPort = 50457;

    // Конструктор клиента
    public Client() {
        try {
            // Получаем адрес локальной машины
            address = InetAddress.getLocalHost();
            // Создаем сокет на случайном порту в диапазоне от 5000 до 5999
            socket = new DatagramSocket((int) (Math.random() * 1000 + 5000));
        } catch (UnknownHostException | SocketException e) {
            System.err.println(e.getMessage());
        }
    }

    // Метод для отправки запроса на сервер
    public Object sendRequest(Request request) {
        try {
            // Подключаемся к серверу
            socket.connect(InetAddress.getLocalHost(), serverPort);

            // Создаем поток для записи объекта запроса в байты
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.flush();
            oos.writeObject(request);

            // Переносим байты запроса в буфер
            buffer = baos.toByteArray();
            // Создаем и отправляем пакет с запросом на сервер
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, socket.getInetAddress(), serverPort);
            socket.send(datagramPacket);

            // Получаем ответ от сервера
            Object message = getResponse();

            // Отключаем сокет
            socket.disconnect();
            return message;

        } catch (IOException e) {
            return e.getMessage();
        }
    }

    // Метод для получения ответа от сервера
    public Object getResponse() {
        try {
            // Буфер для получения данных
            byte[] outBuffer = new byte[4129];
            DatagramPacket packet = new DatagramPacket(outBuffer, outBuffer.length);
            socket.receive(packet);

            // Читаем объект ответа из байтов
            ByteArrayInputStream bais = new ByteArrayInputStream(outBuffer);
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object obj = ois.readObject();

//            // Проверяем тип полученного объекта
//            if (!(obj instanceof Response)) {
//                return "Incorrect response type";
//            }
//            if (obj == null) {
//                return "";
//            }

            // Преобразуем ответ в строку и возвращаем
            Response response = (Response) obj;
            var message = response.getData();
            return message;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "Ответ не получен";
    }

}
