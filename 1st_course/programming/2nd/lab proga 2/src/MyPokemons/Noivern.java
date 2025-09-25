package MyPokemons;

import MyMoves.noibat.*;
import ru.ifmo.se.pokemon.*;
import MyMoves.pheromosa.*;

public class Noivern extends Pokemon {
    public Noivern (java.lang.String name, int level) {
        super(name, level);
        setStats(85, //hp
                70,    //attack
                80,     //def
                97,    // spAtt
                80,     //spDef
                123);   //speed
        setType(Type.DRAGON, Type.FLYING);

        setMove(new AirCutter(), new DoubleTeam());
    }
}
