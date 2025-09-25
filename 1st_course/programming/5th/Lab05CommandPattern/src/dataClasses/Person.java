package dataClasses; /**
 * Класс dataClasses.Person представляет собой объект с информацией о человеке.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Person {
    /**
     * Поле name - хранит имя человека. Не может принимать значения null или пустую строку.
     */
    private String name;

    /**
     * Поле birthday - хранит дату рождения человека в виде обьекта Date. Не может принимать значение null.
     */
    private Date birthday;

    /**
     * Поле height - хранит рост человека в виде числа. Значение поля должно быть больше 0.
     */
    private Integer height;

    /**
     * Поле passportID - хранит номер паспорта человека. Может принимать значение null.
     */
    private String passportID; //Поле может быть null
    /**
     * Конструктор класса {@link Person}, который создаёт нового человека из переданных параметров.
     * @param name Имя человека.
     * @param birthday Дата рождения человека в формате "yyyy-MM-dd" or "EEE MMM dd HH:mm:ss zzz yyyy".
     * @param height Рост человека в виде числа. Если значение равно "", то будет использовано значение по умолчанию (0).
     * @param passportID Номер паспорта человека. Может принимать значение null.
     * @throws ParseException Выбрасывается если формат переданной даты не удовлетворяет ни одному из разрешенных форматов.
     */
    public Person(String name, String birthday, String height, String passportID) throws ParseException {

        if (height != "") {

            var sdf = new SimpleDateFormat();
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date personBirthday = null;
            try {
                personBirthday = sdf.parse(birthday);
            } catch (IllegalArgumentException | NullPointerException | ParseException e) {
                var sdf1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                try {
                    personBirthday = sdf1.parse(birthday);
                } catch (ParseException ignore) {
                    System.out.println("ну пиздец");
                }
            }

            this.name = name;
            this.birthday = personBirthday;
            this.height = Integer.parseInt(height);
            this.passportID = passportID;
        }
    }
    /**
     * Конструктор класса {@link Person}, который создаёт нового человека из переданных параметров.
     * @param name Имя человека.
     * @param birthday Дата рождения человека в виде обьекта Date.
     * @param height Рост человека в виде числа.
     * @param passportID Номер паспорта человека. Может принимать значение null.
     */
    public Person(String name, Date birthday, Integer height, String passportID) {
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.passportID = passportID;
    }
    /**
     * Конструктор класса {@link Person}, который создаёт новую пустую сущность.
     */
    public Person() {
    }
    /**
     * Метод возвращает имя человека.
     * @return Возвращает имя человека в виде строки.
     */
    public String getName() {
        if (name == null){
            return "";
        }
        return name;
    }
    /**
     * Метод возвращает дату рождения человека.
     * @return Возвращает дату рождения человека в виде обьекта Date.
     */
    public String getBirthday() {
        var outputDate = "";
        try {
            outputDate = LocalDateTime.parse(String.valueOf(birthday),
                    DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy",
                            Locale.ENGLISH)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (DateTimeException | NullPointerException e) {
            return null;
        }
        return outputDate;
    }

    /**
     * Метод возвращает рост человека.
     * @return Возвращает рост человека в виде строки.
     */
    public String getHeight() {
        return String.valueOf(height);
    }
    /**
     * Метод возвращает номер паспорта человека.
     * @return Возвращает номер паспорта человека в виде строки.
     */
    public String getPassportID() {
        return passportID;
    }
    /**
     * Метод возвращает полную информацию о человеке.
     * @return Возвращает полную информацию о человеке в виде строки. Содержит: name, birthday, height, passportID.
     */
    public String getAll() {
        return name + "," + birthday + "," + height + "," + passportID;
    }

}
