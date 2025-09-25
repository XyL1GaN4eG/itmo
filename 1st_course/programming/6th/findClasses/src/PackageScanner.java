import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PackageScanner {

    public static String findClasses(String className) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

//        Set<Class<?>> classes = new HashSet<>();

        ClassLoader classLoader = Command.class.getClassLoader();

        var method = classLoader.loadClass("className").getMethod("getName");

        var command = Class.forName("className");

        var commandInstance = command.newInstance();

        String result = (String) method.invoke(commandInstance);

        return result;

    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println(findClasses());
    }
}
