package resources;

import java.util.Objects;


public abstract class Person implements Speech {

    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getStatus();

    @Override
    public String talkingWith() {
        return "говорит";
    }

    @Override
    public String toString() { // переопределнный метод toString для тз
        return getName();
    }

    public boolean equals(Object obj) {
        // Проверка на сравнение с самим собой
        if (this == obj) {
            return true;
        }

        // Проверка на null или различные классы
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        // Приведение объекта к типу Person для сравнения полей
        Person other = (Person) obj;

        // Сравнение полей name двух объектов
        return Objects.equals(name, other.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}