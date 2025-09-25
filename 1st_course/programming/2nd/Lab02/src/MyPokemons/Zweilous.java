package MyPokemons;

import MyMoves.zweilous.*;
import MyMoves.deino.*;
import ru.ifmo.se.pokemon.*;


public class Zweilous extends Pokemon {
    public Zweilous (java.lang.String name, int level) {
        super(name, level);
        setStats(72, //hp
                85,    //attack
                70,     //def
                65,    // spAtt
                70,     //spDef
                58);   //speed
        setType(Type.DRAGON, Type.DARK);

        setMove(new DragonBreathe(), new HyperVoice(), new DoubleHit());
    }
}
