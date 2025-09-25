package Server.commandManager.commands;

import Server.commandManager.Command;

public class FilterByDifficulty extends Command {
    public FilterByDifficulty() {super(true);}

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String getName() {
        return "filter_by_difficulty";
    }

    @Override
    public boolean acceptsCollectionElementOrVariable() {
        return false;
    }

}
