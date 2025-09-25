package Server.commandManager.commands;

import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;
import Server.commandManager.FileManager;

public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public String execute() {
        return (FileManager.readFromFile("helper.txt", false));
    }

    @Override
    public String getName() {return "help";}

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.NULL;
    }


}
