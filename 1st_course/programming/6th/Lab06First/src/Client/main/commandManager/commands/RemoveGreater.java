package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(true);
    }

    @Override
    public String getName() {
        return "remove_greater";
    }
}
