package commandManager.commands;

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
        filename = System.getenv("PWD") + "/rsc/" + filename;
        String returner = "";
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            while ((line = br.readLine()) != null) {
                if (passFirstString) {
                    passFirstString = false;
                } else {
                    returner += line + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден((");
            ;
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
        String fileName = filename; // Путь к вашему файлу
        try (var writer = new PrintWriter(new FileWriter(fileName, true))) { //true нужен чтобы записать
            // в конец файла, а не перезапсиать
            // Записываем текст в файл
            writer.print(str);
        } catch (IOException e) {
            System.err.println("Ошибка записи данных в файл: " + e.getMessage());
        }
    }


}
