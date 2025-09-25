package MyMoves;

import ru.ifmo.se.pokemon.*;


public class SampleMove extends StatusMove {
    public SampleMove() {
        super(
//                Type type, // тип атаки
//                double pow, // энергия
//                double acc, // точность
//                int priority,
//                int hits // количество ударов за ход
        );
    }

    @Override
    protected String describe() {
        String[] pieces = this.getClass().toString().split("\\.");
        return "совершает " + pieces[pieces.length-1];
    }

}
