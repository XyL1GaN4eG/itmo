import java.io.*;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс, содержащий метод main для запуска приложения и работу с лабораторными работами.
 */
public class Main {

    /**
     * Главный метод приложения, запускающий приложение и взаимодействующий с пользователем.
     * @param args Параметры командной строки (не используются).
     * @throws Exception Возможные исключения, выбрасываемые при выполнении метода.
     */
    public static void main(String[] args) throws Exception {
        var file = new File("rsc/data.txt");
        file.delete();
        // Записываем данные для метода info()
        var initializationDate = ZonedDateTime.now();
        LabWorkUtility.writeStrToFilename(String.valueOf(initializationDate), "rsc/data.txt");
        String fileName = "rsc/dataLabWork.csv"; // Путь к вашему файлу

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
                    LabWorkUtility.addElementToSet(parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

        var str = "";

        // Интерактивный режим
        Scanner in = new Scanner(System.in);
        try {
            str = in.nextLine();
            while (true) {
                if (str.equals("exit")) {
                    break;
                } else {
                    History.history(str);
                    try {
                        LabWorkUtility.commandParser(str);
                        str = in.nextLine();

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Введите хоть какую-то команду");
                        str = in.nextLine();
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Вы завершили ввод");
        }
    }

    /**
     * Коллекция для хранения лабораторных работ.
     */
    public static LinkedHashSet<LabWork> linkedHashSet = new LinkedHashSet<>();
}
