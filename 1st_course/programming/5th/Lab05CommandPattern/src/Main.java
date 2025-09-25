import commandManager.CommandExecutor;
import commandManager.commands.FileManager;
import commandManager.commands.LabWorkUtility;
import dataClasses.LabWork;
import dataClasses.LabWorkSet;

import java.io.*;
import java.time.ZonedDateTime;

/**
 * Класс, содержащий метод main для запуска приложения и работу с лабораторными работами.
 */
public class Main {

//    private static final String EnvVar = "rsc/dataLabWork.csv";




    /**
     * Главный метод приложения, запускающий приложение и взаимодействующий с пользователем.
     *
     * @param args Параметры командной строки (не используются).
     */
    public static void main(String[] args) {

        // Установка переменной окружения
        String key = "PATH_TO_CSV";
        String value = "rsc/dataLabWork.csv";
        System.setProperty(key, value);

        key = "PATH_TO_RSC";
        value = "rsc/";
        System.setProperty(key, value);

        var file = new File("rsc/data.txt");
        file.delete();
        // Записываем данные для метода info()
        var initializationDate = ZonedDateTime.now();
        FileManager.writeStrToFilename(String.valueOf(initializationDate), "rsc/data.txt");
        String fileName = System.getProperty("PATH_TO_CSV"); // Путь к вашему файлу
        System.out.println(fileName);
        var i = 0;
        // Добавление лабораторных работ в коллекцию LinkedHashSet
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // Пропускаем первую строку
                } else {
                    String[] parts = LabWorkUtility.customSplit(line, ',');
//                    System.out.println(Arrays.toString(parts));
//                    var add = new AddForCLI();
//                    add.execute(parts);
                    LabWorkSet.labWorks.add(new LabWork(parts));
//                    System.out.println(LabWorkSet.labWorks);
                }
            }
        } catch (IOException e) {
            System.out.println("Проблема с вводом выводом");;
        }

        var str = "";

        var commandExecutor = new CommandExecutor();



        //        // Интерактивный режим
//        Scanner in = new Scanner(System.in);
//        try {
//            str = in.nextLine();
//            while (true) {
//                if (str.equals("exit")) {
//                    break;
//                } else {
//                    History.history(str);
//                    try {
//                        commandExecutor.startExecuting(str);
//                        str = in.nextLine();
//
//                    } catch (ArrayIndexOutOfBoundsException e) {
//                        System.out.println("Введите хоть какую-то команду");
//                        str = in.nextLine();
//                    }
//                }
//            }
//        } catch (NoSuchElementException ex) {
//            System.out.println("Вы завершили ввод");
//        }
//    }
        commandExecutor.startExecuting();

        /**
         * Коллекция для хранения лабораторных работ.
         */
//    public static LinkedHashSet<LabWork> linkedHashSet = new LinkedHashSet<>();
    }
}
