import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        //Записываем данные для метода info()
        var initializationDate = ZonedDateTime.now();
        LabWorkUtility.writeStrToFilename(String.valueOf(initializationDate), "data.txt");
        String fileName = "src/dataLabWork.csv"; // Путь к вашему файлу
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

        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        //Interactive Mode
        while (true) { //не бейте за бесконечный вайл тру😇😇😇
            if (str.equals("exit")) {
                break; } else {
            History.history(str);
            LabWorkUtility.switchCaser(str);
            str = in.nextLine();
            }
        }
    }

    public static LinkedHashSet<LabWork> linkedHashSet = new LinkedHashSet<>();

}