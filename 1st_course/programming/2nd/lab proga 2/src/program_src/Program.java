package program_src;

import MyPokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Program {
    public static void main(String[] args) {
        Battle b = new Battle();

        Deino p1 = new Deino("Banana", 1);
        Hydreigon p2 = new Hydreigon("Coconut", 1);
        Noibat p3 = new Noibat("Blackberry", 1);
        Noivern p4 = new Noivern("Apple", 1);
        Pheromosa p5 = new Pheromosa("Mango", 1);
        Zweilous p6 = new Zweilous("Pomme", 1);

        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }

    public static boolean chance(double d) {
        return d > Math.random(); // если math рандом окажется меньше заданной вероятности то true
    }

}