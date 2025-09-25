//package ServerMain.commandManager;
//
//import java.io.File;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.net.URL;
//import java.net.URLClassLoader;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import static ServerMain.rsc.ServerGlobalVars.PATH_TO_COMMANDS;
//import static ServerMain.rsc.ServerGlobalVars.packageCommandsName;
//
//public class PackageScanner {
//    public static void main(String[] args) {
//        HashMap<String, Boolean[]> classes = findClassesInDirectory(packageCommandsName, PATH_TO_COMMANDS);
//
//        for (Map.Entry<String, Boolean[]> entry : classes.entrySet()) {
//            String key = entry.getKey();
//            Boolean[] value = entry.getValue();
//
//            // Делайте что-то с ключом и значением
//            System.out.print("Key: " + key);
//            System.out.println(" Value: " + Arrays.toString(value));
//        }
//        System.out.println("Loaded classes:");
//    }
//
//
//    //TODO: удалить нахуй этот и следующий метод
//    /*
//    принимает в себя название пакета и путь к этому пакету, возвращает информацию о каждом классе команды из пакета
//    Информация:
//    1) Имя команды
//    2) Принимает ли в себя аргументы
//    3) Принимает в себя в качестве аргумента переменную или же элемент лабворка
//     */
//    public static HashMap<String, Boolean[]> findClassesInDirectory(String packageName, String directoryPath) {
//        File directory = new File(directoryPath);
//
//        if (!directory.exists() || !directory.isDirectory()) {
//            return null;
//        }
//        var result = new HashMap<String, Boolean[]>();
//
//        try {
//            // Создаем массив URL, содержащий URL-адрес директории, преобразованный в URL-адрес
//            URL[] urls = {directory.toURI().toURL()};
//
//            // Создаем новый загрузчик классов URLClassLoader с массивом URL-адресов
//            URLClassLoader classLoader = new URLClassLoader(urls);
//
//            // Идем по всем файлам и подкаталогам в указанной директории
//            Files.walk(Paths.get(directoryPath))
//                    // Фильтруем только обычные файлы (исключая директории)
//                    .filter(Files::isRegularFile)
//                    // Обрабатываем каждый файл
//                    .forEach(path -> {
//                        // Преобразуем путь файла в строку
//                        String filePath = path.toString();
//                        // Проверяем, заканчивается ли имя файла на ".class"
//                        if (filePath.endsWith(".class")) {
//                            // Получаем имя класса из пути к файлу и имени пакета
//                            String className = getClassNameFromFilePath(filePath, packageName);
//                            try {
//                                // Загружаем класс с помощью URLClassLoader
//                                Class<?> clazz = Class.forName(className, true, classLoader);
//
//                                // Вызываем метод invokeGetInfoMethod и ожидаем, что он вернет массив объектов
//                                // Первый элемент массива приводим к строке (String), второй к булевому значению (Boolean)
//                                Object[] info = invokeGetInfoMethod(clazz);
//                                String key = (String) info[0];
//                                Boolean[] value = new Boolean[2];
//                                value[0] = (Boolean) info[1];
//                                value[1] = (Boolean) info[2];
//                                // Добавляем полученные значения в результирующую карту
//                                result.put(key, value);
//                            } catch (ClassNotFoundException e) {
//                                // Обработка исключения, если класс не найден
//                                System.err.println("Error loading class: " + className);
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//        } catch (IOException e) {
//            // Обработка исключения ввода-вывода
//            e.printStackTrace();
//        }
//
//// Возвращаем результирующую карту
//        return result;
//
//
//    }
//
//    private static String getClassNameFromFilePath(String filePath, String packageName) {
//        String relativePath = filePath.substring(filePath.indexOf(packageName.replace('.', File.separatorChar)));
//        String className = relativePath
//                .replace(File.separatorChar, '.')
//                .replace(".class", "");
//        return className;
//    }
//
//    private static Object[] invokeGetInfoMethod(Class<?> clazz) {
//        Object[] result = null;
//        try {
//            Method getInfoMethod = clazz.getMethod("getInfo");
//            Object instance = clazz.getDeclaredConstructor().newInstance();
//            result = (Object[]) getInfoMethod.invoke(instance);
////            System.out.println("getInfo result: " + result);
//        } catch (NoSuchMethodException e) {
//            System.out.println("No getInfo method in class: " + clazz.getName());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//}
