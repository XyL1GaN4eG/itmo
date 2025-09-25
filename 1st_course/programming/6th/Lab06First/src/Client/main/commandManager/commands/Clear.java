package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class Clear extends Command {
    public Clear() {
        super(true);
    }

    @Override
    public String getName() {
        return "clear";
    }
}
