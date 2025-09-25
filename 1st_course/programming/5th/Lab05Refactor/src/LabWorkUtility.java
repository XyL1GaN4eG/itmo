import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class LabWorkUtility {
    // Перегрузка, выводит весь файл

    public static String readFromFile(String filename, Boolean passFirstString) {
        filename = "rsc/" + filename;
        String returner = "";
        String line;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            while ((line = br.readLine()) != null) {
                if (passFirstString) {
                    passFirstString = false;
                    continue; // пропускаем первую строку
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

    static void addElementToSet(String[] parts) throws Exception {
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
    //бывший свитчкейсер
    public static void commandParser(String string) {
        var line = customSplit(string, ' ');
        if (line.length == 1) {
            processCommand(line[0]);
        } else if (line.length == 2) {
            processCommand(line[0], line[1]);
        } else {
            System.out.println("Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек");
        }
    }

    // Метод для обработки команд с одним аргументом
    public static void processCommand(String command) {
        var mass1 = new String[]{"help", "info", "show", "clear", "save", "add", "history"};
        if (contains(command, mass1)) {
            switch (command) {
                case "help" -> LabWorkMethods.help();
                case "info" -> LabWorkMethods.info();
                case "show" -> LabWorkMethods.show(Main.linkedHashSet);
                case "clear" -> LabWorkMethods.clear();
                case "save" -> LabWorkMethods.save();
                case "add" -> LabWorkMethods.add();
                case "history" -> History.view();
            }
        } else {
            System.out.println("Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек");
        }
    }

    // Метод для обработки команд с двумя аргументами
    public static void processCommand(String command, String argument) {
        var mass2 = new String[]{"filterLessThanDifficulty", "updateID", "removeByID", "executeScript", "removeGreater", "removeLower", "filterByDifficulty"};
        if (contains(command, mass2)) {
            try {
                switch (command) {
                    case "updateID" -> LabWorkMethods.updateID(Integer.valueOf(argument));
                    case "removeByID" -> LabWorkMethods.removeByID(Integer.valueOf(argument));
                    case "executeScript" -> {
                        try {
                            LabWorkMethods.executeScript(argument);
                        } catch (StackOverflowError e) {
                            System.out.println("Ты зачем рекурсию устроил((");
                        }
                    }
                    case "removeGreater" -> LabWorkMethods.removeGreater(Integer.parseInt(argument));
                    case "removeLower" -> LabWorkMethods.removeLower(Integer.parseInt(argument));
                    case "filterByDifficulty" -> LabWorkMethods.filterByDifficulty(argument);
                    case "filterLessThanDifficulty" -> LabWorkMethods.filterLessThanDifficulty(argument);
                    default -> System.out.println("Эта команда не поддерживается в вашей версии данного ПО. " +
                            "Проверьте корректность введеной команды.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите вторую переменную в правильном формате");
            }
        } else {
            System.out.println("Вы ввели некорректную команду. Попробуйте ввести \"help\" без кавычек");
        }
    }



    //Перегрузка, можно указать количество строк
    public static String readFromFile(String filename, Boolean passFirstString, Integer numberOfStrings) {
        filename = "rsc/" + filename;
        String returner = "";
        String line;
        var i = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            while ((line = br.readLine()) != null && i <= numberOfStrings) {
                if (passFirstString) {
                    passFirstString = false;
                    continue; // пропускаем первую строку
                } else {
                    i++;
                    returner += line + "\n";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return returner;
    }

    public static void main(String[] args) {
        var str = "1233483686,LabWork1,0,0.0,2024-02-01T10:15:30Z,,,,,,";
        var list = customSplit(str, ',');
        for (String element : list) {
            System.out.print(element);
        }
    }

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
}
