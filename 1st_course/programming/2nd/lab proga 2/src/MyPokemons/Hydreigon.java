package MyPokemons;

import MyMoves.zweilous.*;
import MyMoves.deino.*;
import ru.ifmo.se.pokemon.*;


public class Hydreigon extends Pokemon {
    public Hydreigon (java.lang.String name, int level) {
        super(name, level);
        setStats(92, //hp
                105,    //attack
                90,     //def
                125,    // spAtt
                90,     //spDef
                98);   //speed
        setType(Type.DRAGON, Type.DARK);

        setMove(new DragonBreathe(), new HyperVoice(), new DoubleHit());
    }
}
