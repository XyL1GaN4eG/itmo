package commandManager.commands;

import commandManager.Command;
import dataClasses.LabWorkSet;

public class Info extends Command {

    public Info() {
        super(false);
    }

    @Override
    public void execute() {
        System.out.println("Количество элементов: " + LabWorkSet.labWorks.size());
        System.out.println("Дата создания: " + FileManager.readFromFile("data.txt", false) + "\n");
        System.out.println("Пустая ли коллекция: " + LabWorkSet.labWorks.isEmpty());
    }

    @Override
    public String getName() {
        return null;
    }
}
