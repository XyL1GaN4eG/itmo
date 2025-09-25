package Server.commandManager.commands;

import Server.commandManager.Command;

public class FilterLessThanDifficulty extends Command {
    public FilterLessThanDifficulty() {super(true);}

    @Override
    public String execute() {
        return null;
    }

    @Override
    public String getName() {return "filter_less_than_difficulty";}

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.DIFFICULTY;
    }

}
