package commandManager;

public abstract class Command implements CommandInterface {
    private final boolean hasArgument;

    protected Object argument;

    @Override
    public abstract void execute();

    //    @Override
    public boolean checkArgument(String inputArgument) {
        if ((hasArgument == true && inputArgument != null) ||
                (hasArgument != true && inputArgument == null)) {
            return true;
        } else if (hasArgument != true && inputArgument != null) {
            System.out.printf(this.getName(), " не может принимать аргументы");
            return false;
        } else if (hasArgument == true && inputArgument == null) {
            System.out.printf(this.getName(), " должен принять какой-то аргумент");
        }
        return false;
    }

    //    public abstract boolean checkArgument(String argument);
    public Object getArgument() {
        return argument;
    }

    @Override
    public abstract String getName();

    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }

    /**
     Sets the argument for this command.
     @param argument the argument to set.
     */
    public void setArgument(String argument) {
        this.argument = argument;
    }

}
