package Server.commandManager.commands;

import General.dataClasses.LabWork;
import General.dataClasses.LabWorkSet;
import Server.commandManager.Command;

public class SumOfMinimalPoint extends Command {

    public SumOfMinimalPoint() {
        super(false);
    }

    @Override
    public String execute() {
        float summator = 0;
        for (LabWork element : LabWorkSet.labWorks) {
            summator += element.getMinimalPoint();
        }
        return String.valueOf(summator);
    }

    @Override
    public String getName() {return "sum_of_minimal_point";}
}
