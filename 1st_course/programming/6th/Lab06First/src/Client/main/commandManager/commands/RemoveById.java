package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class RemoveById extends Command {
    public RemoveById() {
        super(false);
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }
}
