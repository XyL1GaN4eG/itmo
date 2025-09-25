package General.dataClasses;

import General.LabWorkUtility;

import java.io.Serial;
import java.io.Serializable;
import java.text.ParseException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Random;

/**
 * Класс General.dataClasses.LabWork представляет собой лабораторную работу.
 * Каждая лабораторная работа имеет уникальный идентификатор, наименование, координаты,
 * дату создания, минимальное количество баллов, уровень сложности и информацию об авторе.
 */
public class LabWork implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Уникальный идентификатор лабораторной работы.
     * Значение поля должно быть больше 0.
     * Значение этого поля должно быть уникальным.
     * Значение этого поля должно генерироваться автоматически.
     */
    private int id;

    /**
     * Наименование лабораторной работы.
     * Поле не может быть null.
     * Строка не может быть пустой.
     */
    private String name;

    /**
     * Координаты лабораторной работы.
     * Поле не может быть null.
     */
    private Coordinates coordinates;

    /**
     * Дата и время создания лабораторной работы.
     * Поле не может быть null.
     * Значение этого поля должно генерироваться автоматически.
     */
    private ZonedDateTime creationDate;

    /**
     * Минимальное количество баллов за выполнение лабораторной работы.
     * Поле не может быть null.
     * Значение поля должно быть больше 0.
     */
    private Float minimalPoint;

    /**
     * Уровень сложности лабораторной работы.
     * Поле не может быть null.
     */
    private Difficulty difficulty;

    /**
     * Автор лабораторной работы.
     * Поле может быть null.
     */
    private Person author;

    /**
     * Конструктор по умолчанию.
     */
    public LabWork() {
    }

    /**
     * Устанавливает наименование лабораторной работы.
     *
     * @param name наименование лабораторной работы.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Устанавливает координаты лабораторной работы.
     *
     * @param coordinates координаты лабораторной работы.
     */
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Устанавливает минимальное количество баллов за выполнение лабораторной работы.
     *
     * @param minimalPoint минимальное количество баллов за выполнение лабораторной работы.
     */
    public void setMinimalPoint(Float minimalPoint) {
        this.minimalPoint = minimalPoint;
    }

    /**
     * Устанавливает уровень сложности лабораторной работы.
     *
     * @param difficulty уровень сложности лабораторной работы.
     */
    private void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Устанавливает автора лабораторной работы.
     *
     * @param author автор лабораторной работы.
     */
    private void setAuthor(Person author) {
        this.author = author;
    }

    /**
     * Создает и возвращает новый экземпляр LabWorkBuilder для построения лабораторной работы.
     *
     * @return новый экземпляр LabWorkBuilder.
     */
    public static LabWorkBuilder builder() {
        return new LabWorkBuilder();
    }

    /**
     * Вложенный класс LabWorkBuilder используется для построения экземпляров General.dataClasses.LabWork.
     */
    public static class LabWorkBuilder {

        /**
         * Экземпляр лабораторной работы.
         */
        private LabWork instance = new LabWork();

        /**
         * Генерирует случайный идентификатор для лабораторной работы.
         *
         * @return текущий экземпляр LabWorkBuilder.
         */
        public LabWorkBuilder randomID() {
            int id = 0;
            do {
                id = Math.abs((new Random()).nextInt());
            } while (id == 0);
            instance.setId(id);
            return this;
        }

        /**
         * Устанавливает идентификатор для лабораторной работы.
         *
         * @param id идентификатор для лабораторной работы.
         * @return текущий экземпляр LabWorkBuilder.
         */
        public LabWorkBuilder randomID(String id) {
            int intID = 0;
            try {
                intID = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ID");
            }
            instance.setId(intID);
            return this;
        }

        /**
         * Строит и возвращает экземпляр лабораторной работы.
         *
         * @return экземпляр лабораторной работы.
         */
        public LabWork build() {
            return instance;
        }


        public LabWorkBuilder name(String name) {
            instance.setName(name);
            return this;
        }

        public LabWorkBuilder coordinates(String x, String y) {
            instance.setCoordinates(new Coordinates(Long.parseLong(x), Double.parseDouble(y)));
            return this;
        }

        public LabWorkBuilder minimalPoint(float minimalPoint) {
            instance.setMinimalPoint(minimalPoint);
            return this;
        }

        public LabWorkBuilder difficulty(String string) {
            instance.setDifficulty(Difficulty.getByIndexOrName(string));
            return this;
        }

        public LabWorkBuilder creationDate(ZonedDateTime zonedDateTime) {
            instance.setCreationDate(zonedDateTime);
            return this;
        }

        public LabWorkBuilder author(Person author) {
            instance.setAuthor(author);
            return this;
        }
    }


    /**
     * Возвращает массив значений полей лабораторной работы.
     *
     * @return массив значений полей лабораторной работы.
     */
    public String[] getMassiv() {
        return new String[]{
                String.valueOf(getID()),
                getName(),
                String.valueOf(coordinates.getX()),
                String.valueOf(coordinates.getY()),
                String.valueOf(getCreationDate()),
                String.valueOf(getMinimalPoint()),
                String.valueOf(getDifficulty()),
                author != null ? author.getName() : "null",
                author != null ? String.valueOf(author.getBirthday()) : "null",
                author != null ? String.valueOf(author.getHeight()) : "null",
                author != null ? author.getPassportID() : "null"
        };
    }


    /**
     * Возвращает идентификатор лабораторной работы.
     *
     * @return идентификатор лабораторной работы.
     */
    public int getID() {
        return id;
    }

    /**
     * Возвращает наименование лабораторной работы.
     *
     * @return наименование лабораторной работы.
     */
    public String getName() {
        return name;
    }


    /**
     * Устанавливает дату и время создания лабораторной работы.
     *
     * @param creationDate дата и время создания лабораторной работы.
     */
    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Устанавливает идентификатор лабораторной работы.
     *
     * @param id идентификатор лабораторной работы.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Создает новый экземпляр лабораторной работы с заданными параметрами.
     *
     * @param id           уникальный идентификатор лабораторной работы.
     * @param name         наименование лабораторной работы.
     * @param coordinates  координаты лабораторной работы.
     * @param creationDate дата и время создания лабораторной работы.
     * @param minimalPoint минимальное количество баллов за выполнение лабораторной работы.
     * @param difficulty   уровень сложности лабораторной работы.
     * @param personName   имя автора лабораторной работы.
     * @param birthday     дата рождения автора лабораторной работы.
     * @param height       рост автора лабораторной работы.
     * @param passportID   идентификационный номер паспорта автора лабораторной работы.
     */
    public LabWork(
            int id,
            String name,
            Coordinates coordinates,
            ZonedDateTime creationDate,
            Float minimalPoint,
            Difficulty difficulty,
            String personName,
            Date birthday,
            String height,
            String passportID
    ) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.minimalPoint = minimalPoint;
        this.difficulty = difficulty;

        if (personName != null) {
            try {
                this.author = new Person(personName, String.valueOf(birthday), height, passportID);
            } catch (ParseException e) {
                System.out.println("Дата в неизвестном формате");
            }
        }
    }

    public LabWork(String[] parts) {
        this.id = Integer.parseInt(parts[0]);
        this.name = parts[1];
        this.coordinates = new Coordinates(Long.parseLong(parts[2]), Double.parseDouble(parts[3]));
        this.creationDate = ZonedDateTime.parse(parts[4]);
        this.minimalPoint = Float.valueOf(parts[5]);
        this.difficulty = Difficulty.valueOf(parts[6]);
        if (parts.length > 7) {
            try {
                this.author = new Person(parts[7], String.valueOf(parts[8]), parts[9], parts[10]);
            } catch (ParseException e) {
                System.out.println("Дата в неизвестном формате");
            }
        }
    }


    /**
     * Возвращает строковое представление массива значений полей лабораторной работы.
     * Если информация об авторе отсутствует, она не добавляется в строку.
     *
     * @return строковое представление массива значений полей лабораторной работы.
     */
    public String getString() {
        var s = getID() + "," +
                getName() + "," +
                getCoordinates().getX() + "," +
                getCoordinates().getY() + "," +
                getCreationDate() + "," +
                getMinimalPoint() + "," +
                getDifficulty();

        try {
            if (!author.getName().isEmpty()) {
                s += "," + author.getAll();
                return s;
            }
        } catch (NullPointerException e) {
            return s;
        }

        return s;
    }

    /**
     * Возвращает массив строковых значений полей лабораторной работы.
     * Если информация об авторе отсутствует, она не добавляется в массив.
     *
     * @param blanc флаг, указывающий на необходимость добавления пустых строк в массив.
     * @return массив строковых значений полей лабораторной работы.
     */
    public String[] getString(Boolean blanc) {
        var s = getID() + "," +
                getName() + "," +
                getCoordinates() + "," +
                getCreationDate() + "," +
                getMinimalPoint() + "," +
                getDifficulty();
        try {
            if (!author.getName().isEmpty()) {
                s += "," + author.getAll();
            }
        } catch (NullPointerException ignore) {
        }

        return LabWorkUtility.customSplit(s, ',');
    }

    /**
     * Возвращает массив длин строковых представлений значений полей лабораторной работы.
     *
     * @return массив длин строковых представлений значений полей лабораторной работы.
     */
    public int[] getLen() {
        int[] lengths = new int[11]; // 11 fields in total

        lengths[0] = String.valueOf(getID()).length();
        lengths[1] = String.valueOf(getName()).length();
        lengths[2] = String.valueOf(coordinates.getX()).length();
        lengths[3] = String.valueOf(coordinates.getY()).length();
        lengths[4] = String.valueOf(getCreationDate()).length();
        lengths[5] = String.valueOf(getMinimalPoint()).length();
        lengths[6] = String.valueOf(getDifficulty()).length();
        if (this.author != null) {
            lengths[7] = (author.getName() != null) ? author.getName().length() : 0;
            lengths[8] = (author.getBirthday() != null) ? author.getBirthday().length() : 0;
            lengths[9] = (author.getHeight() != null) ? author.getHeight().length() : 0;
            lengths[10] = (author.getPassportID() != null) ? author.getPassportID().length() : 0;
        }
        return lengths;
    }


    /**
     * Возвращает координаты лабораторной работы.
     *
     * @return координаты лабораторной работы.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * Возвращает дату и время создания лабораторной работы.
     *
     * @return дата и время создания лабораторной работы.
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * Возвращает минимальное количество баллов за выполнение лабораторной работы.
     *
     * @return минимальное количество баллов за выполнение лабораторной работы.
     */
    public Float getMinimalPoint() {
        return minimalPoint;
    }

    /**
     * Возвращает уровень сложности лабораторной работы.
     *
     * @return уровень сложности лабораторной работы.
     */
    public Difficulty getDifficulty() {
        return difficulty;
    }

    /**
     * Возвращает автора лабораторной работы.
     *
     * @return автор лабораторной работы.
     */
    public Person getAuthor() {
        return author;
    }
}