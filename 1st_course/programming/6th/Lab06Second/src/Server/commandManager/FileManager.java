package Server.commandManager;

import java.io.*;

import static Server.rsc.ServerGlobalVars.PATH_TO_RSC;

public class FileManager {
    /**
     * Метод для чтения данных из файла.
     *
     * @param filename        Имя файла.
     * @param passFirstString Флаг для пропуска первой строки.
     * @return Строка данных из файла.
     */
    public static String readFromFile(String filename, Boolean passFirstString) {
        var myFilename = PATH_TO_RSC + filename;
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
            e.printStackTrace();
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

//        String fileName = filename; // Путь к вашему файлу
        try (var writer = new PrintWriter(new FileWriter(filename, true))) { //true нужен чтобы записать
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи данных в файл: " + e.getMessage() + " по следующему пути: " + filename);
        }
    }


}
