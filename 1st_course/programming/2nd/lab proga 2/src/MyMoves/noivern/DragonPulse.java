package MyMoves.noivern;

import ru.ifmo.se.pokemon.*;

public class DragonPulse extends SpecialMove{
    public DragonPulse() {
        super(
                Type.DRAGON, // тип атаки
                85, // энергия
                100);
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
