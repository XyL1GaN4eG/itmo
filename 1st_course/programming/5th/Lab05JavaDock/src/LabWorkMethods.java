import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;


/**
 * Класс LabWorkMethods содержит различные методы для работы с коллекцией LabWork.
 */
public class LabWorkMethods {
    public static void main(String[] args) {
        help();
    }

    /**
     * Выводит справку по доступным командам.
     */
    static void help() {
        System.out.println(LabWorkUtility.readFromFile("helper.txt", false));
    }

    /**
     * Выводит информацию о коллекции.
     */
    static void info() {
        System.out.println("Количество элементов: " + Main.linkedHashSet.size() + "\n");
        System.out.print("Дата создания: " + LabWorkUtility.readFromFile("data.txt", false) + "\n");
        System.out.println("Пустая ли коллекция: " + Main.linkedHashSet.isEmpty() + "\n");
    }

    /**
     * Выводит отформатированную строку.
     *
     * @param values     Массив значений для вывода.
     * @param maxLengths Список максимальных длин столбцов.
     */
    public static void printFormattedRow(String[] values, List<Integer> maxLengths) {
        for (int i = 0; i < values.length; i++) {
            try {
                System.out.print("| " + values[i] + " ".repeat(Math.max(0, maxLengths.get(i) - values[i].length() + 1)));
            } catch (NullPointerException e) {
                System.out.print("| " + values[i] + " ".repeat(maxLengths.get(i) - 3));
            }
        }
        System.out.println("|");
    }

    /**
     * Выводит заголовок таблицы.
     *
     * @param headers    Список заголовков.
     * @param maxLengths Список максимальных длин столбцов.
     */
    public static void printHeader(List<String> headers, List<Integer> maxLengths) {
        String[] headerArray = headers.toArray(new String[0]);
        printFormattedRow(headerArray, maxLengths);
        System.out.println("-".repeat(maxLengths.stream().mapToInt(Integer::intValue).sum() + maxLengths.size() * 3 + 1));
    }

    /**
     * Выводит данные таблицы.
     *
     * @param labWorks   Список объектов LabWork.
     * @param maxLengths Список максимальных длин столбцов.
     */
    public static void printData(List<LabWork> labWorks, List<Integer> maxLengths) {
        for (LabWork labWork : labWorks) {
            String[] rowData = labWork.getMassiv();
            printFormattedRow(rowData, maxLengths);
        }
    }

    /**
     * Отображает содержимое коллекции.
     *
     * @param linkedHashSet Коллекция LabWork.
     */
    static void show(LinkedHashSet<LabWork> linkedHashSet) {
        List<Integer> maxLengths = new ArrayList<>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
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
        for (LabWork element : linkedHashSet) {
            int[] lengths = element.getLen();
            for (int i = 0; i < lengths.length; i++) {
                maxLengths.set(i, Math.max(
                        maxLengths.get(i),
                        Math.max(lengths[i], headers.get(i).length()))
                );
            }
            j++;
        }
        printHeader(headers, maxLengths);
        List<LabWork> labWorkList = new ArrayList<>(linkedHashSet);
        printData(labWorkList, maxLengths);
        System.out.println("Total: " + j);
    }

    /**
     * Добавляет новый элемент в коллекцию.
     */
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
        Iterator<LabWork> iterator = Main.linkedHashSet.iterator();

        LabWork lastElement = null;
        LinkedHashSet<LabWork> lhs0 = new LinkedHashSet<LabWork>();
        // Проходимся по всем элементам
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }
        lhs0.add(lastElement);
        show(lhs0);
    }

    static public void add(String str) {
        try {
            LabWorkUtility.addElementToSet(LabWorkUtility.customSplit(str, ','));
        } catch (Exception e) {
            System.out.println("Что то пошло не так......");
        }
    }


    /**
     * Обновляет лабораторную работу по указанному ID.
     *
     * @param ID ID лабораторной работы для обновления.
     */
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

    /**
     * Удаляет лабораторную работу по указанному ID.
     *
     * @param ID ID лабораторной работы для удаления.
     */
    static void removeByID(Integer ID) throws NumberFormatException {
        Main.linkedHashSet.removeIf(x -> x.getID() == ID); //удаление изначального элемента
    }

    /**
     * Очищает коллекцию от всех элементов.
     */
    static void clear() {
        Main.linkedHashSet.clear();
    }

    /**
     * Сохраняет коллекцию лабораторных работ в файл.
     */
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

    /**
     * Выполняет скрипт из указанного файла.
     *
     * @param filename Имя файла со скриптом.
     */
    static void executeScript(String filename) {
        String str = LabWorkUtility.readFromFile(filename, false);
        String[] parts = LabWorkUtility.customSplit(str, '\n');
        for (String element : parts) {
            try {
                commandStack.push(element); // Добавляем команду в стек
                if (executeLOG.contains(filename)) {
                    System.out.println("а можно без рекурсии пж....");
                    break;
                } else {
                    LabWorkUtility.commandParser(element);
                }
                commandStack.pop(); // После выполнения команды извлекаем ее из стека
            } catch (Exception e) {
//                System.out.println("Ошибка при выполнении команды: " + e.getMessage());
                commandStack.pop(); // Извлекаем команду из стека, чтобы продолжить выполнение скрипта
            }
        }
    }

    static Stack<String> commandStack = new Stack<>();
    static ArrayList<String> executeLOG = new ArrayList<>();

    /**
     * Завершает выполнение программы.
     *
     * @return Всегда возвращает false.
     */
    static boolean exit() {
        return false;
    }

    /**
     * Удаляет все элементы, чей ID больше заданного.
     *
     * @param ID ID, с которым сравниваются ID элементов коллекции.
     */
    public static void removeGreater(int ID) {
        Main.linkedHashSet.removeIf(element -> element.getID() > ID);
    }

    /**
     * Удаляет все элементы, чей ID меньше заданного.
     *
     * @param ID ID, с которым сравниваются ID элементов коллекции.
     */
    static void removeLower(int ID) {
        Main.linkedHashSet.removeIf(element -> element.getID() < ID);
    }

    /**
     * Выводит сумму минимальных баллов всех лабораторных работ.
     */
    static void sumOfMinimalPoint() {
        float summator = 0;
        for (LabWork element : Main.linkedHashSet) {
            summator += element.getMinimalPoint();
        }
        System.out.println(summator);
    }

    /**
     * Фильтрует коллекцию по указанной сложности и выводит результат.
     *
     * @param difficulty Уровень сложности для фильтрации.
     */
    static void filterByDifficulty(String difficulty) {
        LinkedHashSet<LabWork> lhs = new LinkedHashSet<>();
        for (LabWork element : Main.linkedHashSet) {
            if ((element.getDifficulty()).equals(Difficulty.getByIndexOrName(difficulty))) {
                lhs.add(element);
            }
        }
        show(lhs);
    }

    /**
     * Фильтрует коллекцию, оставляя элементы с уровнем сложности меньше указанного, и выводит результат.
     *
     * @param difficulty Уровень сложности для фильтрации.
     */
    static void filterLessThanDifficulty(String difficulty) {
        LinkedHashSet<LabWork> lhs = new LinkedHashSet<>();
        for (LabWork element : Main.linkedHashSet) {
            if (element.getDifficulty().compareTo(Difficulty.getByIndexOrName(difficulty)) < 0) {
                lhs.add(element);
            }
        }

//        var minElem = Main.linkedHashSet.stream().mapToInt(())

        show(lhs);
    }

    public static String filenameDATALabWorks = "rsc/dataLabWork.csv";

}
