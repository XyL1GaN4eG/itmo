package resources.familyPack;

import resources.IsSick;
import resources.Person;

import java.util.Objects;

public abstract class Siblings extends Person implements IsSick {

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
