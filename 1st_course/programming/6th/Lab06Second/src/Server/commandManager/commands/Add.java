package Server.commandManager.commands;

import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;
import General.dataClasses.TypesOfArguments;
import General.net.Request;
import Server.commandManager.Command;

public class Add extends Command {
//    public Add(String name, Object[] args) {
//        super(name, args);
//    }


    public Add() {
        super(false);
    }


    @Override
    public String execute() {
        Request request = (Request) this.argument;
        LabWorkSet.labWorks.add((LabWork) request.getArgument());
//        LabWorkSet.myAdd(this.argument);
        return "В коллекцию добавлена новый элемент";
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public TypesOfArguments getType() {
        return TypesOfArguments.LABWORK;
    }
}
