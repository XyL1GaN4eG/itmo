package resources.Others;

import resources.items.Thing;

public class Narration {
    Karlson karlson = new Karlson();
    Thing thing = new Thing();

    public void speech1() {
        System.out.print("Дело в том, что накануне вечером " + karlson.getName()
                + " сделал одну очень замысловатую " + thing.thing() + ": ");
    }
}