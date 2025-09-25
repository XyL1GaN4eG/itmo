package MyMoves.pheromosa;

import ru.ifmo.se.pokemon.*;


public class DoubleTeam extends StatusMove {
    public DoubleTeam() {
        super(Type.NORMAL,
                70,
                Double.POSITIVE_INFINITY,
                -1,
                1);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.EVASION, 1);
        p.addEffect(e); // добавляем эффект e(увеличение евасина) в покемона p
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает  " + pieces[pieces.length-1];
    }

}