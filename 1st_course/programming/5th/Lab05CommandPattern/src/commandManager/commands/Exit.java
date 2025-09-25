package commandManager.commands;

import commandManager.Command;

public class Exit extends Command {
    public Exit() {
        super(false);
    }

    @Override
    public void execute() {
        System.out.println("Программа закрывается...");

        System.exit(0);
    }

    @Override
    public String getName() {return "exit";}
}
