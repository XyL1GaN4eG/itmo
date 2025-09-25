import java.io.*;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        var file = new File("rsc/data.txt");
        file.delete();
        //Записываем данные для метода info()
        var initializationDate = ZonedDateTime.now();
        LabWorkUtility.writeStrToFilename(String.valueOf(initializationDate), "rsc/data.txt");
        String fileName = "rsc/dataLabWork.csv"; // Путь к вашему файлу

        // добавление лабворков в линкедхэшстринг
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // пропускаем первую строку
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

        //Interactive Modeeeeeeeeee
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
                        System.out.println("Введите хоть какую то команду");
                        str = in.nextLine();
                    }
                }
            }
        } catch (NoSuchElementException ex) {
            System.out.println("Вы завершили ввод");
        }
    }

    public static LinkedHashSet<LabWork> linkedHashSet = new LinkedHashSet<>();

}