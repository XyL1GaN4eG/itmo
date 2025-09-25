package Client.main;

import Client.main.messages.RequestWithLabwork;
import General.UDPConnection;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            var connection = new UDPConnection();

            //устанавливаем соединение с сервером
            connection.connect("localhost",  8080);

            var request = new RequestWithLabwork();
            request.

            //отправляем реквест
            connection.sendRequest();
        } catch (IOException e) {
            System.err.println("Произошла ошибка! " + e.getMessage());
        }

//            //задаем константы и переменные для обращения к серверу
//            final int port = 8009;
//
//            // datagramChan
//            // передает информацию в датаграмах (типо буфер)
//            var datagramChan = DatagramChannel.open();
//
//            // включаем неблокирующий режим
//            datagramChan.configureBlocking(false);
//
//            ByteBuffer buffer = null;
//
//            var servAddr = new InetSocketAddress("localhost", port);
//
//            var request = new RequestWithLabwork();
////        buffer.
//
//            datagramChan.send(buffer, servAddr);
//            buffer.clear(); // очищаем буфер,
//            // чтобы получить ответ от сервера в пустой буфер
//
//            datagramChan.receive(buffer);
//
//        } catch (IOException e) {
//            System.out.println("Сервер временно недоступен :(");
//        }
    }
}

