package resources.familyPack;


import resources.enums.BabyBoyHadTo;
import resources.enums.SadBabyCharacteristic;

public interface Melancholy {

    String setAction(BabyBoyHadTo action);
    default String getCharacteristics(SadBabyCharacteristic characteristic) {
        return characteristic.definition;
    }
    default String getName() {
        return "Малыш";
    }
    default String pronoun() {
        return "Он";
    }

}
