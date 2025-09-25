package Server.commandManager.commands;

import General.dataClasses.TypesOfArguments;
import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;
import Server.commandManager.FileManager;

public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public String execute() {
        output = ("Количество элементов: " + LabWorkSet.labWorks.size() + "\n");
        output +=("Дата создания: " + FileManager.readFromFile("data.txt", false) + "\n");
        output +=("Пустая ли коллекция: " + LabWorkSet.labWorks.isEmpty());
        return output;
    }

    @Override
    public String getName() {
        return "info";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.NULL;
    }

}
