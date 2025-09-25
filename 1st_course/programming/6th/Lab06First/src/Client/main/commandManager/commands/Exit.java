package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class Exit extends Command {
    public Exit() {
        super(false);
    }

    @Override
    public void execute() {
        System.exit(0);
        System.out.println("Вы завершили работу.");
    }

    public String getName() {
        return "exit";
    }
}
