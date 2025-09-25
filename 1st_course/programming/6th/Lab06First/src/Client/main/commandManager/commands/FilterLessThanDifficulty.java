package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class FilterLessThanDifficulty extends Command {

    public FilterLessThanDifficulty() {
        super(true);
    }
    @Override
    public String getName() {
        return "filter_less_than_difficulty";
    }

}
