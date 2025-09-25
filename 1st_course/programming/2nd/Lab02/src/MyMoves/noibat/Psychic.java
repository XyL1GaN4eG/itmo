package MyMoves.noibat;

import ru.ifmo.se.pokemon.*;
import program_src.Program;
public class Psychic extends SpecialMove {
    public Psychic() {
        super(
                Type.PSYCHIC, // тип атаки
                90, // энергия
                100 // точность
        );
    }

    protected void applySelfEffects(Pokemon p) {
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
