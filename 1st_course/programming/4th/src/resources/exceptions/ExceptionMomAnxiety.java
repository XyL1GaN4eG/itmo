package resources.exceptions;

public class ExceptionMomAnxiety extends Exception {

    public ExceptionMomAnxiety() {
        super();
    }

    @Override
    public String getMessage() {
        return " Да маме плевать на всех, кроме Малыша! ";
    }

}
