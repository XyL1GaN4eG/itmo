package Server.commandManager.commands;

import Server.commandManager.Command;

public class Clear extends Command {

    public Clear() {
        super(false);
    }

    @Override
    public String execute() {
        if (LabWorkSet.labWorks != null) {
            LabWorkSet.labWorks.clear();
        } else {
            output = ("Коллекция уже пуста\n");
        }
        return  output + new Show().execute();
    }


    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.NULL;
    }
}
