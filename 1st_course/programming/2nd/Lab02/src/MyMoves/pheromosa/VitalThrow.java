package MyMoves.pheromosa;

import ru.ifmo.se.pokemon.*;



public class VitalThrow extends PhysicalMove {
    public VitalThrow() {
        super(Type.FIGHTING,
                70,
                Double.POSITIVE_INFINITY,
                -1,
                1);
    }

    protected boolean checkAccuracy(Pokemon att,
                                    Pokemon def) {
        return true;
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }

}