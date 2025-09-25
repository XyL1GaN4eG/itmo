package commandManager.commands;

import commandManager.Command;
import commandManager.CommandMode;

//import static commandManager.commands.LabWorkMethods.show;

public class AddForNonUserMode extends Command {
    CommandMode handler;

    public AddForNonUserMode() {
        super(false);
//        this.handler = handler;
    }

    @Override
    public void execute() {
        try {
            LabWorkUtility.addElementToSet(LabWorkUtility.customSplit(String.valueOf(this.getArgument()), ','));
        } catch (Exception e) {
            System.out.println("Что то пошло не так......");
        }
    }

//    @Override
//    public boolean checkArgument(Object inputArgument) {
//        if (inputArgument == null)
//            return true;
//        else {
//            System.out.println("AddForCLI has no arguments!");
//            return false;
//        }
//    }

    @Override
    public String getName() {
        return "add";
    }

//    @Override
//    public boolean checkArgument(String inputArgument) {
//        if (inputArgument == null || inputArgument.trim().isEmpty())
//            return true;
//        else {
//            System.out.println("Show has no arguments!");
//            return false;
//        }
//    }
}