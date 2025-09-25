package General.messages;

import java.io.Serializable;

public class Request implements Serializable {
    private final String commandName;

    public Request(String commandName){
        this.commandName = commandName;
    }
}
