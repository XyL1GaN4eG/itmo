package resources.Others;

import resources.Person;

public class Karlson extends Person {
    @Override
    public String getName() {
        return "Карлсон";
    }

    @Override
    public String getStatus() {
        return " не болен";
    }

    public String made() {
        return " провел ";
    }

    public void putCall(String name0, String name1) {
        String s;
        s = getName() + made() + name0 + " и " + name1;
        System.out.print(s);
    }

}
