package Client.main;

import General.dataClasses.Difficulty;
import General.dataClasses.LabWork;
import General.dataClasses.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Scanner;

public class CreateNewLabwork {
    public static LabWork Create() {
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
            return labwork;
        }
        return labwork;
    }
}