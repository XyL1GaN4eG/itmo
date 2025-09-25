package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class UpdateId extends Command {

    public UpdateId() {
        super(true);
    }

    @Override
    public String getName() {
        return "update_id";
    }
}
