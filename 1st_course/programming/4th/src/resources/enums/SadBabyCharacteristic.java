package resources.enums;

public enum SadBabyCharacteristic {

    MELANCHOLY ("Меланхоличн"),
    UNPLEASANT_PERSON ("неприятная личность"),
    DEVOTED ("преданный"),
    ATTENTIVE ("внимательный"),
    LOVIING ("любящий"),
    THE_CUTEST_IN_WHOLE_WORLD ("самый милый малыш на свете!"),
    LOVED ("любимым");

    public final String definition;
    SadBabyCharacteristic(String description) {
        this.definition = description;
    }

}
