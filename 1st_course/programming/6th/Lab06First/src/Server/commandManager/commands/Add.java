package Server.commandManager.commands;

import General.dataClasses.LabWork;
import Server.commandManager.Command;
import General.dataClasses.LabWorkSet;

public class Add extends Command {
//    public Add(String name, Object[] args) {
//        super(name, args);
//    }


    public Add() {
        super(true);
    }


    @Override
    public String execute() {
        LabWorkSet.labWorks.add((LabWork) this.argument);
//        LabWorkSet.myAdd(this.argument);
        return "В коллекцию добавлена новый элемент";
    }
}
