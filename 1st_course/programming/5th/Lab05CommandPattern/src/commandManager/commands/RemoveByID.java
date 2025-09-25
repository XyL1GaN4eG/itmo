package commandManager.commands;

import commandManager.Command;
import dataClasses.LabWorkSet;

public class RemoveByID extends Command {

    public RemoveByID() {
        super(true);
    }

    @Override
    public void execute() {
        LabWorkSet.labWorks.removeIf(element -> String.valueOf(element.getID()).equals(String.valueOf(getArgument())));
        var show = new Show();
        show.execute();


//        LabWorkSet.labWorks.removeIf(x -> x.getID() == Integer.parseInt((String) getArgument()));
//        var show = new Show();
//        show.execute();
    }

    @Override
    public String getName() {
        return "Remove By ID";
    }
}
