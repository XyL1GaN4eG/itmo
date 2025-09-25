import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

public class LabWorkMethods {
    public static void main(String[] args) throws Exception {
        help();
    }

    static void help() {
        System.out.println(LabWorkUtility.readFromFile("helper.txt", false));
    }

    static void info() {
        System.out.println("Количество элементов: " + Main.linkedHashSet.size());
        System.out.print("Дата создания: time");
        LabWorkUtility.readFromFile("data.txt", false);
        System.out.println("Пустая ли коллекция: " + Main.linkedHashSet.isEmpty());
    }

    static void show() {
        System.out.println("\t\t\t\t\t\tid\t\tname\t\tcoordinates_x,\tcreationDate\t\t\tminimalPoint\tdifficulty\tauthor_name\tauthor_birthday\tauthor_height\tauthor_passportID");
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tcoordinates_y");
        for (LabWork element : Main.linkedHashSet) {
            System.out.println(element + ": " + "\t" + element.getString());
        }
    }

    static public void add() throws Exception, ParseException {
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
                difficulty = Difficulty.valueOf(String.valueOf(str));
                isValidInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);
        isValidInput = false;
        System.out.println("Введите имя автора: ");
        var personName = in.nextLine();
        Date authorBirthday = null;
        System.out.println(personName + personName.isEmpty() + personName.length() + personName.getClass());
        if (personName.trim().length() > 0) { // если персон существует
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
            Main.linkedHashSet.add(new LabWork(
                    0,
                    labName,
                    new Coordinates(coordinatesX,
                            coordinatesY), //coordinates
                    ZonedDateTime.now(),              //time
                    point, //minimal point
                    difficulty, //difficulty
                    new Person(personName,        //author name
                            authorBirthday,
                            height, //height
                            id)));               //passport id
        }
        else { //если персона не существует
            Main.linkedHashSet.add(new LabWork(
                    0,
                    labName,
                    new Coordinates(coordinatesX,
                            coordinatesY), //coordinates
                    ZonedDateTime.now(),              //time
                    point, //minimal point
                    difficulty, //difficulty
                    null));               //passport id
        }
    }

    static void updateID(Integer ID) throws ParseException {
        try {
            Main.linkedHashSet.removeIf(x -> x.getID() == ID); //удаление изначального элемента
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть в формате числа");
        }
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

    static void removeByID(Integer ID) {
        try {
            Main.linkedHashSet.removeIf(x -> x.getID() == ID); //удаление изначального элемента
        } catch (NumberFormatException e) {
            System.out.println("ID должен быть в формате числа");
        }
    }

    static void clear() {
        Main.linkedHashSet.clear();
    }

    static void save() {
        var str = "";

        String filename = filenameDATALabWorks; // Путь к вашему файлу

        var file = new File("src/" + filename);
        file.delete();
        LabWorkUtility.writeStrToFilename("id,name,coordinates_x,coordinates_y,creationDate,minimalPoint,difficulty,author_name,author_birthday,author_height,author_passportID\n", filename);

        for (LabWork element : Main.linkedHashSet) {
            str = str + element.getAll()+ "\n";
        }
        LabWorkUtility.writeStrToFilename(str, filenameDATALabWorks);
    }

    static void executeScript(String filename) throws Exception {
        String str = null;


            str = LabWorkUtility.readFromFile(filename, false);
        String[] parts = LabWorkUtility.customSplit(str, '\n');
        for (var element : parts) {
            if (element.equals("executeScript")) {
                break;
            } else {
//                    try {
                LabWorkUtility.switchCaser(element);

//                    } catch (FileNotFoundException e) {
//                        System.out.println("Файле не найден(");
//                    }
                }
            }
//        } else {
//            System.out.println("Такого файла не существует");
        }

    static boolean exit() {
        return false;
    }

    static void removeGreater(int ID) {
        for (LabWork element : Main.linkedHashSet) {
            if (element.getID() > ID) {
                Main.linkedHashSet.removeIf(x -> x.getID() == ID);
            }
        }
    }

    static void removeLower(int ID) {
        for (LabWork element : Main.linkedHashSet) {
            if (element.getID() < ID) {
                Main.linkedHashSet.removeIf(x -> x.getID() == ID);
            }
        }
    }

    static void sumOfMinimalPoint() {
        float summator = 0;
        for (LabWork element : Main.linkedHashSet) {
            summator += element.getMinimalPoint();
        }
        System.out.println(summator);
    }

    static void filterByDifficulty(Difficulty difficulty) {
        for (LabWork element : Main.linkedHashSet) {
            if (element.getDifficulty().equals(difficulty)) {
                System.out.println(element.getAll());
            }
        }
    }

    static void filterLessThanDifficulty(Difficulty difficulty) {
        for (LabWork element : Main.linkedHashSet) {
            if (element.getDifficulty().compareTo(difficulty) < 0) {
                System.out.println(element);
            }
        }
    }
    public static String filenameDATALabWorks = "dataLabWork.csv";

}
