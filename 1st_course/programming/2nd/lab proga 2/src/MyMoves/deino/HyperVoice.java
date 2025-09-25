package MyMoves.deino;

import ru.ifmo.se.pokemon.*;

public class HyperVoice extends SpecialMove{
    public HyperVoice() {
        super(
                Type.NORMAL, // тип атаки
                90, // энергия
                100);
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }
}
