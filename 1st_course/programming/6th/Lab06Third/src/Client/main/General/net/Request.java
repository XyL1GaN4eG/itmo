package Client.main.General.net;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable, IMessage {
    @Serial
    private static final long serialVersionUID = 465L; //54L

    private String commandName;
    private Object argument;

    public Request(String commandName, Object argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public Request() {
    }

    public Request(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public Object getArgument() {
        return argument;
    }

    public void setArgument(Object argument) {
        this.argument = argument;
    }

    public String getName() {
        return commandName;
    }

    @Override
    public Object[] getData() {
        try {
            Object[] data = new Object[2];
            data[0] = getName();
            data[1] = getArgument();
            return data;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        Object[] data = new Object[1];
        data[0] = getName();
        System.out.println(data[0]);
        return data;
    }
}
