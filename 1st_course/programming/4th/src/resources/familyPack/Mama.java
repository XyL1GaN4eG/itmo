package resources.familyPack;

import resources.Person;
import resources.exceptions.ExceptionMomAnxiety;

public class Mama extends Person {

    private EmotionalState state;

    public Mama() {
        setName("Mother");
    }

    public Mama(String name) {
        setName(name);
    }

    @Override
    public String getStatus() {
        return "Дорогая";
    }

    public void setState(Person person) throws ExceptionMomAnxiety {
        this.state = new EmotionalState(person);
    }

    public void anx(Person person) throws ExceptionMomAnxiety {
        state.anx(person);
    }

    public String getState(){
        return state.toString();
    }

    // Вложенный класс для представления эмоционального состояния
    public class EmotionalState {
        public EmotionalState(Person person) throws ExceptionMomAnxiety {
            this.anx(person);
        }
        String state;
        public void anx(Person person) throws ExceptionMomAnxiety {
            if (person instanceof Babyboy) {
                if (((Babyboy) person).isTalkingALot()) {
                    state = " волноваться";
                } else {
                    state = "не волноваться";
                }
            } else {
                throw new ExceptionMomAnxiety();
            }
        };

        @Override
        public String toString() {
            return state;
        }
    }


}
