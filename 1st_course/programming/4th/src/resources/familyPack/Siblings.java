package resources.familyPack;

import resources.IsSick;
import resources.Person;

import java.util.Objects;

public class Siblings extends Person implements IsSick {

    @Override
    public String getSicknessName() {
        return "какой-то тиной";
    }

    private String healthStatus = "больна ";

    public String healthStatus() {
        return healthStatus;
    }

    @Override
    public String getStatus() {
        return "Их увезли в больницу. ";
    }

    // экстендит сиблингов потому, что массив FamilyMember должен хранить в себе объекты типа
    // Person а этот класс Бетан хоть и находится внутри класса, экстендящего персон,
    // сам к персон отношения не имеет.
    public static class Betan extends Siblings {
        public Betan(String name) {
            setName(name);
        }
    }

    public static class Bosse extends Siblings {
        public Bosse(String name) {
            setName(name);
        }
    }

    @Override
    public boolean equals(Object obj) {
        // Проверяем, является ли объект экземпляром Siblings
        if (this == obj) {
            return true; // Оптимизация: объекты ссылаются на один и тот же экземпляр
        }

        Siblings other = (Siblings) obj;

        // Сравниваем healthStatus
        return healthStatus().equals(other.healthStatus());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }


}
