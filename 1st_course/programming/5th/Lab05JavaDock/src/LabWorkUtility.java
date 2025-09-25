import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилитарный класс для работы с лабораторными работами.
 */
public class LabWorkUtility {

    /**
     * Метод для чтения данных из файла.
     *
     * @param filename        Имя файла.
     * @param passFirstString Флаг для пропуска первой строки.
     * @return Строка данных из файла.
     */
    public static String readFromFile(String filename, Boolean passFirstString) {
        filename = "rsc/" + filename;
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

    /**
     * Метод для добавления элемента в набор лабораторных работ.
     *
     * @param parts Массив строк с данными о лабораторной работе.
     * @throws Exception Исключение, возникающее при ошибке парсинга данных.
     */
    public static void addElementToSet(String[] parts) throws Exception {
        try {
            Main.linkedHashSet.add(LabWork.builder()
                    .randomID(parts[0])
                    .difficulty(parts[6])
                    .coordinates(parts[2], parts[3])
                    .name(parts[1])
                    .creationDate(ZonedDateTime.parse(parts[4]))
                    .minimalPoint(Float.parseFloat(parts[5]))
                    .author(new Person(parts[7], parts[8], parts[9], parts[10]))
                    .build());
        } catch (IndexOutOfBoundsException e) {
            Main.linkedHashSet.add(LabWork.builder()
                    .randomID(parts[0])
                    .difficulty(parts[6])
                    .coordinates(parts[2], parts[3])
                    .name(parts[1])
                    .creationDate(ZonedDateTime.parse(parts[4]))
                    .minimalPoint(Float.parseFloat(parts[5]))
                    .author(new Person())
                    .build());
        }
    }

    /**
     * Метод для разбиения строки по заданному разделителю.
     *
     * @param str       Входная строка.
     * @param delimiter Разделитель.
     * @return Массив строк после разбиения.
     */
    public static String[] customSplit(String str, char delimiter) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        boolean commaSeen = false;
        for (char c : str.toCharArray()) {
            if (c == delimiter) {
                if (commaSeen) {
                    result.add("");
                } else {
                    result.add(sb.toString());
                    sb.setLength(0);
                }
                commaSeen = true;
            } else {
                sb.append(c);
                commaSeen = false;
            }
        }

        if (sb.length() > 0 || commaSeen) {
            result.add(sb.toString());
        }

        return result.toArray(new String[result.size()]);
    }

    /**
     * Метод для обработки команды с одним аргументом.
     *
     * @param string Входная строка с командой.
     */
    public static void commandParser(String string) {
        var line = customSplit(string, ' ');
        if (line.length == 1) {
            processCommand(line[0]);
        } else if (line.length >= 2) {
            try {
                processCommand(line[0],
                        line[1]
                        + line[2] + " "
                        + line[3] + " "
                        + line[4] + " "
                        + line[5] + " "
                        + line[6] + " "
                        + line[7]
            );
            } catch (IndexOutOfBoundsException e) {
                processCommand(line[0], line[1]);
            }
        } else {
            System.out.println(line[0] + " 1Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек" );
        }
    }

    /**
     * Метод для обработки команды с одним аргументом.
     *
     * @param command Входная команда.
     */
    public static void processCommand(String command) {
        var mass1 = new String[]{"sumOfMinimalPoint","help", "info", "show", "clear", "save", "add", "history", "exit"};
        if (contains(command, mass1)) {
            switch (command) {
                case "help" -> LabWorkMethods.help();
                case "info" -> LabWorkMethods.info();
                case "show" -> LabWorkMethods.show(Main.linkedHashSet);
                case "clear" -> LabWorkMethods.clear();
                case "save" -> LabWorkMethods.save();
                case "add" -> LabWorkMethods.add();
                case "history" -> History.view();
                case "exit" -> LabWorkMethods.exit();
                case "sum_of_minimal_point" -> LabWorkMethods.sumOfMinimalPoint();
            }
        } else {
            System.out.println(command + " 2Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек");
        }
    }

    /**
     * Метод для обработки команды с двумя аргументами.
     *
     * @param command  Входная команда.
     * @param argument Второй аргумент команды.
     */
    public static void processCommand(String command, String argument) {
        var mass2 = new String[]{"add","filter_less_than_ifficulty", "update_id", "remove_by_id", "execute_script", "remove_greater", "remove_lower", "filter_by_difficulty"};
        if (contains(command, mass2)) {
            try {
                switch (command) {
                    case "update_by_id" -> LabWorkMethods.updateID(Integer.valueOf(argument));
                    case "remove_by_id" -> LabWorkMethods.removeByID(Integer.valueOf(argument));
                    case "execute_script" -> {
                        try {
                            LabWorkMethods.executeScript(argument);
                        } catch (StackOverflowError e) {
                            System.out.println("Ты зачем рекурсию устроил((");
                        }
                    }
                    case "remove_greater" -> LabWorkMethods.removeGreater(Integer.parseInt(argument));
                    case "remove_lower" -> LabWorkMethods.removeLower(Integer.parseInt(argument));
                    case "filter_by_difficulty" -> LabWorkMethods.filterByDifficulty(argument);
                    case "filter_less_than_difficulty" -> LabWorkMethods.filterLessThanDifficulty(argument);
                    case "add" -> LabWorkMethods.add(argument);
                    default -> System.out.println(command);
                }
            } catch (NumberFormatException e) {
                System.out.println(command + argument + " 3Введите вторую переменную в правильном формате");
            }
        } else {
            System.out.println(command + argument + " 4Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек");
        }
    }

    /**
     * Метод для проверки наличия строки в массиве.
     *
     * @param string Входная строка для поиска.
     * @param array  Массив строк для поиска.
     * @return true, если строка найдена в массиве, в противном случае - false.
     */
    private static Boolean contains(String string, String[] array) {
        boolean answ = false;
        for (String element : array) {
            if (string.equals(element)) {
                answ = true;
                break;
            }
        }
        return answ;
    }

    /**
     * Точка входа в программу для тестирования методов класса.
     *
     * @param args Параметры командной строки (не используются).
     */
    public static void main(String[] args) {
        var str = "1233483686,LabWork1,0,0.0,2024-02-01T10:15:30Z,,,,,,";
        var list = customSplit(str, ',');
        for (String element : list) {
            System.out.print(element);
        }
    }
}
