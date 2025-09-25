package MyPokemons;

import MyMoves.noibat.*;
import ru.ifmo.se.pokemon.*;
import MyMoves.pheromosa.*;

public class Noibat extends Pokemon {
    public Noibat (java.lang.String name, int level) {
        super(name, level);
        setStats(40, //hp
                30,    //attack
                35,     //def
                45,    // spAtt
                40,     //spDef
                55);   //speed
        setType(Type.DRAGON, Type.FLYING);

        setMove(new AirCutter(), new DoubleTeam(), new Psychic());
    }
}
