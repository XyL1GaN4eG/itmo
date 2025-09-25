package commandManager.commands;

import commandManager.Command;
import dataClasses.LabWorkSet;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(true);
    }

    @Override
    public void execute() {
        LabWorkSet.labWorks.removeIf(element -> element.getID() > (int) this.getArgument());
    }

    @Override
    public String getName() {
        return null;
    }
}
