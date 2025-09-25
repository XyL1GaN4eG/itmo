package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class ExecuteScript extends Command {
    public ExecuteScript() {
        super(true);
    }

    @Override
    public String getName() {
        return "execute_script";
    }
}
