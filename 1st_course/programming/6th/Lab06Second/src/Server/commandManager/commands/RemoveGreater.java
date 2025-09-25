package Server.commandManager.commands;

import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

public class RemoveGreater extends Command {

    public RemoveGreater() {
        super(false);
    }

    //TODO: переписать чтобы сравнивались не элементы, а айдишники лабворков (аргумент должен быть не интом, а айдишником
    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> element.getID() > Integer.parseInt((String) this.getArgument()));
        return null;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.LABWORK;
    }

}