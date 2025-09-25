package resources.enums;

public enum BabyBoyHadTo {

    HAD_TO ("придется"),
    STOP_BEING_LOVED (" перестать быть любимым малышом, "),
    BECOME_A_DOCTOR ("стать главврачом, "),
    BECOME_A_BUILDER ("строителем нового корпуса "),
    BE_RESPONSIBLE (" НЕСТИ ОТВЕТСВЕННОСТЬ"),
    GROW_UP (" вырастет из ");

    public final String definition;
    BabyBoyHadTo(String description) {
        this.definition = description;
    }


}
