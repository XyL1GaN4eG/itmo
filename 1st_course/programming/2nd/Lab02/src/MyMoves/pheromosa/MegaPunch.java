package MyMoves.pheromosa;

import ru.ifmo.se.pokemon.*;

public class MegaPunch extends PhysicalMove {
    public MegaPunch() {
        super(Type.NORMAL,
                80, // power
                85);    // accuracy
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length - 1];
    }
}
