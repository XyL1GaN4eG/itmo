package resources.familyPack;

import resources.Person;

import java.util.ArrayList;
import java.util.List;

public class Family {
    // Список членов семьи
    public List<Person> FamilyMembers = new ArrayList<>();

    // Конструктор, принимающий представителей каждого члена семьи и добавляющий их в список
    public Family(Mama mama, Papa papa, Bosse bosse, Betan betan, Babyboy babyboy) {
        FamilyMembers.add(mama);
        FamilyMembers.add(papa);
        FamilyMembers.add(bosse);
        FamilyMembers.add(betan);
        FamilyMembers.add(babyboy);
    }

    // Метод возвращает статус семьи
    public String getStatus() {
        return "пришел конец.";
    }

    // Метод возвращает имя семьи
    public String getName() {
        return "Семье";
    }

    public Person getByName(String name) {
        for (Person person : FamilyMembers) {
            // Проверяем, совпадает ли имя текущего члена с искомым именем
            if (person.getName().equals(name)) {
                // Если совпадение найдено, возвращаем найденного члена семьи
                return person;
            }
        }
        // Если цикл завершился, и совпадение не было найдено, можно вернуть null (если уверены в отсутствии null)
        return null;
    }
}
