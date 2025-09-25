package resources.familyPack;

import resources.*;
import resources.enums.BabyBoyHadTo;
import resources.enums.Knowledge;
import resources.enums.SadBabyCharacteristic;

public class Babyboy extends Person  {

    @Override
    public String talkingWith() {
        return "Говорит";
    }

    @Override
    public String getStatus() {
        return "изолирован. ";
    }

    public Babyboy(String name) {
        setName(name);
    }

    public String pronoun() {
        return "Я ";
    }

    public String isItHurt() {
        return "Это не больно. ";
    }

    public String prediction() {
        return "конечно заболею этой тиной.";
    }

    public String knowledgeOfLivingDad(Knowledge knowledge) {
        String result = null;
        switch (knowledge) {
            case D_KNOW -> {
                result = "Мне не известно жив ли он. ";
            }
            case KNOW -> {
                result = "Мне известно жив ли он ";
            }
            default -> {
                System.out.println("asd");
            }
        }
        return result;
    }

    private Boolean isKnowingOfDadHealth() {
        return false;
    }

    public String knowledgeOfHEALTHDad() {
        if (isKnowingOfDadHealth() == false) {
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
        return " подошел к " + window + " и позвонил " + karlson + "у.";
    }

    //вложенный нестатический класс. поч не статический? потому что по смыслу должен быть связан с экземпляром внешнего для него класса
    //типо какой меланхоличный малыш без обычного малыша? объект такого класса также создается через объект внешнего класса
    public class MelancholyBabyBoy implements Melancholy{

        @Override
        public String getCharacteristics(SadBabyCharacteristic characteristic) {
            return characteristic.definition;
        }

        @Override
        public String setAction(BabyBoyHadTo action) {
            return action.definition;
        }
    }

}


