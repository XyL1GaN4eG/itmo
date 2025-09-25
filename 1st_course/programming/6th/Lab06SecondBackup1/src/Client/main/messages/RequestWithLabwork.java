package Client.main.messages;

import General.dataClasses.LabWork;

import java.io.Serial;
import java.io.Serializable;

public class RequestWithLabwork implements Serializable {
    @Serial
    private static final long serialVersionUID = 777L;

    private String commandName;

    private LabWork labWork;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public LabWork getLabWork() {
        return labWork;
    }

    public void setLabWork(LabWork labWork) {
        this.labWork = labWork;
    }
}
