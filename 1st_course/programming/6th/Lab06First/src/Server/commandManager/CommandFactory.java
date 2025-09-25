package Server.commandManager;

import Server.commandManager.commands.*;

public class CommandFactory {

    public static Command createCommand(String commandName) {
        switch (commandName) {
            case "help":
                return new Help();
            case "info":
                return new Info();
            case "show":
                return new Show();
            case "add":
                return new Add();
            case "update_by_id":
                return new UpdateId();
            case "remove_by_id":
                return new RemoveByID();
            case "clear":
                return new Clear();
            case "execute_script":
                return new ExecuteScript();
            case "remove_greater":
                return new RemoveGreater();
            case "remove_lower":
                return new RemoveLower();
            case "sum_of_minimal_point":
                return new SumOfMinimalPoint();
            case "filter_by_difficulty":
                return new FilterByDifficulty();
            case "filter_lass_than_difficulty":
                return new FilterLessThanDifficulty();
            case "history":
                return new HistoryCommand();

//            case "add":
//                return new Add(arguments);
            // Добавьте сюда другие команды...
            default:
                throw new IllegalArgumentException("Unknown command: " + commandName);
        }
    }
}
