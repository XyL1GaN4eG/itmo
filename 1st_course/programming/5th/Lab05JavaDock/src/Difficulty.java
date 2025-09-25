/**
 * Перечисление, представляющее уровни сложности.
 */
public enum Difficulty {
    EASY,
    NORMAL,
    VERY_HARD,
    IMPOSSIBLE,
    INSANE,
    getByIndexOrName;

    /**
     * Метод для получения элемента перечисления по индексу или значению.
     *
     * @param indexOrName Индекс или значение, по которому производится поиск.
     * @return Элемент перечисления, соответствующий переданному индексу или значению.
     * @throws IllegalArgumentException Если переданное значение не соответствует ни индексу, ни имени перечисления.
     */
    public static Difficulty getByIndexOrName(String indexOrName) {
        try {
            int index = Integer.parseInt(indexOrName);
            if (index <= Difficulty.values().length && index >= 0) {
                return Difficulty.values()[index];
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            // Если не удалось распарсить строку в индекс или выход за пределы массива, возвращаем null
            // Можно также обработать это иным способом, например, выбросить исключение или вернуть дефолтное значение
            for (Difficulty day : Difficulty.values()) {
                if (day.name().equalsIgnoreCase(indexOrName)) {
                    return day;
                }
            }
        }
        throw new IllegalArgumentException("Invalid index or name for Difficulty enum");
    }
}
