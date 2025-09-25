package Client.main.commandManager;

import General.ICommand;
import General.exceptions.UnknownCommandException;

import java.io.Serial;
import java.io.Serializable;

public class Command implements Serializable, ICommand {

    @Serial
    private static final long serialVersionUID = 1L;

    private final boolean hasArgument;

    protected String name = "";


    protected Object argument = null;

    public Object getArgument() {
        return argument;
    }

    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean checkArgument(Object inputArgument) throws UnknownCommandException {
        return false;
    }



    public void execute() {
        System.out.println("Команда исполняется");
    }


    public boolean checkArgument(String inputArgument) throws UnknownCommandException {
        if ((hasArgument == true && inputArgument.trim() != "") ||
                (hasArgument != true && inputArgument.trim() == "")) {
//            System.out.println("Аргументы верные");
            return true;
        } else if (hasArgument != true && inputArgument.trim() != "") {
            System.out.printf(this.getName(), " не может принимать аргументы");
            throw new UnknownCommandException("");
        } else if (hasArgument == true && inputArgument.trim() == "") {
            System.out.printf(this.getName(), " должен принять какой-то аргумент");
            throw new UnknownCommandException("");
        }
        throw new UnknownCommandException("");
    }
}
