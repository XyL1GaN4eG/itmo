package Server.commandManager.commands;

import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(true);
    }

    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> element.getID() > Integer.parseInt((String)this.getArgument()));
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
