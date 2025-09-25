//package ClientMain.main;
//
//import General.dataClasses.LabWork;
//import General.dataClasses.LabWorkSet;
//import General.dataClasses.Person;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.ZonedDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Утилитарный класс для работы с лабораторными работами.
// */
//public class LabWorkUtility {
//    /**
//     * Метод для записи строки в файл.
//     *
//     * @param str      Строка для записи.
//     * @param filename Имя файла.
//     */
//    public static void writeStrToFilename(String str, String filename) {
//        String fileName = filename; // Путь к вашему файлу
//        try (var writer = new PrintWriter(new FileWriter(fileName, true))) {
//            writer.print(str);
//        } catch (IOException e) {
//            System.err.println("Ошибка записи данных в файл: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Метод для добавления элемента в набор лабораторных работ.
//     *
//     * @param parts Массив строк с данными о лабораторной работе.
//     * @throws Exception Исключение, возникающее при ошибке парсинга данных.
//     */
//    public static void addElementToSet(String[] parts) throws Exception {
//        try {
//            LabWorkSet.labWorks.add(LabWork.builder()
//                    .randomID(parts[0])
//                    .difficulty(parts[6])
//                    .coordinates(parts[2], parts[3])
//                    .name(parts[1])
//                    .creationDate(ZonedDateTime.parse(parts[4]))
//                    .minimalPoint(Float.parseFloat(parts[5]))
//                    .author(new Person(parts[7], parts[8], parts[9], parts[10]))
//                    .build());
//        } catch (IndexOutOfBoundsException e) {
//            LabWorkSet.labWorks.add(LabWork.builder()
//                    .randomID(parts[0])
//                    .difficulty(parts[6])
//                    .coordinates(parts[2], parts[3])
//                    .name(parts[1])
//                    .creationDate(ZonedDateTime.parse(parts[4]))
//                    .minimalPoint(Float.parseFloat(parts[5]))
//                    .author(new Person())
//                    .build());
//        }
//    }
//
//    /**
//     * Метод для разбиения строки по заданному разделителю.
//     *
//     * @param str       Входная строка.
//     * @param delimiter Разделитель.
//     * @return Массив строк после разбиения.
//     */
//    public static String[] customSplit(String str, char delimiter) {
//        List<String> result = new ArrayList<>();
//        StringBuilder sb = new StringBuilder();
//
//        boolean commaSeen = false;
//        for (char c : str.toCharArray()) {
//            if (c == delimiter) {
//                if (commaSeen) {
//                    result.add("");
//                } else {
//                    result.add(sb.toString());
//                    sb.setLength(0);
//                }
//                commaSeen = true;
//            } else {
//                sb.append(c);
//                commaSeen = false;
//            }
//        }
//
//        if (sb.length() > 0 || commaSeen) {
//            result.add(sb.toString());
//        }
//
//        return result.toArray(new String[result.size()]);
//    }
//
//    /**
//     * Метод для проверки наличия строки в массиве.
//     *
//     * @param string Входная строка для поиска.
//     * @param array  Массив строк для поиска.
//     * @return true, если строка найдена в массиве, в противном случае - false.
//     */
//    private static Boolean contains(String string, String[] array) {
//        boolean answ = false;
//        for (String element : array) {
//            if (string.equals(element)) {
//                answ = true;
//                break;
//            }
//        }
//        return answ;
//    }
//
//}
