package MyMoves.pheromosa;

import ru.ifmo.se.pokemon.*;

public class FocusEnergy extends StatusMove {
    public FocusEnergy() {
        super(Type.NORMAL,
                1,
                1);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.SPEED, 1); // увеличение спида
                                // потому что от скорости как раз зависит крит шанс
        p.addEffect(e); // добавляем эффект e(увеличение евасина) в покемона p
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length - 1];
    }

}
