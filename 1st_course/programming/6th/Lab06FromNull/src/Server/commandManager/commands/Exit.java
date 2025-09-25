package Server.commandManager.commands;

import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;

public class Exit extends Command {

    public Exit() {
        super(false);
    }

    @Override
    public String execute() {
        var save = new Save();
        save.execute();
        return "Клиент отключился";
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.NULL;
    }
}
