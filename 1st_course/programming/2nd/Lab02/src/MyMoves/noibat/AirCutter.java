package MyMoves.noibat;

import ru.ifmo.se.pokemon.*;

public class AirCutter extends SpecialMove{
    public AirCutter() {
        super(
            Type.FLYING, // тип атаки
            60, // энергия
            95);
    }

    @Override
    protected double calcCriticalHit(Pokemon att, Pokemon def) {
        return 1d / 8d; // используем дабл потому что иначе целочисл деление
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
