package Client.main.General.net;


import java.io.Serial;
import java.io.Serializable;

//TODO: дописать ответ
public class Response implements Serializable, IMessage {
    @Serial
    private static final long serialVersionUID = 56L;

    private Object data;
    public Response(Object data){
        this.data = data;
    }

    public Serializable getData() {
        return (Serializable) data;
    }

}
