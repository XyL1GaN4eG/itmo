package General.net;

import java.io.Serializable;

public interface IMessage extends Serializable {
    static final long serialVersionUID = 69L;
    Serializable getData();

}
