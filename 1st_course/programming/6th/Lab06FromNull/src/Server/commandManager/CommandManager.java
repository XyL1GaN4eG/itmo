package Server.commandManager;

import General.dataClasses.TypesOfArguments;
import General.exceptions.UnknownCommandException;
import Server.commandManager.commands.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Optional;


public class CommandManager {
    private LinkedHashMap<String, Command> commandMap;
    private HashMap<String, Boolean[]> classes;


//    public CommandManager(){
//        commandMap = new LinkedHashMap<>();
//
//        commandMap.put("help", new Help());
//        commandMap.put("info", new Info());
//        commandMap.put("show", new Show());
//        commandMap.put("update_id", new UpdateId());
//        commandMap.put("clear", new Clear());
//        commandMap.put("save", new Save());
//        commandMap.put("execute_script", new ExecuteScript());
//        commandMap.put("exit", new Exit());
////        commandMap.put("history", new History());
////        commandMap.put("sum_of_meters_above_sea_level", new SumOfMetersAboveSeaLevel());
////        commandMap.put("print_descending", new PrintDescending());
//        commandMap.put("print_field_descending_meters_above_sea_level", new PrintFieldDescendingMetersAboveSeaLevel());
////        commandMap.put("remove_by_id", new RemoveById());
////        commandMap.put("add", new Add());
////        commandMap.put("add_if_min", new AddIfMin());
//        commandMap.put("remove_greater", new RemoveGreater());
//    }

    /**
     * Constructor provides choice of commands behavior: ex. userMode or nonUserMode
     *
     * @see CommandMode
     * @since 2.0
     */
    public CommandManager() {
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("clear", new Clear());
        commandMap.put("save", new Save());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
//        commandMap.put("history", new History());
        commandMap.put("remove_by_id", new RemoveByID());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("add", new Add());
    }

    public HashMap<String, TypesOfArguments> getInfo() {
        var result = new HashMap<String, TypesOfArguments>();
        var keys = commandMap.keySet();

        for (String key : keys) {

            var info = commandMap.get(key).getInfo();

            TypesOfArguments value;
            System.out.println(key + " " + info[1]);
            value = (TypesOfArguments) info[1];
            // Добавляем полученные значения в результирующую карту
            result.put(key, value);
        }

//        while (commandMap.entrySet().iterator().hasNext()) {
//            var info = commandMap.entrySet().iterator().next().getValue().getInfo();
//            String key = (String) info[0];
//            TypesOfArguments value;
//            System.out.println(key + " " + info[1]);
//            value = (TypesOfArguments) info[1];
//            // Добавляем полученные значения в результирующую карту
//            result.put(key, value);
//
//        }
        return result;
    }

////    @SneakyThrows
//    public void executeCommand(String[] args) {
//        if (args.length > 1) {
//            try {
//                Optional.ofNullable(commandMap.get(args[0])).orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( ")
//                        ).setArgument(args[1]);
//            } catch (UnknownCommandException ignore) {}
//        }
//        try {
//            Optional.ofNullable(commandMap.get(args[0]))
//                    .orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( "))
//                    .execute();
//        } catch (UnknownCommandException ignore) {}
////        {throw new RuntimeException(e);}
//    }

//    public void executeCommand(String[] args) {
//        try {
//            // Проверяем, есть ли аргументы в массиве args
//            if (args.length > 1) {
//                // Получаем команду из map по имени (args[0]) с помощью Optional
//                Command command = Optional.ofNullable(commandMap.get(args[0]))
//                        // Если команда не найдена, бросаем исключение UnknownCommandException
//                        .orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( "));
//
//                // Устанавливаем аргумент команды (args[1])
//                command.setArgument(args[1]);
//            }
//
//            // Получаем команду из map по имени (args[0]) с помощью Optional
//            Command commandToExecute = Optional.ofNullable(commandMap.get(args[0]))
//                    // Если команда не найдена, бросаем исключение UnknownCommandException
//                    .orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( "));
//
//            // Выполняем найденную команду
//            commandToExecute.execute();
//
//            // Добавляем имя команды в историю выполненных команд
////            History.addToCommandsHistoryQueue(args[0]);
//
//        } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
//            // Обработка исключений при неправильных аргументах или отсутствии команды
//            System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
//            throw new CommandInterruptedException(e);
//
//        } catch (BuildObjectException | UnknownCommandException e) {
//            // Обработка исключений, связанных с ошибками при выполнении команды
//            System.err.println(e.getMessage());
//            throw new CommandInterruptedException(e);
//
//        } catch (Exception e) {
//            // Обработка непредвиденных исключений
//            System.err.println("В командном менеджере произошла непредвиденная ошибка! ");
//            throw new CommandInterruptedException(e);
//        }
//    }


    public void executeCommand(String[] args) {
        try {
            if (args.length > 1)
                Optional.ofNullable
                                (commandMap.get(args[0])).orElseThrow(
                                () -> new UnknownCommandException
                                        ("\nКоманды " + args[0] + " не обнаружено :( ")
                        )
                        .setArgument(args[1]);
            Optional.ofNullable
                            (commandMap.get(args[0]))
                    .orElseThrow(() -> new UnknownCommandException("\nКоманды " + args[0] + " не обнаружено :( "))
                    .execute();
//            History.addToCommandsHistoryQueue(args[0]);
        } catch (IllegalArgumentException | NullPointerException | NoSuchElementException e) {
            System.err.println("Выполнение команды пропущено из-за неправильных предоставленных аргументов! (" + e.getMessage() + ")");
//            throw new CommandInterruptedException(e);
        } catch (UnknownCommandException e) {
            System.err.println(e.getMessage());
//            throw new CommandInterruptedException(e);
        } catch (Exception e) {
//            System.err.println("В командном менеджере произошла непредвиденная ошибка! (" + e.getMessage() + ")");
//            throw new CommandInterruptedException(e);
//            System.out.println("");
        }

    }


}
