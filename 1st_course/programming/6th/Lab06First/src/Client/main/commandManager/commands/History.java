package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class History extends Command {

    public History() {
        super(false);
    }

    @Override
    public String getName() {return "history";}
}
