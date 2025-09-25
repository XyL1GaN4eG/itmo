package commandManager.commands;

import commandManager.Command;
import commandManager.CommandMode;
import dataClasses.Difficulty;
import dataClasses.LabWork;
import dataClasses.LabWorkSet;
import dataClasses.Person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;

//import static commandManager.commands.LabWorkMethods.show;

public class AddForCLI extends Command {
    CommandMode handler;

    public AddForCLI() {
        super(false);
//        this.handler = handler;
    }

    @Override
    public void execute() {
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
        LabWorkSet.labWorks.add(labwork);
        Iterator<LabWork> iterator = LabWorkSet.labWorks.iterator();

        LabWork lastElement = null;
        LinkedHashSet<LabWork> lhs0 = new LinkedHashSet<LabWork>();
        // Проходимся по всем элементам
        while (iterator.hasNext()) {
            lastElement = iterator.next();
        }
        lhs0.add(lastElement);
        var show = new Show();
        show.execute();
    }


//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("AddForCLI has no arguments!");
//            return false;
//        }
//    }

    @Override
    public String getName() {
        return "add";
    }

//    @Override
//    public boolean checkArgument(String inputArgument) {
//        if (inputArgument == null || inputArgument.trim().isEmpty())
//            return true;
//        else {
//            System.out.println("Show has no arguments!");
//            return false;
//        }
//    }
}