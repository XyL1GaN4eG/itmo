package General;

import General.exceptions.UnknownCommandException;

public interface ICommand {
    Object getArgument();

    void setArgument(Object argument);

    String getName();

    boolean checkArgument(Object inputArgument) throws UnknownCommandException;
}
