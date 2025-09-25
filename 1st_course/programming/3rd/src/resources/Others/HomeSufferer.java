package resources.Others;

import resources.Person;

public class HomeSufferer extends Person {

    private Boolean isSick;

    public void setIsSick(boolean isSick) {
        this.isSick = isSick;
    }

    @Override
    public String getStatus() {
        return isSick ? "больна" : "не больна";
    }

    @Override
    public String getName() {
        return name;
    }

    public String talking() {
        return "\"Маме нужен покой.\"";
    }

}