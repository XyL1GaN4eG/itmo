package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class Info extends Command {
    public Info() {
        super(false);
    }

    @Override
    public String getName() {
        return "info";
    }
}
