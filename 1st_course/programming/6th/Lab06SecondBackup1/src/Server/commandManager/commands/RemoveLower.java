package Server.commandManager.commands;

import General.dataClasses.LabWorkSet;
import Server.commandManager.Command;

public class RemoveLower extends Command {

    public RemoveLower() {
        super(false);
    }


    //TODO: переписать чтобы сравнивались не элементы, а айдишники лабворков (аргумент должен быть не интом, а айдишником
    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> element.getID() < (int) this.getArgument());
        return null;
    }

    @Override
    public String getName() {
        return "remove_lower";
    }

    @Override
    public boolean acceptsCollectionElementOrVariable() {
        return true;
    }
}