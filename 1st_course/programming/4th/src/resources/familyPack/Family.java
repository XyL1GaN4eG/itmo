package resources.familyPack;

import resources.Person;
import resources.exceptions.ExceptionFamily;

import java.util.ArrayList;
import java.util.List;

public class Family {
    // Список членов семьи
    public List<Person> familyMembers = new ArrayList<>();

    // Конструктор, принимающий представителей каждого члена семьи и добавляющий их в список
    public Family(Mama mama, Papa papa, Siblings.Bosse bosse, Siblings.Betan betan, Babyboy babyboy) {   //тут прост обращаемся к бетану и боссе через их внешний класс сиблинг
        familyMembers.add(mama);
        familyMembers.add(papa);
        familyMembers.add(bosse);
        familyMembers.add(betan);
        familyMembers.add(babyboy);
    }

    // Метод возвращает статус семьи
    public String getStatus() {
        return "пришел конец.";
    }

    // Метод возвращает имя семьи
    public String getName() {
        return "Семье";
    }

    public Person getByName(String name) throws ExceptionFamily {
        for (Person person : familyMembers) {
            // Проверяем, совпадает ли имя текущего члена с искомым именем
            if (person.getName().equals(name)) {
                // Если совпадение найдено, возвращаем найденного члена семьи
                return person;
            }
        }
        // Если цикл завершился, и совпадение не было найдено, можно вернуть null (если уверены в отсутствии null)
        throw new ExceptionFamily(); //описал в мейне
    }
}
