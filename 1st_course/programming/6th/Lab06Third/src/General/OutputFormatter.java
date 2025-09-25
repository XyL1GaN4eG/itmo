package General;

public class OutputFormatter {
//    /**
//     * Выводит отформатированную строку.
//     *
//     * @param values     Массив значений для вывода.
//     * @param maxLengths Список максимальных длин столбцов.
//     */
//    public static void printFormattedRow(String[] values, List<Integer> maxLengths) {
//        for (int i = 0; i < values.length; i++) {
//            try {
//                System.out.print("| " + values[i] + " ".repeat(Math.max(0, maxLengths.get(i) - values[i].length() + 1)));
//            } catch (NullPointerException e) {
//                System.out.print("| " + values[i] + " ".repeat(maxLengths.get(i) - 3));
//            }
//        }
//        System.out.println("|");
//    }
//
//    /**
//     * Выводит заголовок таблицы.
//     *
//     * @param headers    Список заголовков.
//     * @param maxLengths Список максимальных длин столбцов.
//     */
//    public static void printHeader(List<String> headers, List<Integer> maxLengths) {
//        String[] headerArray = headers.toArray(new String[0]);
//        printFormattedRow(headerArray, maxLengths);
//        System.out.println("-".repeat(maxLengths.stream().mapToInt(Integer::intValue).sum() + maxLengths.size() * 3 + 1));
//    }
//
//    /**
//     * Выводит данные таблицы.
//     *
//     * @param labWorks   Список объектов General.dataClasses.LabWork.
//     * @param maxLengths Список максимальных длин столбцов.
//     */
//    public static void printData(List<LabWork> labWorks, List<Integer> maxLengths) {
//        for (LabWork labWork : labWorks) {
//            String[] rowData = labWork.getMassiv();
//            printFormattedRow(rowData, maxLengths);
//        }
//    }
}
