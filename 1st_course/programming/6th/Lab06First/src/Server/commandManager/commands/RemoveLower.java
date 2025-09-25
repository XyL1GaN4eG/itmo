package Server.commandManager.commands;

import General.dataClasses.LabWorkSet;
import Server.commandManager.Command;

public class RemoveLower extends Command {

    public RemoveLower() {
        super(true);
    }

    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> element.getID() < (int) this.getArgument());
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}