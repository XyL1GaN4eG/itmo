package resources.exceptions;

public class ExceptionFamily extends Exception{
    public ExceptionFamily() {
        super();
    }

    @Override
    public String getMessage() {
        return "мистер бист не семья";
    }
}
