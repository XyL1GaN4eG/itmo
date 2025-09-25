package MyMoves.hydreigon;

import ru.ifmo.se.pokemon.*;
import program_src.Program;
public class FlashCannon extends SpecialMove {
    public FlashCannon(double pow, double acc) {
        super(Type.STEEL, pow, acc);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        super.applyOppEffects(p);

        if(Program.chance(0.1)) {
            Effect e = new Effect().stat(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
