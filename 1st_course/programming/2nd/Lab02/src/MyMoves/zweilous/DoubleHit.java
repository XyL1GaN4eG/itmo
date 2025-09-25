package MyMoves.zweilous;

import ru.ifmo.se.pokemon.*;

public class DoubleHit extends PhysicalMove{
    public DoubleHit() {
        super(
                Type.NORMAL, // тип атаки
                35, // энергия
                90);
    }

    @Override
    protected void applyOppDamage(Pokemon def, double damage) {
        super.applyOppDamage(def, damage);
        super.applyOppDamage(def, damage);
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
