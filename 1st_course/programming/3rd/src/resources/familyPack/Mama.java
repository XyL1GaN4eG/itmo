package resources.familyPack;

import resources.Person;
public class Mama extends Person {

    @Override
    public String getStatus() {
        return "Дорогая";
    }

    public Mama() {
        setName("Mother");
    }

    public Mama(String name) {
        setName(name);
    }
    public String anx(Boolean statusBabyboy) {
        if (statusBabyboy) {
            return " волноваться";
        } else return " не волноваться.";
    }
}