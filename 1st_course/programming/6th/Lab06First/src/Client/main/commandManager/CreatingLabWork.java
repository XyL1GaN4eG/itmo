package Client.main.commandManager;

import General.dataClasses.Difficulty;
import General.dataClasses.LabWork;
import General.dataClasses.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

import static Client.main.LabWorkUtility.customSplit;

public class CreatingLabWork {

    public static LabWork CLI() {
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
                System.err.println("Неправильно, попробуй еще раз");
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
                System.err.println("Неправильно, попробуй еще раз");
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
                System.err.println("Неправильно, попробуй еще раз");
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
                System.err.println("Неправильно, попробуй еще раз");
                str = in.nextLine();
            }
        } while (!isValidInput);
        isValidInput = false;
        System.out.println("Введите имя автора: ");
        var personName = in.nextLine();
        var labwork = new LabWork();
        if (personName.trim().equals("")) {
            labwork = LabWork.builder()
                    .name(labName)
                    .coordinates(String.valueOf(coordinatesX), String.valueOf(coordinatesY))
                    .minimalPoint(point)
                    .difficulty(String.valueOf(difficulty))
                    .creationDate(ZonedDateTime.now())
                    .author(new Person())
                    .randomID()
                    .build();
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
                    System.err.println("Неправильно, попробуй еще раз");
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
                    System.err.println("Неправильно, попробуй еще раз");
                    str = in.nextLine();
                }
            } while (!isValidInput);
            System.out.println("Введите ID автора: ");
            var id = in.nextLine();

            labwork = (LabWork.builder()
                    .name(labName)
                    .coordinates(String.valueOf(coordinatesX), String.valueOf(coordinatesY))
                    .minimalPoint(point)
                    .creationDate(ZonedDateTime.now())
                    .difficulty(String.valueOf(difficulty))
                    .author(new Person(personName, authorBirthday, height, id))
                    .randomID()
                    .build());
        }
//        LabWorkSet.labWorks.add(labwork);

        return labwork;
    }

    public static LabWork nonUser(String string) {
        try {
            return stringToLabwork(customSplit(String.valueOf(string), ','));
        } catch (Exception e) {
            System.out.println("Что то пошло не так......");
        }
        return null;
    }

    private static LabWork stringToLabwork(String[] parts) throws Exception {
        try {
            return (LabWork.builder()
                    .randomID(parts[0])
                    .difficulty(parts[6])
                    .coordinates(parts[2], parts[3])
                    .name(parts[1])
                    .creationDate(ZonedDateTime.parse(parts[4]))
                    .minimalPoint(Float.parseFloat(parts[5]))
                    .author(new Person(parts[7], parts[8], parts[9], parts[10]))
                    .build());
        } catch (IndexOutOfBoundsException e) {
            return (LabWork.builder()
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

}
