package resources.familyPack;

import resources.Person;

public class Papa extends Person {
    public Papa() {
        setName("Papa");
    }

    public Papa(String name) {
        setName(name);
    }

    @Override
    public String getStatus() {
        return "в Лондоне";
    }
}