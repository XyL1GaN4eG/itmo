package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class SumOfMinimalPoint extends Command {

    public SumOfMinimalPoint() {
        super(false);
    }

    @Override
    public String getName() {
        return "sum_of_minimal_point";
    }

}
