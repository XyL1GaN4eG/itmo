package Server;

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

import static Server.GlobalVars.PATH_TO_CSV;
import static Server.GlobalVars.PATH_TO_RSC;

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
            System.out.println("Проблема с вводом выводом");
        }
    }
}
