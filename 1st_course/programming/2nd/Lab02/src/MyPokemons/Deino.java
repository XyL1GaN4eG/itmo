package MyPokemons;

import ru.ifmo.se.pokemon.*;
import MyMoves.deino.*;

public class Deino extends Pokemon {
    public Deino (java.lang.String name, int level) {
        super(name, level);
        setStats(52, //hp
                65,    //attack
                50,     //def
                45,    // spAtt
                50,     //spDef
                38);   //speed
        setType(Type.DRAGON, Type.DARK);

        setMove(new DragonBreathe(), new HyperVoice());
    }
}
