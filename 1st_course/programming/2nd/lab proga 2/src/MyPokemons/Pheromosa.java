package MyPokemons;

import ru.ifmo.se.pokemon.*;
import MyMoves.pheromosa.*;

public class Pheromosa extends Pokemon {
    public Pheromosa (java.lang.String name, int level) {
        super(name, level);
        setStats(71, //hp
                137,    //attack
                37,     //def
                137,    // spAtt
                37,     //spDef
                151);   //speed
        setType(Type.BUG, Type.FIGHTING);

        setMove(new MegaPunch(), new VitalThrow(), new DoubleTeam(), new FocusEnergy());
    }
}
