package MyMoves.deino;

import program_src.Program;
import ru.ifmo.se.pokemon.*;

public class DragonBreathe extends SpecialMove{
    public DragonBreathe() {
        super(
                Type.DRAGON, // тип атаки
                60, // энергия
                100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applyOppEffects(p);
        if(Program.chance(0.3)) {
            Effect.paralyze(p);
        }
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
