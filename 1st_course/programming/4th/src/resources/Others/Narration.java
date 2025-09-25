package resources.Others;

//import resources.items.Thing;

public class Narration {
    Karlson karlson = new Karlson();

    public void speech1() {
//Локальный класс со статическим методом, чтоб не создавать объект ибо он не нужен
//и сразу вызвать метод, а локальный потому, что объявляется в методе
//делается через имякКласса.метод() конец 19 строчки
         class Thing {
            public static String thing() {
                return "штуку";
            }
         }

        System.out.print("Дело в том, что накануне вечером " + karlson.getName()
                + " сделал одну очень замысловатую " + Thing.thing() + ": ");
    }
}