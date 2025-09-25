package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class RemoveLower extends Command {

    public RemoveLower() {
        super(true);
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

}
