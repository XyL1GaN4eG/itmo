package Client.main;



import Client.main.General.dataClasses.Difficulty;
import Client.main.General.dataClasses.LabWork;
import Client.main.General.dataClasses.TypesOfArguments;
import Client.main.General.net.Request;

import java.util.HashMap;
import java.util.Scanner;

import static Client.main.General.LabWorkUtility.customSplit;


//TODO: проверить работает ли вообще

/**
 * Класс Validation предоставляет методы для валидации и обработки команд, введенных пользователем.
 * Он проверяет введенные команды и аргументы, создает и возвращает соответствующий объект запроса.
 */
public class Validation {

    /**
     * Валидирует введенные пользователем команды и создает соответствующие объекты запроса.
     *
     * @param map Хэш-карта, содержащая команды и соответствующие им типы аргументов.
     * @return Объект запроса, соответствующий введенной команде и аргументу.
     */
    public static Request validate(HashMap<String, TypesOfArguments> map) {
        var in = new Scanner(System.in);
        while (true) {
            var input = customSplit(in.nextLine(), ' ');
            var type = map.get(input[0]);
            if (type != null) {
                String commandName = input[0];
                try {
                    return commandHandler(commandName, type, input);
                } catch (IllegalArgumentException ignore) {}
            }

        }
    }
    private static Request commandHandler(String commandName,
                                          TypesOfArguments type,
                                          String[] input) {
        Object argument = null;
        switch (type) {
            case NULL:
                if (input.length == 1) {
                    //то возвращаем обычный реквест
                    return new Request(commandName);
                } else {
                    System.err.println(
                            "Некорректное количество аргументов!" +
                                    "\nПопробуйте ввести команду \"help\" без кавычек");
                    throw new IllegalArgumentException();
                }
            case INTEGER:
                try {
                    argument = Integer.parseInt(input[1]);
                    return new Request(commandName, argument);
                } catch (NumberFormatException e) {
                    System.err.println(e +
                            "\nЭта команда принимает только целые числа, попробуйте ещё раз");
                    throw new IllegalArgumentException();
                }
            case LABWORK:
                LabWork labWork = CreateNewLabwork.Create();
                argument = new Request(commandName, labWork);
                break;
            case DIFFICULTY:
                try {
                    argument = Difficulty.getByIndexOrName(input[1]);
                } catch (IllegalArgumentException e) {
                    System.err.println(e + "\nЭта команда принимает значения сложности, " +
                            "введите одно из следующих значений, или соответствующее ему значение:\n" +
                            "    EASY       | 0 \n" +
                            "    NORMAL     | 1 \n" +
                            "    VERY_HARD  | 2 \n" +
                            "    IMPOSSIBLE | 3 \n" +
                            "    INSANE     | 4 \n");
                    throw new IllegalArgumentException();
                }
                break;
            case STRING:
                argument = input[1];
            default:
                System.err.println("Команда не найдена!");
        }
        return new Request(commandName, argument);

    }
}

