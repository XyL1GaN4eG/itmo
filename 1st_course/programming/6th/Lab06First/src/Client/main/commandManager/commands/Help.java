package Client.main.commandManager.commands;

//import Server.commandManager.Command;

import Client.main.commandManager.Command;

public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public String getName() {
        return "help";
    }

}
