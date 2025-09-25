package Server.commandManager.commands;

import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

public class RemoveByID extends Command {

    public RemoveByID() {
        super(true);
    }

    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> String.valueOf(element.getID()).equals(String.valueOf(getArgument())));
        return null;
    }

    @Override
    public String getName() {
        return "Remove By ID";
    }
}
