package Client.main.commandManager.commands;

import Client.main.commandManager.Command;

public class FilterByDifficulty extends Command {

    public FilterByDifficulty() {
        super(true);
    }
    @Override
    public String getName() {
        return "filter_by_difficulty";
    }

}
