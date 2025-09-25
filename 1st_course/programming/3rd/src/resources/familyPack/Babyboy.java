package resources.familyPack;

import resources.Knowledge;
import resources.Person;

public class Babyboy extends Person {

    @Override
    public String talkingWith() {
        return "Говорит";
    }

    @Override
    public String getStatus() {
        return "изолирован. ";
    }

    public Babyboy() {
        setName("бэбибой");
    }

    public Babyboy(String name) {
        setName(name);
    }

    public String me() {
        return "Я ";
    }

    public String IsItHurt() {
        return "Это не больно. ";
    }

    public String Prediction() {
        return "конечно заболею этой тиной.";
    }

    public String knowledgeOfLivingDad(Knowledge knowledge) {
        String result = null;
        switch (knowledge) {
            case DKnow -> {
                result = "Мне не известно жив ли он. ";
            }
            case Know -> {
                result = "Мне известно жив ли он ";
            }
            default -> {
                System.out.println("asd");
            }
        }
        return result;
    }

    private Boolean IsKnowingOfDadHealth() {
        return false;
    }

    public String knowledgeOfHEALTHDad() {
        if (IsKnowingOfDadHealth() == false) {
            return "Не слышно что он заболел. ";
        } else {
            return "Слышал о его состоянии здоровья";
        }
    }


    public String predictionOfDadsHealth() {
        return "Наверное болен";
    }

    public String predictionOfDadsHealthBAD() {
        return ", раз все наши больны.";
    }

    public String predictionOfDadsHealthGOOD() {
        return ", но я не знаю почему.";
    }

    public String missMom() {
        return "очень скучаю по тебе, как ты себя чувствуешь, ты больна или не очень?";
    }

    public void startingLetter() {
        System.out.print(",-- начал он.--");
    }

    public String admission() {
        return "могу только с ";
    }

    public String trying() {
        return "стараюсь ";
    }

    public Boolean isTalkingALot() { //переменная для проверки маминого anx (Я стараюсь Говорить поменьше, потому что иначе ты будешь волноваться)
        return true;
    }

    public void predictionKarlSufferer() {
        System.out.println(", но и они скоро заболеют.");
    }

    public void goodByeing() {
        System.out.println("Прощай мамочка, будь здорова.");
    }

    public String goTo(String window, String karlson) {
        String s;
        s = " подошел к " + window + " и позвонил " + karlson + "у.";
        return s;
    }
}


