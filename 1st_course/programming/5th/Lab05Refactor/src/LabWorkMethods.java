import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;

public class LabWorkMethods {
    public static void main(String[] args) {
        help();
    }

    static void help() {
        System.out.println(LabWorkUtility.readFromFile("helper.txt", false));
    }

    static void info() {
        System.out.println("Количество элементов: " + Main.linkedHashSet.size() + "\n");
        System.out.print("Дата создания: " + LabWorkUtility.readFromFile("data.txt", false) + "\n");
        System.out.println("Пустая ли коллекция: " + Main.linkedHashSet.isEmpty() + "\n");
    }

    public static void printFormattedRow(String[] values, List<Integer> maxLengths) {
        // Цикл для прохода по всем элементам массива значений
        for (int i = 0; i < values.length; i++) {
            try {
                // Вывод каждого значения, дополненного пробелами до максимальной длины столбца
                System.out.print("| " + values[i] + " ".repeat(Math.max(0, maxLengths.get(i) - values[i].length() + 1)));
            } catch (NullPointerException e) {
                System.out.print("| " + values[i] + " ".repeat(maxLengths.get(i) - 3));
            }
        }
        // Вывод символа "|" в конце строки
        System.out.println("|");
    }

    // Метод для вывода заголовка таблицы
    public static void printHeader(List<String> headers, List<Integer> maxLengths) {
        // Преобразование списка заголовков в массив и вывод отформатированной строки заголовка
        String[] headerArray = headers.toArray(new String[0]);
        printFormattedRow(headerArray, maxLengths);
        // Вывод строки разделителя "-"
        System.out.println("-".repeat(maxLengths.stream().mapToInt(Integer::intValue).sum() + maxLengths.size() * 3 + 1));
    }

    // Метод для вывода данных таблицы
    public static void printData(List<LabWork> labWorks, List<Integer> maxLengths) {
        // Цикл для прохода по всем объектам LabWork в списке
        for (LabWork labWork : labWorks) {
            // Получение массива данных из объекта LabWork и вывод отформатированной строки данных
            String[] rowData = labWork.getMassiv();
            printFormattedRow(rowData, maxLengths);
        }
    }

    static void show(LinkedHashSet<LabWork> linkedHashSet) {
        // Создание списка с нулевыми значениями для хранения максимальной длины каждого столбца
        List<Integer> maxLengths = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        // Создание списка заголовков таблицы
        List<String> headers = Arrays.asList(
                "id",
                "name",
                "coordinates_x",
                "coordinates_y",
                "creationDate",
                "minimalPoint",
                "difficulty",
                "author_name",
                "author_birthday",
                "author_height",
                "author_passportID");
        var j = 0;
        // Определение максимальной длины каждого столбца на основе данных из LinkedHashSet
        for (LabWork element : linkedHashSet) {
            int[] lengths = element.getLen();
            for (int i = 0; i < lengths.length; i++) {
                // Обновление максимальной длины для каждого столбца, если необходимо
                maxLengths.set(i, Math.max(
                        maxLengths.get(i),
                        Math.max(lengths[i], headers.get(i).length()))
                );
            }
            j++;
        }

        // Вывод заголовка таблицы
        printHeader(headers, maxLengths);

        // Вывод данных таблицы
        // Преобразование LinkedHashSet в List и вывод данных таблицы
        List<LabWork> labWorkList = new ArrayList<>(linkedHashSet);
        printData(labWorkList, maxLengths);
        System.out.println("Total: " + j);
    }


//        System.out.println();

//        for (LabWork lab : Main.linkedHashSet) {
//            for (String element : lab.getMassiv()) {
//            }
//        }
//        System.out.println("\t\t\t\t\t\tid\t\tname\t\tcoordinates_x,\tcreationDate\t\t\tminimalPoint\tdifficulty\tauthor_name\tauthor_birthday\tauthor_height\tauthor_passportID");
//        System.out.println("\t\t\t\t\t\t\t\t\t\t\tcoordinates_y");
//        for (LabWork element : Main.linkedHashSet) {
//            System.out.println(element + ": " + "\t" + element.getString());
//        }

