package Server.commandManager.commands;

import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

public class RemoveByID extends Command {

    public RemoveByID() {
        super(true);
    }


    //TODO: переписать чтобы сравнивались не элементы, а айдишники лабворков (аргумент должен быть не интом, а айдишником
    @Override
    public String execute() {
        LabWorkSet.labWorks.removeIf(element -> String.valueOf(element.getID()).equals(String.valueOf(getArgument())));
        return "Элемент с ID " + getArgument() + " удален!";
    }

    @Override
    public String getName() {
        return "remove_by_ID";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.INTEGER;
    }

}
