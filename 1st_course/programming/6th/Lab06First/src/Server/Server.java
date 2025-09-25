package Server;

import General.ICommand;
import General.LabWorkUtility;
import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;
import Server.commandManager.Command;
import Server.commandManager.CommandFactory;
import Server.commandManager.FileManager;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.ZonedDateTime;

import static General.dataClasses.History.history;

public class Server {
    private static void getLabsFromFile() {

        String PATH_TO_CSV = System.getenv("PWD") + "/src/Server/rsc/dataLabWork.csv";
        String PATH_TO_RSC = System.getenv("PWD") + "/src/Server/rsc/";

        var file = new File(PATH_TO_RSC + "data.txt");
        System.out.println(PATH_TO_RSC);

        file.delete();
        var initializationDate = ZonedDateTime.now();
        FileManager.writeStrToFilename(String.valueOf(initializationDate), "rsc/data.txt");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(PATH_TO_CSV)))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                } else {
                    String[] parts = LabWorkUtility.customSplit(line, ',');
                    LabWorkSet.labWorks.add(new LabWork(parts));
                }
            }
        } catch (IOException e) {
            System.out.println("Проблема с вводом выводом");
        }
    }

    public static void main(String[] args) throws Exception {

        getLabsFromFile();

        var serverPort = 3000;
        var ds = new DatagramSocket(serverPort);
        byte[] receiveData = new byte[999_999_999];
        byte[] sendData;

        DatagramPacket dp;
        dp = new DatagramPacket(receiveData, receiveData.length);
//        var command = new CommandManager(CommandMode.CLI_UserMode);

        // Объявление клиентских данных
        InetAddress clientAddress;
        int clientPort;


        var promptToEnter = "Введите хоть какую-то команду";


        // получение сообщение
        ds.receive(dp);
        clientAddress = dp.getAddress();
        clientPort = dp.getPort();

//        var history = new History();
        // Работа с клиентской частью
        while (true) {

//            //Выполнение команды
//            str = command.executeCommand(customSplit(
//                    new String(
//                    dp.getData(),
//                    0,
//                    dp.getLength()
//            ), ' '));
//            System.out.println(str);

            //Формируем данные для отправки
//            sendData = str.getBytes();
//
//            //Отправляем данные
//            ds.send(new DatagramPacket(sendData,
//                    sendData.length,
//                    clientAddress,
//                    clientPort));
//
//            ds.receive(dp);

            // Получаем пакет от клиента

            // Создаем ByteArrayInputStream из данных пак

            ByteArrayInputStream inputStream = new ByteArrayInputStream(dp.getData(), dp.getOffset(), dp.getLength());
            System.out.println(inputStream);

            // Создаем ObjectInputStream для десериализации объекта Command
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            System.out.println(objectInputStream);

            ICommand receivedCommand = (ICommand) objectInputStream.readObject();
            System.out.println("получена команда " + receivedCommand);

            // Используем CommandFactory для создания и выполнения команды
            Command command = CommandFactory.createCommand(receivedCommand.getName());
            history(command.getName());
            System.out.println(command);

            command.setArgument(receivedCommand.getArgument());

            String result = command.execute();
            System.out.println(result);
            if (result == null) {
                result = "Команда исполнилась";
            }
            ds.send(new DatagramPacket(result.getBytes(),
                    result.length(),
                    clientAddress,
                    clientPort));

            ds.receive(dp);


        }
    }
}