    static public void add() {
        System.out.println("Введите название для лабораторной работы: ");
        var in = new Scanner(System.in);
        var labName = in.nextLine();
        System.out.println("Введите координату x в формате Long: ");
        long coordinatesX = 0;
        var str = in.nextLine();
        var isValidInput = false;
        do {
            try {
                coordinatesX = Long.parseLong(str);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);
        isValidInput = false;
        System.out.println("Введите координату y в формате Double: ");
        var coordinatesY = 0.0;
        str = in.nextLine();
        do {
            try {
                coordinatesY = Double.parseDouble(str);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);

        System.out.println("Введите минимальную оценку: ");
        float point = 0.0f;
        str = in.nextLine();
        isValidInput = false;
        do {
            try {
                point = Float.parseFloat(str);
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);
        isValidInput = false;
        System.out.println("Введите сложность (EASY, NORMAL, VERY_HARD, IMPOSSIBLE, INSANE): ");
        str = in.nextLine();
        Difficulty difficulty = null;
        do {
            try {
                difficulty = Difficulty.getByIndexOrName(str);
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);
        isValidInput = false;
        System.out.println("Введите имя автора: ");
        var personName = in.nextLine();
        if (personName.trim().equals("")) {
            Main.linkedHashSet.add(LabWork.builder()
                    .name(labName)
                    .coordinates(String.valueOf(coordinatesX), String.valueOf(coordinatesY))
                    .minimalPoint(point)
                    .difficulty(String.valueOf(difficulty))
                    .creationDate(ZonedDateTime.now())
                    .author(new Person())
                    .randomID()
                    .build());
        } else {
            Date authorBirthday = null;
            System.out.println("Введите день рождения автора в формате YYYY-MM-DD: ");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            str = in.nextLine();
            do {
                try {
                    authorBirthday = sdf.parse(str);
                    isValidInput = true;
                } catch (ParseException e) {
                    System.out.println("Неправильно, попробуй еще раз");
                    str = in.nextLine();
                }
            } while (!isValidInput);
            System.out.println("Введите рост автора: ");
            str = in.nextLine();
            isValidInput = false;
            Integer height = (null);
            do {
                try {
                    height = Integer.parseInt(str);
                    isValidInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Неправильно, попробуй еще раз");
                    str = in.nextLine();
                }
            } while (!isValidInput);
            System.out.println("Введите ID автора: ");
            var id = in.nextLine();

            Main.linkedHashSet.add(LabWork.builder()
                    .name(labName)
                    .coordinates(String.valueOf(coordinatesX), String.valueOf(coordinatesY))
                    .minimalPoint(point)
                    .creationDate(ZonedDateTime.now())
                    .difficulty(String.valueOf(difficulty))
                    .author(new Person(personName, authorBirthday, height, id))
                    .randomID()
                    .build());
        }
    }

    static void updateID(Integer ID) {
        Main.linkedHashSet.removeIf(x -> x.getID() == ID); //удаление изначального элемента
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                filenameDATALabWorks)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = LabWorkUtility.customSplit(line, ',');
                if (parts[0].equals(ID)) {
                    LabWorkUtility.addElementToSet(parts);
                }
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static void removeByID(Integer ID) throws NumberFormatException {
        Main.linkedHashSet.removeIf(x -> x.getID() == ID); //удаление изначального элемента
    }

    static void clear() {
        Main.linkedHashSet.clear();
    }

    static void save() {
        var str = "";

        String filename = filenameDATALabWorks; // Путь к вашему файлу

        var file = new File(filename);
        file.delete();
        LabWorkUtility.writeStrToFilename("id,name,coordinates_x,coordinates_y,creationDate,minimalPoint,difficulty,author_name,author_birthday,author_height,author_passportID\n", filename);

        for (LabWork element : Main.linkedHashSet) {
            str = str + element.getString() + "\n";
        }
        LabWorkUtility.writeStrToFilename(str, filenameDATALabWorks);
    }

    static void executeScript(String filename) {
        String str = null;
        str = LabWorkUtility.readFromFile(filename, false);
        String[] parts = LabWorkUtility.customSplit(str, '\n');
        for (var element : parts) {
            if (element.equals("executeScript")) {
                break;
            } else {
                LabWorkUtility.commandParser(element);
            }
        }
    }

    static boolean exit() {
        return false;
    }

    public static void removeGreater(int ID) {
        Main.linkedHashSet.removeIf(element -> element.getID() > ID);
    }

    static void removeLower(int ID) {
        Main.linkedHashSet.removeIf(element -> element.getID() < ID);
    }

    static void sumOfMinimalPoint() {
        float summator = 0;
        for (LabWork element : Main.linkedHashSet) {
            summator += element.getMinimalPoint();
        }
        System.out.println(summator);
    }

    static void filterByDifficulty(String difficulty) {
        LinkedHashSet<LabWork> lhs = new LinkedHashSet<>();
        for (LabWork element : Main.linkedHashSet) {
            if ((element.getDifficulty()).equals(Difficulty.getByIndexOrName(difficulty))) {
                lhs.add(element);
            }
        }
        show(lhs);
    }

    static void filterLessThanDifficulty(String difficulty) {
        LinkedHashSet<LabWork> lhs = new LinkedHashSet<>();
        for (LabWork element : Main.linkedHashSet) {
            if (element.getDifficulty().compareTo(Difficulty.getByIndexOrName(difficulty)) < 0) {
                lhs.add(element);
            }
        }
        show(lhs);
    }

    public static String filenameDATALabWorks = "rsc/dataLabWork.csv";

}
