package commandManager.commands;

import commandManager.Command;
import dataClasses.LabWorkSet;

public class Clear extends Command {

    public Clear() {
        super(false);
    }

    @Override
    public void execute() {
        if (LabWorkSet.labWorks != null) {
            LabWorkSet.labWorks.clear();
        } else {
            System.out.println("Коллекция уже пуста");
        }
        var show = new Show();
        show.execute();
    }

//    @Override
//    public boolean checkArgument(String inputArgument) {
//        if (inputArgument != null) {
//            System.out.println("Эта команда не принимает аргументов");
//            return false;
//        }
//        return true;
//    }

    @Override
    public String getName() {
        return null;
    }
}
