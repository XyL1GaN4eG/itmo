package Client.main;


import Client.main.messages.RequestWithoutVars;
import General.messages.Request;

import java.util.HashMap;
import java.util.Scanner;

import static General.LabWorkUtility.customSplit;

/*
TODO: Написать валидацию.
Шаги для реализации:
1) Принимает в себя мапу с сервера со всеми командами
2) Открывает ввод пользователю и считывает строку по энтеру
3) сплитает по пробелу
4) первый элемент сплитованного массива ищется в мапе, если находится,
то проверяем принимает ли команда в себя элемент коллекции.
 Если да, то начинаем создание нового элемента, если нет,
 то чекаем принимает ли в себя в принципе какие то аргументы или нет.
 И дальше логика по идее не особо сложная должна быть
 */
public class Validation {
    public static Request validate(HashMap<String, Boolean[]> map) {
        var in = new Scanner(System.in);
        while (true) {
            var stringa = customSplit(in.nextLine(), ' ');
            var result = map.get(stringa[0]);
            if (result != null) {
                String command = stringa[0];
                boolean haveArgs = result[1];
                boolean argIsElementOrVar = result[2];


                //TODO: вынести валидацию в отдельный метод
                //Если команда не должна принимать элемент коллекции
                if (!argIsElementOrVar) {
                    //Если команда не должна принимать аргументы
                    if (!haveArgs) {
                        //если все хорошо
                        if (stringa.length == 1) {
                            //то возвращаем обычный реквест
                            return new RequestWithoutVars(command);
                        } else {
                            System.out.println("Некорректное количество аргументов!" +
                                    "\nПопробуйте ввести команду \"help\" без кавычек");
                        }
                        //Если команда принимает аргументы
                    } else {
                        //и нужное количество аргументов
                        if (stringa.length == 2) {
                            try {
                                Integer.parseInt(stringa[2]);
                            } catch (NumberFormatException e) {
                                System.err.println(e);
                            }
                        }
                    }
                } else {

                }
            }
            System.out.println("Команда не найдена!");
        }
    }
}
