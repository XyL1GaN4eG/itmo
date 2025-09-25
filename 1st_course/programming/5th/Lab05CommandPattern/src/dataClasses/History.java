package dataClasses;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс dataClasses.History представляет собой историю действий.
 * История хранит до 5 последних действий.
 */
public class History {
    /**
     * Очередь, хранящая строковые представления действий.
     */
    private static Queue<String> queue = new LinkedList<>() {};

    /**
     * Счетчик количества добавленных действий в очередь.
     */
    private static Integer i = 0;

    /**
     * Синхронизированный метод для добавления действия в историю.
     * Если количество действий превышает 5, удаляется последнее действие из очереди.
     *
     * @param string строковое представление действия для добавления в историю.
     */
    public synchronized static void history(String string) {
        if (i <= 5) {
            i++;
        } else {
            //удаление последнего элемента из очереди если там больше пяти элементов
            ((LinkedList<String>) queue).pollLast();
        }
        queue.add(string);
    }

    /**
     * Выводит на экран все действия из истории.
     */
    public static void view() {
        for (String element : queue) {
            System.out.println(element);
        }
//        queue.add("history");
    }
}
