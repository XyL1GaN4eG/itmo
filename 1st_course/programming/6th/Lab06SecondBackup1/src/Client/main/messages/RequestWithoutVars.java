package Client.main.messages;

import General.messages.Request;

import java.io.Serial;

public class RequestWithoutVars extends Request {
    @Serial
    private static final long SerialVersionUID = 123L;

    public RequestWithoutVars(String commandName) {
        super(commandName);
    }
}
