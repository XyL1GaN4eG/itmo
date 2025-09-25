package Client.main.commandManager;

//import Client.main.commandManager.commands.Add;
import Client.main.commandManager.commands.*;
import General.exceptions.UnknownCommandException;

import java.util.LinkedHashMap;

public class CommandManager {

    private LinkedHashMap<String, Command> commandMap;
//    private LinkedHashMap<String, Command> commandMapArg;

    public CommandManager() {
        commandMap = new LinkedHashMap<>();

        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("add", new Add());
        commandMap.put("update_id", new UpdateId());
        commandMap.put("remove_by_id", new RemoveById());
        commandMap.put("clear", new Clear());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("sum_of_minimal_point", new SumOfMinimalPoint());
        commandMap.put("history", new History());
        commandMap.put("exit", new Exit());
//
//        switch (mode) {
//            case CLI_UserMode -> commandMap.put("add", new AddForCLI());
//            case NonUserMode -> commandMap.put("add", new AddForNonUserMode());
//        }
    }

    public Command getArgument(String[] args) {
        try {
            if (args.length > 1) {
                commandMap.get(args[0]).checkArgument(args[1]);
                commandMap.get(args[0]).setArgument(args[1]);
            } else {
                commandMap.get(args[0]).checkArgument("");
            }
            return commandMap.get(args[0]);
        } catch (UnknownCommandException e) {
            System.out.println("Проверьте корректность введенной команды");
            return null;
        }
    }

//    public Command getCommand() {
//        commandMap.get(args[0])
//    }
}
