package Server.net;

import General.LabWorkUtility;
import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;
import Server.commandManager.FileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

import static Server.rsc.ServerGlobalVars.PATH_TO_CSV;
import static Server.rsc.ServerGlobalVars.PATH_TO_RSC;

public class SetupServer {
    public static void getLabsFromFile() {
        File file = new File(PATH_TO_RSC + "data.txt");

        file.delete();
        ZonedDateTime initializationDate = ZonedDateTime.now();
        FileManager.writeStrToFilename(String.valueOf(initializationDate), "rsc/data.txt");
        try (Stream<String> lines = Files.lines(Paths.get(PATH_TO_CSV))) {
            lines.skip(1) // пропускаем первую строку (заголовок)
                    .map(line -> LabWorkUtility.customSplit(line, ',')) // разбиваем строку
                    .map(LabWork::new) // создаем объекты LabWork из строк
                    .forEach(LabWorkSet.labWorks::add); // добавляем в коллекцию LabWorkSet.labWorks
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void findAvailablePort(int port) throws IOException {
//
//        // пока порт не занят
//        while (port <= (Math.pow(2, 16) - 1)) {
//            //вместо адреса принимает нулл потому что сервер будет принимать подключения на всех доступных сетевых интерфейсах
//
//            try {
//                var channel = DatagramChannel.open();
//                channel.socket().bind(new InetSocketAddress(SERVER_PORT));
//                return port
//            } catch ()
//
//
//
//            // datagramChan
//            // передает информацию в датаграмах (типо буфер)
//            var datagramChan = DatagramChannel.open();
//
//            // включаем неблокирующий режим
//            datagramChan.configureBlocking(false);
//
//            ByteBuffer buffer = null;
//        }
//    }
}
