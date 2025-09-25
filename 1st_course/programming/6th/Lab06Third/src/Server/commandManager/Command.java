package Server.commandManager;

import General.dataClasses.TypesOfArguments;

import java.io.Serial;
import java.io.Serializable;

public abstract class Command implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private boolean hasArgument;

    protected Object argument;

    public abstract String execute();

    public Object getArgument() {
        return argument;
    }

    //
//    @Override
//    public abstract String getName();
//
    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }

    /**
     * Sets the argument for this command.
     *
     * @param argument the argument to set.
     */
    public void setArgument(Object argument) {
        this.argument = argument;
    }

    protected String output;

    public String getOutput() {
        return output;
    }

    private String name;
    private Object[] args;

    public Command(String name, Object[] args) {
        this.name = name;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public Object[] getInfo() {
        Object[] array = new Object[2];
        array[0] = getName();
        array[1] = getType();
        return array;

//        return String.valueOf(getHasArgument());
    }


    public abstract TypesOfArguments getType();

    public Object[] getArgs() {
        return args;
    }


}
