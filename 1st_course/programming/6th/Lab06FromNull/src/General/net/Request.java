package General.net;

import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable, IMessage {
    @Serial
    private static final long serialVersionUID = 54L;

    private String commandName;

    public String getName() {
        return commandName;
    }

    @Override
    public Serializable getData() {
        return commandName;
    }
    private Object argument;

    public Request(String commandName, Object argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public Request() {
    }

    public Request(String commandName){this.commandName = commandName;}
}
