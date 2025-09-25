package commandManager.commands;

import commandManager.Command;

public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public void execute() {
        System.out.println(FileManager.readFromFile("helper.txt", false));
    }

    @Override
    public String getName() {return "help";}

}
