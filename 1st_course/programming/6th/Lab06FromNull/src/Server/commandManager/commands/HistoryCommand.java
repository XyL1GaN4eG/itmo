package Server.commandManager.commands;

import General.dataClasses.History;
import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;

public class HistoryCommand extends Command {

    public HistoryCommand() {
        super(false);
    }

    @Override
    public String execute() {
        return History.view();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.NULL;
    }


}
