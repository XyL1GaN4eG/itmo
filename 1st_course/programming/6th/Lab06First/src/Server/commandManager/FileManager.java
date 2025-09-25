package Server.commandManager;

import java.io.*;

public class FileManager {
    /**
     * Метод для чтения данных из файла.
     *
     * @param filename        Имя файла.
     * @param passFirstString Флаг для пропуска первой строки.
     * @return Строка данных из файла.
     */
    public static String readFromFile(String filename, Boolean passFirstString) {
        var myFilename = System.getenv("PWD") + "/src/Server/rsc/" + filename;
        String returner = "";
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(myFilename)))) {
            while ((line = br.readLine()) != null) {
                if (passFirstString) {
                    passFirstString = false;
                } else {
                    returner += line + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден((");
            System.out.println(myFilename);
            System.out.println(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returner;
    }

    /**
     * Метод для записи строки в файл.
     *
     * @param str      Строка для записи.
     * @param filename Имя файла.
     */
    public static void writeStrToFilename(String str, String filename) {
        String fileName = System.getenv("PWD") + "/src/Server/" + filename; // Путь к вашему файлу
        try (var writer = new PrintWriter(new FileWriter(fileName, true))) { //true нужен чтобы записать
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи данных в файл: " + e.getMessage() + " по следующему пути: " + filename);
        }
    }


}
