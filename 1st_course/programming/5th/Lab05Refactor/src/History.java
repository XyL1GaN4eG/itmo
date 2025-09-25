import java.util.LinkedList;
import java.util.Queue;

public class History {
    private static Queue<String> queue = new LinkedList<>() {
    };
    private static Integer i = 0;

    public synchronized static void history(String string) {
        if (i <= 5) {
            i++;
        } else {
            //удаление последнего элемента из очереди если там больше пяти элементов
            ((LinkedList<String>) queue).pollLast();
        }
        queue.add(string);
    }

    public static void view() {
        for (String element : queue) {
            System.out.println(element);
        }
//        queue.add("history");
    }
}
