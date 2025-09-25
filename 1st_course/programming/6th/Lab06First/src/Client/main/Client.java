package Client.main;

//import Server.commandManager.CommandManager;
//import Server.commandManager.CommandMode;

import Client.main.commandManager.CommandManager;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

import static Client.main.LabWorkUtility.customSplit;
//import static General.LabWorkUtility.customSplit;

public class Client {
    public static void main(String[] args) throws Exception {

        //UDP
        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost");
        var serverPort = 3000;
        DatagramPacket sendPacket;
        DatagramPacket receivePacket = null;
        byte[] sendData;
        byte[] receiveData = new byte[999_999_999];

        //Валидация
        Scanner scanner = new Scanner(System.in);
        String in = "";
        boolean a;
        var command = new CommandManager();


        //Работа с серверной частью
        while (true) {
            //Сериализация
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            //Читаем с консоли
            System.out.print("Введите сообщение: ");
            while (true) {
                in = scanner.nextLine();
                try {
                    if (command.getArgument(customSplit(in, ' ')) != null) {
                        break;
                    }
                } catch (NullPointerException e) {
                    System.out.println("Команды не существует! " +
                            "Попробуйте набрать \"help\" без кавычек чтобы увидеть все команды.");
                }
            }

            var commanda = command.getArgument(customSplit(in, ' '));

            commanda.execute();

            objOut.writeObject(command.getArgument(customSplit(in, ' ')));
            objOut.close();

            sendData = byteOut.toByteArray();

            //Создаем пакет
            sendPacket = new DatagramPacket(
                    sendData,
                    sendData.length,
                    ip,
                    serverPort);

            //Отправляем пакет
            clientSocket.send(sendPacket);


            //Создаем пакет для приема данных
            receivePacket = new DatagramPacket(receiveData, receiveData.length);

            //Ждем ответ от сервера
            clientSocket.receive(receivePacket);

            System.out.println(new String(
                    receivePacket.getData(),
                    0,
                    receivePacket.getLength()
            ));
        }
    }
}

