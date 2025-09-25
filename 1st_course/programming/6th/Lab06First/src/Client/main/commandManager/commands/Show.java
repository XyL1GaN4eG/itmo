package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class Show extends Command {
    public Show() {
        super(false);
    }

    @Override
    public String getName() {
        return "show";
    }
}
