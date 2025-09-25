package Client.main.General.dataClasses;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс General.dataClasses.History представляет собой историю действий.
 * История хранит до 5 последних действий.
 */
public class History implements Serializable {

    @Serial
    private static final long serialVersionUID = 187L;
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
            ((LinkedList<String>) queue).pollLast();
        }
        queue.add(string);
    }

    /**
     * Выводит на экран все действия из истории.
     */
    public static String view() {
        var returner = new String();
        for (String element : queue) {
            returner += element + "\n";
        }
        return returner;
    }
}
